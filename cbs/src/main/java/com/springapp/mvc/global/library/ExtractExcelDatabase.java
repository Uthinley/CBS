package com.springapp.mvc.global.library;


import com.springapp.mvc.data.FileUploadDTO;
import com.springapp.mvc.data.FileUploadDetailDTO;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RMA on 3/23/2020.
 */
public class ExtractExcelDatabase {

    @Transactional
    public ResponseMessage readFromExcel(MultipartFile file,CurrentUser currentUser) throws IOException {
        FileUploadDTO exchangeBuyFCDTO = new FileUploadDTO();
        ResponseMessage responseMessage = new ResponseMessage();
        if(file == null){
            responseMessage.setStatus(0);
            responseMessage.setText("Please select a file.");
            return responseMessage;
        }

        String filename = file.getOriginalFilename();
        String fileExt = filename.substring(filename.lastIndexOf(".")+1,filename.length()).toUpperCase();
        Boolean isValidFile = isValidFile(fileExt);
        if(!isValidFile){
            responseMessage.setStatus(0);
            responseMessage.setText("Invalid file!!! Please choose only XLS or XLSX file.");
            return responseMessage;
        }
        Workbook workbook = null;
        if(fileExt.equals("XLSX")){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if(fileExt.equals("XLS")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet sheet = workbook != null?workbook.getSheetAt(0):null;
        if(sheet == null || sheet.getLastRowNum() < 1){
            responseMessage.setStatus(0);
            responseMessage.setText("Selected file is empty.");
            return responseMessage;
        }

        StringBuilder serialNos = new StringBuilder();

        try {

            int i = 0;
            int lastRow = sheet.getLastRowNum();
            int lastCol = 22;
            while (i <= lastRow) {
                for(int j = 0; j<lastCol;j++) {
                    Row row = sheet.getRow(i);
                    System.out.print("Row"+i+" : Col"+j+" - "+row.getCell(j).getStringCellValue());
                    /*switch (i) {
                        case 5:
                            String passportNo = row.getCell(6).getStringCellValue();
                            exchangeBuyFCDTO.setPassportNo(passportNo);
                            i = 10;
                            break;
                        case 10:
                            String cyAbbrev = row.getCell(2).getStringCellValue();
                            cyAbbrev = cyAbbrev.equals("EUR") ? cyAbbrev + "O" : cyAbbrev;
                            Date exDate = new SimpleDateFormat("yyyy MM/dd").parse(row.getCell(6).getStringCellValue());
                            if (exDate != null && DateUtils.truncate(exDate, Calendar.DATE).after(new Date())) {
                                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                                responseMessage.setText("Your exchange date cannot be greater than current date");
                                return responseMessage;
                            }
                            exchangeBuyFCDTO.setExDate(exDate);
                            currencyDTO.setCyAbbrev(cyAbbrev);
                            currencyDTO.setCurrencyCode(currencySetupService.getCurrencyCode(cyAbbrev));
                            exchangeRateDTO = rateService.getExchangeRates(currencyDTO.getCurrencyCode()
                                    , currentUser.getBranchCode(), exDate, currentUser.getBankId());
                            if (exchangeRateDTO == null) {
                                rateService.save(currentUser, exDate);
                                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                                responseMessage.setText("Please try again.");
                                return responseMessage;
                            }
                            currencyDTO.setRate(exchangeRateDTO.getExNoteBuy());
                            currencyDTO.setCommission(exchangeRateDTO.getCommissionDTO().getCommissionCh());
                            i = 12;
                            break;
                        case 19:
                            String denomination = row.getCell(1).getStringCellValue();
                            String denomCount = row.getCell(3).getStringCellValue();
                            BigDecimal rate = exchangeRateDTO.getExNoteBuy();
                            DenominationDTO denominationDTO = setDenomination(denomination, denomCount, currencyDTO);
                            if (denominationDTO != null)
                                denominationDTOs.add(denominationDTO);
                            i = 27;
                            break;
                        default:
                            if (i >= 12 && i < 19) {
                                denomination = row.getCell(1).getStringCellValue();
                                denomCount = row.getCell(3).getStringCellValue();
                                rate = exchangeRateDTO.getExNoteBuy();
                                denominationDTO = setDenomination(denomination, denomCount, currencyDTO);
                                if (denominationDTO != null)
                                    denominationDTOs.add(denominationDTO);
                                i++;
                            }
                            if (i >= 27) {
                                if (row.getCell(4) == null && row.getCell(10) == null) {
                                    lastRow = i;
                                }
                                String serialNo5Col = row.getCell(4) == null ? null : row.getCell(4).getStringCellValue();
                                String serialNo11Col = row.getCell(10) == null ? null : row.getCell(10).getStringCellValue();
                                if (serialNo5Col != null && !serialNo5Col.isEmpty())
                                    if (!serialNo5Col.contains("?????"))
                                        serialNos.append(",").append(serialNo5Col);
                                if (serialNo11Col != null && !serialNo11Col.isEmpty())
                                    if (!serialNo11Col.contains("?????"))
                                        serialNos.append(",").append(serialNo11Col);
                                i++;

                            }
                            break;

                    }*/
                }

            }
        }catch (Exception e){
            responseMessage.setStatus(0);
            responseMessage.setText("Please check the format for your excel sheet. It must be machine generated one.");
            return responseMessage;
        }
        responseMessage.setStatus(0);
        return responseMessage;

    }

    private Boolean isValidFile(String fileExt){
        return fileExt.equals("XLS") || fileExt.equals("XLSX");
    }

    private ArrayList<FileUploadDetailDTO> list = new ArrayList<>();

    public  static void readFromExcel(String file) throws IOException {
        String excelFilePath = "C:\\Users\\RMA\\Desktop\\"+file;
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        for (Row nextRow : firstSheet) {
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    /*case Cell.CELL_TYPE_STRING://To get string value.
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN://To get boolean value.
//                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC://To get Number value
                        System.out.print(cell.getNumericCellValue());
                        break;*/
                }
                System.out.print(" - ");
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();
    }


    public static List getStudentsListFromExcel(MultipartFile file) {
        List dtoList = new ArrayList();
        try {
            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                    FileUploadDetailDTO dto = new FileUploadDetailDTO();
                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = (Cell) cellIterator.next();
                        //The Cell Containing String will is name.) {
                            dto.setDate(cell.getStringCellValue());

                            //The Cell Containing numeric value will contain amount

                            //Cell with index 1 contains marks in Maths
                            if (cell.getColumnIndex() == 1) {
                                dto.setBobAmount(String.valueOf(cell.getNumericCellValue()));
                            }
                            else  if (cell.getColumnIndex() == 2) {
                                dto.setBnbAmount(String.valueOf(cell.getNumericCellValue()));
                            }
                            else if(cell.getColumnIndex() == 3){
                                dto.setPnbAmount(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 2 contains marks in Science
                            else if (cell.getColumnIndex() == 4) {
                                dto.setBdlbAmount(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in English
                            else if (cell.getColumnIndex() == 5) {
                                dto.setTbankAmount(String.valueOf(cell.getNumericCellValue()));
                            }

                    }
                    //end iterating a row, add all the elements of a row in list
                    dtoList.add(dto);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dtoList;
    }

}
