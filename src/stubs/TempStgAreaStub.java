package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class TempStgAreaStub extends SharedRegionStub {

    public TempStgAreaStub(String serverHostName, int serverPort) {

        super(serverHostName, serverPort);
    }

    public void carryItToAppropriateStore(int [] bag){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.CARRYITTOAPPROPRIATESTORETMP);
        newMessage.setBag1(bag);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
