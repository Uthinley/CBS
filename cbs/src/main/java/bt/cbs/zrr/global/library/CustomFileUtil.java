package bt.cbs.zrr.global.library;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class CustomFileUtil {

    public static String uploadFile(MultipartFile attachment,String sub_folder, String fileName) throws IOException {
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            Resource resource = new ClassPathResource("/lang/fileUpload.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            rootPath = props.getProperty("fileUpload.loc");

            rootPath = rootPath + "/" + sub_folder + "/" + fileName;

            byte[] bytes = attachment.getBytes();
            Path path = Paths.get(rootPath);

            Path parentDir = path.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);

        }
        return rootPath;
    }

    public static Long wordCount(InputStream inputStream) throws IOException {
        String line;
        Long count;
        try (XWPFDocument doc = new XWPFDocument(inputStream)) {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            // find number of words in the document
            count = Arrays.stream(docText.split("\\s+")).count();

        }
//        File convFile = new File(file.getOriginalFilename());
//        convFile.createNewFile();
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        FileInputStream fis = new FileInputStream(convFile);
//        byte[] bytesArray = new byte[(int)convFile.length()];
//        fis.read(bytesArray);
//        String s = new String(bytesArray);
//        String [] data = s.split(" ");
//         for(int i = 0; i< data.length; i++){
//             count++;
//         }
        return count;
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
