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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    FileDAO fileDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpSession httpSession, ModelMap modelMap) {
        modelMap.put("fileList", fileDAO.getDirectory((String) httpSession.getAttribute("username")));
        return new ModelAndView("dashboard", modelMap);
    }
}
