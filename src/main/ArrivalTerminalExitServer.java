package main;

import common.ServerCom;
import proxies.ArrivalTerminalExitProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.ArrivalTerminalExit;
import stubs.ArrivalLoungeStub;
import stubs.ArrivalTerminalExitStub;
import stubs.DepartureTerminalEntranceStub;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class ArrivalTerminalExitServer {

    private static final int portNumb = 33004;
    public static int waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        ArrivalTerminalExitProxy arrivalTerminalExitProxy;
        ArrivalTerminalExit arrivalTerminalExit ;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        arrivalTerminalExit = new ArrivalTerminalExit(new RepositoryStub("localhost", 33001),new ArrivalLoungeStub("localhost", 33002), new DepartureTerminalEntranceStub("localhost", 33008));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = 0;
        while (waitConnection!=3)
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
