package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.BagColPoint;

public class BagColPointProxy implements SharedRegionProxy {

    private final BagColPoint bagColPoint;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public BagColPointProxy(BagColPoint bagColPoint) {
        this.bagColPoint = bagColPoint;
        this.simFinished = false;
    }

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

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
