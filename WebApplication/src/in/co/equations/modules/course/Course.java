package in.co.equations.modules.course;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class Course {
    public static final long BANK_PO = 1;
    public static final long BANK_CLERICAL = 2;
    public static final long MAT = 3;
    public static final long OTHER = 4;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    @Unique
    private String name;

    @Persistent
    private String description;

    public Course() {

    }

    public Course(String id, String name, String description) {
        this.key = KeyFactory.createKey(Course.class.getSimpleName(), id);
        this.name = name;
        this.description = description;
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

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.key != other.key && (this.key == null || !this.key.equals(other.key))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 59 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }
}
