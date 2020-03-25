package com.springboot.excelapi.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.springboot.excelapi.dto.CustomerOrder;
import com.springboot.excelapi.dto.DemoDTO;
import com.springboot.excelapi.dto.ExampleDTO;
import com.springboot.excelapi.services.utils.ExcelServiceUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Description(value = "Service layer responsible for processing data.")
@Service
public class ExcelService {

    /**
     * Method for reading from specific excel file when we know types and number of cells.
     * The readed rows are inserted into the "customer_order" database table
     *
     * @return list of mapped objects
     * @throws IOException - throws IO exception.
     */
    public void insertDataFromExcel() throws IOException
    {
        // get file that needs to be mapped into object.
        Resource resource = new ClassPathResource("documents/sample.xlsx");
        FileInputStream inputStream = new FileInputStream(resource.getFile());

        // get workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

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
        
    }

    
    
    
}
