package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

/**
 * Exposes Baggage Collection Point server services to the client side.
 */
public class BagColPointStub extends SharedRegionStub {

    /**
     * Constructor method for Baggage Collection Point Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public BagColPointStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     * Passenger goes to Baggage Collection Point trying to collect it's bags.
     * It waits for the Porter to put the bag on the conveyor belt.
     * If it's one of their bags, they go try to collect it.
     * A passenger can only collect one bag per attempt.
     * (service solicitation)
     */
    public void goCollectABag(){

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

        outMessage.setMessageType(MessageType.GOCOLLECTABAG);
        outMessage.setIntValue1(p.getnBagsCollected());
        outMessage.setIntValue2(p.getnBagsToCollect());
        outMessage.setEntityID(p.getID());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        p.setnBagsCollected(inMessage.getIntValue1());

        if (inMessage.getMessageType() != MessageType.GOCOLLECTABAG) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Move a bag from the plane hold to the bag collection point.
     * (service solicitation)
     *
     * @param bag Bag to be moved to the bag collection point.
     */
    public void carryItToAppropriateStore(int [] bag){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();

        outMessage.setMessageType(MessageType.CARRYITTOAPPROPRIATESTOREBCP);
        outMessage.setBag1(bag);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.CARRYITTOAPPROPRIATESTOREBCP) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set the variable no more bags.
     * Notifies all threads.
     * (service solicitation)
     *
     * @param noMoreBags True if there are no more bags in the plane hold or False otherwise.
     */
    public void setNoMoreBags(boolean noMoreBags){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETNOMOREBAGS);
        outMessage.setBooleanValue1(noMoreBags);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETNOMOREBAGS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Reset the variables for the next flight.
     * (service solicitation)
     */
    public void resetBagColPoint(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.RESETBAGCOLPOINT);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.RESETBAGCOLPOINT) {
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
