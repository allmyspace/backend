package in.allmyspce.app.Model;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ModifyFileRequest {
    String username;
    String localPath;
    long modified_at;


    public String getUsername() {
        return username;
    }

    public String getLocalPath() {
        return localPath;
    }

    public long getModified_at() {
        return modified_at;
    }

}
