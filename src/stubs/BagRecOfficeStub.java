package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class BagRecOfficeStub extends SharedRegionStub {

    public BagRecOfficeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void reportMissingBags(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.REPORTMISSINGBAGS);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
