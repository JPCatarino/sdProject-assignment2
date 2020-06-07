package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;
import entities.Passenger;
import entities.Porter;
import states.PassengerDecisions;

import java.util.List;

/**
 * Exposes Arrival Lounge server services to the client side.
 */
public class ArrivalLoungeStub extends SharedRegionStub {

    /**
     * Constructor method for Arrival Lounge Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public ArrivalLoungeStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     *  If the passenger journey hasn't ended, he takes a bus.
     *  This function makes sure he transits to the next state.
     *  (service solicitation)
     */
    public void takeABus() {

        Passenger p = (Passenger) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.TAKEABUS);
        outMessage.setEntityID(p.getID());

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.TAKEABUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * When the passenger arrives at the airport,
     * this function is called so he decides what to do next.
     * Check the Passenger.run() documentation for the logic behind this.
     * (service solicitation)
     *
     * @see Passenger#run()
     * @return the passenger decision of what to do next.
     */
    public PassengerDecisions whatShouldIDo() {

        Passenger p = (Passenger) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.WHATSHOULDIDO);
        outMessage.setEntityID(p.getID());
        outMessage.setBooleanValue1(p.isJourneyEnding());
        outMessage.setIntValue1(p.getnBagsToCollect());

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.WHATSHOULDIDO) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getPassengerDecisions1();
    }

    /**
     * Wait until the last passenger to reach the arrival lounge do the operation what should i do.
     * After carry all the bags for the appropriate store wait until the operation mentioned before from the next flight.
     * If all the operations where done to all the flights, he finish running.
     * (service solicitation)
     *
     * @return False if all the operations where done on all the flights. Otherwise True.
     */
    public boolean takeARest() {

        Porter pt = (Porter) Thread.currentThread();

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.TAKEAREST);
        outMessage.setBooleanValue1(pt.isPlaneHoldEmpty());

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        pt.setPlaneHoldEmpty(inMessage.getBooleanValue2());

        if (inMessage.getMessageType() != MessageType.TAKEAREST) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getBooleanValue1();
    }

    /**
     * Go get a bag to the plane hold.
     * (service solicitation)
     *
     * @return The bag collected from the plane hold.
     */
    public int[] tryToCollectABag() {

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.TRYTOCOLLECTABAG);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.TRYTOCOLLECTABAG) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getBag1();
    }

    /**
     * Report the status and update variable that wake the porter.
     * (service solicitation)
     */
    public void noMoreBagsToCollect() {

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.NOMOREBAGSTOCOLLECT);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.NOMOREBAGSTOCOLLECT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Setter for PlainBags.
     * (service solicitation)
     *
     * @param plainBags List of bags for the flight
     */
    public void setPlainBags(List<int[]> plainBags){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETPLAINBAGS);
        outMessage.setBagList1(plainBags);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETPLAINBAGS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Setter for the FlightNumber
     * (service solicitation)
     *
     * @param flightNumber Current flight number.
     */
    public void setFlightNumber(int flightNumber){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETFLIGHTNUMBER);
        outMessage.setIntValue1(flightNumber);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETFLIGHTNUMBER) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Checks if all the flights of the day have finished.
     * This is given by comparing the current flight to the total number of flights
     * and checking if it has finished.
     * (service solicitation)
     *
     * @return true if the flights have finished
     */
    public boolean isDayFinished(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.ISDAYFINISHED);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.ISDAYFINISHED) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getBooleanValue1();
    }

    /**
     * Setter for finishedFlight, true if every passenger has finished.
     * (service solicitation)
     *
     * @param finishedFlight True if every passenger has finished.
     */
    public void setFinishedFlight(boolean finishedFlight){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETFINISHEDFLIGHT);
        outMessage.setBooleanValue1(finishedFlight);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETFINISHEDFLIGHT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Checks if porter has finished is cycle and is asleep
     * (service solicitation)
     *
     * @return true if porter is still active.
     */
    public boolean ispWake(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.ISPWAKE);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.ISPWAKE) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();

        return inMessage.getBooleanValue1();
    }

    /**
     * Sets needed global parameters (service solicitation)
     * @param n_passengers Number of Passenger per flight
     * @param k_landings Total number of landings
     */
    public void probPar (int n_passengers, int k_landings)
    {
        ClientCom cc = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SETNFIC, n_passengers, k_landings);
        cc.writeObject (outMessage);
        inMessage = (Message) cc.readObject ();

        if (inMessage.getMessageType() != MessageType.NFICDONE) {
            System.out.println ("Simulation start: Invalid type!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close ();
    }

    /**
     * Signals the servers that a entity has ended. If all 3 entities are down, the server can shutdown safely (service solicitation)
     * @param value signal flag
     */
    public void shutdown (int value) {

        ClientCom cc = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message ();
        outMessage.setMessageType(MessageType.SHUT);
        outMessage.setIntValue1(value);

        cc.writeObject (outMessage);

        inMessage = (Message) cc.readObject ();

        if (inMessage.getMessageType () != MessageType.ACK) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close ();
    }
}
