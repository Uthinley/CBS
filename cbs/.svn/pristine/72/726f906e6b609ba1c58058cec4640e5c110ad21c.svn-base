package com.springapp.mvc.data;

import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by RMA on 3/23/2020.
 */

@Controller
@RequestMapping(value = "/fileUpload")
//@PreAuthorize("isAuthenticated()")
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;


    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "data/fileUpload";
    }

    @ResponseBody
    @RequestMapping(value = "/getUploadedFileList", method = RequestMethod.GET)
    public List<FileUploadDTO> getUploadedFileList(HttpServletRequest request, HttpServletResponse response) {
        currentUser = getCurrentUser(request);
        return fileUploadService.getUploadedFileList(currentUser.getUserName());
    }


    @ResponseBody
    @RequestMapping(value = "/deleteFileByUserID", method = RequestMethod.POST)
    public ResponseMessage deleteFileByUserID(HttpServletRequest request, BigInteger fileId) throws IOException {
        currentUser = getCurrentUser(request);
        return fileUploadService.deleteFileByUserID(fileId, currentUser);
    }

    @RequestMapping(value = "/viewFile", method = RequestMethod.GET)
    public void viewFile(HttpServletResponse response, BigInteger fileId) throws IOException {
        fileUploadService.viewFile(response, fileId);
    }
}
