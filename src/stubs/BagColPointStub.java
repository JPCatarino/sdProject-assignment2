package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class BagColPointStub extends SharedRegionStub {

    public BagColPointStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void goCollectABag(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOCOLLECTABAG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void carryItToAppropriateStore(int [] bag){

    }

    public void setNoMoreBags(boolean noMoreBags){

    }

    public void resetBagColPoint(){

    }
}
