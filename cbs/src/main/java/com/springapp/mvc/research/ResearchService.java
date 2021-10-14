package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.enumeration.ApplicationStatusCode;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResearchService extends BaseService {

    @Autowired
    private ResearchDAO researchDAO;

    private ApplicationStatusCode applicationStatusCode;


    public ResponseMessage save(MultipartFile file, CurrentUser currentUser, ResearchDTO researchDTO) throws IOException {
        if(Objects.isNull(file)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose file to upload.");
        }
        String filePath = uploadFile(file, currentUser);
        Long wordCount =  wordCount(filePath);
//        String filePath ="";
        ResearchEntity researchEntity = convertDTOToEntity(researchDTO, filePath, currentUser, wordCount);
        researchDAO.save(researchEntity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully");
        return responseMessage;
    }

    private ResearchEntity convertDTOToEntity(ResearchDTO researchDTO, String filePath, CurrentUser currentUser, Long wordCount){
        ResearchEntity researchEntity = new ResearchEntity();
        researchEntity.setResearchTopic(researchDTO.getResearchTopic());
        researchEntity.setResearch_description(researchDTO.getResearch_description());
        researchEntity.setFilepath(filePath);
        researchEntity.setCreatedBy(currentUser.getUserName());
        researchEntity.setCreatedDate(new Date());
        researchEntity.setWordCount(wordCount);
//        researchEntity.setStatus(applicationStatusCode.find(1));
//        researchEntity.setStatus("SUBMITTED");
        researchEntity.setStatus(1);
        return researchEntity;
    }

    private Long wordCount(String filePath) throws IOException {
        String line;
        Long count;
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(filePath)))) {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            System.out.println(docText);
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

    private String uploadFile(MultipartFile file, CurrentUser currentUser) throws IOException {
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy");
        dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = dateTimeInGMT.format(new Date());
        Resource resource = new ClassPathResource("/lang/fileUpload.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String rootpath = properties.getProperty("fileUpload.loc");
        rootpath = rootpath +"/"+ currentUser.getUserName() + "/" + date + "/" +file.getOriginalFilename();
        Path path = Paths.get(rootpath);
        byte[] bytes = file.getBytes();
        Path parentDir = path.getParent();
        if(!Files.exists(parentDir))
            Files.createDirectories(parentDir);
        Files.write(path, bytes);
        return rootpath;
    }

    public List<ResearchDTO> getResearchList(CurrentUser currentUser) {
        return researchDAO.getResearchList(currentUser);
    }

    public List<ResearchDTO> geAllResearchList() {
        return researchDAO.geAllResearchList();
    }

    public ResponseMessage saveReviewerComments(Integer researchId, String rComment, Integer statusId) {
        researchDAO.saveReviewerComments(researchId, rComment, statusId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully");
        return responseMessage;
    }
}
