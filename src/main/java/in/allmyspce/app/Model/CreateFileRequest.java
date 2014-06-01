package in.allmyspce.app.Model;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateFileRequest {

    String uid;
    String lid;
    long ct;
    String rid;

    public String getUid() {
        return uid;
    }

    public String getLid() {
        return lid;
    }

    public long getCt() {
        return ct;
    }

    public String getRid() {
        return rid;
    }
}
