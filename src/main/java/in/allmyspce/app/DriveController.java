package in.allmyspce.app;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.api.client.auth.oauth2.AuthorizationRequestUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: sachin.go
 * Date: 5/31/14
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/drive/accesstoken")
public class DriveController {

    private static String CLIENT_ID = "401774623329-8s53l7gvf0e824qmfe7cgf3a6loi60o1.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "qYUmt-r4CcAhbpNDwKXo9cie";
    private static String REDIRECT_URI = "http://localhost/drive/accesstoken/accept";

    @RequestMapping(method = RequestMethod.GET)
    public String showGoogleLogin(HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
                .setAccessType("online")
                .setApprovalPrompt("auto").build();
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        httpServletResponse.sendRedirect(url);
        return "hello";
    }

    @RequestMapping(value = "accept", method = RequestMethod.GET)
    public String getToken(ModelMap modelMap, HttpServletRequest httpRequest){
        modelMap.put("message", httpRequest.getRequestURL());
        return "hello";
    }
}
