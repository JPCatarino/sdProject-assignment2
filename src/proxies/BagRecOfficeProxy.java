package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.BagRecOffice;

public class BagRecOfficeProxy implements SharedRegionProxy {

    /**
     * Baggage Reclaim Office (represents the service to be provided).
     *
     * @serialField bagRecOffice.
     */
    private final BagRecOffice bagRecOffice;

    /**
     * BagRecOfficeProxy Constructor.
     * It initiates the Baggage Reclaim Office.
     *
     * @param bagRecOffice Baggage Reclaim Office.
     */
    public BagRecOfficeProxy( BagRecOffice bagRecOffice) {
        this.bagRecOffice = bagRecOffice;
    }

    /**
     * Process messages by executing corresponding task.
     * Generate answer message.
     *
     * @param msg message in the request.
     *
     * @return answer message.
     */
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
}
