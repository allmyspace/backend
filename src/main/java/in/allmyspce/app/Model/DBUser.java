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
    String boxrefreshtoken;
    long dropboxtoken_created_at;
    long boxtoken_created_at;

    public DBUser(String username, String password, String dropboxtoken,String boxtoken, String boxrefreshtoken, long dropboxtoken_created_at, long boxtoken_created_at) {
        this.username = username;
        this.password = password;
        this.dropboxtoken = dropboxtoken;
        this.dropboxtoken_created_at = dropboxtoken_created_at;
        this.boxrefreshtoken = boxrefreshtoken;
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

    public long getDropboxtoken_created_at() {
        return dropboxtoken_created_at;
    }

    public long getBoxtoken_created_at() {
        return boxtoken_created_at;
    }

    public String getBoxrefreshtoken() {
        return boxrefreshtoken;
    }

    public void setBoxrefreshtoken(String boxrefreshtoken) {
        this.boxrefreshtoken = boxrefreshtoken;
    }
}
