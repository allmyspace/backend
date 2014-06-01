package in.allmyspce.app.DAO;

import in.allmyspce.app.Model.FileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FileDAO {
    @Autowired
    JdbcTemplate template;
    public void createFile(String localPath,String username,String service,String remoteId,long modifiedAt) {
      template.update("insert into files(local_path,username,service,remote_id,modified_at) values (?,?,?,?,?)",localPath,username,service,remoteId,modifiedAt);
    }

    public void modifyFile(String localPath, String username,long modified_at ) {
     template.update("update files set modified_at=? where username=? and local_path=?",modified_at,username,localPath);
    }

    public void deleteFile(String localPath, String username, String service) {
     template.update("delete  from files where username=? and local_path=?",username,localPath);
    }

    public List<HashMap<String, Object>> getDirectory(String service, String username) {
        return template.query("Select * from files where username=? and service =?",new RowMapper<HashMap<String, Object>>() {
            @Override
            public HashMap<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                HashMap<String,Object> result=new HashMap<>();
                result.put("rid",resultSet.getString("remote_id"));
                result.put("lid",resultSet.getString("local_path"));
                result.put("mt",resultSet.getLong("modified_at"));
                result.put("sl",resultSet.getString("shared_link"));
                return result;
            }
        },username, service);

    }
}
