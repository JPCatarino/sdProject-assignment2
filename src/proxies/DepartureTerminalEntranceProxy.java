package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy extends Thread implements  SharedRegionProxy {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public DepartureTerminalEntranceProxy(ServerCom sconi, DepartureTerminalEntrance departureTerminalEntrance) {
        super ("Proxy_" + DepartureTerminalEntranceProxy.getProxyId ());

        this.sconi = sconi;
        this.departureTerminalEntrance = departureTerminalEntrance;
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
            cl = Class.forName ("proxies.DepartureTerminalEntranceProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados DepartureTerminalEntranceProxy não foi encontrado!");
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
            case PREPARENEXTLEG:
                serviceProviderProxy.setId(msg.getEntityID());
                departureTerminalEntrance.prepareNextLeg();
                break;
            case SETALLPASSENGERSFINISHED:
                departureTerminalEntrance.setAllPassengersFinished(msg.getBooleanValue1());
                break;
            case GETPASSENGERSDTE:
                nm.setIntValue1(departureTerminalEntrance.getPassengersDTE());
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
