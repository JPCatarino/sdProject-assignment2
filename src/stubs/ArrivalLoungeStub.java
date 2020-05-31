package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import states.PassengerDecisions;

import java.util.List;

public class ArrivalLoungeStub extends SharedRegionStub {

    public ArrivalLoungeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void takeABus() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TAKEABUS);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();

    }

    public void whatShouldIDo() {

    }

    public void takeARest() {

    }

    public void tryToCollectABag() {

    }

    public void noMoreBagsToCollect() {

    }

    public void setPlainBags(List<int[]> plainBags){

    }

    public void setFlightNumber(int flightNumber){

    }

    public void isDayFinished(){

    }

    public void getMaxNumberOfPassengers(){

    }

    public void setFinishedFlight(boolean finishedFlight){

    }

    public void ispWake(){

    }

}
