package com.springboot.excelapi.resources;


import com.springboot.excelapi.dto.CustomerOrder;
import com.springboot.excelapi.dto.DemoDTO;
import com.springboot.excelapi.dto.ExampleDTO;
import com.springboot.excelapi.service.OrderService;
import com.springboot.excelapi.service.OrderServiceImpl;
import com.springboot.excelapi.services.ExcelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Description(value = "Resource layer for handling REST requests.")
@RestController
@RequestMapping("api")
public class ExcelResource {

    private ExcelService excelService;
    
    @Autowired
	OrderService orderService;
    
    private final Logger logger = LoggerFactory.getLogger(ExcelResource.class);

    /**
     * Constructor / dependency injector
     * @param excelService - service layer dependency.
     */
    public ExcelResource(ExcelService excelService) {
        this.excelService = excelService;
    }

    
    @PostMapping(value = "/upload_customers_orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<CustomerOrder>> uploadCompVibrAndTempoExcelTab(@RequestParam("file") MultipartFile uploadFile) throws IOException
    {
    	
    	logger.debug("uploadCompVibrAndTempoExcelTab() START: Single file upload!");
    	
        try {
			orderService.insertOrdersFromExcel(uploadFile);
		} catch (Exception ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);		
		}
        
        return new ResponseEntity<>(HttpStatus.OK);
    	
    }
}































