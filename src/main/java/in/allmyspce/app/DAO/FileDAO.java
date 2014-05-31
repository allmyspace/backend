package in.allmyspce.app.DAO;

import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FileDAO {
    public void createFile(String fileid,String username,String parentFolder,String service,String remoteId,long modifiedAt) {

    }

    public void modifyFile(String fileName, String username,long modified_at ) {

    }

    public void deleteFile(String fileName, String username, String service, String localPath) {

    }
}
