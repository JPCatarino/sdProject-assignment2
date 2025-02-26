package proxies;

import common.Message;
import common.MessageType;
import sharedRegions.Repository;

public class RepositoryProxy implements SharedRegionProxy {

    /**
     * Repository (represents the service to be provided).
     *
     * @serialField repository.
     */
    private final Repository repository;

    /**
     * RepositoryProxy Constructor.
     * It initiates the Repository.
     *
     * @param repository Repository.
     */
    public RepositoryProxy(Repository repository) {
        this.repository = repository;
    }

    /**
     * Process messages by executing corresponding task.
     * Generate answer message.
     *
     * @param msg message in the request.
     *
     * @return answer message.
     */
    @Override
    public Message processAndReply(Message msg) {
        Message nm = new Message();
        ServiceProviderProxy serviceProviderProxy = (ServiceProviderProxy) Thread.currentThread();

        switch(msg.getMessageType()) {
            case SETFN:
                repository.setFN(msg.getIntValue1());
                nm.setMessageType(MessageType.SETFN);
                break;
            case SETBN:
                repository.setBN(msg.getIntValue1());
                nm.setMessageType(MessageType.SETBN);
                break;
            case SETCB:
                repository.setCB(msg.getIntValue1());
                nm.setMessageType(MessageType.SETCB);
                break;
            case SETSR:
                repository.setSR(msg.getIntValue1());
                nm.setMessageType(MessageType.SETSR);
                break;
            case SETPSTAT:
                repository.setP_Stat(msg.getStringValue1());
                nm.setMessageType(MessageType.SETPSTAT);
                break;
            case SETDSTAT:
                repository.setD_Stat(msg.getStringValue1());
                nm.setMessageType(MessageType.SETDSTAT);
                break;
            case SETQIN:
                repository.setQIn(msg.getIntValue1(), msg.getStringValue1());
                nm.setMessageType(MessageType.SETQIN);
                break;
            case SETQOUT:
                repository.setQOut();
                nm.setMessageType(MessageType.SETQOUT);
                break;
            case SETS:
                repository.setS(msg.getIntValue1(), msg.getStringValue1());
                nm.setMessageType(MessageType.SETS);
                break;
            case SETST:
                repository.setST(msg.getIntValue1(), msg.getStringValue1());
                nm.setMessageType(MessageType.SETST);
                break;
            case SETSI:
                repository.setSI(msg.getIntValue1(), msg.getStringValue1());
                nm.setMessageType(MessageType.SETSI);
                break;
            case SETNR:
                repository.setNR(msg.getIntValue1(), msg.getIntValue2());
                nm.setMessageType(MessageType.SETNR);
                break;
            case SETNA:
                repository.setNA(msg.getIntValue1(), msg.getIntValue2());
                nm.setMessageType(MessageType.SETNA);
                break;
            case ADDBAGSLOST:
                repository.addBagsLost(msg.getIntValue1());
                nm.setMessageType(MessageType.ADDBAGSLOST);
                break;
            case RESETPASSENGER:
                repository.reset_Passenger(msg.getIntValue1());
                nm.setMessageType(MessageType.RESETPASSENGER);
                break;
            case HEADERDEBUG:
                nm.setStringValue1(repository.header_debug());
                nm.setMessageType(MessageType.HEADERDEBUG);
                break;
            case TOSTRINGDEBUG:
                nm.setStringValue1(repository.toString_debug());
                nm.setMessageType(MessageType.TOSTRINGDEBUG);
                break;
            case HEADERREQ:
                nm.setStringValue1(repository.header_requested());
                nm.setMessageType(MessageType.HEADERREQ);
                break;
            case TOSTRINGREQ:
                nm.setStringValue1(repository.toString());
                nm.setMessageType(MessageType.TOSTRINGREQ);
                break;
            case REPORTINITIALSTATUS:
                repository.reportInitialStatus();
                nm.setMessageType(MessageType.REPORTINITIALSTATUS);
                break;
            case REPORTSTATUS:
                repository.reportStatus();
                nm.setMessageType(MessageType.REPORTSTATUS);
                break;
            case FINALREPORT:
                repository.finalReport();
                nm.setMessageType(MessageType.FINALREPORT);
                break;
            case SETNFIC:
                repository.InitializeN_PASSENGERS(msg.getN_passengers());
                repository.InitializeT_seats(msg.getT_seats());
                nm.setMessageType(MessageType.NFICDONE);
                break;
            case SHUT:
                nm.setMessageType(MessageType.ACK);
                serviceProviderProxy.shutdown(msg.getIntValue1(),7);
                break;
        }
        return nm;
    }
}
