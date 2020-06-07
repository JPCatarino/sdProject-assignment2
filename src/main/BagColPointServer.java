package main;

import common.ServerCom;
import proxies.BagColPointProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.BagColPoint;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class BagColPointServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33005;
    public static int waitConnection;

    /**
     *  Main program.
     */
    public static void main (String [] args) {

        ServiceProviderProxy serviceProviderProxy;
        BagColPointProxy bagColPointProxy;
        BagColPoint bagColPoint ;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        bagColPoint = new BagColPoint(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                bagColPointProxy = new BagColPointProxy(bagColPoint);
                serviceProviderProxy= new ServiceProviderProxy( bagColPointProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
