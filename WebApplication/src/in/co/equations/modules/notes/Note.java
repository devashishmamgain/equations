package in.co.equations.modules.notes;

import com.google.appengine.api.datastore.Key;
import in.co.equations.modules.user.EqUser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author AASHISH
 */
@PersistenceCapable(detachable="true")
public class Note implements Serializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String subject;
    @Persistent
    private Date createdAt;
    @Persistent
    private Date updatedAt;
    @Persistent
    private EqUser eqUser;
    @Persistent
    private Key noteCategoryKey;
    @Persistent(defaultFetchGroup = "true", mappedBy="note")
    @Element(dependent = "true")
    private List<NoteContent> noteContents;

    public Note() {
        
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public EqUser getEqUser() {
        return eqUser;
    }

    public void setEqUser(EqUser eqUser) {
        this.eqUser = eqUser;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getNoteCategoryKey() {
        return noteCategoryKey;
    }

    public void setNoteCategoryKey(Key noteCategoryKey) {
        this.noteCategoryKey = noteCategoryKey;
    }

    public List<NoteContent> getNoteContents() {
        return noteContents;
    }

    public void setNoteContents(List<NoteContent> noteContents) {
        this.noteContents = noteContents;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Note other = (Note) obj;
        if (this.key != other.key && (this.key == null || !this.key.equals(other.key))) {
            return false;
        }
        if ((this.subject == null) ? (other.subject != null) : !this.subject.equals(other.subject)) {
            return false;
        }
        if (this.createdAt != other.createdAt && (this.createdAt == null || !this.createdAt.equals(other.createdAt))) {
            return false;
        }
        if (this.updatedAt != other.updatedAt && (this.updatedAt == null || !this.updatedAt.equals(other.updatedAt))) {
            return false;
        }
        if (this.eqUser != other.eqUser && (this.eqUser == null || !this.eqUser.equals(other.eqUser))) {
            return false;
        }
        if (this.noteCategoryKey != other.noteCategoryKey && (this.noteCategoryKey == null || !this.noteCategoryKey.equals(other.noteCategoryKey))) {
            return false;
        }
        if (this.noteContents != other.noteContents && (this.noteContents == null || !this.noteContents.equals(other.noteContents))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 97 * hash + (this.subject != null ? this.subject.hashCode() : 0);
        hash = 97 * hash + (this.createdAt != null ? this.createdAt.hashCode() : 0);
        hash = 97 * hash + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
        hash = 97 * hash + (this.eqUser != null ? this.eqUser.hashCode() : 0);
        hash = 97 * hash + (this.noteCategoryKey != null ? this.noteCategoryKey.hashCode() : 0);
        hash = 97 * hash + (this.noteContents != null ? this.noteContents.hashCode() : 0);
        return hash;
    }
}
