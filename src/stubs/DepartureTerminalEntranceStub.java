package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class DepartureTerminalEntranceStub extends SharedRegionStub {

    public DepartureTerminalEntranceStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void prepareNextLeg(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.PREPARENEXTLEG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setAllPassengersFinished(boolean allPassengersFinished){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETALLPASSENGERSFINISHED);
        newMessage.setBooleanValue1(allPassengersFinished);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public int getPassengersDTE(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GETPASSENGERSDTE);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getIntValue1();
    }
}
