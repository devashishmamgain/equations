package in.co.equations.modules.enquiry;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Text;

import java.util.Date;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Enquiry {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private Email email;

    @Persistent
    private String name;

    @Persistent
    private Date dated;

    @Persistent
    private String type;

    @Persistent
    private String subject;

    @Persistent
    @Column(allowsNull = "true")
    private Text comment;

    @Persistent
    private PhoneNumber contactNumber;

    public Enquiry() {

    }

    public Enquiry(Email email, String name, String type) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public Key getKey() {
        return key;
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

    public Text getComment() {
        return comment;
    }

    public void setComment(Text comment) {
        this.comment = comment;
    }

    //Added to support spring to set the comment
    public void setComment(String comment) {
        this.comment = new Text(comment);
    }
    
    public String getCommentValue() {
        return this.comment!=null?comment.getValue():"";
    }

    public void setCommentValue(String comment) {
        this.comment = new Text(comment);
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }
    
    public Email getEmail() {
        return email;
    }

    public String  getEmailValue(){
        return email !=null ? this.email.getEmail():"";
    }
    
    public void setEmailValue(String email){
        this.email = new Email( email );
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneNumber getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(PhoneNumber contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public String getContactNumberValue( ){
        return this.contactNumber!=null?this.contactNumber.getNumber():"";
    }
    
    public void setContactNumberValue( String contactNumber ){
        this.contactNumber = new PhoneNumber( contactNumber );
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    
}
