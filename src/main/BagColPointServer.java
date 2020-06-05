package main;

import common.ServerCom;
import proxies.BagColPointProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.BagColPoint;

import java.net.SocketTimeoutException;

public class BagColPointServer {

    private static final int portNumb = 33005;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        BagColPointProxy bagColPointProxy;
        BagColPoint bagColPoint ;
        ServerCom scon, sconi;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        bagColPoint = new BagColPoint();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                bagColPointProxy = new BagColPointProxy(bagColPoint);
                serviceProviderProxy= new ServiceProviderProxy( bagColPointProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }

}
