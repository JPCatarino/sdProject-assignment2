package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy extends Thread implements SharedRegionProxy {

    private final ArrivalTerminalExit arrivalTerminalExit;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public ArrivalTerminalExitProxy(ServerCom sconi, ArrivalTerminalExit arrivalTerminalExit) {
        super ("Proxy_" + ArrivalTerminalExitProxy.getProxyId ());

        this.sconi = sconi;
        this.arrivalTerminalExit = arrivalTerminalExit;
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
            cl = Class.forName ("proxies.ArrivalTerminalExitProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados ArrivalTerminalExitProxy não foi encontrado!");
            e.printStackTrace ();
            System.exit (1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }

    // check how to do setDTE
    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case GOHOME:
                serviceProviderProxy.setId(msg.getEntityID());
                arrivalTerminalExit.goHome();
                break;
            case SETALLPASSENGERSFINISHED:
                arrivalTerminalExit.setAllPassengersFinished(msg.getBooleanValue1());
                break;
            case GETPASSENGERSATE:
                nm.setIntValue1(arrivalTerminalExit.getPassengersATE());
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
