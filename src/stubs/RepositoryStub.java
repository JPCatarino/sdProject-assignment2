package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class RepositoryStub extends SharedRegionStub {

    public RepositoryStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    public void setFN(int fn){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETFN);
        newMessage.setIntValue1(fn);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setBN(int bn){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETBN);
        newMessage.setIntValue1(bn);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setCB(int cb){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETCB);
        newMessage.setIntValue1(cb);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setSR(int sr){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETSR);
        newMessage.setIntValue1(sr);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setP_Stat(String p_Stat){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETPSTAT);
        newMessage.setStringValue1(p_Stat);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setD_Stat(String d_Stat){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETDSTAT);
        newMessage.setStringValue1(d_Stat);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setQIn(int num, String q){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETQIN);
        newMessage.setStringValue1(q);
        newMessage.setIntValue1(num);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setQOut(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETQOUT);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setS(int num, String s){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETS);
        newMessage.setStringValue1(s);
        newMessage.setIntValue1(num);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setST(int num, String st){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETST);
        newMessage.setStringValue1(st);
        newMessage.setIntValue1(num);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setSI(int num, String si){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETSI);
        newMessage.setStringValue1(si);
        newMessage.setIntValue1(num);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setNR(int num, int nr){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETNR);
        newMessage.setIntValue1(num);
        newMessage.setIntValue2(nr);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void setNA(int num, int na){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.SETNA);
        newMessage.setIntValue1(num);
        newMessage.setIntValue2(na);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void addBagsLost(int nBagsLost){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.ADDBAGSLOST);
        newMessage.setIntValue1(nBagsLost);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void reset_Passenger(int num) {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.RESETPASSENGER);
        newMessage.setIntValue1(num);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public String header_debug(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.HEADERDEBUG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getStringValue1();
    }

    public String toString_debug(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TOSTRINGDEBUG);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getStringValue1();
    }

    public String header_requested(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.HEADERREQ);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getStringValue1();
    }

    public String reqToString() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.TOSTRINGREQ);

        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
        return newMessage.getStringValue1();
    }

    public void reportInitialStatus() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.REPORTINITIALSTATUS);


        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void reportStatus() {
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.REPORTSTATUS);


        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void finalReport(){
        Message newMessage = new Message();

        newMessage.setMessageType(MessageType.FINALREPORT);


        ClientCom cc = new ClientCom(super.getServerHostName(),super.getServerPort());
        cc.open();
        cc.writeObject(newMessage);

        newMessage =(Message) cc.readObject();
    }

    public void probPar (int n_passengers, int t_seats)
    {
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
}
