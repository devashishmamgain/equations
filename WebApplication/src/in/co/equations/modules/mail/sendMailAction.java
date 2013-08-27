/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.equations.modules.mail;

import com.google.appengine.api.datastore.Email;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Adarsh
 */
public class sendMailAction extends ActionSupport implements  ModelDriven {

    
    private SimpleMailMessage templateMessage;
    private EqEmail eqEmail;

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    public void setEqEmail(EqEmail eqEmail) {
        this.eqEmail = eqEmail;
    }

    public String sendMail() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {

            Message msg = new MimeMessage(session);
            MimeBodyPart htmlPart = new MimeBodyPart();
            Multipart mp = new MimeMultipart();

            Set<Email> emailSet = eqEmail.getToEmails();
            InternetAddress[] emailAddr = new InternetAddress[emailSet.size()];
            int index = 0;
            for (Email email : emailSet) {
                emailAddr[index++] = new InternetAddress(email.getEmail());
            }
            htmlPart.setContent(eqEmail.getmsgBody(), "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            msg.setFrom(new InternetAddress(eqEmail.getFromEmail().getEmail()));
            msg.addRecipients(Message.RecipientType.TO , emailAddr );
            msg.setSubject(eqEmail.getSubject());
           
            //msg.setText();
            Transport.send(msg);
        } catch (Exception e) {
            return com.opensymphony.xwork2.Action.ERROR;
        }
        return com.opensymphony.xwork2.Action.SUCCESS;
    }

   @Override
   public EqEmail getModel() {
        return eqEmail;
    }
}



