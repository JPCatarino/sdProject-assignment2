package stubs;

/**
 * Generic Stub Class to use as a base for the others. It's used for the client side
 * to communicate with the server side.
 */
public class SharedRegionStub {

    /**
     * Host name of the server where this stub should connect
     * @serialField serverHostName
     */
    private String serverHostName;

    /**
     * Communication port for protocol establishment
     * @serialField serverPort
     */
    private int serverPort;

    /**
     * Constructor of the Shared Region Stub
     * @param serverHostName Server Host Name
     * @param serverPort Communication Port
     */
    public SharedRegionStub(String serverHostName, int serverPort){
        this.serverHostName = serverHostName;
        this.serverPort = serverPort;
    }

    /**
     * Getter for serverHostName
     * @return Server Host Name
     */
    public String getServerHostName() {
        return serverHostName;
    }

    /**
     * Getter for serverPort
     * @return server port
     */
    public int getServerPort() {
        return serverPort;
    }
}
