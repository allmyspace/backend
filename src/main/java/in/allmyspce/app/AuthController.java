package in.allmyspce.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 6:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage()
    {
        return "login";
    }
    @RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
    public String getLoginUser(String username,String password,HttpSession session,ModelMap modelMap,HttpServletResponse response) throws IOException {
        session.setAttribute("username",username);
        modelMap.put("message",session.getAttribute("username"));
        response.sendRedirect("/dashboard");
        return "result";

    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogoutPage(HttpSession session)
    {
        session.invalidate();
        return "login";
    }
}
