package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.BagRecOffice;

public class BagRecOfficeProxy extends Thread implements SharedRegionProxy {

    private final BagRecOffice bagRecOffice;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public BagRecOfficeProxy(ServerCom sconi, BagRecOffice bagRecOffice) {
        super ("Proxy_" + BagRecOfficeProxy.getProxyId ());

        this.sconi = sconi;
        this.bagRecOffice = bagRecOffice;
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
            cl = Class.forName ("proxies.BagRecOfficeProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados BagRecOfficeProxy não foi encontrado!");
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
            case REPORTMISSINGBAGS:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setnBagsCollected(msg.getIntValue1());
                serviceProviderProxy.setnBagsToCollect(msg.getIntValue2());
                bagRecOffice.reportMissingBags();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
