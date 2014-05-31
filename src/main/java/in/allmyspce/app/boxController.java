package in.allmyspce.app;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.omg.DynamicAny.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

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
    private static String REDIRECT_URI = "http://localhost/box/accept";

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
    public String getToken(@RequestParam(value = "code") String code, HttpServletRequest httpRequest) throws IOException {
        String url = "https://www.box.com/api/oauth2/token";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "grant_type=authorization_code&code=" + code + "&client_id=" + CLIENT_ID+"&client_secret="+CLIENT_SECRET;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return "hello";
    }
}
