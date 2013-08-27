package in.co.equations.modules.user;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Text;
import com.thenextpointer.db.PersistenceService;
import com.thenextpointer.pagination.ListServiceImpl;
import in.co.equations.modules.role.Role;
import in.co.equations.modules.course.Course;
import in.co.equations.modules.course.CourseCrudService;
import in.co.equations.modules.course.CourseService;
import in.co.equations.modules.note.Note;
import in.co.equations.modules.role.RoleCrudService;
import in.co.equations.modules.role.RoleService;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

@PersistenceCapable(detachable = "true")
@XmlRootElement(name = "eqUser")
public class EqUser implements Serializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
    @Persistent
    private String idNo;
    @Persistent
    private Email email;
    @Persistent
    private String name;
    @Persistent
    private long roleId;
    @NotPersistent
    private Role role;
    @Persistent
    private PhoneNumber primaryContactNumber;
    @Persistent
    private PhoneNumber alternateContactNumber;
    @Persistent
    private Text permanentAddress;
    @Persistent
    private Text correspondenceAddress;
    @Persistent
    private boolean smsNotification = true;
    @Persistent
    private Set<Key> coursesKey;
    @Persistent(defaultFetchGroup = "false", mappedBy = "eqUser", nullValue = NullValue.NONE)
    @Element(dependent = "true")
    private List<Note> notes;
    @NotPersistent
    private Set<Course> courses;
    @NotPersistent
    private Long userId;
    @NotPersistent
    private String keyString;
    @NotPersistent
    private CourseService courseService;
    @NotPersistent
    private RoleService roleService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public EqUser() {
        coursesKey = new HashSet<Key>();
        courses = new HashSet<Course>();
        PersistenceService<Role, Key> rolePersistenceService = new PersistenceService<Role, Key>();
        rolePersistenceService.setCrudService(new RoleCrudService());
        rolePersistenceService.setListService(new ListServiceImpl<Role>());
        roleService = new RoleService();
        roleService.setPersistenceService(rolePersistenceService);
        PersistenceService<Course, Key> coursePersistenceService = new PersistenceService<Course, Key>();
        coursePersistenceService.setCrudService(new CourseCrudService());
        coursePersistenceService.setListService(new ListServiceImpl<Course>());
        courseService = new CourseService();
        courseService.setPersistenceService(coursePersistenceService);
    }

    public EqUser(Email email) {
        coursesKey = new HashSet<Key>();
        courses = new HashSet<Course>();
        this.email = email;
    }

    public String getKeyString() {
        return (id != null ? KeyFactory.keyToString(id) : "");
    }

    public void setKeyString(String text) {
        if (text == null || text.length() == 0) {
            this.id = null;
        } else {
            try {
                this.id = KeyFactory.stringToKey(text);
            } catch (Exception e) {
                // log.error("Cannot parse key: " + text, e);
            }
        }
    }

    public String getIdNo() {
        return idNo;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setEmailValue(String email) {
        this.email = new Email(email);
    }

    public String getEmailValue() {
        return email != null ? this.email.getEmail() : "";
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /*public Role getRole() {
    
    System.out.println("RoleId :: "+ roleId );
    Key key = KeyFactory.createKey( Role.class.getSimpleName(), String.valueOf ( this.getRoleId() ) );
    role = (Role) roleService.getPersistenceService().getCrudService().read( key );
    return role;
    }
    public String getRoleName(){
    
    return  this.getRole().getName();
    
    }*/
    public String getCoursesNames() {
        Set<Course> coursesSet = this.getCourses();
        String courseStr = "";
        int i = 0;
        for (Course course : coursesSet) {
            if (i == 0) {
                courseStr = course.getName();
                i++;
            } else {
                courseStr = courseStr + "," + course.getName();
            }
        }

        return courseStr;
    }

    public void setCoursesNames(String[] courseKeys) {
        Set<Key> courseKeySet = new HashSet<Key>();
        courseKeys = courseKeys[0].split(",");
        for (String strKey : courseKeys) {
            Key gKey = KeyFactory.stringToKey(strKey);
            courseKeySet.add(gKey);
        }
        setCoursesKey(courseKeySet);
    }

    public PhoneNumber getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(PhoneNumber alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public PhoneNumber getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public String getPrimaryContactNumberValue() {
        return primaryContactNumber != null ? primaryContactNumber.getNumber() : "";
    }

    public void setPrimaryContactNumberValue(String priContectNum) {
        this.primaryContactNumber = new PhoneNumber(priContectNum);
    }

    public void setPrimaryContactNumber(PhoneNumber primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public Text getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(Text correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getCorrespondenceAddressValue() {
        return correspondenceAddress != null ? correspondenceAddress.getValue() : "";
    }

    public void setCorrespondenceAddressValue(String correspondenceAddress) {
        this.correspondenceAddress = new Text(correspondenceAddress);
    }

    public Text getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Text permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentAddressValue() {
        return permanentAddress != null ? permanentAddress.getValue() : "";
    }

    public void setPermanentAddressValue(String permanentAddress) {
        this.permanentAddress = new Text(permanentAddress);
    }

    public Set<Course> getCourses() {
        if (coursesKey != null && coursesKey.size() != 0) {
            for (Key key : coursesKey) {
                Course course = (Course) courseService.getCrudService().read(key);
                courses.add(course);
            }
        }
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        if (courses != null) {
            coursesKey.clear();
            for (Course course : courses) {
                Key key = course.getKey();
                coursesKey.add(key);
            }
        }
        this.courses = courses;
    }

    public Set<Key> getCoursesKey() {
        return coursesKey;
    }

    public void setCoursesKey(Set<Key> coursesKey) {
        this.coursesKey = coursesKey;
    }

    public boolean isSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(boolean smsNotification) {
        this.smsNotification = smsNotification;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        try {
            this.userId = Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            userId = null;
        }

    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
