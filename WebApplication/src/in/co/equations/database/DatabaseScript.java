package in.co.equations.database;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import com.thenextpointer.db.PMF;
import com.thenextpointer.db.PersistenceService;
import in.co.equations.modules.course.Course;
import in.co.equations.modules.course.CourseCrudService;
import in.co.equations.modules.note.Note;
import in.co.equations.modules.notification.Notification;
import in.co.equations.modules.notification.NotificationService;
import in.co.equations.modules.notificationtype.NotificationType;
import in.co.equations.modules.notificationtype.NotificationTypeCrudService;
import in.co.equations.modules.notificationtype.NotificationTypeService;
import in.co.equations.modules.role.Role;
import in.co.equations.modules.role.RoleCrudService;
import in.co.equations.modules.user.EqUser;
import in.co.equations.modules.user.EqUserCrudService;
import in.co.equations.modules.user.EqUserService;
import org.springframework.stereotype.Controller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.jdo.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class DatabaseScript {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationTypeService notificationTypeService;
    @Autowired
    private EqUserService eqUserService;
    private List<Key> keyList;

    //Todo: set null for notes in existing equsers.
    @PostConstruct
    public String createEntries() throws IOException, PreexistingEntityException, NonexistentEntityException, Exception {
        createRoles();
        createCourses();
        createEqUsers();
        createNotificationTypes();
        //createNotications();
        return "success";
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public NotificationTypeService getNotificationTypeService() {
        return notificationTypeService;
    }

    public void setNotificationTypeService(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    private void createRoles() throws PreexistingEntityException {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        keyList = new ArrayList<Key>();
        Role role1 = null;
        String query = "select from " + Role.class.getName();
        List<Role> roles = (List<Role>) pm.newQuery(query).execute();
        System.out.println(roles.size());
        if (roles.isEmpty()) {
            PersistenceService<Role, Key> persistenceService = new PersistenceService<Role, Key>();
            persistenceService.setCrudService(new RoleCrudService());
            role1 = new Role("1", "Admin", "Administrator");
            // role.setId(new Long(1));
            persistenceService.getCrudService().create(role1);
            Role role = new Role("2", "Faculty", "Faculty of institute");
            // role.setId(new Long(2));
            persistenceService.getCrudService().create(role);
            role = new Role("3", "Student", "Students of the institute");
            // role.setId(new Long(3));
            persistenceService.getCrudService().create(role);

            System.out.println("Role Creation completed.....");
        }
    }

    private void createNotications() throws NonexistentEntityException, Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        String query = "select from " + Notification.class.getName();
        List<Notification> notifications = (List<Notification>) pm.newQuery(query).execute();
        for (Notification notification : notifications) {
            notification.setNotificationTypeKey(null);
            notificationService.getCrudService().update(notification);
        }

        query = "select from " + NotificationType.class.getName();
        List<NotificationType> notificationTypes = (List<NotificationType>) pm.newQuery(query).execute();
        System.out.println(notificationTypes.size());
    }

    private void createNotificationTypes() throws PreexistingEntityException {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + NotificationType.class.getName();
        List<NotificationType> notificationTypes = (List<NotificationType>) pm.newQuery(query).execute();
        System.out.println(notificationTypes.size());

        if (notificationTypes.isEmpty()) {
            PersistenceService<NotificationType, Key> persistenceService = new PersistenceService<NotificationType, Key>();
            persistenceService.setCrudService(new NotificationTypeCrudService());
            NotificationType notificationType = new NotificationType("JOB.OPENINGS", "Job Openings");
            persistenceService.getCrudService().create(notificationType);
            notificationType = new NotificationType("EXAM.RESULTS", "Exam Results");
            persistenceService.getCrudService().create(notificationType);
            System.out.println("Notification Type creation completed.....");
        }
    }

    private void createEqUsers() throws PreexistingEntityException {        
                
        EqUser eqUser = eqUserService.getEqUser(new Email("devashish.mamgain@gmail.com"));
        
        if (eqUser == null) {
            PersistenceService<EqUser, Key> persistenceService = new PersistenceService<EqUser, Key>();
            persistenceService.setCrudService(new EqUserCrudService());
            eqUser = new EqUser();
            eqUser.setIdNo("developer");
            eqUser.setEmail(new Email("devashish.mamgain@gmail.com"));
            eqUser.setName("devashish");
            List<Note> notes = new ArrayList<Note>();
            eqUser.setNotes(notes);
            PersistenceService<Course, Key> coursePersistenceService = new PersistenceService<Course, Key>();
            coursePersistenceService.setCrudService(new CourseCrudService());
            Course course = coursePersistenceService.getCrudService().read(keyList.get(0));
            eqUser.getCoursesKey().add(course.getKey());
            course = coursePersistenceService.getCrudService().read(keyList.get(1));
            eqUser.getCoursesKey().add(course.getKey());
            //eqUser.setRoleId(role1.getKey().getId());
            eqUser.setRoleId(1);
            persistenceService.getCrudService().create(eqUser);
            System.out.println("Developer account creation completed.....");
        }
    }

    private void createCourses() throws PreexistingEntityException {
        String query = "select from " + Course.class.getName();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        List<Course> courses = (List<Course>) pm.newQuery(query).execute();
        System.out.println(courses.size());

        if (courses.isEmpty()) {
            PersistenceService<Course, Key> persistenceService = new PersistenceService<Course, Key>();
            persistenceService.setCrudService(new CourseCrudService());
            Course course = new Course("1", "BANK_PO", "Bank Probationary Officer");
            persistenceService.getCrudService().create(course);
            keyList.add(course.getKey());
            course = new Course("2", "BANK_CLERICAL", "Bank Clerical");
            persistenceService.getCrudService().create(course);
            keyList.add(course.getKey());
            course = new Course("3", "MAT", "MAT Entrance Exam for admission to MBA");
            persistenceService.getCrudService().create(course);
            keyList.add(course.getKey());
            course = new Course("4", "OTHER", "Other Course");
            persistenceService.getCrudService().create(course);
            keyList.add(course.getKey());
            System.out.println("Course creation completed.....");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                Course course1 = courses.get(i);
                keyList.add(course1.getKey());
            }
        }
    }
   
}
