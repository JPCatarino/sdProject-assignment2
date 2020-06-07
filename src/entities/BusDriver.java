package entities;

import stubs.ArrivalQuayStub;
import stubs.DepartureQuayStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Bus Driver thread and life cycle.
 * It stores all relevant information about the BusDriver.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class BusDriver extends Thread {

    /**
     * List of bus seats.
     *
     * @serialField busSeats
     */
    private List<Integer> busSeats;

    /**
     * The time that the bus driver wait until do the journey.
     *
     * @serialField TTL
     */
    private final int TTL;

    /**
     * ArrivalQuay Shared Memory.
     *
     * @serialField aq
     */
    private final ArrivalQuayStub aq;

    /**
     * DepartureQuay Shared Memory.
     *
     * @serialField dq
     */
    private final DepartureQuayStub dq;

    /**
     * BusDriver Constructor.
     * It initiates a new BusDriver thread.
     *
     * @param TTL Time the bus driver waits before leaving the Arrival Quay.
     * @param aq  Arrival Quay Shared Region.
     * @param dq  Departure Quay Shared Region.
     */
    public BusDriver(int TTL, ArrivalQuayStub aq, DepartureQuayStub dq) {
        this.busSeats = new ArrayList<>();
        this.TTL = TTL;
        this.aq = aq;
        this.dq = dq;
    }

    /**
     * BusDriver lifecycle
     * <p>
     * While he is on is shift, the BusDriver follows a routine: <p>
     * First he announces the bus is available to board.<p>
     * When it's time to leave, he drives to the departure terminal.<p>
     * After parking he let's the passengers off and drives back to the Arrival Terminal.<p>
     * If his shift hasn't ended by then, he repeats.
     */
    @Override
    public void run(){
        while(!aq.hasDaysWorkEnded()){
            aq.announcingBusBoarding();
            aq.goToDepartureTerminal();
            dq.parkTheBusAndLetPassOff();
            dq.goToArrivalTerminal();
            aq.parkTheBus();
        }
    }

    /**
     * Get Bus Seats.
     *
     * @return The list of bus seats.
     */
    public List<Integer> getBusSeats() {
        return busSeats;
    }

    /**
     * Set Bus Seats.
     *
     * @param busSeats New list of bus seats.
     */
    public void setBusSeats(List<Integer> busSeats){
        this.busSeats = busSeats;
    }

    /**
     * Get time to leave.
     *
     * @return The time that the bus driver wait until do the journey.
     */
    public int getTTL() {
        return TTL;
    }
}
