package stubs;

import common.ClientCom;
import common.Message;
import common.MessageType;

public class RepositoryStub extends SharedRegionStub {

    public RepositoryStub(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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

        cc.close();
    }

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
}
