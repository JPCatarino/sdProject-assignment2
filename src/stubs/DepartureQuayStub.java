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

        BusDriver bd = (BusDriver) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.PARKTHEBUSANDLETPASSOFF);
        outMessage.setIntList1(bd.getBusSeats());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();
    }

    public void goToArrivalTerminal(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.GOTOARRIVALTERMINAL);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        cc.close();
    }

    public void leaveTheBus(){

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
        outMessage.setMessageType(MessageType.LEAVETHEBUS);
        outMessage.setEntityID(p.getID());
        outMessage.setIntValue1(p.getBusSeat());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();
    }
}
