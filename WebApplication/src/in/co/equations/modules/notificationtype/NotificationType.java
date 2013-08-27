package in.co.equations.modules.notificationtype;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.Serializable;
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
public class NotificationType implements Serializable {

    public static final int JOB_OPENINGS = 1;
    public static final int EXAM_RESULTS = 2;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @NotPersistent
    private String keyString;
    @NotPersistent
    private Long id;
    @Persistent
    private String name;
    @Persistent
    private String description;

    public NotificationType() {
    }

    public NotificationType(int id, String name, String description) {
        this.key = KeyFactory.createKey(NotificationType.class.getSimpleName(), id);
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
