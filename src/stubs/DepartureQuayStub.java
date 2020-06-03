package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class DepartureQuayStub extends SharedRegionStub {

    public DepartureQuayStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void parkTheBusAndLetPassOff(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.PARKTHEBUSANDLETPASSOFF);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void goToArrivalTerminal(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOTOARRIVALTERMINAL);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void leaveTheBus(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.LEAVETHEBUS);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
    
}
