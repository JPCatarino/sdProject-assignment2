package entities;

import states.PassengerStates;

public interface PassengerInterface {

    /**
     * Getter for the passenger ID.
     *
     * @return Passenger ID
     */
    int getID();

    /**
     * Increments the number of bags collected by the passenger.
     */
    void collectedABag();

    /**
     * Getter for nBagsToCollect.
     *
     * @return the number of bags the passenger has to collect.
     */
    int getnBagsToCollect();

    /**
     * Getter for nBagsCollected.
     *
     * @return the number of bags the passenger has collected.
     */
    int getnBagsCollected();
    /**
     * Checks if the passenger has no more flights.
     *
     * @return true if the passenger is going home.
     */
    boolean isJourneyEnding();

    /**
     * Set Passenger state.
     *
     * @param state new state of the Passenger.
     */
    void setPassengerState(PassengerStates state);

    /**
     * Setter for passenger bus seat.
     * @param busSeat Number of the bus seat passenger is using.
     */
    void setBusSeat(int busSeat);

    /**
     * Getter for passenger bus seat.
     * @return Number of the seat the passenger is using.
     */
    int getBusSeat();

    /**
     * Setter for the number of bags collected.
     * @param nBagsCollected Number of bags collected.
     */
    void setnBagsCollected(int nBagsCollected);
}
