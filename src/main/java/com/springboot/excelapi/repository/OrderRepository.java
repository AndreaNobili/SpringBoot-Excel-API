package com.springboot.excelapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.excelapi.dto.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
