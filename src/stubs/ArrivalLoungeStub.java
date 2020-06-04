package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;
import states.PassengerDecisions;

import java.util.List;

public class ArrivalLoungeStub extends SharedRegionStub {

    public ArrivalLoungeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void takeABus() {
        Passenger p = (Passenger) Thread.currentThread();
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TAKEABUS);
        newMessage.setEntityID(p.getID());

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();

    }

    public PassengerDecisions whatShouldIDo() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.WHATSHOULDIDO);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getPassengerDecisions1();
    }

    public boolean takeARest() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TAKEAREST);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getBooleanValue1();
    }

    public int[] tryToCollectABag() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TRYTOCOLLECTABAG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getBag1();
    }

    public void noMoreBagsToCollect() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.NOMOREBAGSTOCOLLECT);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setPlainBags(List<int[]> plainBags){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETPLAINBAGS);
        newMessage.setBagList1(plainBags);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setFlightNumber(int flightNumber){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETFLIGHTNUMBER);
        newMessage.setIntValue1(flightNumber);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public boolean isDayFinished(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.ISDAYFINISHED);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getBooleanValue1();
    }

    // This function might need to be changed.
    // Maybe we need to just pass max passengers parameter to dte and ate instead of calling this function
/*    public int getMaxNumberOfPassengers(){

    }*/

    public void setFinishedFlight(boolean finishedFlight){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETFINISHEDFLIGHT);
        newMessage.setBooleanValue1(finishedFlight);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public boolean ispWake(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.ISPWAKE);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getBooleanValue1();
    }

}
