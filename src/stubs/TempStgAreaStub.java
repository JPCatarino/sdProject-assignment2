package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

/**
 * Exposes Temporary Storage Area server services to the client side.
 */
public class TempStgAreaStub extends SharedRegionStub {

    /**
     * Constructor method for Temporary Storage Area Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public TempStgAreaStub(String serverHostName, int serverPort) {

        super(serverHostName, serverPort);
    }

    /**
     * Move a bag from the plane hold to the temporary storage area.
     * (service solicitation)
     *
     * @param bag Bag to be moved to the temporary storage area.
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
        outMessage.setMessageType(MessageType.CARRYITTOAPPROPRIATESTORETMP);
        outMessage.setBag1(bag);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.CARRYITTOAPPROPRIATESTORETMP) {
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
