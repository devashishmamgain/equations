package in.co.equations.modules.enquiry;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EnquiryValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Enquiry instances
        return Enquiry.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "required.email", "Field name is required.");

        Enquiry enquiry = (Enquiry) target;
        /*
        if(!(enquiry.getPassword().equals(cust.getConfirmPassword()))){
        errors.rejectValue("password", "notmatch.password");
        }*/
    }
}
