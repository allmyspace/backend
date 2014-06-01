package in.allmyspce.app;
/**
 * @author : jknair
 */

import in.allmyspce.app.DAO.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    FileDAO fileDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpServletResponse response,HttpSession httpSession, ModelMap modelMap) throws IOException {
        if(httpSession.getAttribute("username")!=null)
        { String username=(String)httpSession.getAttribute("username");
        modelMap.put("fileList", fileDAO.getDirectory(username));//
        return new ModelAndView("dashboard", modelMap); }
        else
        {
         response.sendRedirect("localhost:8080/auth/login");
         return new ModelAndView("result");
        }
    }
}
