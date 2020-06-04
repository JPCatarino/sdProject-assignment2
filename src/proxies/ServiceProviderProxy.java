package proxies;

import common.Message;
import common.ServerCom;

public class ServiceProviderProxy extends Thread {

    private SharedRegionProxy sharedRegionProxy;

    private ServerCom serverCom;

    public ServiceProviderProxy(SharedRegionProxy sharedRegion, ServerCom serverCom) {
        this.sharedRegionProxy= sharedRegion;
        this.serverCom = serverCom;
    }

    @Override
    public void run() {
        Message msg = (Message) serverCom.readObject();
        msg = sharedRegionProxy.processAndReply(msg);
        serverCom.writeObject(msg);
        serverCom.close();
    }
}
