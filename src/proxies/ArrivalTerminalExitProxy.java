package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy implements SharedRegionProxy {

    private final ArrivalTerminalExit arrivalTerminalExit;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public ArrivalTerminalExitProxy(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
        this.simFinished = false;
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
                nm.setMessageType(MessageType.GOHOME);
                break;
            case SETALLPASSENGERSFINISHED:
                arrivalTerminalExit.setAllPassengersFinished(msg.getBooleanValue1());
                nm.setMessageType(MessageType.SETALLPASSENGERSFINISHED);
                break;
            case GETPASSENGERSATE:
                nm.setIntValue1(arrivalTerminalExit.getPassengersATE());
                nm.setMessageType(MessageType.GETPASSENGERSATE);
                break;
            case SETNFIC:
                arrivalTerminalExit.setMaxNumberOfPassengers(msg.getN_passengers());
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
