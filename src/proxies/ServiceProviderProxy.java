package proxies;

import common.Message;
import common.ServerCom;
import entities.BusDriverInterface;
import entities.PassengerInterface;
import entities.PorterInterface;
import main.*;
import states.PassengerStates;

import java.util.List;

public class ServiceProviderProxy extends Thread implements BusDriverInterface, PassengerInterface, PorterInterface {

    /**
     * Shared Region Proxy
     *
     * @serialField sharedRegionProxy
     */
    private final SharedRegionProxy sharedRegionProxy;

    /**
     * Communication channel
     *
     * @serialField serverCom
     */
    private final ServerCom serverCom;

    /**
     * launched threads counter
     *
     *  @serialField nProxy
     */
    private static int nProxy = 0;

    /**
     * Passenger id.
     *
     * @serialField id
     */
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

    /**
     * Number of seats in the bus.
     *
     * @serialField busSeat
     */
    private int busSeat;

    /**
     * True if the passenger has no subsequent flights.
     *
     * @serialField journeyEnding
     */
    private boolean journeyEnding;

    /**
     * Bag being carried from the plane hold to the baggage collection point or to the temporary storage area.
     *
     * @serialField bag
     */
    private int[] bag;

    /**
     * Report if the plane hold is empty.
     *
     * @serialField planeHoldEmpty
     */
    private boolean planeHoldEmpty;

    public ServiceProviderProxy(SharedRegionProxy sharedRegion, ServerCom serverCom) {
        super("Proxy_" + getProxyId(sharedRegion));
        this.sharedRegionProxy = sharedRegion;
        this.serverCom = serverCom;
    }

    @Override
    public void run() {
        Message msg = (Message) serverCom.readObject();

        try {
            msg = sharedRegionProxy.processAndReply(msg);
        } catch (Exception e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

        serverCom.writeObject(msg);
        serverCom.close();
    }

    /**
     * Generate instantiation identifier.
     *
     * @return instantiation identifier
     */
    private static int getProxyId(SharedRegionProxy sharedRegion) {
        Class<?> cl = null;

        int proxyId;

        try {
            cl = Class.forName("proxies." + sharedRegion.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("The type of data" + sharedRegion.getClass().getSimpleName() + " was not found!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
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

    /**
     * Set the time to leave.
     *
     * @param TTL time to leave until the bus driver leaves.
     */
    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    /**
     * Set number of bags to collect by the passenger.
     *
     * @param nBagsToCollect Total number of bags to collect by the passenger.
     */
    public void setnBagsToCollect(int nBagsToCollect) {
        this.nBagsToCollect = nBagsToCollect;
    }

    /**
     * Set number of bags collected by the passenger.
     *
     * @param nBagsCollected Number of bags collected by the passenger.
     */
    public void setnBagsCollected(int nBagsCollected) {
        this.nBagsCollected = nBagsCollected;
    }

    /**
     * Set for the variable journeyEnding.
     *
     * @param journeyEnding True if the passenger has no subsequent flights.
     */
    public void setJourneyEnding(boolean journeyEnding) {
        this.journeyEnding = journeyEnding;
    }

    /**
     * Getter for variable baggage.
     *
     * @return One bag.
     */
    public int[] getBag() {
        return bag;
    }

    /**
     * Set the value of the plane hold.
     *
     * @param bag Bag being carried from the plane hold to the baggage collection point or to the temporary storage area.
     */
    public void setBag(int[] bag) {
        this.bag = bag;
    }

    /**
     * Set the passenger id.
     *
     * @param id passenger id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for communication channel.
     *
     * @return Communication channel.
     */
    public ServerCom getServerCom() {
        return serverCom;
    }

    /**
     * This function increments the number of shutdowns that each server received. When it reaches 3 (One by each client)
     * the server initiates its shutdown.
     *
     * @param value amount of shutdowns.
     * @param value2 server to shutdown.
     */
    public void shutdown(int value, int value2) {

        switch (value2) {
            case 0:
                ArrivalLoungeServer.waitConnection += value;
                if (ArrivalLoungeServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 1:
                ArrivalQuayServer.waitConnection += value;
                if (ArrivalQuayServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 2:
                ArrivalTerminalExitServer.waitConnection += value;
                if (ArrivalTerminalExitServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 3:
                BagColPointServer.waitConnection += value;
                if (BagColPointServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 4:
                BagRecOfficeServer.waitConnection += value;
                if (BagRecOfficeServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 5:
                DepartureQuayServer.waitConnection += value;
                if (DepartureQuayServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 6:
                DepartureTerminalEntranceServer.waitConnection += value;
                if (DepartureTerminalEntranceServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 7:
                RepositoryServer.waitConnection += value;
                if (RepositoryServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
            case 8:
                TempStgAreaServer.waitConnection += value;
                if (TempStgAreaServer.waitConnection == 3)
                    (((ServiceProviderProxy) (Thread.currentThread())).getServerCom()).setTimeout(1);
                break;
        }
    }
}
