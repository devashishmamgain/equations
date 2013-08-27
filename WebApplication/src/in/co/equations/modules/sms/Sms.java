package in.co.equations.modules.sms;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Sms {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String senderId = "test";

    @Persistent
    private Text receivers;

    @Persistent
    private String message;

    @Persistent
    private Date date;

    @NotPersistent
    private Set<String> receiversSet = new HashSet<String>();

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getReceiversSet() {
        return receiversSet;
    }

    public void setReceiversSet(Set<String> receiversSet) {
        this.receiversSet = receiversSet;
    }

    public Text getReceivers() {
        return receivers;
    }

    public void setReceivers(Text receivers) {
        this.receivers = receivers;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}