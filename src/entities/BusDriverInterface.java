package entities;

import java.util.List;

public interface BusDriverInterface {

    /**
     * Get Bus Seats.
     *
     * @return The list of bus seats.
     */
    List<Integer> getBusSeats();

    /**
     * Set Bus Seats.
     *
     * @param busSeats New list of bus seats.
     */
    void setBusSeats(List<Integer> busSeats);

    /**
     * Get time to leave.
     *
     * @return The time that the bus driver wait until do the journey.
     */
    int getTTL();
}
