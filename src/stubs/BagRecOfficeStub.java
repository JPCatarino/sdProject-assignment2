package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;

/**
 * Exposes BaggageReclaimOffice services to the client side.
 */
public class BagRecOfficeStub extends SharedRegionStub {

    /**
     * Constructor method for Baggage Reclaim Office Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public BagRecOfficeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     *  Simulates a passenger reporting a missing bag.
     *  It puts the Passenger to sleep for some milliseconds before transitioning to the next state.
     *  (service solicitation)
     */
    public void reportMissingBags(){

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
        outMessage.setMessageType(MessageType.REPORTMISSINGBAGS);
        outMessage.setEntityID(p.getID());
        outMessage.setIntValue1(p.getnBagsCollected());
        outMessage.setIntValue2(p.getnBagsToCollect());

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.REPORTMISSINGBAGS) {
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
