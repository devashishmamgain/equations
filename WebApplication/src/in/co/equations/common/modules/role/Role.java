package in.co.equations.common.modules.role;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.Serializable;
import javax.jdo.annotations.*;

@PersistenceCapable
public class Role implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String BUSINESS = "BUSINESS";
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Unique
    private String name;
    @Persistent
    private String label;
    @Persistent
    private String description;

    public Role() {
    }

    public Role(String name, String label, String description) {
        this.name = name;
        this.label = label;
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
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        final Role other = (Role) obj;
        if (this.key != other.key && (this.key == null || !this.key.equals(other.key))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 23 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 23 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }
}