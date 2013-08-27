package in.co.equations.modules.enquiry;

import com.thenextpointer.exceptions.PreexistingEntityException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/enquiry/enquiry.form")
public class EnquiryController {

    private EnquiryValidator enquiryValidator;
    private EnquiryService enquiryService;

    public EnquiryController() {
        this.enquiryValidator = new EnquiryValidator();
    }

    public EnquiryService getEnquiryService() {
        return enquiryService;
    }

    public void setEnquiryService(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(
            @ModelAttribute("enquiry") Enquiry enquiry,
            BindingResult result, SessionStatus status) throws PreexistingEntityException {

        enquiryValidator.validate(enquiry, result);

        if (result.hasErrors()) {
            return "enquiry/create";
        } else {
            status.setComplete();
            enquiry.setDated(new Date());
            enquiryService.getCrudService().create(enquiry);
            return "enquiry/success";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {

        Enquiry enquiry = new Enquiry();
        //enquiry.setEmail(new Email("Enter your email id here."));

        //command object
        model.addAttribute("enquiry", enquiry);

        //return form view
        return "enquiry/create";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /*
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
         */
    }
}
