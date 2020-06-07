package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.BusDriver;
import entities.Passenger;

/**
 * Exposes Departure Quay server services to the client side.
 */
public class DepartureQuayStub extends SharedRegionStub {

    /**
     * Constructor method for Departure Quay Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public DepartureQuayStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     * The busDriver parks the bus and waits till every passenger has gotten off.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.PARKTHEBUSANDLETPASSOFF) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * After letting the passengers off, the bus driver goes back to the Arrival Terminal.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.GOTOARRIVALTERMINAL) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Arriving at the Departure Terminal Transfer Quay, the passenger leaves the bus.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.LEAVETHEBUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
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
