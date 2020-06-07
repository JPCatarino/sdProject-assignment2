package main;

import entities.Porter;
import stubs.*;

public class ClientPorter {

    public static void main(String[] args) {

        // Global variables.
        int K_landings = 5;
        int N_passengers = 6;
        int T_seats = 3;

        //
        String fName="Log.txt";                                     // logging file name
        String baseServerHostName = "localhost";                    // name from the machine where the server is
        int baseServerPortNumb=33000;                               // server port number

        // Initiate Shared Regions
        ArrivalLoungeStub arrivalLounge = new ArrivalLoungeStub(baseServerHostName, baseServerPortNumb+2);
        BagColPointStub bagColPoint = new BagColPointStub(baseServerHostName, baseServerPortNumb+5);
        TempStgAreaStub tempStgArea = new TempStgAreaStub(baseServerHostName, baseServerPortNumb+9);

        // Initiate Porter
        Porter porter = new Porter(arrivalLounge, bagColPoint, tempStgArea);

        //Communicate parameters
        arrivalLounge.probPar(N_passengers,K_landings);

        // Join Porter
        porter.start();

        try {
            porter.join();
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
