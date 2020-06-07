package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.DepartureQuay;

public class DepartureQuayProxy implements SharedRegionProxy {

    /**
     * Departure Quay (represents the service to be provided).
     *
     * @serialField departureQuay.
     */
    private final DepartureQuay departureQuay;

    /**
     * DepartureQuayProxy Constructor.
     * It initiates the Departure Quay.
     *
     * @param departureQuay Departure Quay.
     */
    public DepartureQuayProxy(DepartureQuay departureQuay) {
        this.departureQuay = departureQuay;
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
            case PARKTHEBUSANDLETPASSOFF:
                serviceProviderProxy.setBusSeats(msg.getIntList1());
                departureQuay.parkTheBusAndLetPassOff();
                nm.setMessageType(MessageType.PARKTHEBUSANDLETPASSOFF);
                break;
            case GOTOARRIVALTERMINAL:
                departureQuay.goToArrivalTerminal();
                nm.setMessageType(MessageType.GOTOARRIVALTERMINAL);
                break;
            case LEAVETHEBUS:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setBusSeat(msg.getIntValue1());
                departureQuay.leaveTheBus();
                nm.setMessageType(MessageType.LEAVETHEBUS);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),5);
                break;
        }

        return nm;
    }
}
