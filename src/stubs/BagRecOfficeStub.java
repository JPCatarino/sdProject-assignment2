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

        Passenger p = (Passenger) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.REPORTMISSINGBAGS);
        outMessage.setEntityID(p.getID());
        outMessage.setIntValue1(p.getnBagsCollected());
        outMessage.setIntValue2(p.getnBagsToCollect());

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        cc.close();
    }
}
