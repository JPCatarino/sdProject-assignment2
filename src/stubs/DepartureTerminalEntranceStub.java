package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

/**
 * Exposes Departure Terminal Entrance server services to the client side.
 */
public class DepartureTerminalEntranceStub extends SharedRegionStub {

    /**
     * Constructor method for Departure Terminal Entrance Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public DepartureTerminalEntranceStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     *  Waits till every passenger is finished to transit to the terminal state.
     *  (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.PREPARENEXTLEG) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Setter to all passenger finished.
     * If all passenger are finished notifies all threads.
     * (service solicitation)
     *
     * @param allPassengersFinished True if all the passengers have arrived to the exit zones.
     */
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

        if (inMessage.getMessageType() != MessageType.SETALLPASSENGERSFINISHED) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Getter for number of passengers on this terminal.
     * Used by ATE to check the number of passengers here.
     * (service solicitation)
     *
     * @return number of passengers on this terminal.
     */
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

        if (inMessage.getMessageType() != MessageType.GETPASSENGERSDTE) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getIntValue1();
    }

    /**
     * Sets needed global parameters
     * @param n_passengers Number of passengers per flight
     */
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

        if (inMessage.getMessageType() != MessageType.NFICDONE) {
            System.out.println ("Simulation start: Invalid type!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close ();
    }

    /**
     * Signals the servers that a entity has ended. If all 3 entities are down, the server can shutdown safely (service solicitation)
     * @param value signal flag
     */
    public void shutdown (int value) {

        ClientCom cc = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message ();
        outMessage.setMessageType(MessageType.SHUT);
        outMessage.setIntValue1(value);

        cc.writeObject (outMessage);

        inMessage = (Message) cc.readObject ();

        if (inMessage.getMessageType () != MessageType.ACK) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close ();
    }
}
