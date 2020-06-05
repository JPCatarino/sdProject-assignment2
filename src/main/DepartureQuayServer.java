package main;

import common.ServerCom;
import proxies.DepartureQuayProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.DepartureQuay;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class DepartureQuayServer {

    private static final int portNumb = 33007;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        DepartureQuayProxy departureQuayProxy;
        DepartureQuay departureQuay;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        departureQuay = new DepartureQuay(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                departureQuayProxy = new DepartureQuayProxy(departureQuay);
                serviceProviderProxy= new ServiceProviderProxy( departureQuayProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
