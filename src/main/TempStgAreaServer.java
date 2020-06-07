package main;

import common.ServerCom;
import proxies.ServiceProviderProxy;
import proxies.TempStgAreaProxy;
import sharedRegions.TempStgArea;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class TempStgAreaServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33009;
    public static int waitConnection;

    /**
     *  Main program.
     */
    public static void main (String [] args) {

        ServiceProviderProxy serviceProviderProxy;
        TempStgAreaProxy tempStgAreaProxy;
        TempStgArea tempStgArea;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        tempStgArea = new TempStgArea(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                tempStgAreaProxy = new TempStgAreaProxy(tempStgArea);
                serviceProviderProxy= new ServiceProviderProxy( tempStgAreaProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
