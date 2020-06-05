package main;

import common.ServerCom;
import proxies.ArrivalTerminalExitProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalTerminalExit;

import java.net.SocketTimeoutException;

public class ArrivalTerminalExitServer {

    private static final int portNumb = 33004;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        ArrivalTerminalExitProxy arrivalTerminalExitProxy;
        ArrivalTerminalExit arrivalTerminalExit ;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalTerminalExit = new ArrivalTerminalExit();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                arrivalTerminalExitProxy = new ArrivalTerminalExitProxy(arrivalTerminalExit);
                serviceProviderProxy= new ServiceProviderProxy( arrivalTerminalExitProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
