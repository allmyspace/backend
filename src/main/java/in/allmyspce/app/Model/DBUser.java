package in.allmyspce.app.Model;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class DBUser {
    String username;
    String password;
    String dropboxtoken;
    String boxtoken;
    String dropboxtoken_created_at;
    String boxtoken_created_at;

    public DBUser(String username, String password, String dropboxtoken, String dropboxtoken_created_at, String boxtoken, String boxtoken_created_at) {
        this.username = username;
        this.password = password;
        this.dropboxtoken = dropboxtoken;
        this.dropboxtoken_created_at = dropboxtoken_created_at;
        this.boxtoken = boxtoken;
        this.boxtoken_created_at = boxtoken_created_at;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDropboxtoken() {
        return dropboxtoken;
    }

    public String getBoxtoken() {
        return boxtoken;
    }

    public String getDropboxtoken_created_at() {
        return dropboxtoken_created_at;
    }

    public String getBoxtoken_created_at() {
        return boxtoken_created_at;
    }
}
