package entities;

import states.PassengerStates;

public interface PassengerInterface {

    /**
     * Getter for the passenger ID.
     *
     * @return Passenger ID
     */
    public int getID();

    /**
     * Increments the number of bags collected by the passenger.
     */
    public void collectedABag();

    /**
     * Getter for nBagsToCollect.
     *
     * @return the number of bags the passenger has to collect.
     */
    public int getnBagsToCollect();

    /**
     * Getter for nBagsCollected.
     *
     * @return the number of bags the passenger has collected.
     */
    public int getnBagsCollected();
    /**
     * Checks if the passenger has no more flights.
     *
     * @return true if the passenger is going home.
     */
    public boolean isJourneyEnding();

    /**
     * Set Passenger state.
     *
     * @param state new state of the Passenger.
     */
    public void setPassengerState(PassengerStates state);

    /**
     * Setter for passenger bus seat.
     * @param busSeat Number of the bus seat passenger is using.
     */
    public void setBusSeat(int busSeat);

    /**
     * Getter for passenger bus seat.
     * @return Number of the seat the passenger is using.
     */
    public int getBusSeat();

    public void setnBagsCollected(int nBagsCollected);
}
