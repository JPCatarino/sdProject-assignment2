package proxies;

import common.Message;
import sharedRegions.DepartureQuay;

public class DepartureQuayProxy implements SharedRegionProxy {

    private final DepartureQuay departureQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public DepartureQuayProxy(DepartureQuay departureQuay) {
        this.departureQuay = departureQuay;
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
