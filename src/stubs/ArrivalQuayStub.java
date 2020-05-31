package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class ArrivalQuayStub extends SharedRegionStub {

    public ArrivalQuayStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public boolean hasDaysWorkEnded(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.HASDAYSWORKENDED);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getBooleanValue1();
    }

    public void announcingBusBoarding(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.ANNOUNCINGBUSBOARDING);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void goToDepartureTerminal(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOTODEPARTURETERMINAL);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void parkTheBus(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.PARKTHEBUS);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void enterTheBus(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.ENTERTHEBUS);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
