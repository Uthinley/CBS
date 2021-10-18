package com.springapp.mvc.global.library;

import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class CustomFileUtil {

    public static String uploadFile(MultipartFile attachment,String sub_folder, String fileName){
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            try {

                Resource resource = new ClassPathResource("/properties/fileUpload.properties");
                Properties props = PropertiesLoaderUtils.loadProperties(resource);
                rootPath = props.getProperty("fileUpload.loc");

                rootPath = rootPath + "/" + sub_folder + "/" + fileName;

                byte[] bytes = attachment.getBytes();
                Path path = Paths.get(rootPath);

                // Decode the file name (might contain spaces and on) and prepare file object.
                Path parentDir = path.getParent();
                if (!Files.exists(parentDir))
                    Files.createDirectories(parentDir);
                Files.write(path, bytes);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return rootPath;
    }

    public static void viewDownloadFile(String documentPath,HttpServletResponse response) throws Exception{
        if(documentPath != null) {
            //documentPath = documentPath.replaceAll("///","\\");
            File file = new File(documentPath);
            byte[] bFile = Files.readAllBytes(file.toPath());
            if(file.exists())
                try {
                    downloadFile(bFile,file.getName(),response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static void downloadFile(byte[] file, String fileName, HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExt) {
            case "xlsx":
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "xls":
                response.setContentType("application/vnd.ms-excel");
                //response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                break;
            case "docx":
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                //response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                break;
            case "doc":
                response.setContentType("application/msword");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "pdf":
                response.setContentType("application/pdf");
                //response.addHeader("Content-Disposition", "inline:filename=" + fileName);
                break;
            case "png":
                response.setContentType("image/png");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpg":
                response.setContentType("image/jpeg");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpeg":
                response.setContentType("image/pjpeg");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "txt":
                response.setContentType("application/octet-stream");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "csv":
                response.setContentType("text/csv");
                //response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
        }

        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentLength(file.length);
        FileCopyUtils.copy(file, response.getOutputStream());

    }

    public static String getFileEXT(MultipartFile attachment){
        String originalFN = attachment.getOriginalFilename();
        return Objects.requireNonNull(originalFN).substring(originalFN.lastIndexOf("."));
    }

}
