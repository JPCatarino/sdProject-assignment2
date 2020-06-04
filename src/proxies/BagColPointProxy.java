package proxies;

import common.Message;
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
        ServiceProviderProxy sp = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case GOCOLLECTABAG:
                bagColPoint.goCollectABag();
                break;
            case CARRYITTOAPPROPRIATESTOREBCP:
                bagColPoint.carryItToAppropriateStore(msg.getBag1());
                break;
            case SETNOMOREBAGS:
                bagColPoint.setNoMoreBags(nm.getBooleanValue1());
                break;
            case RESETBAGCOLPOINT:
                bagColPoint.resetBagColPoint();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
