package in.allmyspce.app;

import in.allmyspce.app.DAO.UserDao;
import in.allmyspce.app.service.BoxTokenService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: sachin.go
 * Date: 6/1/14
 * Time: 12:40 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/box")
public class boxController {

    private static String CLIENT_ID = "fqh2onk3sw4qjj5uk5ykembdcxa2qtd5";
    private static String CLIENT_SECRET = "8wNFvWkBi0JslKhE3TNFUwZaM0N3NbkE";
    private static String REDIRECT_URI = "http://localhost:8080/box/accept";
    @Autowired
    UserDao userDao;
    @Autowired
    BoxTokenService boxTokenService;
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showBoxLogin(HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException {
        StringBuilder url = new StringBuilder();
        url.append("https://www.box.com/api/oauth2/authorize?response_type=code&client_id=");
        url.append(CLIENT_ID);
        url.append("&state=security_token%3DKnhMJatFipTAnM0nHlZA");

        URL obj = new URL(url.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();
        httpServletResponse.sendRedirect(url.toString());
        return "hello";
    }

    @RequestMapping(value = "accept", method = RequestMethod.GET)
    public String getToken(@RequestParam(value = "code") String code,HttpServletResponse response, HttpServletRequest httpRequest,HttpSession session) throws IOException, ParseException {
        String url = "https://www.box.com/api/oauth2/token";
        String username = (String) session.getAttribute("username");
        String urlParameters = "grant_type=authorization_code&code=" + code + "&client_id=" + CLIENT_ID+"&client_secret="+CLIENT_SECRET;
        JSONObject jsonObject = boxTokenService.getJsonResultFromPost(urlParameters, url);

            userDao.setBoxToken(jsonObject.get("access_token").toString(), jsonObject.get("refresh_token").toString(), username);
        response.sendRedirect("/hello");
        return "hello";
    }



}
