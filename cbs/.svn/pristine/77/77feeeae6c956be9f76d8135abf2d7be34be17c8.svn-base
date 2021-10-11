package com.springapp.mvc.data;

import com.springapp.mvc.global.base.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Dechen Wangdi on 4/8/2020.
 */

@Repository
public class FileUploadDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<FileUploadDTO> getUploadedFileList(String userName) {
        sqlQuery = properties.getProperty("FileUploadDao.getUploadedFileList");
        Query hQuery = hibernateQuery(sqlQuery, FileUploadDTO.class).setParameter("userName", userName);
        return hQuery.list();
    }

    @Transactional
    public void deleteFileByUserID(BigInteger fileId, String userName, Date serverDate) {
        sqlQuery = properties.getProperty("FileUploadDao.deleteFileByUserID");
        Query hQuery = hibernateQuery(sqlQuery).setParameter("fileId", fileId)
                .setParameter("userName", userName)
                .setParameter("serverDate", serverDate);
        hQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    public FileUploadDTO getFileUploadByFileId(BigInteger fileId) {
        sqlQuery = properties.getProperty("FileUploadDao.getFileUploadByFileId");
        Query hQuery = hibernateQuery(sqlQuery,FileUploadDTO.class).setParameter("fileId", fileId);
        return (FileUploadDTO)hQuery.uniqueResult();
    }
}
