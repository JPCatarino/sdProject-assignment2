package proxies;

import common.Message;
import common.ServerCom;
import sharedRegions.TempStgArea;

public class TempStgAreaProxy extends Thread implements SharedRegionProxy {

    private final TempStgArea tempStgArea;

    /**
     * The simulation has finished
     * @serialField simFinished
     */
    private boolean simFinished;

    private static int nProxy = 0;

    private ServerCom sconi;

    public TempStgAreaProxy(ServerCom sconi, TempStgArea tempStgArea) {
        super ("Proxy_" + TempStgAreaProxy.getProxyId ());

        this.sconi = sconi;
        this.tempStgArea = tempStgArea;
        this.simFinished = false;
    }


    @Override
    public void run ()
    {
        Message inMessage = null,
                outMessage = null;

        inMessage = (Message) sconi.readObject ();

        try {
            outMessage = processAndReply (inMessage);
        }
        catch (Exception e) {
            System.out.println ("Thread " + getName () + ": " + e.getMessage () + "!");
            System.exit (1);
        }

        sconi.writeObject (outMessage);
        sconi.close ();
    }

    private static int getProxyId ()
    {
        Class<?> cl = null;

        int proxyId;

        try {
            cl = Class.forName ("proxies.TempStgAreaProxy");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("O tipo de dados TempStgAreaProxy não foi encontrado!");
            e.printStackTrace ();
            System.exit (1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }

    @Override
    public Message processAndReply(Message msg) {
        return null;
    }

    @Override
    public boolean getSimStatus() {
        return false;
    }
}
