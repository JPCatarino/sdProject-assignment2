package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
import sharedRegions.TempStgArea;

public class TempStgAreaProxy implements SharedRegionProxy {

    /**
     * Temporary Storage Area (represents the service to be provided).
     *
     * @serialField tempStgArea.
     */
    private final TempStgArea tempStgArea;

    /**
     * TempStgAreaProxy Constructor.
     * It initiates the Temporary Storage Area.
     *
     * @param tempStgArea Temporary Storage Area.
     */
    public TempStgAreaProxy(TempStgArea tempStgArea) {
        this.tempStgArea = tempStgArea;
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
            case CARRYITTOAPPROPRIATESTORETMP:
                tempStgArea.carryItToAppropriateStore(msg.getBag1());
                nm.setMessageType(MessageType.CARRYITTOAPPROPRIATESTORETMP);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),8);
                break;
        }
        return nm;
    }
}
