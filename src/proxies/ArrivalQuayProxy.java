package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.ArrivalQuay;

public class ArrivalQuayProxy implements SharedRegionProxy {

    /**
     * Arrival Quay (represents the service to be provided).
     *
     * @serialField arrivalQuay.
     */
    private final ArrivalQuay arrivalQuay;

    /**
     * ArrivalQuayProxy Constructor.
     * It initiates the Arrival Quay.
     *
     * @param arrivalQuay Arrival Quay.
     */
    public ArrivalQuayProxy(ArrivalQuay arrivalQuay) {
        this.arrivalQuay = arrivalQuay;
    }

    /**
     * Process messages by executing corresponding task.
     * Generate answer message.
     *
     * @param msg message in the request.
     *
     * @return answer message.
     */
    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case HASDAYSWORKENDED:
                serviceProviderProxy.setTTL(msg.getIntValue1());
                nm.setBooleanValue1(arrivalQuay.hasDaysWorkEnded());
                nm.setMessageType(MessageType.HASDAYSWORKENDED);
                break;
            case ANNOUNCINGBUSBOARDING:
                arrivalQuay.announcingBusBoarding();
                nm.setMessageType(MessageType.ANNOUNCINGBUSBOARDING);
                break;
            case GOTODEPARTURETERMINAL:
                arrivalQuay.goToDepartureTerminal();
                nm.setIntList1(serviceProviderProxy.getBusSeats());
                nm.setMessageType(MessageType.GOTODEPARTURETERMINAL);
                break;
            case PARKTHEBUS:
                arrivalQuay.parkTheBus();
                nm.setMessageType(MessageType.PARKTHEBUS);
                break;
            case ENTERTHEBUS:
                serviceProviderProxy.setId(msg.getEntityID());
                arrivalQuay.enterTheBus();
                nm.setIntValue1(serviceProviderProxy.getBusSeat());
                nm.setMessageType(MessageType.ENTERTHEBUS);
                break;
            case SETNFIC:
                arrivalQuay.setMaxNumberOfSeats(msg.getT_seats());
                nm.setMessageType(MessageType.NFICDONE);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),1);
                break;
        }

        return nm;
    }
}
