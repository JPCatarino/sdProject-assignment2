package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.BagColPoint;

public class BagColPointProxy implements SharedRegionProxy {

    /**
     * Baggage Collection Point (represents the service to be provided).
     *
     * @serialField bagColPoint.
     */
    private final BagColPoint bagColPoint;

    /**
     * ArrivalQuayProxy Constructor.
     * It initiates the Baggage Collection Point.
     *
     * @param bagColPoint Baggage Collection Point.
     */
    public BagColPointProxy(BagColPoint bagColPoint) {
        this.bagColPoint = bagColPoint;
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
            case GOCOLLECTABAG:
                serviceProviderProxy.setnBagsCollected(msg.getIntValue1());
                serviceProviderProxy.setnBagsToCollect(msg.getIntValue2());
                serviceProviderProxy.setId(msg.getEntityID());
                bagColPoint.goCollectABag();
                nm.setIntValue1(serviceProviderProxy.getnBagsCollected());
                nm.setMessageType(MessageType.GOCOLLECTABAG);
                break;
            case CARRYITTOAPPROPRIATESTOREBCP:
                bagColPoint.carryItToAppropriateStore(msg.getBag1());
                nm.setMessageType(MessageType.CARRYITTOAPPROPRIATESTOREBCP);
                break;
            case SETNOMOREBAGS:
                bagColPoint.setNoMoreBags(msg.getBooleanValue1());
                nm.setMessageType(MessageType.SETNOMOREBAGS);
                break;
            case RESETBAGCOLPOINT:
                bagColPoint.resetBagColPoint();
                nm.setMessageType(MessageType.RESETBAGCOLPOINT);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),3);
                break;
        }
        return nm;
    }
}
