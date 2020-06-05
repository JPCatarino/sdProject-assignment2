package proxies;

import common.Message;
import common.MessageType;
import common.ServerCom;
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
        Message nm = new Message();

        switch(msg.getMessageType()) {
            case SETFN:
                repository.setFN(msg.getIntValue1());
                break;
            case SETBN:
                repository.setBN(msg.getIntValue1());
                break;
            case SETCB:
                repository.setCB(msg.getIntValue1());
                break;
            case SETSR:
                repository.setSR(msg.getIntValue1());
                break;
            case SETPSTAT:
                repository.setP_Stat(msg.getStringValue1());
                break;
            case SETDSTAT:
                repository.setD_Stat(msg.getStringValue1());
                break;
            case SETQIN:
                repository.setQIn(msg.getIntValue1(), msg.getStringValue1());
                break;
            case SETQOUT:
                repository.setQOut();
                break;
            case SETS:
                repository.setS(msg.getIntValue1(), msg.getStringValue1());
                break;
            case SETST:
                repository.setST(msg.getIntValue1(), msg.getStringValue1());
                break;
            case SETSI:
                repository.setSI(msg.getIntValue1(), msg.getStringValue1());
                break;
            case SETNR:
                repository.setNR(msg.getIntValue1(), msg.getIntValue2());
                break;
            case SETNA:
                repository.setNA(msg.getIntValue1(), msg.getIntValue2());
                break;
            case ADDBAGSLOST:
                repository.addBagsLost(msg.getIntValue1());
                break;
            case RESETPASSENGER:
                repository.reset_Passenger(msg.getIntValue1());
                break;
            case HEADERDEBUG:
                nm.setStringValue1(repository.header_debug());
                break;
            case TOSTRINGDEBUG:
                nm.setStringValue1(repository.toString_debug());
                break;
            case HEADERREQ:
                nm.setStringValue1(repository.header_requested());
                break;
            case TOSTRINGREQ:
                nm.setStringValue1(repository.toString());
                break;
            case REPORTINITIALSTATUS:
                repository.reportInitialStatus();
                break;
            case REPORTSTATUS:
                repository.reportStatus();
                break;
            case FINALREPORT:
                repository.finalReport();
                break;
            case SETNFIC:
                repository.setN_PASSENGERS(msg.getN_passengers());
                repository.setT_seats(msg.getT_seats());
                nm.setMessageType(MessageType.NFICDONE);
                break;
        }
        return nm;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
