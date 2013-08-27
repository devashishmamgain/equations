package in.co.equations.modules.enquiry;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/enquiry/list.page")
public class EnquiryListController {

    private EnquiryService enquiryService;

    public EnquiryService getEnquiryService() {
        return enquiryService;
    }

    public void setEnquiryService(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getEnquiryList(HttpServletRequest request, ModelMap model) {
        model.addAttribute("enquiryList", enquiryService.getPersistenceService().getListService().getResults(null, null));
        return "admin/inbox";
    }
}
