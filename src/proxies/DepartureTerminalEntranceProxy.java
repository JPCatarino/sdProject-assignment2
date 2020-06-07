package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements  SharedRegionProxy {

    /**
     * Departure Terminal Entrance (represents the service to be provided).
     *
     * @serialField departureTerminalEntrance.
     */
    private final DepartureTerminalEntrance departureTerminalEntrance;

    /**
     * DepartureTerminalEntranceProxy Constructor.
     * It initiates the Departure Terminal Entrance.
     *
     * @param departureTerminalEntrance Departure Terminal Entrance.
     */
    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
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
            case PREPARENEXTLEG:
                serviceProviderProxy.setId(msg.getEntityID());
                departureTerminalEntrance.prepareNextLeg();
                nm.setMessageType(MessageType.PREPARENEXTLEG);
                break;
            case SETALLPASSENGERSFINISHED:
                departureTerminalEntrance.setAllPassengersFinished(msg.getBooleanValue1());
                nm.setMessageType(MessageType.SETALLPASSENGERSFINISHED);
                break;
            case GETPASSENGERSDTE:
                nm.setIntValue1(departureTerminalEntrance.getPassengersDTE());
                nm.setMessageType(MessageType.GETPASSENGERSDTE);
                break;
            case SETNPDTE:
                departureTerminalEntrance.setMaxNumberOfPassengers(msg.getN_passengers());
                nm.setMessageType(MessageType.NFICDONE);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),6);
                break;
        }
        return nm;
    }
}
