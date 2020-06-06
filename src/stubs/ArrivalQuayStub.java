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

        outMessage.setMessageType(MessageType.HASDAYSWORKENDED);
        outMessage.setIntValue1(bd.getTTL());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();

        return inMessage.getBooleanValue1();
    }

    public void announcingBusBoarding(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.ANNOUNCINGBUSBOARDING);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        cc.close();
    }

    public void goToDepartureTerminal(){

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
        outMessage.setMessageType(MessageType.GOTODEPARTURETERMINAL);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        bd.setBusSeats(inMessage.getIntList1());

        cc.close();
    }

    public void parkTheBus(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.PARKTHEBUS);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        cc.close();
    }

    public void enterTheBus(){

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
        outMessage.setMessageType(MessageType.ENTERTHEBUS);
        outMessage.setEntityID(p.getID());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        p.setBusSeat(inMessage.getIntValue1());

        cc.close();
    }

    public void probPar (int t_seats) {

        ClientCom con = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!con.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SETNFIC, t_seats);

        con.writeObject (outMessage);

        inMessage = (Message) con.readObject ();

        if (inMessage.getMessageType() != MessageType.NFICDONE) {
            System.out.println ("Arranque da simulação: Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        con.close ();
    }
}
