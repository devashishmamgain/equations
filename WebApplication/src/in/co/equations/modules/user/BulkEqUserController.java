package in.co.equations.modules.user;

import com.google.appengine.api.datastore.PhoneNumber;
import com.thenextpointer.exceptions.PreexistingEntityException;
import in.co.equations.modules.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BulkEqUserController {

    @Autowired
    private EqUserService eqUserService;
    
    public BulkEqUserController() {
        
    }

    @RequestMapping(value = "/admin/user/bulkCreate.form", method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        return "admin/user/bulkCreate";
    }

    @RequestMapping(value = "/admin/user/bulkSave.form", method = RequestMethod.POST)
    @SuppressWarnings("empty-statement")
    public String createUsers(ModelMap model, @RequestParam("users") String users) throws PreexistingEntityException {

        String[] userValues = users.split("\n");
        for (String userValue : userValues) {            
            String[] values = userValue.split(",");
            
            String serialNo = values[0].trim();
            String name = values[1].trim();
            String idNo = values[2].trim();
            String contactNumber = "";
            if (values.length == 4) {
                contactNumber = values[3].trim();
            }   

            EqUser eqUser = new EqUser();
            eqUser.setIdNo(idNo);
            eqUser.setName(name);
            eqUser.setRoleId(Role.STUDENT);
            eqUser.setPrimaryContactNumber(new PhoneNumber(contactNumber));
            eqUserService.getCrudService().create(eqUser);
        }

        return "admin/user/bulkCreate";
    }
}

