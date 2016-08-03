package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;

public interface SalesChanceMapper {

	long getTotalElements(Map<String, Object> params);

	List<SalesChance> getContent(Map<String, Object> params);

	void save(SalesChance chance);

	void delete(Long id);

	SalesChance getSalesChanceById(Long id);

	void update(SalesChance chance);

	void dispatch(SalesChance chance);

	void updateStatus(@Param("id")Long id, @Param("status")int status);

	SalesChance getSalesChanceAndPlansById(Long chanceId);

	void stop(Long chanceId);

}
