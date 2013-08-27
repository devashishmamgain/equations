package com.thenextpointer.commons;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 *
 * @author devashish
 */
public class ChannelApiUtils {
    public static String getToken(String channelKey) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        String token = channelService.createChannel(channelKey);
        token = token.replaceAll("\\{\\{ token \\}\\}", token);  
        return token;
    }
}
