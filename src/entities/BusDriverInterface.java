package entities;

import states.BusDriverStates;

import java.util.List;

public interface BusDriverInterface {

    /**
     * Get Bus Seats.
     *
     * @return The list of bus seats.
     */
    public List<Integer> getBusSeats();

    /**
     * Set Bus Seats.
     *
     * @param busSeats New list of bus seats.
     */
    public void setBusSeats(List<Integer> busSeats);

    /**
     * Set Bus Driver state.
     *
     * @param state New state of the Bus Driver.
     */
    public void setBusDriverState(BusDriverStates state);

    /**
     * Get time to leave.
     *
     * @return The time that the bus driver wait until do the journey.
     */
    public int getTTL();
}
