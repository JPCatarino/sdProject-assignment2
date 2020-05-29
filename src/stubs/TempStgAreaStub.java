package stubs;

public class TempStgAreaStub extends SharedRegionStub {

    public TempStgAreaStub(String serverHostName, int serverPort) {

        super(serverHostName, serverPort);
    }

    public synchronized void carryItToAppropriateStore(int [] bag){

    }
}
