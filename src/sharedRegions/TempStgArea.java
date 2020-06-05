package sharedRegions;

import interfaces.TSAPorter;
import states.PorterStates;
import stubs.RepositoryStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Temporary Storage Area Shared Memory
 * The porter stores the transiting passengers baggage here.
 */
public class TempStgArea implements TSAPorter {

    /**
     * General Repo of Information.
     *
     * @serialField repo
     */
    private RepositoryStub repo;

    /**
     * Data structure simulating a storeroom, where the porter stores the baggage.
     *
     * @serialField storeroom
     */
    private List<int[]> storeroom;

    public TempStgArea(){
        this.storeroom = new ArrayList<>();
    }

    /**
     * Constructor method for TempStgArea.
     *
     * @param repo General Information Repository.
     */
    public TempStgArea(RepositoryStub repo){
        this.repo = repo;
        this.storeroom = new ArrayList<>();
    }

    @Override
    public synchronized void carryItToAppropriateStore(int [] bag) {

        this.storeroom.add(bag);

        repo.setP_Stat(PorterStates.AT_THE_STOREROOM.getState());
        repo.setSR(storeroom.size());
        repo.reportStatus();

        notifyAll();
    }
}
