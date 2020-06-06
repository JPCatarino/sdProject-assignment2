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
                break;
            case SETALLPASSENGERSFINISHED:
                departureTerminalEntrance.setAllPassengersFinished(msg.getBooleanValue1());
                break;
            case GETPASSENGERSDTE:
                nm.setIntValue1(departureTerminalEntrance.getPassengersDTE());
                break;
            case SETNPDTE:
                departureTerminalEntrance.setMaxNumberOfPassengers(msg.getN_passengers());
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
