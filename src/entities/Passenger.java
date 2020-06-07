package entities;

import stubs.*;

/**
 * Passenger thread and life cycle.
 * It stores all relevant information about the Passenger.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class Passenger extends Thread {

    /**
     * Passengers's id.
     *
     * @serialField id
     */
    private final int id;

    /**
     * Number of bags belonging to the passenger.
     *
     * @serialField nBagsToCollect
     */
    private final int nBagsToCollect;

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
    private final boolean journeyEnding;

    /**
     * Arrival Lounge Shared Memory.
     *
     * @serialField al
     */
    private final ArrivalLoungeStub al;

    /**
     * BagColPoint Shared Memory.
     *
     * @serialField bcp
     */
    private final BagColPointStub bcp;

    /**
     * BagRecOffice Shared Memory.
     *
     * @serialField bro
     */
    private final BagRecOfficeStub bro;

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
     * DepartureTerminalEntrance Shared Memory.
     *
     * @serialField dte
     */
    private final DepartureTerminalEntranceStub dte;

    /**
     * ArrivalTerminalExit Shared Memory.
     *
     * @serialField ate
     */
    private final ArrivalTerminalExitStub ate;

    /**
     * Passenger Constructor.
     * It initiates a passenger thread.
     *
     * @param id Passenger Identifier.
     * @param nBagsToCollect Number of Bags the Passenger has to collect.
     * @param journeyEnding  True if Passenger journey is ending.
     * @param al  Arrival Lounge Shared Region.
     * @param bcp Baggage Collection Point Shared Region.
     * @param bro Baggage Reclaim Office Shared Region.
     * @param aq  Arrival Quay Shared Region.
     * @param dq  Departure Quay Shared Region.
     * @param dte Departure Terminal Exit Shared Region.
     * @param ate Arrival Terminal Exit Shared Region.
     */
    public Passenger(int id, int nBagsToCollect, boolean journeyEnding, ArrivalLoungeStub al, BagColPointStub bcp, BagRecOfficeStub bro, ArrivalQuayStub aq, DepartureQuayStub dq, DepartureTerminalEntranceStub dte, ArrivalTerminalExitStub ate) {
        this.id = id;
        this.nBagsToCollect = nBagsToCollect;
        this.nBagsCollected = 0;
        this.journeyEnding = journeyEnding;
        this.al = al;
        this.bcp = bcp;
        this.bro = bro;
        this.aq = aq;
        this.dq = dq;
        this.dte = dte;
        this.ate = ate;
    }

    /**
     * Passenger Life cycle.
     * <p>
     * When the passenger arrives to arrival lounge, he ask himself what to do.
     * If his journey has ended and he has no bags, he'll go home.
     * If he has bags, he goes to collect them.
     * In case his journey hasn't ended, he goes to catch a bus.
     * </p>
     */
    @Override
    public void run(){
        switch(al.whatShouldIDo()){
            case GO_HOME:
                ate.goHome();
                break;
            case COLLECT_A_BAG:
                bcp.goCollectABag();
                if(nBagsCollected != nBagsToCollect)
                    bro.reportMissingBags();
                ate.goHome();
                break;
            case TAKE_A_BUS:
                al.takeABus();
                aq.enterTheBus();
                dq.leaveTheBus();
                dte.prepareNextLeg();
                break;
        }
    }

    /**
     * Getter for the passenger ID.
     *
     * @return Passenger ID
     */
    public int getID() {
        return id;
    }

    /**
     * Getter for nBagsToCollect.
     *
     * @return the number of bags the passenger has to collect.
     */
    public int getnBagsToCollect() {
        return nBagsToCollect;
    }

    /**
     * Getter for nBagsCollected.
     *
     * @return the number of bags the passenger has collected.
     */
    public int getnBagsCollected() {
        return nBagsCollected;
    }

    /**
     * Checks if the passenger has no more flights.
     *
     * @return true if the passenger is going home.
     */
    public boolean isJourneyEnding() {
        return journeyEnding;
    }

    /**
     * Setter for passenger bus seat.
     * @param busSeat Number of the bus seat passenger is using.
     */
    public void setBusSeat(int busSeat) {
        this.busSeat = busSeat;
    }

    /**
     * Getter for passenger bus seat.
     * @return Number of the seat the passenger is using.
     */
    public int getBusSeat() {
        return busSeat;
    }

    /**
     * Setter for the number of bags collected.
     * @param nBagsCollected Number of bags collected.
     */
    public void setnBagsCollected(int nBagsCollected) {
        this.nBagsCollected = nBagsCollected;
    }
}
