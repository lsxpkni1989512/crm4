package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mappers.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;

@Transactional
@Service
public class CustService {
	
	@Autowired
	private CustomerMapper customerMapper;

	
	private Map<String, Object> parseRequestParams2MybatisParams(
			Map<String, Object> requestParams) {
		List<PropertyFilter> filters = PropertyFilter.parseRequestParamsToPropertyFilters(requestParams);
		
		Map<String, Object> params = new HashMap<>();
		
		for(PropertyFilter filter: filters){
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		
		return params;
	}
	
	public Page<Customer> getPage(int pageNo, Map<String, Object> params1) {
		Map<String, Object> params = parseRequestParams2MybatisParams(params1);
		Page<Customer> page = new Page<>();
		
		int totalElements = (int) customerMapper.getTotalElements(params);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Customer> content = customerMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	public List<String> getAllRegions() {
		return customerMapper.getAllRegions();
	}

	public List<String> getAllLevels() {
		return customerMapper.getAllLevels();
	}

	public List<Contact> getAllManagers() {
		return customerMapper.getAllManagers();
	}
	
	public List<String> getAllCredits() {
		return customerMapper.getAllCredits();
	}
	
	public List<String> getAllSatify() {
		return customerMapper.getAllSatify();
	}

	public Customer getCustById(Long custId) {
		return customerMapper.getCustById(custId);
	}

	public void update(Customer cust) {
		customerMapper.update(cust);
		
	}

	public void delete(Long custId) {
		customerMapper.delete(custId);
		
	}
}
