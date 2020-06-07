package common;

import java.io.Serializable;

public enum MessageType implements Serializable {

    /**
     *  TAKE A REST
     */
    TAKEAREST,

    /**
     *  TRY TO COLLECT A BAG
     */
    TRYTOCOLLECTABAG,

    /**
     *  CARRY IT TO APPROPRIATE STORE BAGGAGE COLLECTION POINT
     */
    CARRYITTOAPPROPRIATESTOREBCP,

    /**
     *  CARRY IT TO APPROPRIATE STORE BAGGAGE TEMPORARY STORAGE AREA
     */
    CARRYITTOAPPROPRIATESTORETMP,

    /**
     *  NO MORE BAGS TO COLLECT
     */
    NOMOREBAGSTOCOLLECT,

    /**
     *  WHAT SHOULD I DO
     */
    WHATSHOULDIDO,

    /**
     *  GO COLLECT A BAG
     */
    GOCOLLECTABAG,

    /**
     *  REPORT MISSING BAGS
     */
    REPORTMISSINGBAGS,

    /**
     *  GO HOME
     */
    GOHOME,

    /**
     *  TAKE A BUS
     */
    TAKEABUS,

    /**
     *  ENTER THE BUS
     */
    ENTERTHEBUS,

    /**
     *  LEAVE THE BUS
     */
    LEAVETHEBUS,

    /**
     *  PREPARE NEXT LEG
     */
    PREPARENEXTLEG,

    /**
     *  HAS DAYS WORK ENDED
     */
    HASDAYSWORKENDED,

    /**
     *  ANNOUNCING BUS BOARDING
     */
    ANNOUNCINGBUSBOARDING,

    /**
     *  GO TO DEPARTURE TERMINAL
     */
    GOTODEPARTURETERMINAL,

    /**
     *  PARK THE BUS AND LET PASSENGERS OFF
     */
    PARKTHEBUSANDLETPASSOFF,

    /**
     *  GO TO ARRIVAL TERMINAL
     */
    GOTOARRIVALTERMINAL,

    /**
     *  PARK THE BUS
     */
    PARKTHEBUS,

    /**
     *  SET PLAIN BAGS
     */
    SETPLAINBAGS,

    /**
     *  SET FLIGHT NUMBER
     */
    SETFLIGHTNUMBER,

    /**
     *  IS DAY FINISHED
     */
    ISDAYFINISHED,

    /**
     *  SET FINISHED FLIGHT
     */
    SETFINISHEDFLIGHT,

    /**
     *  IS PASSENGER WAKE
     */
    ISPWAKE,

    /**
     *  SET ALL PASSENGERS FINISHED
     */
    SETALLPASSENGERSFINISHED,

    /**
     *  GET PASSENGERS AT TERMINAL ENTRANCE
     */
    GETPASSENGERSATE,

    /**
     *  SET NO MORE BAGS
     */
    SETNOMOREBAGS,

    /**
     *  RESET BAGGAGE COLLECTION POINT
     */
    RESETBAGCOLPOINT,

    /**
     *  GET PASSENGERS DEPARTURE TERMINAL ENTRANCE
     */
    GETPASSENGERSDTE,

    /**
     *  SET FLIGHT NUMBER
     */
    SETFN,

    /**
     *  SET NUMBER OF PIECES OF LUGGAGE PRESENTLY AT THE PLANE'S HOLD
     */
    SETBN,

    /**
     *  SET NUMBER OF PIECES OF LUGGAGE PRESENTLY AT THE CONVEYOR BELT
     */
    SETCB,

    /**
     *  SET NUMBER OF PIECES OF LUGGAGE AT THE STOREROOM
     */
    SETSR,

    /**
     *  SET PORTER STATE
     */
    SETPSTAT,

    /**
     *  SET DRIVER STATE
     */
    SETDSTAT,

    /**
     *  SET WAITING QUEUE
     */
    SETQIN,

    /**
     *  REMOVE ELEMENT FROM QUEUE
     */
    SETQOUT,

    /**
     *  SET OCCUPATION STATE FOR SEAT IN THE BUS
     */
    SETS,

    /**
     *  SET STATE OF PASSENGER
     */
    SETST,

    /**
     *  SET SITUATION OF PASSENGER
     */
    SETSI,

    /**
     *  SET NUMBER OF PIECES OF LUGGAGE CARRIED AT THE START OF THE JOURNEY
     */
    SETNR,

    /**
     *  SET NUMBER OF PIECES OF LUGGAGE CURRENTLY COLLECTED
     */
    SETNA,

    /**
     *  ADD BAGS LOST
     */
    ADDBAGSLOST,

    /**
     *  RESET PASSENGER
     */
    RESETPASSENGER,

    /**
     *  HEADER DEBUG
     */
    HEADERDEBUG,

    /**
     *  TO STRING DEBUG
     */
    TOSTRINGDEBUG,

    /**
     *  HEADER REQUESTED
     */
    HEADERREQ,

    /**
     *  TO STRING REQUESTED
     */
    TOSTRINGREQ,

    /**
     *  REPORT INITIAL STATUS
     */
    REPORTINITIALSTATUS,

    /**
     *  REPORT STATUS
     */
    REPORTSTATUS,

    /**
     *  FINAL REPORT
     */
    FINALREPORT,

    /**
     *  SET GLOBAL VARIABLES
     */
    SETNFIC,

    /**
     *  SET GLOBAL VARIABLES RESPONSE
     */
    NFICDONE,

    /**
     *  GET MAX NUMBER OF PASSENGERS
     */
    GETMAXNUMBEROFPASSENGERS,

    /**
     *  SET MAX NUMBER OF PASSENGERS
     */
    SETNPDTE,
    /**
     *  SHUTDOWN SERVER
     */
    SHUT,

    /**
     *  SHUTDOWN SERVER RESPONSE
     */
    ACK
}
