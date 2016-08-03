package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.ReportMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;

@Service
public class ReportService {

	@Autowired
	private ReportMapper reportMapper;
	
	@Transactional(readOnly=true)
	public Page<Map<String, Object>> getCustomerToalOrderMoneyPage(int pageNo, Map<String, Object> requestParams){
		Map<String, Object> params = parseRequestParams2MybatisParams(requestParams);
		
		Page<Map<String, Object>> page = new Page<>();
		
		int totalElements = (int) reportMapper.getCustomerToalOrderMoneyCount(params);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Map<String, Object>> content = reportMapper.getCustomerToalOrderMoneyContent(params);
		page.setContent(content);
		
		return page;
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
}
