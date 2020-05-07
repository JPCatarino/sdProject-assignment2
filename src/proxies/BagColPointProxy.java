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
        return null;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
