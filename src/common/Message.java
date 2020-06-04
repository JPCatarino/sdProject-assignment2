package common;

import states.PassengerDecisions;

import java.util.List;

public class Message {

    private MessageType messageType;

    private int entityID;

    private int intValue1;

    private int intValue2;

    private Boolean booleanValue1;

    private String stringValue1;

    private PassengerDecisions passengerDecisions1;

    private int[] bag1;

    private List<int[]> bagList1;

    public Message(){}

    public MessageType getMessageType() {
        return messageType;
    }

    public Boolean getBooleanValue1() {
        return booleanValue1;
    }

    public PassengerDecisions getPassengerDecisions1() {
        return passengerDecisions1;
    }

    public int[] getBag1() {
        return bag1;
    }

    public List<int[]> getBagList1() {
        return bagList1;
    }

    public int getIntValue1() {
        return intValue1;
    }

    public String getStringValue1() {
        return stringValue1;
    }

    public int getIntValue2() {
        return intValue2;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setIntValue2(int intValue2) {
        this.intValue2 = intValue2;
    }

    public void setStringValue1(String stringValue1) {
        this.stringValue1 = stringValue1;
    }

    public void setIntValue1(int intValue1) {
        this.intValue1 = intValue1;
    }

    public void setBagList1(List<int[]> bagList1) {
        this.bagList1 = bagList1;
    }

    public void setBag1(int[] bagValue1) {
        this.bag1 = bagValue1;
    }

    public void setBooleanValue1(Boolean booleanValue1) {
        this.booleanValue1 = booleanValue1;
    }

    public void setPassengerDecisions1(PassengerDecisions passengerDecisions1) {
        this.passengerDecisions1 = passengerDecisions1;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
