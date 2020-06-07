package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.BagRecOffice;

public class BagRecOfficeProxy implements SharedRegionProxy {

    private final BagRecOffice bagRecOffice;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public BagRecOfficeProxy( BagRecOffice bagRecOffice) {
        this.bagRecOffice = bagRecOffice;
        this.simFinished = false;
    }

    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case REPORTMISSINGBAGS:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setnBagsCollected(msg.getIntValue1());
                serviceProviderProxy.setnBagsToCollect(msg.getIntValue2());
                bagRecOffice.reportMissingBags();
                nm.setMessageType(MessageType.REPORTMISSINGBAGS);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),4);
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
