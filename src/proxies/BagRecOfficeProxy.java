package proxies;

import common.Message;
import sharedRegions.BagRecOffice;

public class BagRecOfficeProxy implements SharedRegionProxy {

    private final BagRecOffice bagRecOffice;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public BagRecOfficeProxy(BagRecOffice bagRecOffice) {
        this.bagRecOffice = bagRecOffice;
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
