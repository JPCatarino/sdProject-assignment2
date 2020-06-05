package main;

import common.ServerCom;
import proxies.BagRecOfficeProxy;
import sharedRegions.BagRecOffice;

import java.net.SocketTimeoutException;

public class BagRecOfficeServer {

    private static final int portNumb = 33006;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        BagRecOffice bagRecOffice ;
        ServerCom scon, sconi;
        BagRecOfficeProxy bagRecOfficeProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        bagRecOffice = new BagRecOffice();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                bagRecOfficeProxy = new BagRecOfficeProxy(sconi, bagRecOffice);
                bagRecOfficeProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
