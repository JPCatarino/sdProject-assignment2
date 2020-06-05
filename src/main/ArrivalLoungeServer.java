package main;

import common.ServerCom;
import proxies.ArrivalLoungeProxy;
import sharedRegions.ArrivalLounge;

import java.net.SocketTimeoutException;

public class ArrivalLoungeServer {

    private static final int portNumb = 33002;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ArrivalLounge arrivalLounge ;
        ServerCom scon, sconi;
        ArrivalLoungeProxy arrivalLoungeProxy;

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
                arrivalLoungeProxy = new ArrivalLoungeProxy(sconi, arrivalLounge);
                arrivalLoungeProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
