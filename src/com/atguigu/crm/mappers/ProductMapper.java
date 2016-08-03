package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Product;

public interface ProductMapper {

	List<Product> getContent(Map<String, Object> params);
	
	long getTotalElements(Map<String, Object> params);

	void addProduct(Product produce);

	void delete(Long id);

	Product getProductById(Long id);

	void update(Product pro);
}
