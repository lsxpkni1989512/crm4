package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;

public interface CustomerMapper {

	long getTotalElements(Map<String, Object> params);

	List<Customer> getContent(Map<String, Object> params);

	List<String> getAllRegions();

	List<String> getAllLevels();

	List<Contact> getAllManagers();
	
	List<String> getAllSatify();

	Customer getCustById(Long custId);

	List<String> getAllCredits();

	void update(Customer cust);

	void delete(Long custId);
	
	@Update("{call check_drain}")
	void test();


}
