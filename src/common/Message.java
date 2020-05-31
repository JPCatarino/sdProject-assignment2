package common;

public class Message {

    private MessageType messageType;

    private Boolean booleanValue1;

    public Message(){}

    public Boolean getBooleanValue1() {
        return booleanValue1;
    }

    public void setBooleanValue1(Boolean booleanValue1) {
        this.booleanValue1 = booleanValue1;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
