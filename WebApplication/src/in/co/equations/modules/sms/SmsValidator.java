package in.co.equations.modules.sms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class SmsValidator implements Validator {

   @Override
    public boolean supports(Class clazz) {
        //just validate the Enquiry instances
        return Sms.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message",
                "required.message", "Message required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receivers",
                "required.receivers", "Receivers required.");

        Sms sms = (Sms) target;
        if (sms.getReceivers() != null && sms.getReceivers().getValue().equals("")) {
            sms.setReceivers(null);
        }

        /*
        if(!(enquiry.getPassword().equals(cust.getConfirmPassword()))){
        errors.rejectValue("password", "notmatch.password");
        }*/
    }
}
