package stubs;

/**
 * Generic Stub Class to use as a base for the others. It's used for the client side
 * to communicate with the server side.
 */
public class SharedRegionStub {

    private String serverHostName;

    private int serverPort;

    public SharedRegionStub(String serverHostName, int serverPort){
        this.serverHostName = serverHostName;
        this.serverPort = serverPort;
    }
}
