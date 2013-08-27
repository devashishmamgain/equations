package in.co.equations.common.modules.feedback;

/**
 *
 * @author Adarsh
 */


import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.*;

@PersistenceCapable
public class Feedback implements Serializable {

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
    @Column(allowsNull = "true")
    private Text comment;
    
    public Feedback() {

    }

    public Feedback(Email email, String name ) {
        this.email = email;
        this.name = name;
    }

    public Key getKey() {
        return key;
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

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Email getEmail() {
        return email;
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

    @Override
    public String toString() {
        return "Feedback{" + "key=" + key + ", email=" + email + ", name=" + name + ", dated=" + dated + ", comment=" + comment + '}';
    }
}
