package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.DepartureQuay;

public class DepartureQuayProxy extends Thread implements SharedRegionProxy {

    private final DepartureQuay departureQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public DepartureQuayProxy(ServerCom sconi, DepartureQuay departureQuay) {
        super ("Proxy_" + DepartureQuayProxy.getProxyId ());

        this.sconi = sconi;
        this.departureQuay = departureQuay;
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
            cl = Class.forName ("proxies.DepartureQuayProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados DepartureQuayProxy não foi encontrado!");
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
            case PARKTHEBUSANDLETPASSOFF:
                serviceProviderProxy.setBusSeats(msg.getIntList1());
                departureQuay.parkTheBusAndLetPassOff();
                break;
            case GOTOARRIVALTERMINAL:
                departureQuay.goToArrivalTerminal();
                break;
            case LEAVETHEBUS:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setBusSeat(msg.getIntValue1());
                departureQuay.leaveTheBus();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
