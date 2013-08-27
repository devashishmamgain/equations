package in.co.equations.common.modules.channel;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author devashish
 */
public class InstantMessageService {

    private static final Logger LOG = Logger.getLogger(InstantMessageService.class.getName());
    private ChannelService channelService = ChannelServiceFactory.getChannelService();

    public void send(String clientId, InstantMessage instantMessage) {
        LOG.log(Level.INFO, "Sending channel notification to {0} of type {1}", new Object[]{clientId, instantMessage.getType().name()});
        try {
            channelService.sendMessage(new ChannelMessage(clientId, new Gson().toJson(instantMessage)));
        } catch (Exception ex) {
            LOG.log(Level.INFO, "Failed to send update to channel: {0}, {1}", new Object[]{clientId, ex.getMessage()});
        }
    }
}