package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.BusDriver;
import entities.Passenger;

/**
 * Exposes Arrival Quay server services to the client side.
 */
public class ArrivalQuayStub extends SharedRegionStub {

    /**
     * Constructor method for Arrival Quay Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public ArrivalQuayStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     *   Let's the BusDriver know his shift as ended, so he can enter terminal state.
     *   (service solicitation)
     *   @return True, if the day has ended.
     */
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

        if (inMessage.getMessageType() != MessageType.HASDAYSWORKENDED) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getBooleanValue1();
    }

    /**
     * The bus driver waits until there's passengers in the queue or it's time to leave.
     * After this, he notifies all passengers that the bus is ready to board.
     * (service solicitation)
     */
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

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.ANNOUNCINGBUSBOARDING) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * After boarding all passengers, the bus driver then drives to the DepartureTerminal.
     * This function changes state to DRIVING_FORWARD and unparks the bus.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.GOTODEPARTURETERMINAL) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Parks the bus after returning from Departure Terminal.
     * It assumes the Bus comes back empty from the terminal.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.PARKTHEBUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Simulates the entrance of a passenger on the bus.
     * The passenger gets in the queue and waits orders from the BusDriver to board.
     * (service solicitation)
     */
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

        if (inMessage.getMessageType() != MessageType.ENTERTHEBUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Sets needed global parameters (service solicitation)
     * @param t_seats Maximum number of bus seats
     */
    public void probPar (int t_seats) {

        ClientCom cc = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SETNFIC, t_seats);

        cc.writeObject (outMessage);

        inMessage = (Message) cc.readObject ();

        if (inMessage.getMessageType() != MessageType.NFICDONE) {
            System.out.println ("Arranque da simulação: Tipo inválido!");
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
