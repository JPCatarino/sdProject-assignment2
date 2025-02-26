package main;

import common.ServerCom;
import proxies.ArrivalQuayProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalQuay;
import stubs.ArrivalLoungeStub;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class ArrivalQuayServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33003;
    public static int waitConnection;

    /**
     *  Main program.
     */
    public static void main (String [] args) {

        ServiceProviderProxy serviceProviderProxy;
        ArrivalQuayProxy arrivalQuayProxy;
        ArrivalQuay arrivalQuay ;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalQuay = new ArrivalQuay(new RepositoryStub("localhost", 33001), new ArrivalLoungeStub("localhost", 33002));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                arrivalQuayProxy = new ArrivalQuayProxy(arrivalQuay);
                serviceProviderProxy= new ServiceProviderProxy( arrivalQuayProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
