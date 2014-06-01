package in.allmyspce.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpSession session,HttpServletResponse response) throws IOException {
        if(session.getAttribute("username")!=null)
            response.sendRedirect("/hello");
        else
            response.sendRedirect("/auth/login");
		model.addAttribute("message", "Hello world!");
		return "result";
	}
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String printhello(ModelMap model,HttpSession session,HttpServletResponse response) throws IOException {

        model.addAttribute("message", "Hello world!");
        return "hello";
    }
}