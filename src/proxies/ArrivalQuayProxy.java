package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.ArrivalQuay;

public class ArrivalQuayProxy extends Thread implements SharedRegionProxy {

    private final ArrivalQuay arrivalQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public ArrivalQuayProxy(ServerCom sconi, ArrivalQuay arrivalQuay) {
        super ("Proxy_" + ArrivalQuayProxy.getProxyId ());

        this.sconi = sconi;
        this.arrivalQuay = arrivalQuay;
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
            cl = Class.forName ("proxies.ArrivalQuayProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados ArrivalQuayProxy não foi encontrado!");
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
            case HASDAYSWORKENDED:
                serviceProviderProxy.setTTL(msg.getIntValue1());
                nm.setBooleanValue1(arrivalQuay.hasDaysWorkEnded());
                break;
            case ANNOUNCINGBUSBOARDING:
                arrivalQuay.announcingBusBoarding();
                break;
            case GOTODEPARTURETERMINAL:
                arrivalQuay.goToDepartureTerminal();
                nm.setIntList1(serviceProviderProxy.getBusSeats());
                break;
            case PARKTHEBUS:
                arrivalQuay.parkTheBus();
                break;
            case ENTERTHEBUS:
                serviceProviderProxy.setId(msg.getEntityID());
                arrivalQuay.enterTheBus();
                nm.setIntValue1(serviceProviderProxy.getBusSeat());
                break;
            case SETNFIC:
                arrivalQuay.setMaxNumberOfSeats(msg.getT_seats());
                nm.setMessageType(MessageType.NFICDONE);
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
