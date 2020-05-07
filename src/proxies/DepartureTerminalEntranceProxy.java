package proxies;

import common.Message;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements  SharedRegionProxy {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
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
