package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

public class DepartureTerminalEntranceStub extends SharedRegionStub {

    public DepartureTerminalEntranceStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void prepareNextLeg(){
        Message newMessage = new Message();
        Passenger p = (Passenger) Thread.currentThread();

        newMessage.setMessageType(MessageType.PREPARENEXTLEG);
        newMessage.setEntityID(p.getID());

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        cc.close();

    }

    public void setAllPassengersFinished(boolean allPassengersFinished){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETALLPASSENGERSFINISHED);
        newMessage.setBooleanValue1(allPassengersFinished);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        cc.close();

    }

    public int getPassengersDTE(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GETPASSENGERSDTE);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        cc.close();

        return newMessage.getIntValue1();
    }

    public void probPar (int n_passengers)
    {
        ClientCom con = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!con.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETNPDTE);
        outMessage.setN_passengers(n_passengers);
        con.writeObject (outMessage);
        inMessage = (Message) con.readObject ();

        con.close ();
    }
}
