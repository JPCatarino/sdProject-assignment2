package stubs;

public class BagColPointStub extends SharedRegionStub {

    public BagColPointStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public synchronized void goCollectABag(){

    }

    public synchronized void carryItToAppropriateStore(int [] bag){

    }

    public synchronized void setNoMoreBags(boolean noMoreBags){

    }

    public synchronized void resetBagColPoint(){

    }
}
