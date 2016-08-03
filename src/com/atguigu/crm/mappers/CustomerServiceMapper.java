package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;

public interface CustomerServiceMapper {

	Long save(CustomerService cs);

	List<Customer> getAllCustomers();

	List<CustomerService> getAllCS();

	long getTotalElements(Map<String, Object> params1);

	List<CustomerService> getContent(Map<String, Object> params);

	void allot(CustomerService cs);

	long getTotalElementsDeal(Map<String, Object> params1);

	List<CustomerService> getContentDeal(Map<String, Object> params1);

	CustomerService getEntityById(Long csId);

	CustomerService getAllotNameById(Long csId);

	void updateDeal(CustomerService cs);

	long getTotalElementsFeedBack(Map<String, Object> params1);

	List<CustomerService> getContentFeedBack(Map<String, Object> params1);

	CustomerService getFeedbackEntityById(Long csId);

	void updateFeedbacl(CustomerService cs);

	long getTotalElementsArchive(Map<String, Object> params1);

	List<CustomerService> getContentArchive(Map<String, Object> params1);

	CustomerService getArchiveEntityById(Long csId);
}
