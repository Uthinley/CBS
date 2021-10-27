package bt.cbs.zrr.data;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Nima Yoezer on 3/23/2020.
 * Description :
 * Purpose:
 * Modified by :
 * Modified on :
 */
public class FileUploadDTO implements Serializable {
    private MultipartFile uploadFile;
    private MultipartFile detailFile;
    private BigInteger bankId;
    private Integer iDataName;
    private BigInteger file_id;
    private String file_name;
    private String file_location;
    private Date reportDate;
    private String remark;
    private String username;

    private List<FileUploadDTO> fileUploadDTOList;

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public BigInteger getBankId() {
        return bankId;
    }

    public void setBankId(BigInteger bankId) {
        this.bankId = bankId;
    }

    public Integer getiDataName() {
        return iDataName;
    }

    public void setiDataName(Integer iDataName) {
        this.iDataName = iDataName;
    }

    public BigInteger getFile_id() {
        return file_id;
    }

    public void setFile_id(BigInteger file_id) {
        this.file_id = file_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public MultipartFile getDetailFile() {
        return detailFile;
    }

    public void setDetailFile(MultipartFile detailFile) {
        this.detailFile = detailFile;
    }

    public List<FileUploadDTO> getFileUploadDTOList() {
        return fileUploadDTOList;
    }

    public void setFileUploadDTOList(List<FileUploadDTO> fileUploadDTOList) {
        this.fileUploadDTOList = fileUploadDTOList;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
