package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.ArrivalQuay;

public class ArrivalQuayProxy implements SharedRegionProxy {

    private final ArrivalQuay arrivalQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public ArrivalQuayProxy(ArrivalQuay arrivalQuay) {
        this.arrivalQuay = arrivalQuay;
        this.simFinished = false;
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
