package in.allmyspce.app.DAO;

import in.allmyspce.app.Model.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao {
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.template=jdbcTemplate;
    }
    JdbcTemplate template;
    public void setDropboxToken(String token,String username)
    {
      template.update("update users set dropboxtoken=?,dropboxtoken_created_at=? where username=?",
              token,System.currentTimeMillis()/1000,username);
    }
    public void setBoxToken(String token, String refreshToken, String username)
    {
        template.update("update users set boxtoken=?, refresh_token=?, boxtoken_created_at=? where username=?",
                token,System.currentTimeMillis()/1000,username);
    }

    public String getBoxRefreshToken(String username){
        return template.queryForObject("select refresh_token from users where username=?",String.class, username);
    }
    public String getDropboxToken(String username)
    {
        return template.queryForObject("select dropboxtoken from users where username=?",String.class,username);
    }
    public String getBoxToken(String username)
    {
        return template.queryForObject("select boxtoken from users where username=?",String.class,username);
    }
    public DBUser getUserByUsername(String username)
    {
        return template.queryForObject("select * from users where username=?",new RowMapper<DBUser>() {
            @Override
            public DBUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return new DBUser(resultSet.getString("username"),resultSet.getString("password"),
                    resultSet.getString("dropboxtoken"),resultSet.getString("boxtoken"), resultSet.getString("box_refresh_token"),
                    resultSet.getLong("dropboxtoken_created_at"),resultSet.getLong("boxtoken_created_at"));
            }
        },username);
    }

    public long getBoxAccessTokenCreationDate(String username) {
        return Long.parseLong(template.queryForObject("select boxtoken_createdat from users where username=?",String.class,username));
    }
}
