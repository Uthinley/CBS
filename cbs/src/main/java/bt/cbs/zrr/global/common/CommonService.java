package bt.cbs.zrr.global.common;

import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.global.library.GlobalVariable;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import bt.cbs.zrr.global.enumeration.ApplicationStatusCode;
import bt.cbs.zrr.global.enumeration.ScreenType;
import bt.cbs.zrr.global.enumeration.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * ====================================================================
 * Created by Dechen Wangdi on 05/12/2019.
 * Description:
 * ====================================================================
 */
@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

    @Transactional(readOnly = true)
    public List<DropdownDTO> getScreenList() {
        return commonDao.getScreenList();
    }

    public List<DropdownDTO> getScreenByTypeList(Character screenType) {
        return commonDao.getScreenByTypeList(screenType);
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getUserGroupList() {
        return commonDao.getUserGroupList();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getResearchMonthList() {
        return commonDao.getResearchMonthList();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getBankList() {
        return commonDao.getBankList(StatusCode.ACTIVE.getValue());
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getTSWCFLoans() {
        return commonDao.getTSWCFLoans(StatusCode.ACTIVE.getValue());
    }

    /**
     * To get status list
     *
     * @return List<DropdownDTO>
     */
    public List<DropdownDTO> getStatusList() {
        List<DropdownDTO> statusList = new ArrayList<>();
        DropdownDTO dropdownDTO;
        for (StatusCode statusCode : StatusCode.values()) {
            dropdownDTO = new DropdownDTO(statusCode.getValue(), statusCode.getText());
            statusList.add(dropdownDTO);
        }
        return statusList;
    }

    /**
     * To get application status list
     *
     * @return List<DropdownDTO>
     */
    public List<DropdownDTO> getApplicationStatusCode() {
        List<DropdownDTO> applicationStatusCodeList = new ArrayList<>();
        DropdownDTO dropdownDTO;
        for (ApplicationStatusCode applicationStatusCode : ApplicationStatusCode.values()) {
            dropdownDTO = new DropdownDTO(applicationStatusCode.getValue(), applicationStatusCode.getText());
            applicationStatusCodeList.add(dropdownDTO);
        }
        return applicationStatusCodeList;
    }

    public List<DropdownDTO> getScreenTypeList() {
        List<DropdownDTO> screenTypeList = new ArrayList<>();
        DropdownDTO dropdownDTO;
        for (ScreenType screenType : ScreenType.values()) {
            dropdownDTO = new DropdownDTO(screenType.getValue(), screenType.getText());
            screenTypeList.add(dropdownDTO);
        }
        return screenTypeList;
    }

    public List<DropdownDTO> getBranchCodeList() {
        return commonDao.getBranchCodeList(StatusCode.ACTIVE.getValue());
    }

    public List<DropdownDTO> getReportList() {
        return commonDao.getReportList(StatusCode.ACTIVE.getValue());
    }

    public Object getValue(String tblName, String colName, String filterCol, Object filterVal) {
        return commonDao.getValue(tblName, colName, filterCol, filterVal);
    }

    public Object getNextID(String tblName, String colName) {
        return commonDao.getNextID(tblName, colName);
    }

    public Date getDateFromString(String sDate) throws ParseException {
        return new SimpleDateFormat("dd-MMM-yyyy").parse(sDate);
    }

    /**
     * read excel file and convert to object class
     *
     * @param mFile    -- MultipartFile
     * @param classObj -- Class to map
     * @return List
     * @throws IOException
     */
    public List readExcel(MultipartFile mFile, Class classObj) throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(0).datePattern("dd-rererwerrrrwrwer-yyyy").build();
        InputStream inputStream = mFile.getInputStream();
        List list = Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, classObj, options);
        inputStream.close();
        return list;
    }

    /**
     * read from excel file from specified row number only
     *
     * @param mFile    -- MultipartFile
     * @param classObj -- Class to map
     * @param row      --  row number to read from
     * @return List
     * @throws IOException
     */
    public List readExcel(MultipartFile mFile, Class classObj, int row) throws IOException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(row).datePattern("dd-MMM-yyyy").build();
        InputStream inputStream = mFile.getInputStream();
        List list = Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, classObj, options);
        inputStream.close();
        return list;

    }

    /**
     * read from excel file from specified row number only
     *
     * @param file     -- MultipartFile
     * @param classObj -- Class to map
     * @param row      --  row number to read from
     * @return List
     * @throws IOException
     */
    public List readExcel(MultipartFile file, Class classObj, int row, Date reportDate, String username) throws Exception {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(row).preferNullOverDefault(true).datePattern("dd-MMM-yyyy").ignoreHiddenSheets(true).build();
        InputStream inputStream = file.getInputStream();
        List list = Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, classObj, options);
        inputStream.close();

       /* String rootPath = uploadFile(file, username);
        saveFileDetail(rootPath, file.getContentType(), file.getOriginalFilename(), reportDate, username);*/
        return list;

    }

    /**
     * read from excel file from specified row number only
     *
     * @param file     -- MultipartFile
     * @param classObj -- Class to map
     * @param row      --  row number to read from
     * @return List
     * @throws IOException
     */
    public List readExcel(MultipartFile file, Class classObj, int row, Date reportDate, int sheetIndex, CurrentUser currentUser) throws Exception {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(row).preferNullOverDefault(true).sheetIndex(sheetIndex).datePattern("dd-MMM-yyyy").build();
        InputStream inputStream = file.getInputStream();
        List list = Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, classObj, options);
        inputStream.close();
        String rootPath = uploadFile(file, currentUser);
        saveFileDetail(rootPath
                , file.getContentType()
                , file.getOriginalFilename()
                , reportDate
                , currentUser);
        return list;

    }

    /**
     * read excel file and convert to object class
     *
     * @param mFile    -- MultipartFile
     * @param classObj -- Class to map
     * @return List
     * @throws IOException
     */
    public List readExcel(MultipartFile mFile, Class classObj, PoijiOptions options) throws IOException {
        InputStream inputStream = mFile.getInputStream();
        List list = Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, classObj, options);
        inputStream.close();
        return list;
    }

    /**
     * To upload the attachment to the file location
     *
     * @param attachment -- file
     * @throws Exception
     */
    public String uploadFile(MultipartFile attachment, CurrentUser currentUser) throws Exception {
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String folder = simpleDateFormat.format(new Date());

            Resource resource = new ClassPathResource("/lang/fileUpload.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            rootPath = props.getProperty("fileUpload.loc");

            rootPath = rootPath + folder + "/" + currentUser.getUserName() + "/" + attachment.getOriginalFilename();

            byte[] bytes = attachment.getBytes();
            Path path = Paths.get(rootPath);

            Path parentDir = path.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);

        }
        return rootPath;
    }


    public void saveFileDetail(String loc, String fileType, String fileName, Date rDate, CurrentUser currentUser) {
        BigInteger file_id = (BigInteger) getNextID("common_file_detail", "file_id");
        FileDetailEntity fileDetail = new FileDetailEntity();
        fileDetail.setFile_id(file_id);
        fileDetail.setFile_name(fileName);
        fileDetail.setFile_location(loc);
        fileDetail.setFile_type(fileType);
        fileDetail.setReport_date(rDate);
        fileDetail.setCreatedBy(currentUser.getUserName());
        fileDetail.setCreatedDate(currentUser.getServerDate());
        commonDao.saveUpdate(fileDetail);
        GlobalVariable.FILE_ID = file_id;
    }


}
