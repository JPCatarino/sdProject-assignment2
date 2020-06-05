package main;

import common.ServerCom;
import proxies.ArrivalLoungeProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalLounge;

import java.net.SocketTimeoutException;

public class ArrivalLoungeServer {

    private static final int portNumb = 33002;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        ArrivalLoungeProxy arrivalLoungeProxy;
        ArrivalLounge arrivalLounge ;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalLounge = new ArrivalLounge();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                arrivalLoungeProxy = new ArrivalLoungeProxy(arrivalLounge);
                serviceProviderProxy= new ServiceProviderProxy( arrivalLoungeProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
