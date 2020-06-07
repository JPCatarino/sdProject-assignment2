package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy implements SharedRegionProxy {

    /**
     * Arrival Terminal Exit (represents the service to be provided).
     *
     * @serialField arrivalTerminalExit.
     */
    private final ArrivalTerminalExit arrivalTerminalExit;

    /**
     * ArrivalQuayProxy Constructor.
     * It initiates the Arrival Terminal Exit.
     *
     * @param arrivalTerminalExit Arrival Terminal Exit.
     */
    public ArrivalTerminalExitProxy(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
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
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),2);
                break;
        }
        return nm;
    }
}
