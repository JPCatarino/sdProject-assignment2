package sharedRegions;

import entities.PassengerInterface;
import entities.PorterInterface;
import interfaces.ALPassenger;
import interfaces.ALPorter;
import proxies.ServiceProviderProxy;
import states.PassengerDecisions;
import states.PassengerStates;
import states.PorterStates;
import stubs.RepositoryStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Arrival Lounge Shared Memory
 * The passengers arrive here and get the whole airport moving.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class ArrivalLounge implements ALPassenger, ALPorter {

    /**
     * Repository of Information.
     *
     * @serialField repo
     */
    private final RepositoryStub repo;

    /**
     * List of passengers bags for this flight.
     *
     * @serialField plainBags
     */
    private List<int[]> plainBags;

    /**
     * Condition Variable that lets the porter wake up.
     *
     * @serialField pWake
     */
    private boolean pWake;

    /**
     * Number of Passengers in the arrival lounge.
     *
     * @serialField numberOfPassengers
     */
    private int numberOfPassengers;

    /**
     * Max Number of Passengers per flight.
     *
     * @serialField maxNumberOfPassengers
     */
    private int maxNumberOfPassengers;

    /**
     * Number of flights that are arriving.
     *
     * @serialField maxNumberOfFlights
     */
    private int maxNumberOfFlights;

    /**
     * Number of the flight that just landed.
     *
     * @serialField flightNumber
     */
    private int flightNumber;

    /**
     * Number of Passengers of this flight that have finished their lifecycle.
     *
     * @serialField finishedPassengers
     */
    private int finishedPassengers;

    /**
     * True if all the current flight passengers have left the airport.
     *
     * @serialField finishedFlight
     */
    private boolean finishedFlight;

    /**
     * Arrival Lounge Constructor.
     *
     * @param repo General Repository of Information
     **/
    public ArrivalLounge(RepositoryStub repo){
        this.repo = repo;
        this.plainBags = new ArrayList<>();
    }

    @Override
    public synchronized void takeABus(){
        PassengerInterface p = (ServiceProviderProxy) Thread.currentThread();
        p.setPassengerState(PassengerStates.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
        repo.setST(p.getID(), PassengerStates.AT_THE_ARRIVAL_TRANSFER_TERMINAL.getState());
        repo.reportStatus();
    }

    @Override
    public synchronized boolean takeARest() {
        PorterInterface pt = (ServiceProviderProxy) Thread.currentThread();

        try {
            while (!pWake) {
                pt.setPlaneHoldEmpty(false);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return flightNumber != maxNumberOfFlights || !pt.isPlaneHoldEmpty();
    }

    @Override
    public synchronized int[] tryToCollectABag() {
        int[] bag = null;

        repo.setP_Stat(PorterStates.AT_THE_PLANES_HOLD.getState());

        if (!plainBags.isEmpty()) {
            bag = plainBags.remove(0);
            repo.setBN(plainBags.size());
        }

        repo.reportStatus();

        return bag;
    }

    @Override
    public synchronized void noMoreBagsToCollect() {

        repo.setP_Stat(PorterStates.WAITING_FOR_A_PLANE_TO_LAND.getState());
        repo.reportStatus();
        notifyAll();
        if(flightNumber != maxNumberOfFlights)
            pWake = false;

    }

    @Override
    public synchronized PassengerDecisions whatShouldIDo(){
        PassengerInterface p = (ServiceProviderProxy) Thread.currentThread();
        p.setPassengerState(PassengerStates.AT_THE_DISEMBARKING_ZONE);
        repo.setST(p.getID(), PassengerStates.AT_THE_DISEMBARKING_ZONE.getState());
        repo.setNR(p.getID(),p.getnBagsToCollect());
        // Increment number of passengers on the ArrivalLounge
        numberOfPassengers++;

        // Wake up Porter
        if(numberOfPassengers == maxNumberOfPassengers){
            pWake = true;
            numberOfPassengers = 0;
            notifyAll();
        }

        // If journey is ending, passenger should either collect bags or go home
        // otherwise, he takes a bus
        if(p.isJourneyEnding()){
            repo.setSI(p.getID(), PassengerStates.FINAL_DESTINATION.getState());
            repo.reportStatus();
            return p.getnBagsToCollect() != 0 ? PassengerDecisions.COLLECT_A_BAG : PassengerDecisions.GO_HOME;
        }
        repo.setSI(p.getID(), PassengerStates.IN_TRANSIT.getState());
        repo.reportStatus();
        return PassengerDecisions.TAKE_A_BUS;

    }

    /**
     * Setter for PlainBags.
     *
     * @param plainBags List of bags for the flight
     */
    public synchronized void setPlainBags(List<int[]> plainBags) {
        this.plainBags = plainBags;
    }

    /**
     * Setter for the FlightNumber
     *
     * @param flightNumber Current flight number.
     */
    public synchronized void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
        this.finishedFlight = false;
    }

    /**
     * Setter for the total number of passengers in the flight.
     *
     * @param maxNumberOfPassengers Number of passengers in the flight.
     */
    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    /**
     * Setter for the total number of flights in the simulation.
     *
     * @param maxNumberOfFlights Total number of flights in the simulation.
     */
    public void setMaxNumberOfFlights(int maxNumberOfFlights) {
        this.maxNumberOfFlights = maxNumberOfFlights;
    }

    /**
     * Checks if all the flights of the day have finished.
     * This is given by comparing the current flight to the total number of flights
     * and checking if it has finished.
     *
     * @return true if the flights have finished
     */
    public boolean isDayFinished() {
        return (this.maxNumberOfFlights == this.flightNumber) && this.finishedFlight;
    }

    /**
     * Getter for the maximum number of passengers per flight.
     *
     * @return maximum number of passengers per flight
     */
    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    /**
     * Setter for finishedFlight, true if every passenger has finished.
     *
     * @param finishedFlight True if every passenger has finished.
     */
    public void setFinishedFlight(boolean finishedFlight) {
        this.finishedFlight = finishedFlight;
    }

    /**
     * Checks if porter has finished is cycle and is asleep
     *
     * @return true if porter is still active.
     */
    public boolean ispWake() {
        return pWake;
    }
}
