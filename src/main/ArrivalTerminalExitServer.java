package main;

import common.ServerCom;
import proxies.ArrivalTerminalExitProxy;
import sharedRegions.ArrivalTerminalExit;

import java.net.SocketTimeoutException;

public class ArrivalTerminalExitServer {

    private static final int portNumb = 33004;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ArrivalTerminalExit arrivalTerminalExit ;
        ServerCom scon, sconi;
        ArrivalTerminalExitProxy arrivalTerminalExitProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalTerminalExit = new ArrivalTerminalExitProxy();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                arrivalTerminalExitProxy = new ArrivalTerminalExitProxy(sconi, arrivalTerminalExit);
                arrivalTerminalExitProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
