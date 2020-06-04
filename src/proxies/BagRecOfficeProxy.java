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
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case REPORTMISSINGBAGS:
                bagRecOffice.reportMissingBags();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
