package com.atguigu.crm.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesPlanService;

public class TestIOC {

	ApplicationContext ioc = null;
	DataSource ds = null;
	
	{
		ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
				
		ds = ioc.getBean(DataSource.class);
	}
	@Test
	public void test() throws SQLException {
		System.out.println(ds.getConnection());
	}
	@Test
	public void test1(){
		SalesPlanService salesPlanService = ioc.getBean(SalesPlanService.class);
		User createBy = new User();
		createBy.setId(24L);
		Page<SalesChance> page = salesPlanService.getPage(1, createBy);
		List<SalesChance> content = page.getContent();
		for (SalesChance salesChance : content) {
			
		System.out.println(salesChance);
		}
	}

}
