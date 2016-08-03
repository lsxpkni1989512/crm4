package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mappers.ProductMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	private Map<String, Object> parseRequestParams2MybatisParams(
			Map<String, Object> requestParams) {
		List<PropertyFilter> filters = PropertyFilter.parseRequestParamsToPropertyFilters(requestParams);
		
		Map<String, Object> params = new HashMap<>();
		
		for(PropertyFilter filter: filters){
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		
		return params;
	}
	
	public Page<Product> getPage(int pageNo, Map<String, Object> params1) {
		Map<String, Object> params = parseRequestParams2MybatisParams(params1);
		Page<Product> page = new Page<>();
		
		int totalElements = (int) productMapper.getTotalElements(params);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Product> content = productMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	public void addProduct(Product produce) {
		productMapper.addProduct(produce);
		
	}

	public void delete(Long id) {
		productMapper.delete(id);
		
	}

	public Product getProductById(Long id) {
		return productMapper.getProductById(id);
	}

	public void update(Product pro) {
		productMapper.update(pro);
		
	}
}
