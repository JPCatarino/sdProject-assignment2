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

        Passenger p = (Passenger) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.PREPARENEXTLEG);
        outMessage.setEntityID(p.getID());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();
    }

    public void setAllPassengersFinished(boolean allPassengersFinished){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETALLPASSENGERSFINISHED);
        outMessage.setBooleanValue1(allPassengersFinished);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();
    }

    public int getPassengersDTE(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.GETPASSENGERSDTE);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();

        return inMessage.getIntValue1();
    }

    public void probPar (int n_passengers)
    {
        ClientCom cc = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETNPDTE);
        outMessage.setN_passengers(n_passengers);

        cc.writeObject (outMessage);

        inMessage = (Message) cc.readObject ();

        cc.close ();
    }
}
