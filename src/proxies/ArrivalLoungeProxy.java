package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.ArrivalLounge;

public class ArrivalLoungeProxy extends Thread implements SharedRegionProxy {

    private final ArrivalLounge arrivalLounge;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public ArrivalLoungeProxy(ServerCom sconi, ArrivalLounge arrivalLounge) {
        super ("Proxy_" + ArrivalLoungeProxy.getProxyId ());

        this.sconi = sconi;
        this.arrivalLounge = arrivalLounge;
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
            cl = Class.forName ("proxies.ArrivalLoungeProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados ArrivalLoungeProxy não foi encontrado!");
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
            case TAKEABUS:
                serviceProviderProxy.setId(msg.getEntityID());
                arrivalLounge.takeABus();
                break;
            case WHATSHOULDIDO:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setJourneyEnding(msg.getBooleanValue1());
                serviceProviderProxy.setnBagsToCollect(msg.getIntValue1());
                arrivalLounge.whatShouldIDo();
                break;
            case TAKEAREST:
                serviceProviderProxy.setPlaneHoldEmpty(msg.getBooleanValue1());
                arrivalLounge.takeARest();
                break;
            case TRYTOCOLLECTABAG:
                arrivalLounge.tryToCollectABag();
                break;
            case NOMOREBAGSTOCOLLECT:
                arrivalLounge.noMoreBagsToCollect();
                break;
            case SETPLAINBAGS:
                arrivalLounge.setPlainBags(msg.getBagList1());
                break;
            case SETFLIGHTNUMBER:
                arrivalLounge.setFlightNumber(msg.getIntValue1());
                break;
            case ISDAYFINISHED:
                nm.setBooleanValue1(arrivalLounge.isDayFinished());
                break;
            case SETFINISHEDFLIGHT:
                arrivalLounge.setFinishedFlight(msg.getBooleanValue1());
                break;
            case ISPWAKE:
                nm.setBooleanValue1(arrivalLounge.ispWake());
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
