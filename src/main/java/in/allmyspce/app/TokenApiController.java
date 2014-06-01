package in.allmyspce.app;

import in.allmyspce.app.DAO.UserDao;
import in.allmyspce.app.Model.CreateFileRequest;
import in.allmyspce.app.service.BoxTokenService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private static String CLIENT_ID = "fqh2onk3sw4qjj5uk5ykembdcxa2qtd5";
    private static String CLIENT_SECRET = "8wNFvWkBi0JslKhE3TNFUwZaM0N3NbkE";
    @Autowired
    UserDao userDao;
    @Autowired
    BoxTokenService boxTokenService;
    @RequestMapping(value="token/{service}/{username}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public HashMap<String, String> getToken(@PathVariable String service, @PathVariable final String username) throws IOException, ParseException {
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
                { put("token",getTokenForBox(username));
                }
            };
        }
        return null;
    }
    @RequestMapping(value="tokens/{username}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public HashMap<String, String> getTokens( @PathVariable final String username) throws IOException, ParseException {

              return new HashMap<String,String>(){
                { put("dropbox",userDao.getDropboxToken(username));
                }
                { put("box",getTokenForBox(username));
                }
            };
    }
    private String getTokenForBox(String username) throws IOException, ParseException {
        Long time = userDao.getBoxAccessTokenCreationDate(username);
        long currTime = System.currentTimeMillis() / 1000l;
        if(time != -1 && (currTime-3500) >= time){
            refreshAccessToken(username);

        }
        return userDao.getBoxToken(username);

    }
    public void refreshAccessToken(String username) throws IOException, ParseException {
        String url = "https://www.box.com/api/oauth2/token";

        String refreshToken = userDao.getBoxRefreshToken(username);
        String urlParameters = "grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=" + CLIENT_ID+"&client_secret="+CLIENT_SECRET;
        JSONObject jsonObject = boxTokenService.getJsonResultFromPost(urlParameters, url);
        userDao.setBoxToken(jsonObject.get("access_token").toString(), jsonObject.get("refresh_token").toString(), username);
    }
}
