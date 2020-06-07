package proxies;

import common.Message;
import common.MessageType;
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

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
