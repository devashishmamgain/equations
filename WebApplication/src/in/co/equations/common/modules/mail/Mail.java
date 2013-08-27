package in.co.equations.common.modules.mail;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import java.util.Date;
import java.util.Set;

public class Mail {

    private Key key;
    private Email fromEmail;
    private String fromName;
    private Set<Email> toEmails;
    private Set<Email> replyTo;
    private Date createdAt;
    private String subject;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Email getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(Email fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Email> getToEmails() {
        return toEmails;
    }

    public void setToEmails(Set<Email> toEmails) {
        this.toEmails = toEmails;
    }

    public Set<Email> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Set<Email> replyTo) {
        this.replyTo = replyTo;
    }
}
