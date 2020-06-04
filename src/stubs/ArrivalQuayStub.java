package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.BusDriver;
import entities.Passenger;

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
        BusDriver bd = (BusDriver) Thread.currentThread();
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOTODEPARTURETERMINAL);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        bd.setBusSeats(newMessage.getIntList1());
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
        Passenger p = (Passenger) Thread.currentThread();

        newMessage.setMessageType(MessageType.ENTERTHEBUS);
        newMessage.setEntityID(p.getID());

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }
}
