package bt.cbs.zrr.data;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dechen Wangdi on 4/8/2020.
 */

@Service
public class FileUploadService extends BaseService {


    //region private variables.
    @Autowired
    private FileUploadDao fileUploadDao;

    @Autowired
    private CommonService commonService;
    //endregion

    //region public methods.
    public List<FileUploadDTO> getUploadedFileList(String userName) {
        return fileUploadDao.getUploadedFileList(userName);
    }

    public ResponseMessage deleteFileByUserID(BigInteger fileId, CurrentUser currentUser) {

        if (Objects.isNull(fileId)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("No file is to delete.");
            return responseMessage;
        }

        Object obj = commonService.getValue("common_file_detail", "file_location", "file_id", fileId);

        File fileToDelete = new File(String.valueOf(obj));
        fileToDelete.delete();

        fileUploadDao.deleteFileByUserID(fileId, currentUser.getUserName(), currentUser.getServerDate());

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("File ID#" + fileId + " is deleted successfully.");

        return responseMessage;
    }

    public void viewFile(HttpServletResponse response, BigInteger fileId) throws IOException {

        FileUploadDTO fileUploadDTO = fileUploadDao.getFileUploadByFileId(fileId);

        if (fileUploadDTO != null) {

            File file = new File(fileUploadDTO.getFile_location());
            byte[] bFile = Files.readAllBytes(file.toPath());
            if (file.exists())
                try {
                    downloadFile(bFile, fileUploadDTO.getFile_name(), response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    public ResponseMessage downloadFile(byte[] file, String fileName, HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();
        if (fileName == null || fileName.isEmpty()) {
            responseMessage.setStatus(0);
            responseMessage.setText("File Name is empty");
            return responseMessage;
        }
        if (file == null) {
            responseMessage.setStatus(0);
            responseMessage.setText("No file to download");
            return responseMessage;
        }

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExt) {
            case "xlsx":
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "xls":
                response.setContentType("application/vnd.ms-excel");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "docx":
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "doc":
                response.setContentType("application/msword");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "pdf":
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "inline:filename=" + fileName);
                break;
            case "png":
                response.setContentType("image/png");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpg":
                response.setContentType("image/jpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpeg":
                response.setContentType("image/pjpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "txt":
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "csv":
                response.setContentType("text/csv");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
        }
        response.setContentLength(file.length);
        FileCopyUtils.copy(file, response.getOutputStream());
        responseMessage.setStatus(1);
        responseMessage.setText("File downloaded successfully.");

        return responseMessage;

    }
    //endregion

}
