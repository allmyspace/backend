package in.allmyspce.app;

import in.allmyspce.app.DAO.FileDAO;
import in.allmyspce.app.Model.CreateFileRequest;
import in.allmyspce.app.Model.DeleteFileRequest;
import in.allmyspce.app.Model.ModifyFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 6/1/14
 * Time: 1:40 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/api")
public class FileAPIController {
    @Autowired
    FileDAO fileDAO;



    @RequestMapping(value="create/{service}",method = RequestMethod.POST,consumes = "application/json")
    public void createFile(@RequestBody CreateFileRequest request, @PathVariable String service){
        String[] paths=request.getLocalPath().split("/");
        String fileName=paths[paths.length-1];
        fileDAO.createFile(fileName,request.getUsername(),request.getLocalPath().substring(0,request.getLocalPath().length()-fileName.length()+1),
                service,request.getRemote_id(),request.getCreated_at());


    }
    @RequestMapping(value="modify/{service}",method = RequestMethod.POST,consumes = "application/json")
    public void modifyFile(@RequestBody ModifyFileRequest request, @PathVariable String service){
        String[] paths=request.getLocalPath().split("/");
        String fileName=paths[paths.length-1];
        fileDAO.modifyFile(fileName,request.getUsername(),request.getModified_at());
    }
    @RequestMapping(value="delete/{service}",method = RequestMethod.POST,consumes = "application/json")
    public void deleteFile(@RequestBody DeleteFileRequest request, @PathVariable String service){
        String[] paths=request.getLocalPath().split("/");
        String fileName=paths[paths.length-1];
        fileDAO.deleteFile(fileName, request.getUsername(),service,request.getLocalPath() );
    }
    @RequestMapping(value = "directory/{username}")


}
