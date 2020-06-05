package sharedRegions;

import entities.Passenger;
import entities.PassengerInterface;
import interfaces.BROPassenger;
import proxies.ServiceProviderProxy;
import states.PassengerStates;

/**
 * Implementation of the Baggage Reclaim Office Shared Memory
 * The passenger comes here in case he loses any baggage, to
 * report the occurrence.
 *
 * @author Fábio Alves
 * @author Jorge Catarino
 */
public class BagRecOffice implements BROPassenger {

    /**
     * General Repository of Information.
     *
     * @serialField repo
     */
    private Repository repo;

    public BagRecOffice(){}

    /**
     * Constructor for BagRecOffice.
     *
     * @param repo General Repo Of Information.
     */
    public BagRecOffice(Repository repo){
        this.repo = repo;
    }

    @Override
    public synchronized void reportMissingBags(){
        PassengerInterface p = (ServiceProviderProxy) Thread.currentThread();
        p.setPassengerState(PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        repo.setST(p.getID(), PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE.getState());
        repo.addBagsLost(p.getnBagsToCollect() - p.getnBagsCollected());
        repo.reportStatus();
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException ex){
            System.err.println("reportMissingBags - Thread Interrupted");
        }
    }
}
