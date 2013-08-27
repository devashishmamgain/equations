package in.co.equations.modules.notification;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import in.co.equations.modules.notificationtype.NotificationType;
import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author devashish
 */
@PersistenceCapable
public class Notification implements Serializable {
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;    

    @Persistent
    private Key createdByEqUserKey;

    @Persistent
    private String subject;

    @Persistent
    private Text content;

    @Persistent
    private Key notificationTypeKey;

    @Persistent
    private Date createdAt;

    @Persistent
    private Date updatedAt;

    @NotPersistent
    private String keyString;

    @Persistent
    private String bankName;

    @Persistent
    private String vacancies;

    @Persistent
    private String exam;

    @Persistent
    private Date examDate;

    @Persistent
    private Date submissionDate;

    @Persistent 
    private String applicationFee;

    @Persistent
    private String links;

    @Persistent
    private String challanLink;

    public String getChallanLink() {
        return challanLink;
    }

    public void setChallanLink(String challanLink) {
        this.challanLink = challanLink;
    }

    public Notification() {
        this.createdAt = new Date();
    }

    public Text getContent() {
        return content;
    }

    public void setContent(Text content) {
        this.content = content;
    }

    public void setContentValue(String contentValue) {
        this.content = new Text(contentValue);
    }

    public String getContentValue() {
        return content != null ? content.getValue() : "";
    }

    public String getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        this.notificationTypeKey = KeyFactory.createKey(NotificationType.class.getSimpleName(), notificationTypeId);
	}

    public Key getNotificationTypeKey() {
        return notificationTypeKey;
    }

    public void setNotificationTypeKey(Key notificationTypeKey) {
        this.notificationTypeKey = notificationTypeKey;
    }

    public String getNotificationTypeKeyString() {
        return (notificationTypeKey != null ? KeyFactory.keyToString(notificationTypeKey) : "");
    }

    public void setNotificationTypeKeyString(String text) {
        if (text == null || text.length() == 0) {
            this.notificationTypeKey = null;
        } else {
            try {
                this.notificationTypeKey = KeyFactory.stringToKey(text);
            } catch (Exception e) {
                // log.error("Cannot parse key: " + text, e);
            }
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Key getCreatedByEqUserKey() {
        return createdByEqUserKey;
    }

    public void setCreatedByEqUserKey(Key createdByEqUserKey) {
        this.createdByEqUserKey = createdByEqUserKey;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getKeyString() {
        return (key != null ? KeyFactory.keyToString(key) : "");
    }

    public void setKeyString(String text) {
        if (text == null || text.length() == 0) {
            this.key = null;
        } else {
            try {
                this.key = KeyFactory.stringToKey(text);
            } catch (Exception e) {
                // log.error("Cannot parse key: " + text, e);
            }
        }
    }
}
