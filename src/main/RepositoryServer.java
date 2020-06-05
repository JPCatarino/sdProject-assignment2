package main;

import common.ServerCom;
import proxies.RepositoryProxy;
import proxies.ServiceProviderProxy;
import sharedRegions.Repository;

import java.net.SocketTimeoutException;

public class RepositoryServer {

    private static final int portNumb = 33001;
    public static boolean waitConnection;

    public static void main (String [] args)
    {
        ServiceProviderProxy serviceProviderProxy;
        RepositoryProxy repositoryProxy;
        Repository repository;
        ServerCom scon, sconi;

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
                repositoryProxy = new RepositoryProxy(repository);
                serviceProviderProxy= new ServiceProviderProxy( repositoryProxy,sconi);
                serviceProviderProxy.start ();
            }
            catch (SocketTimeoutException e)
            {
            }
        scon.end ();
        System.out.println ("O servidor foi desactivado.");
    }
}
