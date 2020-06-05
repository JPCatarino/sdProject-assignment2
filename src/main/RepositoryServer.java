package main;

import common.ServerCom;
import proxies.RepositoryProxy;
import sharedRegions.Repository;

import java.net.SocketTimeoutException;

public class RepositoryServer {

    private static final int portNumb = 33001;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        Repository repository;
        ServerCom scon, sconi;
        RepositoryProxy repositoryProxy;

        // Start service

        scon = new ServerCom(portNumb);
        scon.start ();
        repository = new Repository();
        System.out.println ("O serviço foi estabelecido!");
        System.out.println ("O servidor esta em escuta.");

        // Process requests

        waitConnection = true;
        while (waitConnection)
            try
            { sconi = scon.accept ();
                repositoryProxy = new RepositoryProxy(sconi, repository);
                repositoryProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
