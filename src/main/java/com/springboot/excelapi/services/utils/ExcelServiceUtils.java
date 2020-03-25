package com.springboot.excelapi.services.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.excelapi.dto.CustomerOrder;
import com.springboot.excelapi.resources.ExcelResource;

public class ExcelServiceUtils {
	
	private static String UPLOADED_FOLDER = "/home/developer/ExcelUpload/";
	private final static Logger logger = LoggerFactory.getLogger(ExcelServiceUtils.class);
	
	
	/**
	 * Return the object represent a specified Excel sheet into a specified Excel document.
	 * @param fileName the name of the Excel document.
	 * @param sheetName the name of the Excel sheet into the specified Excel document.
	 * @return the Sheet object representing the specified Excel sheet into the specified Excel document.
	 * @throws IOException 
	 */
	public static Sheet getExcelSheet(String fileName, String sheetName) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(UPLOADED_FOLDER + fileName);
        
        //String fileName = resource.getFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);

        Workbook workbook = null;
        Sheet sheet = null;
        
        // Get workbook and sheet:
        if(fileExtension.equals("xlsx") || fileExtension.contentEquals("XLSX")) {
        	workbook = new XSSFWorkbook(inputStream);
        	//sheet = workbook.getSheet("COMP VIBR & TEMP.");
        	
        	//sheet = workbook.getSheet(sheetName + "."); 
        	sheet = workbook.getSheet(sheetName);
        }
        else if(fileExtension.equals("xls") || fileExtension.equals("XLS")) {
        	workbook = new HSSFWorkbook(inputStream);
        	//sheet = workbook.getSheet("COMP VIBR & TEMP."); 
        	//sheet = workbook.getSheet(sheetName + "."); 
        	sheet = workbook.getSheet(sheetName);
        }
		
        return sheet;	
	}
	
	/**
	 * Handle the upload of the Excel document. Check if the file have the correct extension and save it into
	 * a specific directory.
	 * @param uploadfile the uploaded file that have to be saved.
	 * @return the uploaded file name.
	 * @throws Exception contains the message error that can be used as return values for the error case by the API.
	 */
	public static String uploadExcelDocument(MultipartFile uploadfile) throws Exception {
		String fileName = uploadfile.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
    	
    	if (uploadfile.isEmpty()) 
    	{
    		throw new Exception("Please select a file!");
            //return new ResponseEntity("Please select a file!", HttpStatus.OK);
        }
    	else if(!fileExtension.equals("XLS")  && 
    			!fileExtension.equals("xls")  &&
    			!fileExtension.equals("XLSX") &&
    			!fileExtension.equals("xlsx"))
    	{
    		throw new Exception("Please select an Excel file");
    		//return new ResponseEntity("Please select an Excel file", HttpStatus.OK);
    	}
    	
    	try 
    	{
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
        	throw new Exception("BAD REQUEST");
        }
    	
    	return fileName;
	}
	
	/**
	 * Save the uploaded file into a specific folder
	 * @param files the uploaded file that have to be saved
	 * @throws IOException
	 */
	private static void saveUploadedFiles(List<MultipartFile> files) throws IOException {
    	
    	logger.debug("saveUploadedFiles() START");
    	
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }
        
        logger.debug("saveUploadedFiles() END");
    	
    }
	
	/**
	 * Method that return a list of DTO for the "CustomerOrder" sheet from this Excel sheet
	 * @param sheet a specific Excel Sheet
	 * @return the list of "CompVibrAndTemp" DTOs representing all the rows into the specified Excel sheet
	 */
	
	public static List<CustomerOrder> getCustomersOrdersFromExcelSheet(Sheet sheet) {
		
        List<CustomerOrder> ordersList = new ArrayList<>();

        // iterate through rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext())
        {
            Row currentRow = iterator.next();

            // skip heading row.
            if (currentRow.getRowNum() == 0) {
                continue;
            }
            
            CustomerOrder currentOrder = new CustomerOrder();
            
            currentOrder.setFullName(currentRow.getCell(0).getStringCellValue());
            currentOrder.setAddress(currentRow.getCell(1).getStringCellValue());
            currentOrder.setOrderDate(currentRow.getCell(2).getDateCellValue());
            currentOrder.setProduct(currentRow.getCell(3).getStringCellValue());
            currentOrder.setQuantity((int)currentRow.getCell(4).getNumericCellValue());
            
            ordersList.add(currentOrder);

            
        }
        
        return ordersList;
     
    }
    
		

}
