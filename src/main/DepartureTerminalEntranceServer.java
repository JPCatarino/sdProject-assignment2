package main;

import common.ServerCom;
import proxies.DepartureTerminalEntranceProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.DepartureTerminalEntrance;
import stubs.ArrivalLoungeStub;
import stubs.ArrivalTerminalExitStub;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class DepartureTerminalEntranceServer {

    /**
     * Listening port number of the service provided
     *
     *  @serialField portNumb
     */
    private static final int portNumb = 33008;
    public static int waitConnection;

    /**
     *  Main program.
     */
    public static void main (String [] args){

        ServiceProviderProxy serviceProviderProxy;
        DepartureTerminalEntranceProxy departureTerminalEntranceProxy;
        DepartureTerminalEntrance departureTerminalEntrance;
        ServerCom scon, sconi;

        // Start service
        scon = new ServerCom(portNumb);
        scon.start ();
        departureTerminalEntrance = new DepartureTerminalEntrance(new RepositoryStub("localhost", 33001),new ArrivalLoungeStub("localhost", 33002), new ArrivalTerminalExitStub("localhost", 33004));
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests
        waitConnection = 0;
        while (waitConnection!=3)
            try
            { sconi = scon.accept ();
                departureTerminalEntranceProxy = new DepartureTerminalEntranceProxy(departureTerminalEntrance);
                serviceProviderProxy= new ServiceProviderProxy( departureTerminalEntranceProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException ignored) {}
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
