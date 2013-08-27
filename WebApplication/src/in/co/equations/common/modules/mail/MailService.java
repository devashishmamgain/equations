package in.co.equations.common.modules.mail;

import com.google.appengine.api.datastore.Email;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import in.co.equations.common.modules.feedback.Feedback;

/**
 *
 * @author devashish
 */
public class MailService {

    private static Properties properties = new Properties();
    private static String registrationMailBody = "";
    private static final Logger LOG = Logger.getLogger(MailService.class.getName());

    public MailService() {
        try {
            properties.load(MailService.class.getClassLoader().getResourceAsStream("mail.properties"));
            InputStream inputStream = MailService.class.getClassLoader().getResourceAsStream("emailTemplates/registration.html");
            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String text;
                while ((text = reader.readLine()) != null) {
                    registrationMailBody += text;
                }
            }
            registrationMailBody = registrationMailBody.replace("[SITE_URL]", "http://www.equations.co.in");
        } catch (IOException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendMail(Mail mail) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        for (Email toEmail : mail.getToEmails()) {
            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(mail.getFromEmail().getEmail(), mail.getFromName()));
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(toEmail.getEmail(), toEmail.getEmail()));
                Set<Email> replyTo = mail.getReplyTo();
                if (replyTo != null && !replyTo.isEmpty()) {
                    InternetAddress[] addresses = new InternetAddress[replyTo.size()];
                    Iterator it = replyTo.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        Email email = (Email) it.next();
                        addresses[i++] = new InternetAddress(email.getEmail());
                    }
                    msg.setReplyTo(addresses);
                }

                msg.setSubject(mail.getSubject());
                Multipart mp = new MimeMultipart();
                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(mail.getBody(), "text/html");
                mp.addBodyPart(htmlPart);
                msg.setContent(mp);
                Transport.send(msg);
            } catch (UnsupportedEncodingException ex) {
                LOG.log(Level.SEVERE, null, ex);
            } catch (AddressException ex) {
                LOG.log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendMail(String toEmail, String toName) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@equations.co.in", "Equations"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail, toName));
            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("admin@equations.co.in", "Equations"));
            msg.setSubject("Your Equations account has been activated");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (UnsupportedEncodingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
        
    public void sendMail(Feedback feedback) {
        try {
            Mail mail = new Mail();
            Set<Email> toEmails = new HashSet<Email>();
            toEmails.add(new Email(properties.getProperty("toEmails.feedback")));
            mail.setToEmails(toEmails);
            mail.setFromEmail(new Email(properties.getProperty("fromEmail.feedback")));
            Set<Email> replyTo = new HashSet<Email>();
            replyTo.add(feedback.getEmail());
            mail.setReplyTo(replyTo);
            mail.setFromName(feedback.getName());
            mail.setSubject("Feedback from: " + feedback.getEmail().getEmail());
            mail.setBody(feedback.getComment() == null ? "" : feedback.getComment().getValue());
            sendMail(mail);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void sendErrorMail(String info, String stackTrace) {
        try {
            Mail mail = new Mail();
            Set<Email> toEmails = new HashSet<Email>();
            toEmails.add(new Email(properties.getProperty("toEmails.error")));
            mail.setToEmails(toEmails);
            mail.setFromEmail(new Email(properties.getProperty("fromemail.error.device")));
            mail.setFromName(properties.getProperty("fromname.error.device"));
            mail.setSubject(properties.getProperty("subject.error.device"));
            mail.setBody(info + ", " + stackTrace);
            sendMail(mail);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}