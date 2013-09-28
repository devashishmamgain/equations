package in.co.equations.modules.user;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.thenextpointer.db.PersistenceService;
import in.co.equations.modules.editor.GoogleDatastoreKeyEditor;
import in.co.equations.modules.editor.GoogleDatastoreTextEditor;
import in.co.equations.modules.role.Role;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EqUserListController {

    private UserService userService = UserServiceFactory.getUserService();
    private EqUserService eqUserService;

    public EqUserListController() {
        this.eqUserService = new EqUserService();
    }    

    @RequestMapping(value = "/admin/user/list.page", method = RequestMethod.GET)
    public String hello(HttpServletRequest request, ModelMap model) {
        int startPos = 0;
        if (request.getParameter("startPos") != null) {
            startPos = Integer.parseInt(request.getParameter("startPos"));
        } 
        
        String url = request.getRequestURI();
        List<EqUser> eqUsersList = null;
        /*
         * Temporary fetching all the users untill pagination plugin is
         * integrated.
         * Todo: Replace eqUserService.getEqUserList() with 
         * eqUserService.getList(EqUser.class, startPos, startPos + 20) after
         * pagination integration.
         */
        //eqUsersList = eqUserService.getList(EqUser.class, startPos, startPos + 20);
        eqUsersList = eqUserService.getEqUserList();
        model.addAttribute("eqUserList", eqUsersList);
        model.addAttribute("count", eqUserService.getEqUserList().size());
        model.addAttribute("max", 20);
        return "admin/user/list";
    }

    @RequestMapping(value = "/admin/user/getList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public String getJSONUsersList() throws JSONException {
        //JSONObject json = new JSONObject();
        JSONObject jsonUsers = new JSONObject();
        for (EqUser eqUser: eqUserService.getEqUserList()) {
            JSONObject jsonEqUser = new JSONObject();
            jsonEqUser.put("id", eqUser.getId().getId());
            jsonEqUser.put("Id No", eqUser.getIdNo());
            jsonUsers.append("eqUsers", jsonEqUser);
        }
        //json.put("eqUsers", jsonUsers);
        return jsonUsers.toString();
    }

    @RequestMapping(value = "/admin/user/update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam String keyString, @RequestParam String column, @RequestParam String value) {
        PersistenceManager pm = new PersistenceService().getPersistenceManager();
        try {
            EqUser eqUser = pm.getObjectById(EqUser.class, KeyFactory.stringToKey(keyString));
            String[] columns = new String[]{"Id No", "Email", "Name", "Role", "Permanent Address",
                "Correspondence Address", "Primary Contact Number", "SMS Notification"};
            if (columns[0].equals(column)) {
                eqUser.setIdNo(value);
            } else if (columns[1].equals(column)) {
                eqUser.setEmail(new Email(value));
            } else if (columns[2].equals(column)) {
                eqUser.setName(value);
            } else if (columns[3].equals(column)) {
                long roleId = Role.STUDENT;
                if (value.equalsIgnoreCase("ADMIN")) {
                    roleId = Role.ADMIN;
                } else if (value.equalsIgnoreCase("FACULTY")) {
                    roleId = Role.FACULTY;
                } else if (value.equalsIgnoreCase("STUDENT")) {
                    roleId = Role.STUDENT;
                }
                eqUser.setRoleId(roleId);
            } else if (columns[4].equals(column)) {
                eqUser.setPermanentAddress(new Text(value));
            } else if (columns[5].equals(column)) {
                eqUser.setCorrespondenceAddress(new Text(value));
            } else if (columns[6].equals(column)) {
                eqUser.setPrimaryContactNumber(new PhoneNumber(value));
            } else if (columns[7].equals(column)) {
                eqUser.setSmsNotification(Boolean.parseBoolean(value));
            }
        } finally {
            pm.close();
        }
        return "success";
    }
   
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(com.google.appengine.api.datastore.Key.class,
                new GoogleDatastoreKeyEditor());
        binder.registerCustomEditor(com.google.appengine.api.datastore.Text.class,
                new GoogleDatastoreTextEditor());
        // binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class));
    }
}
