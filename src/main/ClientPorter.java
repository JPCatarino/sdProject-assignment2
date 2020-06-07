package main;

import entities.Porter;
import stubs.*;

public class ClientPorter {

    public static void main(String[] args) {

        // Global variables.
        int K_landings = 5;
        int N_passengers = 6;

        // Server global variables.
        String baseServerHostName = "localhost";                    // name from the machine where the server is
        int baseServerPortNumb=33000;                               // server port number

        // Initiate Shared Regions
        RepositoryStub repository = new RepositoryStub(baseServerHostName, baseServerPortNumb+1);
        ArrivalLoungeStub arrivalLounge = new ArrivalLoungeStub(baseServerHostName, baseServerPortNumb+2);
        ArrivalQuayStub arrivalQuay = new ArrivalQuayStub(baseServerHostName, baseServerPortNumb+3);
        ArrivalTerminalExitStub arrivalTerminalExit = new ArrivalTerminalExitStub(baseServerHostName, baseServerPortNumb+4);
        BagColPointStub bagColPoint = new BagColPointStub(baseServerHostName, baseServerPortNumb+5);
        BagRecOfficeStub bagRecOffice = new BagRecOfficeStub(baseServerHostName, baseServerPortNumb+6);
        DepartureQuayStub departureQuay = new DepartureQuayStub(baseServerHostName, baseServerPortNumb+7);
        DepartureTerminalEntranceStub departureTerminalEntrance = new DepartureTerminalEntranceStub(baseServerHostName, baseServerPortNumb+8);
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

        //Shutdown
        arrivalLounge.shutdown(1);
        arrivalQuay.shutdown(1);
        arrivalTerminalExit.shutdown(1);
        bagColPoint.shutdown(1);
        bagRecOffice.shutdown(1);
        departureQuay.shutdown(1);
        departureTerminalEntrance.shutdown(1);
        repository.shutdown(1);
        tempStgArea.shutdown(1);
    }
}
