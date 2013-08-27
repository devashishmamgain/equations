package com.thenextpointer.commons;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author devashish
 */
public class ValidationUtils {
    private static final Logger LOGGER = Logger.getLogger(ValidationUtils.class.getName());

    public static boolean isValidEmailAddress(String emailAddress) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public static boolean isValidKeyString(String keyString) {
        try {
            if (StringUtils.isBlank(keyString)) {
                return false;
            }
            
            Key key = KeyFactory.stringToKey(keyString);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, null, "Invalid keyString: " + keyString);
            return false;
        }
    }
}
