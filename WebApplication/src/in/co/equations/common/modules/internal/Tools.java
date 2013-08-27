package in.co.equations.common.modules.internal;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.Collections;
import java.util.logging.Logger;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.cache.CacheStatistics;

/**
 *
 * @author devashish
 */
public class Tools {
    private static final Logger LOG = Logger.getLogger(Tools.class.getName());

    public static void clearSessions() {
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("_ah_SESSION");
        PreparedQuery results = datastore.prepare(query);
         LOG.info("Deleting " + results.countEntities() + " sessions from data store");
        for (Entity session : results.asIterable()) {
            datastore.delete(session.getKey());
        }
    }

    // clearing everything in the cache, because sessions are also kept in   memcache 
    public static void clearCache() throws CacheException {
        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
        Cache cache = cacheFactory.createCache(Collections.emptyMap());
        CacheStatistics stats = cache.getCacheStatistics();

         LOG.info("Clearing " + stats.getObjectCount() +  " objects in cache");
        cache.clear();
    }
}
