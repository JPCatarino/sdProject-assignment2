package main;

import common.ServerCom;
import proxies.ArrivalQuayProxy;
import sharedRegions.ArrivalQuay;

import java.net.SocketTimeoutException;

public class ArrivalQuayServer {

    private static final int portNumb = 33003;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ArrivalQuay arrivalQuay ;
        ServerCom scon, sconi;
        ArrivalQuayProxy arrivalQuayProxy;

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
                arrivalQuayProxy = new ArrivalQuayProxy(sconi, arrivalQuay);
                arrivalQuayProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
