package main;

import common.ServerCom;
import proxies.BagRecOfficeProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.BagRecOffice;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class BagRecOfficeServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33006;
    public static int waitConnection;

    /**
     *  Main program.
     */
    public static void main (String [] args) {

        ServiceProviderProxy serviceProviderProxy;
        BagRecOfficeProxy bagRecOfficeProxy;
        BagRecOffice bagRecOffice ;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        bagRecOffice = new BagRecOffice(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                bagRecOfficeProxy= new BagRecOfficeProxy(bagRecOffice);
                serviceProviderProxy= new ServiceProviderProxy( bagRecOfficeProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
