package proxies;

import common.Message;
import sharedRegions.ArrivalLounge;

public class ArrivalLoungeProxy implements SharedRegionProxy {

    private final ArrivalLounge arrivalLounge;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    public ArrivalLoungeProxy(ArrivalLounge arrivalLounge) {
        this.arrivalLounge = arrivalLounge;
        this.simFinished = false;
    }

    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch (msg.getMessageType()){
            case TAKEABUS:
                arrivalLounge.takeABus();
                break;
            case WHATSHOULDIDO:
                arrivalLounge.whatShouldIDo();
                break;
            case TAKEAREST:
                arrivalLounge.takeARest();
                break;
            case TRYTOCOLLECTABAG:
                arrivalLounge.tryToCollectABag();
                break;
            case NOMOREBAGSTOCOLLECT:
                arrivalLounge.noMoreBagsToCollect();
                break;
            case SETPLAINBAGS:
                arrivalLounge.setPlainBags(msg.getBagList1());
                break;
            case SETFLIGHTNUMBER:
                arrivalLounge.setFlightNumber(msg.getIntValue1());
                break;
            case ISDAYFINISHED:
                nm.setBooleanValue1(arrivalLounge.isDayFinished());
                break;
            case SETFINISHEDFLIGHT:
                arrivalLounge.setFinishedFlight(msg.getBooleanValue1());
                break;
            case ISPWAKE:
                nm.setBooleanValue1(arrivalLounge.ispWake());
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
