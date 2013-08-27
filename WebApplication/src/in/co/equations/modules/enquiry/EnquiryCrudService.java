package in.co.equations.modules.enquiry;

import com.google.appengine.api.datastore.Key;
import com.thenextpointer.crud.CrudServiceImpl;
import com.thenextpointer.db.PMF;
import com.thenextpointer.exceptions.PreexistingEntityException;
import java.util.Date;
import javax.jdo.PersistenceManager;

/**
 *
 * @author devashish
 */
public class EnquiryCrudService extends CrudServiceImpl<Enquiry, Key>{
    @Override
    public Enquiry create(Enquiry enquiry) throws PreexistingEntityException {
        enquiry.setDated(new Date());
        return super.create(enquiry);
    }

    @Override
    public Enquiry update(Enquiry updatedEnquiry) {
        Enquiry existingEnquiry = null;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            existingEnquiry = pm.getObjectById( Enquiry.class, updatedEnquiry.getKey());
            existingEnquiry.setType( updatedEnquiry.getType() );
            existingEnquiry.setName( updatedEnquiry.getName() );
            existingEnquiry.setSubject( updatedEnquiry.getSubject() );
            existingEnquiry.setComment( updatedEnquiry.getComment() );
            existingEnquiry.setEmail( updatedEnquiry.getEmail() );
            existingEnquiry.setContactNumber( updatedEnquiry.getContactNumber() );
        } finally {
            pm.close();
        }
        return existingEnquiry;

    }

    @Override
    public void delete(Enquiry updatedEnquiry) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
             Object dataobject = pm.getObjectById(Enquiry.class, updatedEnquiry.getKey());
             pm.deletePersistent(dataobject);
        } finally {
            pm.close();
        }
      
    }
}
