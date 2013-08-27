package com.thenextpointer.commons;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author devashish
 */
public class ContactNumberUtils {
    public static String getPhoneNumber(String number, String defaultCountryCode) {
        if (StringUtils.isEmpty(number)) {
            return "";
        }
        
        PhoneNumber phoneNumber;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();        

        if (StringUtils.isEmpty(number)) {
            return "";
        }
        
        long contactNumber = 0;
        int countryCode = 0;

        try {            
            phoneNumber = phoneUtil.parse(number, defaultCountryCode);                           
            
            if (phoneNumber.hasCountryCode()) {
                countryCode = phoneNumber.getCountryCode();
            }
            
            contactNumber = phoneNumber.getNationalNumber();
        } catch (Exception ex) {
            try {
            contactNumber = Long.parseLong(number);
            Logger.getLogger(ContactNumberUtils.class.getName()).log(Level.INFO, null, ex);
            } catch(Exception e) {
                return number;
            }
        } finally {
                 
        }        
        return "+" + countryCode + contactNumber;
    }
}    
