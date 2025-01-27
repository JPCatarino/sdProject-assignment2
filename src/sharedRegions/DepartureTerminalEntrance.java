package sharedRegions;

import entities.Passenger;
import entities.PassengerInterface;
import interfaces.DTEPassenger;
import proxies.ServiceProviderProxy;
import states.PassengerStates;
import stubs.ArrivalLoungeStub;
import stubs.ArrivalTerminalExitStub;
import stubs.RepositoryStub;

/**
 * Implementation of the Departure Terminal Entrance Shared Memory
 * The Passengers arrive here and wait other passengers to
 * reach final state so they can progress.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class DepartureTerminalEntrance implements DTEPassenger {

    /**
     * Information Repository.
     *
     * @serialField repo
     */
    private RepositoryStub repo;

    /**
     * ArrivalLounge Shared Memory.
     *
     * @serialField al
     */
    private ArrivalLoungeStub al;

    /**
     * True if all the passengers have arrived to the exit zones.
     *
     * @serialField allPassengersFinished
     */
    private boolean allPassengersFinished;

    /**
     * ArrivalTerminalExit Shared Memory.
     *
     * @serialField ate
     */
    private ArrivalTerminalExitStub ate;

    /**
     * Number of passengers currently waiting on this terminal.
     * @serialField passengersATE
     */
    private int passengersDTE;

    /**
     * Maximum number of passengers on this flight.
     * @serialField maxNumberOfPassengers
     */
    private int maxNumberOfPassengers;

    /**
     * DepartureTerminalEntrance Shared Memory.
     *
     * @param repo General Information Repository.
     * @param al Arrival Lounge for the latest information on flights.
     * @param ate Arrival Terminal for synchronization.
     */
    public DepartureTerminalEntrance(RepositoryStub repo, ArrivalLoungeStub al, ArrivalTerminalExitStub ate){
        this.repo = repo;
        this.al = al;
        this.allPassengersFinished = false;
        this.ate = ate;
        this.passengersDTE = 0;
    }

    @Override
    public synchronized void prepareNextLeg() {
        PassengerInterface p = (ServiceProviderProxy) Thread.currentThread();
        p.setPassengerState(PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL);
        repo.setST(p.getID(), PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL.getState());

        passengersDTE++;
        this.allPassengersFinished = ((ate.getPassengersATE() + passengersDTE) == maxNumberOfPassengers);
        if(allPassengersFinished) {
            al.setFinishedFlight(true);
        }

        if(this.allPassengersFinished){
            notifyAll();
            ate.setAllPassengersFinished(this.allPassengersFinished);
        }
        else {
            try {
                while (!allPassengersFinished) wait();
            } catch (InterruptedException ex) {
                System.out.println("prepareNextLeg - Interrupted Thread");
            }
        }
        this.passengersDTE--;
        if (passengersDTE == 0){
            this.allPassengersFinished = false;
        }
    }

    /**
     * Setter to all passenger finished.
     * If all passenger are finished notifies all threads.
     *
     * @param allPassengersFinished True if all the passengers have arrived to the exit zones.
     */
    public synchronized void setAllPassengersFinished(boolean allPassengersFinished) {
        this.allPassengersFinished = allPassengersFinished;
        if(this.allPassengersFinished){
            notifyAll();
        }
    }

    /**
     * Getter for number of passengers on this terminal.
     * Used by ATE to check the number of passengers here.
     * @return number of passengers on this terminal.
     */
    public int getPassengersDTE() {
        return passengersDTE;
    }

    /**
     * Setter for the total number of passengers in the flights.
     *
     * @param maxNumberOfPassengers set the number of passengers in the flights.
     */
    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }
}
