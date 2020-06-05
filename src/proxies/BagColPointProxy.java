package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.BagColPoint;

public class BagColPointProxy extends Thread  implements SharedRegionProxy {

    private final BagColPoint bagColPoint;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public BagColPointProxy(ServerCom sconi, BagColPoint bagColPoint) {
        super ("Proxy_" + BagColPointProxy.getProxyId ());

        this.sconi = sconi;
        this.bagColPoint = bagColPoint;
        this.simFinished = false;
    }


    @Override
    public void run ()
    {
        Message inMessage = null,
                outMessage = null;

        inMessage = (Message) sconi.readObject ();

        try {
            outMessage = processAndReply (inMessage);
        }
        catch (Exception e) {
            System.out.println ("Thread " + getName () + ": " + e.getMessage () + "!");
            System.exit (1);
        }

        sconi.writeObject (outMessage);
        sconi.close ();
    }

    private static int getProxyId ()
    {
        Class<?> cl = null;

        int proxyId;

        try {
            cl = Class.forName ("proxies.BagColPointProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados BagColPointProxy não foi encontrado!");
            e.printStackTrace ();
            System.exit (1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }

    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case GOCOLLECTABAG:
                serviceProviderProxy.setnBagsCollected(msg.getIntValue1());
                bagColPoint.goCollectABag();
                nm.setIntValue1(serviceProviderProxy.getnBagsCollected());
                break;
            case CARRYITTOAPPROPRIATESTOREBCP:
                bagColPoint.carryItToAppropriateStore(msg.getBag1());
                break;
            case SETNOMOREBAGS:
                bagColPoint.setNoMoreBags(nm.getBooleanValue1());
                break;
            case RESETBAGCOLPOINT:
                bagColPoint.resetBagColPoint();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
