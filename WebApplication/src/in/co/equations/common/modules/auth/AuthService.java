package in.co.equations.common.modules.auth;

import com.google.apphosting.api.ApiProxy;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi20;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author devashish
 */
public class AuthService {
    public static final Logger LOG = Logger.getLogger(AuthService.class.getName());

    private OAuthService oAuthService;
    private static Properties properties = new Properties();
    public static HashMap<String, String> propertyMap;

    public AuthService() {
    }

    private void init() {
        String appId = ApiProxy.getCurrentEnvironment().getAppId().replace("s~", "");
        try {
            properties.load(AuthService.class.getClassLoader().getResourceAsStream("oauth_google_" + appId + ".properties"));
            propertyMap = new HashMap(properties);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        oAuthService = new ServiceBuilder()
                .provider(GoogleApi20.class)
                .apiKey(propertyMap.get(OAuthConstants.CLIENT_ID))
                .apiSecret(propertyMap.get(OAuthConstants.CLIENT_SECRET))
                .callback(propertyMap.get(OAuthConstants.CALLBACK))
                .scope(propertyMap.get(OAuthConstants.SCOPE))
                .grantType(OAuthConstants.AUTHORIZATION_CODE)
                .build();
        /*oAuthService = new ServiceBuilder().provider(GoogleApi20.class)
         .propertiesResource("oauth_google_" + appId + ".properties")
         .grantType(OAuthConstants.AUTHORIZATION_CODE)
         .build();*/
    }

    public OAuthService getOAuthService() {
        return oAuthService;
    }

    public void setOAuthService(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    public String getGroupUrl() {
        return propertyMap.get("groupUrl");
    }

    public String getContactUrl() {
        return propertyMap.get("contactUrl");
    }

    public String getContactUrl(Short page) {
        return propertyMap.get("contactUrl") + "&start-index=" + (2000 * (page - 1) + 1);
    }

    public String getContactUrl(Date updatedMin) {
        return propertyMap.get("contactUrl") + "&updated-min=" + new SimpleDateFormat("yyyy-MM-dd'T'hh:m:ss").format(updatedMin);
    }

    public String getTokenUrl() {
        return propertyMap.get("tokenUrl");
    }

    public String getUserInfo() {
        return propertyMap.get("userInfo");
    }

    public Token getAccessToken(String refreshToken) {
        try {
            OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, getTokenUrl());
            oAuthRequest.addQuerystringParameter(OAuthConstants.CLIENT_ID, AuthService.propertyMap.get(OAuthConstants.CLIENT_ID));
            oAuthRequest.addQuerystringParameter(OAuthConstants.CLIENT_SECRET, AuthService.propertyMap.get(OAuthConstants.CLIENT_SECRET));
            oAuthRequest.addQuerystringParameter(OAuthConstants.REFRESH_TOKEN, refreshToken);
            oAuthRequest.addQuerystringParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.REFRESH_TOKEN);
            Response response = oAuthRequest.send();
            String responseBody = response.getBody();
            LOG.log(Level.INFO, "ResponseBody: {0}", responseBody);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(responseBody, HashMap.class);
            
            if (map.get("access_token") != null) {
                return new Token(map.get("access_token"), responseBody);            
            }            
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (JsonParseException ex) {       
            LOG.info("JsonParseException");
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
