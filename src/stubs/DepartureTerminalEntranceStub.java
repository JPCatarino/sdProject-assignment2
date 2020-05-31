package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class DepartureTerminalEntranceStub extends SharedRegionStub {

    public DepartureTerminalEntranceStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void prepareNextLeg(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.PREPARENEXTLEG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setAllPassengersFinished(boolean allPassengersFinished){

    }

    public void getPassengersDTE(){

    }
}
