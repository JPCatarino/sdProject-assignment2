package main;

import common.ServerCom;
import proxies.DepartureTerminalEntranceProxy;
import sharedRegions.DepartureTerminalEntrance;

import java.net.SocketTimeoutException;

public class DepartureTerminalEntranceServer {

    private static final int portNumb = 33008;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        DepartureTerminalEntrance departureTerminalEntrance;
        ServerCom scon, sconi;
        DepartureTerminalEntranceProxy departureTerminalEntranceProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        departureTerminalEntrance = new DepartureTerminalEntrance();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                departureTerminalEntranceProxy = new DepartureTerminalEntranceProxy(sconi, departureTerminalEntrance);
                departureTerminalEntranceProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
