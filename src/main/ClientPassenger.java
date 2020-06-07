package main;

import entities.BusDriver;
import entities.Passenger;
import entities.Porter;
import stubs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class for the Airport Rhapsody Problem.
 * It launches the threads for all entities of the problem.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class ClientPassenger {

    public static void main(String[] args) {

        // Global variables.
        int K_landings = 5;
        int N_passengers = 6;
        int M_luggage = 2;
        int T_seats = 3;

        //
        String fName="Log.txt";                                 // logging file name
        String baseServerHostName = "localhost";                // name from the machine where the server is
        int baseServerPortNumb=33000;                           // server port number

        // Constant that characterize the state of the passenger/piece of luggage.
        final int TRANSIT = 0,
                  FINAL_DESTINATION = 1;

        // Initiate data structures.
        int[][] statePassenger = new int[K_landings][N_passengers];
        int[][][] numBagsPassenger = new int[K_landings][N_passengers][2];
        List<int[]> plainBags = new ArrayList<>();

        // Generation of the number of the total of pieces of luggage, how many where lost and the state for all the passengers.
        for (int i=0;i<K_landings;i++){
            for (int j=0;j<N_passengers;j++) {
                statePassenger[i][j] = (Math.random() < 0.40) ? TRANSIT : FINAL_DESTINATION;
                numBagsPassenger[i][j][0] = new Random().nextInt(M_luggage + 1);
                if( numBagsPassenger[i][j][0] > 0){
                    if(Math.random() < 0.10)
                        numBagsPassenger[i][j][1] = new Random().nextInt(numBagsPassenger[i][j][0]);
                    else
                        numBagsPassenger[i][j][1] = numBagsPassenger[i][j][0];
                }
                else{
                    numBagsPassenger[i][j][1] = numBagsPassenger[i][j][0];
                }
            }
        }

        // Initiate Shared Regions
        RepositoryStub repository = new RepositoryStub(baseServerHostName, baseServerPortNumb+1);
        ArrivalLoungeStub arrivalLounge = new ArrivalLoungeStub(baseServerHostName, baseServerPortNumb+2);
        ArrivalQuayStub arrivalQuay = new ArrivalQuayStub(baseServerHostName, baseServerPortNumb+3);
        ArrivalTerminalExitStub arrivalTerminalExit = new ArrivalTerminalExitStub(baseServerHostName, baseServerPortNumb+4);
        BagColPointStub bagColPoint = new BagColPointStub(baseServerHostName, baseServerPortNumb+5);
        BagRecOfficeStub bagRecOffice = new BagRecOfficeStub(baseServerHostName, baseServerPortNumb+6);
        DepartureQuayStub departureQuay = new DepartureQuayStub(baseServerHostName, baseServerPortNumb+7);
        DepartureTerminalEntranceStub departureTerminalEntrance = new DepartureTerminalEntranceStub(baseServerHostName, baseServerPortNumb+8);

        // Initiate passengers (For each flight, initiate N passenger)
        Passenger[][] flights = new Passenger[K_landings][N_passengers];
        for(int i = 0; i < flights.length; i++) {
            for (int z = 0; z < flights[i].length; z++){
                flights[i][z] = new Passenger(z, numBagsPassenger[i][z][0], statePassenger[i][z] == 1, arrivalLounge, bagColPoint, bagRecOffice, arrivalQuay, departureQuay, departureTerminalEntrance, arrivalTerminalExit);
            }
        }

        //Communicate parameters
        repository.probPar(N_passengers, T_seats);
        arrivalLounge.probPar(N_passengers,K_landings);
        arrivalQuay.probPar(T_seats);
        arrivalTerminalExit.probPar(N_passengers);
        departureTerminalEntrance.probPar(N_passengers);


        // Join the different passengers for each flight.

        // We start the threads for a flight and wait till the flight as ended
        // before starting another batch of passengers.
        // After, we wait till the porter and bus driver finished and close the program successfully
        for(int i = 0; i < flights.length; i++){
            repository.setFN(i + 1);
            arrivalLounge.setFlightNumber(i+1);
            for (int j=0;j<N_passengers;j++) {
                for (int l=0;l<numBagsPassenger[i][j][1];l++){
                    int[] tmpArr = new int[2];
                    tmpArr[0] = j;
                    tmpArr[1] = statePassenger[i][j];
                    plainBags.add(tmpArr);
                }
                repository.reset_Passenger(j);
            }
            arrivalLounge.setPlainBags(plainBags);
            repository.setBN(plainBags.size());
            bagColPoint.resetBagColPoint();

            for(int z = 0; z < flights[i].length; z++){
                flights[i][z].start();
            }
            try{
                for(Passenger passenger: flights[i]){
                    passenger.join();
                }
            }
            catch(InterruptedException ex){
                System.out.println(ex.getMessage());
                System.exit(1);
            }
            // check if porter has collected all baggage
            while(arrivalLounge.ispWake() && i+1 != K_landings);
            plainBags.clear();
        }
        repository.finalReport();
    }
}
