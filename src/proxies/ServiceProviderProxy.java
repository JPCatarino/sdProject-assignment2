package proxies;

import common.Message;
import common.ServerCom;
import entities.BusDriverInterface;
import entities.PassengerInterface;
import entities.PorterInterface;
import states.BusDriverStates;
import states.PassengerStates;

import java.util.List;

public class ServiceProviderProxy extends Thread implements BusDriverInterface, PassengerInterface, PorterInterface {

    private SharedRegionProxy sharedRegionProxy;

    private ServerCom serverCom;

    private int id;

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
    private int TTL;

    /**
     * Number of bags belonging to the passenger.
     *
     * @serialField nBagsToCollect
     */
    private int nBagsToCollect;

    /**
     * Number of bags collected by the passenger.
     *
     * @serialField nBagsCollected
     */
    private int nBagsCollected;

    private int busSeat;

    /**
     * True if the passenger has no subsequent flights.
     *
     * @serialField journeyEnding
     */
    private boolean journeyEnding;

    /**
     *  Bag being carried from the plane hold to the baggage collection point or to the temporary storage area.
     *
     *  @serialField bag
     */
    private int[] bag;

    /**
     *  Report if the plane hold is empty.
     *
     *  @serialField planeHoldEmpty
     */
    private boolean planeHoldEmpty;

    public ServiceProviderProxy(SharedRegionProxy sharedRegion, ServerCom serverCom) {
        this.sharedRegionProxy= sharedRegion;
        this.serverCom = serverCom;
    }

    @Override
    public void run() {
        Message msg = (Message) serverCom.readObject();
        msg = sharedRegionProxy.processAndReply(msg);
        serverCom.writeObject(msg);
        serverCom.close();
    }

    /**
     * Get Bus Seats.
     *
     * @return The list of bus seats.
     */
    @Override
    public List<Integer> getBusSeats() {
        return busSeats;
    }

    /**
     * Set Bus Seats.
     *
     * @param busSeats New list of bus seats.
     */
    @Override
    public void setBusSeats(List<Integer> busSeats) {
        this.busSeats = busSeats;
    }

    /**
     * Set Bus Driver state.
     *
     * @param state New state of the Bus Driver.
     */
    @Override
    public void setBusDriverState(BusDriverStates state) {

    }

    /**
     * Get time to leave.
     *
     * @return The time that the bus driver wait until do the journey.
     */
    @Override
    public int getTTL() {
        return TTL;
    }

    /**
     * Getter for the passenger ID.
     *
     * @return Passenger ID
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Increments the number of bags collected by the passenger.
     */
    @Override
    public void collectedABag() {
        this.nBagsCollected++;
    }

    /**
     * Getter for nBagsToCollect.
     *
     * @return the number of bags the passenger has to collect.
     */
    @Override
    public int getnBagsToCollect() {
        return nBagsToCollect;
    }

    /**
     * Getter for nBagsCollected.
     *
     * @return the number of bags the passenger has collected.
     */
    @Override
    public int getnBagsCollected() {
        return nBagsCollected;
    }

    /**
     * Checks if the passenger has no more flights.
     *
     * @return true if the passenger is going home.
     */
    @Override
    public boolean isJourneyEnding() {
        return journeyEnding;
    }

    /**
     * Set Passenger state.
     *
     * @param state new state of the Passenger.
     */
    @Override
    public void setPassengerState(PassengerStates state) {

    }

    /**
     * Setter for passenger bus seat.
     *
     * @param busSeat Number of the bus seat passenger is using.
     */
    @Override
    public void setBusSeat(int busSeat) {
        this.busSeat = busSeat;
    }

    /**
     * Getter for passenger bus seat.
     *
     * @return Number of the seat the passenger is using.
     */
    @Override
    public int getBusSeat() {
        return busSeat;
    }

    /**
     * Verify if there are bags in the plane hold.
     *
     * @return True, if the Plane Hold is empty. False, if the Plane Hold is not empty.
     */
    @Override
    public boolean isPlaneHoldEmpty() {
        return planeHoldEmpty;
    }

    /**
     * Set the value of the plane hold.
     *
     * @param planeHoldEmpty True, if the Plane Hold is empty or to false, if the Plane Hold is not empty.
     */
    @Override
    public void setPlaneHoldEmpty(boolean planeHoldEmpty) {
        this.planeHoldEmpty = planeHoldEmpty;
    }

    public void setPassengerId(int id){
        this.id = id;
    }
}
