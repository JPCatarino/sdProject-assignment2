package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
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
                serviceProviderProxy.setId(msg.getEntityID());
                arrivalLounge.takeABus();
                nm.setMessageType(MessageType.TAKEABUS);
                break;
            case WHATSHOULDIDO:
                serviceProviderProxy.setId(msg.getEntityID());
                serviceProviderProxy.setJourneyEnding(msg.getBooleanValue1());
                serviceProviderProxy.setnBagsToCollect(msg.getIntValue1());
                nm.setPassengerDecisions1(arrivalLounge.whatShouldIDo());
                nm.setMessageType(MessageType.WHATSHOULDIDO);
                break;
            case TAKEAREST:
                serviceProviderProxy.setPlaneHoldEmpty(msg.getBooleanValue1());
                nm.setBooleanValue1(arrivalLounge.takeARest());
                nm.setBooleanValue2(serviceProviderProxy.isPlaneHoldEmpty());
                nm.setMessageType(MessageType.TAKEAREST);
                break;
            case TRYTOCOLLECTABAG:
                nm.setBag1(arrivalLounge.tryToCollectABag());
                nm.setMessageType(MessageType.TRYTOCOLLECTABAG);
                break;
            case NOMOREBAGSTOCOLLECT:
                arrivalLounge.noMoreBagsToCollect();
                nm.setMessageType(MessageType.NOMOREBAGSTOCOLLECT);
                break;
            case SETPLAINBAGS:
                arrivalLounge.setPlainBags(msg.getBagList1());
                nm.setMessageType(MessageType.SETPLAINBAGS);
                break;
            case SETFLIGHTNUMBER:
                arrivalLounge.setFlightNumber(msg.getIntValue1());
                nm.setMessageType(MessageType.SETFLIGHTNUMBER);
                break;
            case ISDAYFINISHED:
                nm.setBooleanValue1(arrivalLounge.isDayFinished());
                nm.setMessageType(MessageType.ISDAYFINISHED);
                break;
            case SETFINISHEDFLIGHT:
                arrivalLounge.setFinishedFlight(msg.getBooleanValue1());
                nm.setMessageType(MessageType.SETFINISHEDFLIGHT);
                break;
            case ISPWAKE:
                nm.setBooleanValue1(arrivalLounge.ispWake());
                nm.setMessageType(MessageType.ISPWAKE);
                break;
            case SETNFIC:
                arrivalLounge.setMaxNumberOfFlights(msg.getK_landings());
                arrivalLounge.setMaxNumberOfPassengers(msg.getN_passengers());
                nm.setMessageType(MessageType.NFICDONE);
                break;
            case GETMAXNUMBEROFPASSENGERS:
                nm.setIntValue1(arrivalLounge.getMaxNumberOfPassengers());
                nm.setMessageType(MessageType.GETMAXNUMBEROFPASSENGERS);
                break;
        }

        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
