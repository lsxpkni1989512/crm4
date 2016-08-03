package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.SalesPlanMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesPlanService {
	@Autowired
	private SalesPlanMapper salesPlanMapper;

	public Page<SalesChance> getPage(int pageNo, User createBy) {
		Page<SalesChance> page = new Page<>();

		page.setPageNo(pageNo);

		Map<String,Object> params = new HashMap<>();
		
		params.put("status", 1);
		
		params.put("createBy", createBy);
		
		int totalElements = (int) salesPlanMapper.getTotalElements(params);

		page.setTotalElements(totalElements);

//		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
//		int endIndex = fromIndex + page.getPageSize();
		
		
		
		int fromIndex = (page.getPageNo() - 1 ) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesPlanMapper.getContent(params);

		page.setContent(content);
		
		return page;
	}

	public SalesChance getSalesChanceById(Long id) {
		return salesPlanMapper.getSalesChanceById(id);
	}
	public SalesChance getDesigneeNameByDesigneeId(Long id) {
		return salesPlanMapper.getDesigneeNameByDesigneeId(id);
	}

	public void savePlan(SalesPlan plan) {
		
		salesPlanMapper.savePlan(plan);
		
	}

	public SalesPlan getSalesPlanByPlanId(Long id) {
		return salesPlanMapper.getSalesPlanByPlanId(id);
	}

	public void update(SalesPlan plan) {
		salesPlanMapper.updatePlan(plan);
		
	}

	public List<SalesPlan> getAllPlan(Long id) {
		return salesPlanMapper.getAllPlan(id);
	}

	public void deletePlan(Long planId) {
		salesPlanMapper.delete(planId);
		
	}

	public void savePlanResult(Long planId, String result) {
		salesPlanMapper.savePlanResult(planId, result);
		
	}
	
	

}
