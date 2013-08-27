package com.thenextpointer.crud;

import com.thenextpointer.exceptions.NonexistentEntityException;
import com.thenextpointer.exceptions.PreexistingEntityException;
import java.io.Serializable;

public interface CrudService<T, PK extends Serializable> extends Serializable {

    /** Persist the newInstance object into database
     * @throws PreexistingEntityException */
    T create(T newInstance) throws PreexistingEntityException;

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  
     * @throws Exception 
     * @throws NonexistentEntityException */
    T update(T transientObject) throws NonexistentEntityException, Exception;

    /** Remove an object from persistent storage in the database 
     * @throws NonexistentEntityException */
    void delete(T persistentObject) throws NonexistentEntityException;
    
    void delete(PK id) throws NonexistentEntityException;
}
