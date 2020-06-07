package proxies;

import common.Message;

/**
 * Generic Proxy Class. The proxy receives and processes messages
 */
public interface SharedRegionProxy {

    /**
     * Process message and decide which shared region
     * function to call. Replies to the message accordingly.
     * @param msg received messages
     * @return reply
     */
    Message processAndReply(Message msg);
}
