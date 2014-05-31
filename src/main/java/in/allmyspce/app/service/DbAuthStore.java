package in.allmyspce.app.service;

import com.dropbox.core.DbxWebAuth;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 5/31/14
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DbAuthStore {
    private final ConcurrentHashMap<String,DbxWebAuth> store;

    public DbAuthStore() {
        store = new ConcurrentHashMap<>();
    }
    public DbxWebAuth getAuth(String key)
    {
        return store.get(key);
    }
    public void putAuth(String key, DbxWebAuth auth)
    {
        store.put(key,auth);
    }
}
