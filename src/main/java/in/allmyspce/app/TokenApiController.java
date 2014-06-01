package in.allmyspce.app;

import in.allmyspce.app.DAO.UserDao;
import in.allmyspce.app.Model.CreateFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/api/")
public class TokenApiController {
    @Autowired
    UserDao userDao;
    @RequestMapping(value="token/{service}/{username}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public HashMap<String, String> getToken(@PathVariable String service, @PathVariable final String username){
        System.out.println("service "+service);
        if(service.equals("dropbox"))
        {
            return new HashMap<String,String>(){
                { put("token",userDao.getDropboxToken(username));
                    }
            };
        }
       else if(service.equals("box"))
        {
            return new HashMap<String,String>(){
                { put("token",userDao.getBoxToken(username));
                }
            };
        }
        return null;
    }
    @RequestMapping(value="tokens/{username}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public HashMap<String, String> getTokens( @PathVariable final String username){

              return new HashMap<String,String>(){
                { put("dropbox",userDao.getDropboxToken(username));
                }
                { put("box",userDao.getBoxToken(username));
                }
            };



    }

}
