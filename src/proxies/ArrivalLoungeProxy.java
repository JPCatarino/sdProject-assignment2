package proxies;

import common.Message;
import sharedRegions.ArrivalLounge;

public class ArrivalLoungeProxy implements SharedRegionProxy {

    private final ArrivalLounge arrivalLounge;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public ArrivalLoungeProxy(ArrivalLounge arrivalLounge) {
        this.arrivalLounge = arrivalLounge;
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
