package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mappers.CustomerDrainMapper;
import com.atguigu.crm.mappers.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;

@Service
public class CustomerDrainService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	
	
	public void test(){
		customerMapper.test();
	}

	private Map<String, Object> parseRequestParams2MybatisParams(
			Map<String, Object> requestParams) {
		List<PropertyFilter> filters = PropertyFilter.parseRequestParamsToPropertyFilters(requestParams);
		
		Map<String, Object> params = new HashMap<>();
		
		for(PropertyFilter filter: filters){
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		
		return params;
	}

	public Page<CustomerDrain> getPage(int pageNo, Map<String, Object> params1) {
		Map<String, Object> params = parseRequestParams2MybatisParams(params1);
		Page<CustomerDrain> page = new Page<>();
		
		int totalElements = (int) customerDrainMapper.getTotalElements(params);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<CustomerDrain> content = customerDrainMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	public CustomerDrain getCustDrainById(Long drainId) {
		return customerDrainMapper.getCustDrainById(drainId);
	}

	public void save(CustomerDrain cd) {
		customerDrainMapper.save(cd);
		
	}
}
