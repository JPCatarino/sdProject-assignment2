package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

public class BagColPointStub extends SharedRegionStub {

    public BagColPointStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void goCollectABag(){
        Passenger p = (Passenger) Thread.currentThread();
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOCOLLECTABAG);
        newMessage.setIntValue1(p.getnBagsCollected());
        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        p.setnBagsCollected(newMessage.getIntValue1());
    }

    public void carryItToAppropriateStore(int [] bag){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.CARRYITTOAPPROPRIATESTOREBCP);
        newMessage.setBag1(bag);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setNoMoreBags(boolean noMoreBags){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETNOMOREBAGS);
        newMessage.setBooleanValue1(noMoreBags);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void resetBagColPoint(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.RESETBAGCOLPOINT);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
