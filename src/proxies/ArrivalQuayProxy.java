package proxies;

import common.Message;
import sharedRegions.ArrivalQuay;

public class ArrivalQuayProxy implements SharedRegionProxy {

    private final ArrivalQuay arrivalQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public ArrivalQuayProxy(ArrivalQuay arrivalQuay) {
        this.arrivalQuay = arrivalQuay;
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
