package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.BusDriver;
import entities.Passenger;

public class DepartureQuayStub extends SharedRegionStub {

    public DepartureQuayStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void parkTheBusAndLetPassOff(){
        Message newMessage = new Message();
        BusDriver bd = (BusDriver) Thread.currentThread();

        newMessage.setMessageType(MessageType.PARKTHEBUSANDLETPASSOFF);
        newMessage.setIntList1(bd.getBusSeats());

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
        Passenger p = (Passenger) Thread.currentThread();

        newMessage.setMessageType(MessageType.LEAVETHEBUS);
        newMessage.setEntityID(p.getID());
        newMessage.setIntValue1(p.getBusSeat());

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

}
