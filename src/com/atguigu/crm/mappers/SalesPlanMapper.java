package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	long getTotalElements(Map<String, Object> params);

	List<SalesChance> getContent(Map<String, Object> params);

	SalesChance getSalesChanceById(Long id);
	SalesChance getDesigneeNameByDesigneeId(Long id);

	void savePlan(SalesPlan plan);

	SalesPlan getSalesPlanByPlanId(@Param("id")Long id);

	void updatePlan(SalesPlan plan);

	List<SalesPlan> getAllPlan(Long id);

	void delete(Long planId);

	void savePlanResult(@Param("id")Long planId, @Param("result")String result);

}
