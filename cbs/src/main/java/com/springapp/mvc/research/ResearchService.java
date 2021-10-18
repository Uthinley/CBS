package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.enumeration.ApplicationStatusCode;
import com.springapp.mvc.global.library.CustomFileUtil;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.aspectj.util.FileUtil;
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


    public ResponseMessage save(MultipartFile file, CurrentUser currentUser, ResearchDTO researchDTO) throws IOException {
        if(Objects.isNull(file)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose file to upload.");
        }

        String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        String sub_folder = currentUser.getUserName() + "/" + date + "/" +file.getOriginalFilename();

        String filePath = CustomFileUtil.uploadFile(file,sub_folder,file.getOriginalFilename());
        Long wordCount =  CustomFileUtil.wordCount(file.getInputStream());
        researchDTO.setWordCount(wordCount);
        researchDTO.setFilePath(filePath);
        ResearchEntity researchEntity = convertDTOToEntity(researchDTO, currentUser);
        researchDAO.save(researchEntity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your research has been recorded and forwarded for review.");
        return responseMessage;
    }

    private ResearchEntity convertDTOToEntity(ResearchDTO researchDTO, CurrentUser currentUser){
        ResearchEntity researchEntity = new ResearchEntity();
        researchEntity.setResearchTopic(researchDTO.getResearchTopic());
        researchEntity.setResearch_description(researchDTO.getResearch_description());
        researchEntity.setFilepath(researchDTO.getFilePath());
        researchEntity.setCreatedBy(currentUser.getUserName());
        researchEntity.setCreatedDate(new Date());
        researchEntity.setWordCount(researchDTO.getWordCount());
        researchEntity.setStatus(ApplicationStatusCode.SUBMITTED.getValue());;
        return researchEntity;
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
