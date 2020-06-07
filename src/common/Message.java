package common;

import states.PassengerDecisions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Implements the structure of the messages sent between servers and clients.
 */
public class Message implements Serializable {

    /**
     * Type of the Message
     *
     * @serial messageType
     */
    private MessageType messageType;

    /**
     * ID of the client sending the message
     *
     * @serial entityID
     */
    private int entityID;

    /**
     * First integer value transported
     *
     * @serial intValue1
     */
    private int intValue1;

    /**
     * Second integer value transported
     *
     * @serial intValue2
     */
    private int intValue2;

    /**
     * Maximum number of bus seats available
     *
     * @serial t_seats
     */
    private int t_seats;

    /**
     * Number of passengers per flight
     *
     * @serial n_passengers
     */
    private int n_passengers;

    /**
     * Number of landings on this run
     *
     * @serial k_landings
     */
    private int k_landings;

    /**
     * First boolean value transported
     *
     * @serial booleanValue1
     */
    private Boolean booleanValue1;

    /**
     * Second boolean value transported
     *
     * @serial booleanValue2
     */
    private Boolean booleanValue2;

    /**
     * First string value transported
     *
     * @serial stringValue1
     */
    private String stringValue1;

    /**
     * First integer list transported
     *
     * @serial intList1
     */
    private List<Integer> intList1;

    /**
     * First passenger decisions transported
     *
     * @serial passengerDecisions1
     */
    private PassengerDecisions passengerDecisions1;

    /**
     * First bag transported
     *
     * @serial bag1
     */
    private int[] bag1;

    /**
     * First bag list transported
     *
     * @serial bagList1
     */
    private List<int[]> bagList1;

    /**
     * Constructor Method for Message object.
     */
    public Message(){}

    /**
     * Maximum number of bus seats available
     * @param type Type of the message
     * @param t_seats Maximum number of bus seats available
     */
    public Message (MessageType type, int t_seats) {
        this.messageType = type;
        this.t_seats = t_seats;
    }

    /**
     * Maximum number of bus seats available
     * @param type Type of the message
     * @param n_passengers Number of passengers per flight
     * @param k_landings Number of landings on this run
     */
    public Message (MessageType type, int n_passengers, int k_landings) {
        this.messageType = type;
        this.n_passengers = n_passengers;
        this.k_landings = k_landings;
    }

    /**
     * Maximum number of bus seats available
     * @param n_passengers Number of passengers per flight
     * @param type Type of the message
     * @param t_seats Maximum number of bus seats available.
     */
    public Message (int n_passengers, MessageType type, int t_seats) {
        this.messageType = type;
        this.t_seats = t_seats;
        this.n_passengers = n_passengers;
    }

    /**
     * Returns maximum number of bus seats available.
     * @return t_seats
     */
    public int getT_seats() {
        return t_seats;
    }

    /**
     * Returns number of passengers per flight
     * @return n_passengers
     */
    public int getN_passengers() {
        return n_passengers;
    }

    /**
     * Return number of flights of this run
     * @return k_landings
     */
    public int getK_landings() {
        return k_landings;
    }

    /**
     * Returns type of this message.
     * @return messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Returns first boolean value transported
     * @return booleanValue1s
     */
    public Boolean getBooleanValue1() {
        return booleanValue1;
    }

    /**
     * Returns first passengers decisions transported
     * @return passengerDecisions1
     */
    public PassengerDecisions getPassengerDecisions1() {
        return passengerDecisions1;
    }

    /**
     * Returns first bag transported
     * @return bag1
     */
    public int[] getBag1() {
        return bag1;
    }

    /**
     * Returns first bag list transported
     * @return bagList1
     */
    public List<int[]> getBagList1() {
        return bagList1;
    }

    /**
     * Returns first integer value transported
     * @return intValue1
     */
    public int getIntValue1() {
        return intValue1;
    }

    /**
     * Returns first string value transported
     * @return stingValue1
     */
    public String getStringValue1() {
        return stringValue1;
    }

    /**
     * Returns second integer value transported
     * @return intValue2
     */
    public int getIntValue2() {
        return intValue2;
    }

    /**
     * Returns first integer list transported
     * @return intList1
     */
    public List<Integer> getIntList1() {
        return intList1;
    }

    /**
     * Sets the first integer list transported
     * @param intList1 first integer list
     */
    public void setIntList1(List<Integer> intList1) {
        this.intList1 = intList1;
    }

    /**
     * Returns the ID of the client sending the message
     * @return entityID
     */
    public int getEntityID() {
        return entityID;
    }

    /**
     * Return the second boolean value transported
     * @return booleanValue2
     */
    public Boolean getBooleanValue2() {
        return booleanValue2;
    }

    /**
     * Sets the second boolean value transported
     * @param booleanValue2 second boolean value
     */
    public void setBooleanValue2(Boolean booleanValue2) {
        this.booleanValue2 = booleanValue2;
    }

    /**
     * Sets the ID of the client sending the message
     * @param entityID ID of the client sending the message
     */
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    /**
     * Sets the second integer value transported
     * @param intValue2 second integer value
     */
    public void setIntValue2(int intValue2) {
        this.intValue2 = intValue2;
    }

    /**
     * Sets the first string value transported
     * @param stringValue1 first string value
     */
    public void setStringValue1(String stringValue1) {
        this.stringValue1 = stringValue1;
    }

    /**
     * Sets the first integer value transported
     * @param intValue1 first integer value
     */
    public void setIntValue1(int intValue1) {
        this.intValue1 = intValue1;
    }

    /**
     * Sets the first bag list transported
     * @param bagList1 first bag list
     */
    public void setBagList1(List<int[]> bagList1) {
        this.bagList1 = bagList1;
    }

    /**
     * Sets the first bag transported
     * @param bagValue1 first bag
     */
    public void setBag1(int[] bagValue1) {
        this.bag1 = bagValue1;
    }

    /**
     * Sets the first boolean value transported
     * @param booleanValue1 first boolean value
     */
    public void setBooleanValue1(Boolean booleanValue1) {
        this.booleanValue1 = booleanValue1;
    }

    /**
     * Sets the first passenger decisions transported
     * @param passengerDecisions1 first passenger decisions
     */
    public void setPassengerDecisions1(PassengerDecisions passengerDecisions1) {
        this.passengerDecisions1 = passengerDecisions1;
    }

    /**
     * Sets the number of passengers per flight
     * @param n_passengers number of passengers per flight
     */
    public void setN_passengers(int n_passengers) {
        this.n_passengers = n_passengers;
    }

    /**
     * Sets the type of the message
     * @param messageType type of the message
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageType=" + messageType +
                ", entityID=" + entityID +
                ", intValue1=" + intValue1 +
                ", intValue2=" + intValue2 +
                ", t_seats=" + t_seats +
                ", n_passengers=" + n_passengers +
                ", k_landings=" + k_landings +
                ", booleanValue1=" + booleanValue1 +
                ", booleanValue2=" + booleanValue2 +
                ", stringValue1='" + stringValue1 + '\'' +
                ", intList1=" + intList1 +
                ", passengerDecisions1=" + passengerDecisions1 +
                ", bag1=" + Arrays.toString(bag1) +
                ", bagList1=" + bagList1 +
                '}';
    }
}
