package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;
import sharedRegions.DepartureTerminalEntrance;

public class ArrivalTerminalExitStub extends SharedRegionStub {

    public ArrivalTerminalExitStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void goHome(){
        Message newMessage = new Message();
        Passenger p = (Passenger) Thread.currentThread();

        newMessage.setMessageType(MessageType.GOHOME);
        newMessage.setEntityID(p.getID());
        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    // This probably can't be done, we gotta check if there's another way
    public void setDte(DepartureTerminalEntranceStub dte){

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

    public int getPassengersATE(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GETPASSENGERSATE);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getIntValue1();
    }
}
