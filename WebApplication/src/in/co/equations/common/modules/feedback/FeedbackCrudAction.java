package in.co.equations.common.modules.feedback;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudAction;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import in.co.equations.common.modules.mail.MailService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.datanucleus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adarsh
 */
public class FeedbackCrudAction extends CrudAction<Feedback, Key> implements ServletRequestAware {

    private String name;
    private String emailId;
    private String comment;
    private String title;
    private String inputStream;
    private HttpServletRequest request;
    
    @Autowired
    private MailService mailService;

    public String getComment() {
        return comment;
    }
    public String getTitle(){
        return title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInputStream() {
        return inputStream;
    }

    public void setInputStream(String inputStream) {
        this.inputStream = inputStream;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }    

    //Creating the entity class
    public String create() {
        if (StringUtils.isEmpty(getEmailId())) {
            request.setAttribute("error", true);
            return ERROR;
        }
        
        Email email = new Email(getEmailId());
        Feedback feedback = new Feedback();
        feedback.setName(getName());
        feedback.setComment(getTitle() + getComment());
        feedback.setEmail(email);
        feedback.setDated(new Date());
        setOperator("CREATE");
        setEntity(feedback);
        setCrudService(new FeedbackCrudService());
        performCrudAction();
        mailService.sendMail(feedback);
        request.setAttribute("feedbackSubmitted", true);        
        return SUCCESS;       
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
