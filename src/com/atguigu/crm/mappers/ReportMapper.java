package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

	List<Map<String, Object>> getCustomerToalOrderMoneyContent(Map<String, Object> params);
	
	long getCustomerToalOrderMoneyCount(Map<String, Object> params);
	
}
