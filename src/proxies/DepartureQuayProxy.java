package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.DepartureQuay;

public class DepartureQuayProxy implements SharedRegionProxy {

    private final DepartureQuay departureQuay;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public DepartureQuayProxy(DepartureQuay departureQuay) {
        this.departureQuay = departureQuay;
        this.simFinished = false;
    }

    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case PARKTHEBUSANDLETPASSOFF:
                serviceProviderProxy.setBusSeats(msg.getIntList1());
                departureQuay.parkTheBusAndLetPassOff();
                break;
            case GOTOARRIVALTERMINAL:
                departureQuay.goToArrivalTerminal();
                break;
            case LEAVETHEBUS:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setBusSeat(msg.getIntValue1());
                departureQuay.leaveTheBus();
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
