package main;

import common.ServerCom;
import proxies.ArrivalQuayProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalQuay;

import java.net.SocketTimeoutException;

public class ArrivalQuayServer {

    private static final int portNumb = 33003;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        ArrivalQuayProxy arrivalQuayProxy;
        ArrivalQuay arrivalQuay ;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalQuay = new ArrivalQuay();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                arrivalQuayProxy = new ArrivalQuayProxy(arrivalQuay);
                serviceProviderProxy= new ServiceProviderProxy( arrivalQuayProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
