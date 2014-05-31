package in.allmyspce.app;

import com.dropbox.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 5/31/14
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/dropbox/token")
public class DropboxTokenController {
    DbxWebAuth auth;
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(HttpServletResponse response,ModelMap model,HttpSession session) throws IOException {
        DbxRequestConfig requestConfig = new DbxRequestConfig("AllMySpaceDev/0.1", null);
        DbxAppInfo appInfo = new DbxAppInfo("0iy80szhisdycxk","m3ulpaqq3rnrydp");
        String sessionKey = "dropbox-auth-csrf-token";
        DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session, sessionKey);
        String redirectUri = "http://localhost:8080/dropbox/token/tokenRedirect";
        auth = new DbxWebAuth(requestConfig, appInfo, redirectUri, csrfTokenStore);
        model.addAttribute("message", "Hello world!");
        String authorizePageUrl = auth.start();
        response.sendRedirect(authorizePageUrl);
        return "hello";
    }
    @RequestMapping(value = "/tokenRedirect",method = RequestMethod.GET)
    public String OAuthRedirect(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException {
        DbxAuthFinish authFinish = null;
        try {
            authFinish = auth.finish(request.getParameterMap());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
        assert authFinish != null;
        String accessToken = authFinish.accessToken;
        modelMap.put("token",accessToken);
        return "redirected";
    }
}
