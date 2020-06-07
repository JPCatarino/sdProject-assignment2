package main;

import common.ServerCom;
import proxies.ArrivalLoungeProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalLounge;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class ArrivalLoungeServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33002;
    public static int waitConnection;

    /**
     *  Main program.
     */

    public static void main (String [] args) {

        ServiceProviderProxy serviceProviderProxy;
        ArrivalLoungeProxy arrivalLoungeProxy;
        ArrivalLounge arrivalLounge ;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalLounge = new ArrivalLounge(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                arrivalLoungeProxy = new ArrivalLoungeProxy(arrivalLounge);
                serviceProviderProxy= new ServiceProviderProxy( arrivalLoungeProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
