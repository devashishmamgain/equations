package in.co.equations.modules.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EqUserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Enquiry instances
        return EqUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "required.email", "Field name is required.");

        EqUser eqUser = (EqUser) target;
        if (eqUser.getEmail() != null && eqUser.getEmail().getEmail().equals("")) {
            eqUser.setEmail(null);
        }
        
        if (eqUser.getPermanentAddress() != null && eqUser.getPermanentAddress().getValue().trim().equals("")) {
            eqUser.setPermanentAddress(null);
        }

        if (eqUser.getCorrespondenceAddress() != null && eqUser.getCorrespondenceAddress().getValue().trim().equals("")) {
            eqUser.setCorrespondenceAddress(null);
        }

        if (eqUser.getPrimaryContactNumber() != null && eqUser.getPrimaryContactNumber().getNumber().trim().equals("")) {
            eqUser.setPrimaryContactNumber(null);
        }

        if (eqUser.getAlternateContactNumber() != null && eqUser.getAlternateContactNumber().getNumber().trim().equals("")) {
            eqUser.setAlternateContactNumber(null);
        }
        /*
        if(!(enquiry.getPassword().equals(cust.getConfirmPassword()))){
        errors.rejectValue("password", "notmatch.password");
        }*/
    }
}
