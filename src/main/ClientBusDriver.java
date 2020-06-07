package main;

import entities.BusDriver;
import stubs.*;

public class ClientBusDriver {

    public static void main(String[] args) {

        // Global variables.
        int N_passengers = 6;
        int T_seats = 3;

        //
        String fName="Log.txt";                                 // logging file name
        String baseServerHostName = "localhost";                // name from the machine where the server is
        int baseServerPortNumb=33000;                           // server port number

        // Initiate Shared Regions
        ArrivalQuayStub arrivalQuay = new ArrivalQuayStub(baseServerHostName, baseServerPortNumb+3);
        DepartureQuayStub departureQuay = new DepartureQuayStub(baseServerHostName, baseServerPortNumb+7);

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
    }
}
