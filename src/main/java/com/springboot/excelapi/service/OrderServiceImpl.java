package com.springboot.excelapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.excelapi.dto.CustomerOrder;
import com.springboot.excelapi.repository.OrderRepository;
import com.springboot.excelapi.services.utils.ExcelServiceUtils;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<CustomerOrder> getOrdersList() {
		List<CustomerOrder> result = (List<CustomerOrder>) orderRepository.findAll();
		return result;
	}


	@Override
	public void insertSingleOrder(CustomerOrder order) {
		orderRepository.save(order);
	}
	
	/**
     * Method that processes the "Sheet1" tab of the provided Excel sheet and insert its content
     * into a specific database table
     * 
     * @param fileName the file that have to be processed and inserted into the DB
     * @return a Boolean value which specifies the outcome of the transaction
     * @throws Exception 
     */
    public Boolean insertOrdersFromExcel(MultipartFile uploadfile) throws Exception {
    	Boolean result = true;
    	
	    try {
	    	String fileName = ExcelServiceUtils.uploadExcelDocument(uploadfile);
	    	String tabName = "TAB1";
	    	
	    	Sheet sheet = ExcelServiceUtils.getExcelSheet(fileName, tabName);
	    	
	    	List<CustomerOrder> rowsList = ExcelServiceUtils.getCustomersOrdersFromExcelSheet(sheet);
	    	
	    	Iterator<CustomerOrder> rowsListIterator = rowsList.iterator();
	    	CustomerOrder currentOrder;
	    	
	    	while(rowsListIterator.hasNext()) {
	    		currentOrder = rowsListIterator.next();	
	    		
	    		orderRepository.save(currentOrder);		
	    	}
    	} catch (Exception e) {
			return false;
		}
    	
    	return result;
    }

}
