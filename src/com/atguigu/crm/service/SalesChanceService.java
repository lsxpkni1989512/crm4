package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.SalesChanceMapper;
import com.atguigu.crm.mappers.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Transactional(readOnly = true)
	public Page<SalesChance> getPage(int pageNo, User createBy) {

		Page<SalesChance> page = new Page<>();

		page.setPageNo(pageNo);

		Map<String,Object> params = new HashMap<>();
		
		params.put("status", 1);
		
		params.put("createBy", createBy);
		
		int totalElements = (int) salesChanceMapper.getTotalElements(params);

		page.setTotalElements(totalElements);

//		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
//		int endIndex = fromIndex + page.getPageSize();
		
		
		
		int fromIndex = (page.getPageNo() - 1 ) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.getContent(params);

		page.setContent(content);
		
		return page;

	}

	public void input(SalesChance chance) {
		chance.setStatus(1);
		salesChanceMapper.save(chance);
		
	}

	public void delete(Long id) {
		salesChanceMapper.delete(id);
		
	}

	public SalesChance getSalesChanceById(Long id) {
		return salesChanceMapper.getSalesChanceById(id);
	}

	public void update(SalesChance chance) {
		salesChanceMapper.update(chance);
		
	}
	/**
	 * 去更新指派人和指派时间
	 * @param chance
	 */
	public void dispatch(SalesChance chance) {
		chance.setStatus(2);
		
		salesChanceMapper.dispatch(chance);
		
	}

	public SalesChance finishDevelop(Long id) {
		
		SalesChance chance = salesChanceMapper.getSalesChanceById(id);
		
		salesChanceMapper.updateStatus(id, 3);
		return chance;
	}
/*
	@Transactional(readOnly = true)
	public Page<SalesChance> getConditionPage(String pageNoStr,
			Map<String, Object> requestParams) {
		
		Map<String, Object> params = DataProcessUtils.getMybatisMap(requestParams);
		//根据需要的信息状态和当前用户查询符合条件的总记录数
		int totalRecordNo = (int)salesChanceMapper.getTotalRecord(params);
		
		//根据页码和总记录数创建page对象，修正页码
		Page<SalesChance> page = new Page<>(pageNoStr, totalRecordNo);
		int pageNo = page.getPageNo();//获取修正后的页码
		
		//计算开始记录和结束记录
		int startNum = (pageNo - 1) * Page.PAGE_SIZE + 1;
		int endNum = startNum + Page.PAGE_SIZE;
		
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		
		//根据开始和结束脚标查询分页记录
		List<SalesChance> list = salesChanceMapper.getConditionList(params);
		page.setList(list);
		return page;
	}
*/

	public SalesChance getSalesChanceAndPlansById(Long chanceId) {
		//SalesChance sc = salesChanceMapper.getAllotAndCreatedName(chanceId);
		SalesChance scYes = salesChanceMapper.getSalesChanceAndPlansById(chanceId);
		User designee = userMapper.getUserById(scYes.getDesignee().getId());
		User createBy = userMapper.getUserById(scYes.getCreateBy().getId());
		scYes.setCreateBy(createBy);
		scYes.setDesignee(designee);
		return scYes;
		
	}

	public void stop(Long chanceId) {
		salesChanceMapper.stop(chanceId);
		
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
	
	public Page<SalesChance> getPage2(int pageNo, Map<String, Object> params1) {
		/*User user = (User) params1.get("createBy");*/
		
		

		Map<String, Object> params = parseRequestParams2MybatisParams(params1);
		Page<SalesChance> page = new Page<>();
		
	/*	params.put("createById", user.getId());*/
		
		int totalElements = (int) salesChanceMapper.getTotalElements2(params);
		page.setTotalElements(totalElements);
		page.setPageNo(pageNo);
		
		//若需要查詢當前額頁面對應的 Content, 則還需要傳入類似于 fromInde 和 endIndex 的變量.
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = fromIndex + page.getPageSize();
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		
		
		List<SalesChance> content = salesChanceMapper.getContent2(params);
		page.setContent(content);
		return page;
	}
	
}
