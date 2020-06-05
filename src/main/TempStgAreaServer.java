package main;

import common.ServerCom;
import proxies.TempStgAreaProxy;
import sharedRegions.TempStgArea;

import java.net.SocketTimeoutException;

public class TempStgAreaServer {

    private static final int portNumb = 33009;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        TempStgArea tempStgArea;
        ServerCom scon, sconi;
        TempStgAreaProxy tempStgAreaProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        tempStgArea = new TempStgArea();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                tempStgAreaProxy = new TempStgAreaProxy(sconi, tempStgArea);
                tempStgAreaProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
