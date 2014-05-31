package in.allmyspce.app.Model;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateFileRequest {

    String username;
    String localPath;
    long created_at;
    String remote_id;

    public String getUsername() {
        return username;
    }

    public String getLocalPath() {
        return localPath;
    }

    public long getCreated_at() {
        return created_at;
    }

    public String getRemote_id() {
        return remote_id;
    }
}
