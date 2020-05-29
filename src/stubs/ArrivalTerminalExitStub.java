package stubs;

import sharedRegions.DepartureTerminalEntrance;

public class ArrivalTerminalExitStub extends SharedRegionStub {

    public ArrivalTerminalExitStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public synchronized void goHome(){

    }

    public synchronized void setDte(DepartureTerminalEntrance dte){

    }

    public synchronized void setAllPassengersFinished(boolean allPassengersFinished){

    }

    public synchronized void getPassengersATE(){

    }
}
