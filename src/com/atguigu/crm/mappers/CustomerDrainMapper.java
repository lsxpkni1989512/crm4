package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {

	long getTotalElements(Map<String, Object> params);

	List<CustomerDrain> getContent(Map<String, Object> params);

	CustomerDrain getCustDrainById(Long drainId);

	void save(CustomerDrain cd);

	
	
}
