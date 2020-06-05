package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

public class BagRecOfficeStub extends SharedRegionStub {

    public BagRecOfficeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void reportMissingBags(){
        Message newMessage = new Message();
        Passenger p = (Passenger) Thread.currentThread();

        newMessage.setMessageType(MessageType.REPORTMISSINGBAGS);
        newMessage.setEntityID(p.getID());
        newMessage.setIntValue1(p.getnBagsCollected());
        newMessage.setIntValue2(p.getnBagsToCollect());
        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
