package proxies;

import common.Message;
import sharedRegions.Repository;

public class RepositoryProxy implements SharedRegionProxy {

    private final Repository repository;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public RepositoryProxy(Repository repository) {
        this.repository = repository;
        this.simFinished = false;
    }

    @Override
    public Message processAndReply(Message msg) {
        return null;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
