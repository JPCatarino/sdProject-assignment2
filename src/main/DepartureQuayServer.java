package main;

import common.ServerCom;
import proxies.DepartureQuayProxy;
import sharedRegions.DepartureQuay;

import java.net.SocketTimeoutException;

public class DepartureQuayServer {

    private static final int portNumb = 33007;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        DepartureQuay departureQuay;
        ServerCom scon, sconi;
        DepartureQuayProxy departureQuayProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        departureQuay = new DepartureQuay();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                departureQuayProxy = new DepartureQuayProxy(sconi, departureQuay);
                departureQuayProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
