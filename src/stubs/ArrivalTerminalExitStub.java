package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import sharedRegions.DepartureTerminalEntrance;

public class ArrivalTerminalExitStub extends SharedRegionStub {

    public ArrivalTerminalExitStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void goHome(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.GOHOME);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setDte(DepartureTerminalEntrance dte){

    }

    public void setAllPassengersFinished(boolean allPassengersFinished){

    }

    public void getPassengersATE(){

    }
}
