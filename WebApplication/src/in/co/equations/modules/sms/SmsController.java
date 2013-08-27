package in.co.equations.modules.sms;

import com.google.appengine.api.datastore.Text;
import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.user.EqUser;
import in.co.equations.modules.user.EqUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class SmsController {

    @Autowired
    private SmsService smsService;

    private SmsValidator smsValidator;
    
    @Autowired
    private EqUserService eqUserService;

    public SmsController() {
        this.smsValidator = new SmsValidator();
    }

    public SmsService getSmsService() {
        return smsService;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    public EqUserService getEqUserService() {
        return eqUserService;
    }

    public void setEqUserService(EqUserService eqUserService) {
        this.eqUserService = eqUserService;
    }

    @RequestMapping(value = "/admin/sms/send.form", method = RequestMethod.POST)
    public String send(@ModelAttribute("sms") Sms sms,
            BindingResult result, SessionStatus status) throws PreexistingEntityException {
        smsValidator.validate(sms, result);
        if (result.hasErrors()) {
            //if validator failed
            return "admin/sms/create";
        } else {
            status.setComplete();
            String smsSend = smsService.sendToGroup(sms);
            if ("success".equals(smsSend)) {
                return "admin/sms/success";
            } else {
                return "admin/sms/error";
            }
        }
    }

    @RequestMapping(value = "/admin/sms/sendSMS.form", method = RequestMethod.POST)
    @ResponseBody
    public String sendSMS(@RequestParam String receivers, @RequestParam String message) throws PreexistingEntityException {
        Sms sms = new Sms();
        sms.setMessage(message);
        sms.setReceivers(new Text(receivers));
        smsService.sendToGroup(sms);
        return "success";
    }

    @RequestMapping(value = "/admin/sms/create.form", method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        Sms sms = new Sms();
        model.addAttribute("sms", sms);
        return "admin/sms/create";
    }

    //public String getAjax(@RequestBody String group) {
    @RequestMapping(value = "/admin/sms/getContactNumbers.page", method = RequestMethod.POST)
    @ResponseBody
    public String getAjax(@RequestParam String group) {
        String receivers = "";
        List<EqUser> eqUsers = new ArrayList<EqUser>();
        if ("ALL".equals(group)) {
            eqUsers = eqUserService.getEqUserList();
        } else if ("SMS.NOTIFICATION.SELECTED".equals(group)) {
            for (EqUser eqUser : eqUserService.getEqUserList()) {
                if (eqUser.isSmsNotification()) {
                    eqUsers.add(eqUser);
                }
            }
        }

        for (EqUser eqUser : eqUsers) {
            if (eqUser.getPrimaryContactNumber() != null) {
                receivers += eqUser.getPrimaryContactNumber().getNumber().trim() + ",";
            }
        }

        if (receivers.equals("") == false) {
            receivers = receivers.substring(0, receivers.length() - 1);
        }

        return receivers;
    }
}
