package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements  SharedRegionProxy {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
        this.simFinished = false;
    }

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

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
