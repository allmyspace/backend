package in.allmyspce.app;

import in.allmyspce.app.DAO.FileDAO;
import in.allmyspce.app.Model.CreateFileRequest;
import in.allmyspce.app.Model.DeleteFileRequest;
import in.allmyspce.app.Model.FileDetails;
import in.allmyspce.app.Model.ModifyFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

        fileDAO.createFile(request.getLid(),request.getUid(),
                service,request.getRid(),request.getMt());


    }
    @RequestMapping(value="modify/{service}",method = RequestMethod.POST,consumes = "application/json")
    public void modifyFile(@RequestBody ModifyFileRequest request, @PathVariable String service){

        fileDAO.modifyFile(request.getLid(),request.getUid(),request.getMt());
    }
    @RequestMapping(value="delete/{service}",method = RequestMethod.POST,consumes = "application/json")
    public void deleteFile(@RequestBody DeleteFileRequest request, @PathVariable String service){        fileDAO.deleteFile(request.getLid(), request.getUid(),service,request.getLid() );
    }
    @RequestMapping(value = "directory/{username}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public HashMap<String, List<FileDetails>> getDirectory(@PathVariable String username)
    {
        HashMap<String,List<FileDetails>> result=new HashMap<>();
        result.put("box",fileDAO.getDirectory("box",username));
        result.put("dropbox",fileDAO.getDirectory("dropbox",username));
        return result;

    }


}
