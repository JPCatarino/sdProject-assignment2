package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class TempStgAreaStub extends SharedRegionStub {

    public TempStgAreaStub(String serverHostName, int serverPort) {

        super(serverHostName, serverPort);
    }

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
}
