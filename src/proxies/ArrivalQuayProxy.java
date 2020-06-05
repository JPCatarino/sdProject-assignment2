package proxies;

import common.Message;
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
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
