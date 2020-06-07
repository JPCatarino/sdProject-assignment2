package main;

import entities.BusDriver;
import stubs.*;

public class ClientBusDriver {

    public static void main(String[] args) {

        // Global variables.
        int T_seats = 3;

        // Server global variables.
        String baseServerHostName = "localhost";                // name from the machine where the server is
        int baseServerPortNumb=33000;                           // server port number

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

        // Initiate Bus Driver
        BusDriver busDriver = new BusDriver(100, arrivalQuay, departureQuay);

        //Communicate parameters
        arrivalQuay.probPar(T_seats);

        // Join BusDriver
        busDriver.start();

        try {
            busDriver.join();
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
