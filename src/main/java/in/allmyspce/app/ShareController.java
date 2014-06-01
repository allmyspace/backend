package in.allmyspce.app;


import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import in.allmyspce.app.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    @Autowired
    DbxRequestConfig requestConfig;
    @Autowired
    UserDao userDao;
    @RequestMapping(value = "dropbox/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getdropboxSharedLink(@PathVariable String id,HttpSession session) throws DbxException {   String username=(String) session.getAttribute("username");
        DbxClient client=new DbxClient(requestConfig,userDao.getDropboxToken(username));
        String url=client.createShareableUrl(id);
        userDao.addDropboxShareURL(username,id,url);
        return url;
    }
}
