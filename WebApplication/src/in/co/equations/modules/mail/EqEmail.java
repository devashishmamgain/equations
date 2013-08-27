package in.co.equations.modules.mail;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author Adarsh
 */
@PersistenceCapable
public class EqEmail {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private Email fromEmail;
    @Persistent
    private Set<Email> toEmails;
    @Persistent
    private Date dated;
    @Persistent
    private String subject;
    @Persistent
    @Column(allowsNull = "true")
    private String msgBody;

    public String getmsgBody() {
        return this.msgBody;

    }

    public Set<Email> getToEmails( ) {
        return this.toEmails;
    }
    public void setToEmails( Set<Email> emails ){
        this.toEmails = emails;
    }

    public void setToEmails(String toEmails) {
        String[] emailsArr = toEmails.split(",");
        Set<Email> emails = new HashSet<Email>();
        for (String emailStr : emailsArr) {
            Email toemail = new Email(emailStr);
            emails.add(toemail);
        }
        setToEmails( emails );
    }

    public void setMsgBody(String msgBody){
        this.msgBody=msgBody;
    }
    public void setFromEmail(String fromEmail){
        this.fromEmail= new Email( fromEmail);
    }
    public Email getFromEmail( ){
      return this.fromEmail;
    }
    public void setSubject(String subject ){
        this.subject=subject;
    }
   public String  getSubject(){
        return this.subject;
    }
  
}
