package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.TempStgArea;

public class TempStgAreaProxy implements SharedRegionProxy {

    private final TempStgArea tempStgArea;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public TempStgAreaProxy(TempStgArea tempStgArea) {

        this.tempStgArea = tempStgArea;
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
