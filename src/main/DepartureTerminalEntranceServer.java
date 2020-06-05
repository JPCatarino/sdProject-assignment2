package main;

import common.ServerCom;
import proxies.DepartureTerminalEntranceProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.DepartureTerminalEntrance;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class DepartureTerminalEntranceServer {

    private static final int portNumb = 33008;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        DepartureTerminalEntranceProxy departureTerminalEntranceProxy;
        DepartureTerminalEntrance departureTerminalEntrance;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        departureTerminalEntrance = new DepartureTerminalEntrance(new RepositoryStub("localhost", 33001));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                departureTerminalEntranceProxy = new DepartureTerminalEntranceProxy(departureTerminalEntrance);
                serviceProviderProxy= new ServiceProviderProxy( departureTerminalEntranceProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
