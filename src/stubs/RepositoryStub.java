package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

/**
 * Exposes Repository server services to the client side.
 */
public class RepositoryStub extends SharedRegionStub {

    /**
     * Constructor method for Repository Stub
     *
     * @param serverHostName Server Host Name
     * @param serverPort Communication port
     */
    public RepositoryStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    /**
     * Set flight number.
     * (service solicitation)
     *
     * @param fn number of the actual flight.
     */
    public void setFN(int fn){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETFN);
        outMessage.setIntValue1(fn);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETFN) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set luggage number.
     * (service solicitation)
     *
     * @param bn number of pieces of luggage presently at the plane's hold.
     */
    public void setBN(int bn){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();

        outMessage.setMessageType(MessageType.SETBN);
        outMessage.setIntValue1(bn);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETBN) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set conveyor belt.
     * (service solicitation)
     *
     * @param cb number of pieces of luggage presently on the conveyor belt.
     */
    public void setCB(int cb){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETCB);
        outMessage.setIntValue1(cb);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETCB) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set storeroom.
     * (service solicitation)
     *
     * @param sr number of pieces of luggage belonging to passengers in transit presently stored at the storeroom.
     */
    public void setSR(int sr){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETSR);
        outMessage.setIntValue1(sr);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETSR) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set porter state.
     * (service solicitation)
     *
     * @param p_Stat state of the porter.
     */
    public void setP_Stat(String p_Stat){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETPSTAT);
        outMessage.setStringValue1(p_Stat);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETPSTAT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set driver state.
     * (service solicitation)
     *
     * @param d_Stat state of the driver.
     */
    public void setD_Stat(String d_Stat){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();

        outMessage.setMessageType(MessageType.SETDSTAT);
        outMessage.setStringValue1(d_Stat);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETDSTAT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set occupation state for the waiting queue (passenger id / - (empty)).
     * (service solicitation)
     *
     * @param num position of the queue to add one passenger.
     * @param q passenger to put in the queue position.
     */
    public void setQIn(int num, String q){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETQIN);
        outMessage.setStringValue1(q);
        outMessage.setIntValue1(num);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETQIN) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Remove first element from the waiting queue and add '-' (empty) to the last position.
     * (service solicitation)
     */
    public void setQOut(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETQOUT);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETQOUT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set occupation state for seat in the bus (passenger id / - (empty)).
     * (service solicitation)
     *
     * @param num seat position to put the passenger.
     * @param s passenger to put in the seat position.
     */
    public void setS(int num, String s){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETS);
        outMessage.setStringValue1(s);
        outMessage.setIntValue1(num);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set state of passenger # (# - 0 .. 5).
     * (service solicitation)
     *
     * @param num passenger to set state.
     * @param st state of the passenger.
     */
    public void setST(int num, String st){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETST);
        outMessage.setStringValue1(st);
        outMessage.setIntValue1(num);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETST) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set situation of passenger # (# - 0 .. 5) – TRT (in transit) / FDT (has this airport as her final destination).
     * Count total number of passengers in transit and with this airport as final destination (all flights).
     * (service solicitation)
     *
     * @param num passenger to set situation.
     * @param si situation of the passenger.
     */
    public void setSI(int num, String si){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETSI);
        outMessage.setStringValue1(si);
        outMessage.setIntValue1(num);

        cc.writeObject(outMessage);

        inMessage =(Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETSI) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set number of pieces of luggage the passenger # (# - 0 .. 5) carried at the start of her journey.
     * Count total number of pieces of luggage (all flights).
     * (service solicitation)
     *
     * @param num passenger that carried the pieces of luggage at the start of her journey.
     * @param nr number of pieces of luggage the passenger carried at the start of her journey.
     */
    public void setNR(int num, int nr){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();

        outMessage.setMessageType(MessageType.SETNR);
        outMessage.setIntValue1(num);
        outMessage.setIntValue2(nr);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETNR) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Set number of pieces of luggage the passenger # (# - 0 .. 5) he has presently collected.
     * (service solicitation)
     *
     * @param num passenger that has presently collected the pieces of luggage.
     * @param na number of pieces of luggage that the passenger has presently collected.
     */
    public void setNA(int num, int na){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.SETNA);
        outMessage.setIntValue1(num);
        outMessage.setIntValue2(na);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.SETNA) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Add one to the number of piece of luggage lost.
     * (service solicitation)
     *
     * @param nBagsLost Number of bags the passengers reported lost.
     */
    public void addBagsLost(int nBagsLost){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.ADDBAGSLOST);
        outMessage.setIntValue1(nBagsLost);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.ADDBAGSLOST) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Count number of pieces of luggage that the passenger has presently collected (all passengers in all flight).
     * Reset passenger to the initial state.
     * (service solicitation)
     *
     * @param num passenger to be put in the initial state.
     */
    public void reset_Passenger(int num) {

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.RESETPASSENGER);
        outMessage.setIntValue1(num);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.RESETPASSENGER) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Write the initial State (Calculate logger file name, create logger file and add header to the logger file).
     * (service solicitation)
     */
    public void reportInitialStatus() {

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.REPORTINITIALSTATUS);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.REPORTINITIALSTATUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Append the current State to the logger file.
     * (service solicitation)
     */
    public void reportStatus() {

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.REPORTSTATUS);
        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.REPORTSTATUS) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Calculate the number of pieces of luggage missing.
     * Append the final report to the logger file.
     * (service solicitation)
     */
    public void finalReport(){

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        Message inMessage, outMessage;

        while (!cc.open ()) {
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message();
        outMessage.setMessageType(MessageType.FINALREPORT);

        cc.writeObject(outMessage);

        inMessage = (Message) cc.readObject();

        if (inMessage.getMessageType() != MessageType.FINALREPORT) {
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        cc.close();
    }

    /**
     * Sets needed global parameters
     * @param n_passengers Number of passenger per flight
     * @param t_seats Maximum number of bus seats
     */
    public void probPar (int n_passengers, int t_seats) {

        ClientCom con = new ClientCom (super.getServerHostName(), super.getServerPort());
        Message inMessage, outMessage;

        while (!con.open ()){
            try {
                Thread.sleep((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message (n_passengers, MessageType.SETNFIC, t_seats);

        con.writeObject (outMessage);

        inMessage = (Message) con.readObject ();

        if (inMessage.getMessageType() != MessageType.NFICDONE) {
            System.out.println ("Arranque da simulação: Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        con.close ();
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
