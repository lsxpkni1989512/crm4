package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.CustomerServiceMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
@Transactional
@Service
public class CustomerServiceService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	public Long save(CustomerService cs) {
		return customerServiceMapper.save(cs);
	}

	public List<Customer> getAllCustomers() {
		return customerServiceMapper.getAllCustomers();
	}

	public List<CustomerService> getAllCS() {
		return customerServiceMapper.getAllCS();
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
	
	@Transactional(readOnly=true)
	public Page<CustomerService> getDealPage(int pageNo, Map<String, Object> params) {
		Map<String, Object> params1 = parseRequestParams2MybatisParams(params);
		Page<CustomerService> page = new Page<>();
		
		int totalElements = (int) customerServiceMapper.getTotalElementsDeal(params1);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params1.put("fromIndex", fromIndex);
		params1.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.getContentDeal(params1);
		page.setContent(content);
		return page;
	}
	@Transactional(readOnly=true)
	public Page<CustomerService> getPage(int pageNo, Map<String, Object> params) {
		Map<String, Object> params1 = parseRequestParams2MybatisParams(params);
		Page<CustomerService> page = new Page<>();
		
		int totalElements = (int) customerServiceMapper.getTotalElements(params1);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params1.put("fromIndex", fromIndex);
		params1.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.getContent(params1);
		page.setContent(content);
		return page;
	}

	public void allot(CustomerService cs) {
		customerServiceMapper.allot(cs);
		
	}

	public CustomerService getEntityById(Long csId) {
		CustomerService cs =customerServiceMapper.getEntityById(csId);
		CustomerService csAllotName =customerServiceMapper.getAllotNameById(csId);
		
		cs.setAllotTo(csAllotName.getAllotTo());
		
		return cs;
	}

	public void updateDeal(CustomerService cs) {
		customerServiceMapper.updateDeal(cs);
		
	}

	public Page<CustomerService> getFeedBackPage(int pageNo, Map<String, Object> params) {
		Map<String, Object> params1 = parseRequestParams2MybatisParams(params);
		Page<CustomerService> page = new Page<>();
		
		int totalElements = (int) customerServiceMapper.getTotalElementsFeedBack(params1);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params1.put("fromIndex", fromIndex);
		params1.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.getContentFeedBack(params1);
		page.setContent(content);
		return page;
	}

	public CustomerService getFeedbackEntityById(Long csId) {
		CustomerService cs = customerServiceMapper.getFeedbackEntityById(csId);
		
		CustomerService hasAllotName = customerServiceMapper.getAllotNameById(csId);
		
		cs.setAllotTo(hasAllotName.getAllotTo());
		
		return cs;
	}

	public void updateFeedbacl(CustomerService cs) {
		customerServiceMapper.updateFeedbacl(cs);
		
	}

	public Page<CustomerService> getArchivePage(int pageNo, Map<String, Object> params) {
		Map<String, Object> params1 = parseRequestParams2MybatisParams(params);
		Page<CustomerService> page = new Page<>();
		
		int totalElements = (int) customerServiceMapper.getTotalElementsArchive(params1);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params1.put("fromIndex", fromIndex);
		params1.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.getContentArchive(params1);
		page.setContent(content);
		return page;
	}

	public CustomerService getArchiveEntityById(Long csId) {
		CustomerService cs = customerServiceMapper.getArchiveEntityById(csId);
		
		CustomerService hasAllotName = customerServiceMapper.getAllotNameById(csId);
		
		cs.setAllotTo(hasAllotName.getAllotTo());
		
		return cs;
	}

}
