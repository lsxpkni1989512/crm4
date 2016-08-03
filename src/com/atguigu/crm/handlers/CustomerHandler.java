package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustService;

@RequestMapping("/customer")
@Controller
public class CustomerHandler {

	@Autowired
	private CustService custService;
	
	
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public String delete(@RequestParam("id")Long custId){
		
		custService.delete(custId);
		
		return "1";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String update(Customer cust){
		custService.update(cust);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/create")
	public String toUpdate(@RequestParam(value="id",required=false)Long id,Map<String,Object> map){
		Customer cust = custService.getCustById(id);
		map.put("customer", cust);
		
		//从数据库中获取 地区和 客户等级，并放到请求域中带到页面上显示
		List<String> regions = custService.getAllRegions();
		List<String> levels = custService.getAllLevels();
		List<Contact> managers = custService.getAllManagers(); 
		List<String> satisfies = custService.getAllSatify();
		List<String> credits = custService.getAllCredits();
	
		map.put("regions", regions);
		map.put("levels", levels);
		map.put("managers", managers);
		map.put("satisfies", satisfies);
		map.put("credits", credits);
		
		return "pages/customer/input";
	}
	
	@RequestMapping("/list")
	public String toList(@RequestParam(value="pageNo",required=false)String pageNoStr,
				HttpSession session,HttpServletRequest request,Map<String,Object> map){
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		
		Page<Customer> page = custService.getPage(pageNo, params);
		map.put("page", page);
		
		//从数据库中获取 地区和 客户等级，并放到请求域中带到页面上显示
		List<String> regions = custService.getAllRegions();
		List<String> levels = custService.getAllLevels();
	
		map.put("regions", regions);
		map.put("levels", levels);
		
		return "pages/customer/list";
	}
	
	private String parseRequestParams2QueryString(Map<String, Object> params) {
		StringBuilder queryString = new StringBuilder();
		
		if(params == null || params.size() == 0){
			return "";
		}
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			if(entry.getValue() == null || entry.getValue().toString().trim().equals("")){
				continue;
			}
			
			queryString.append("search_")
			           .append(entry.getKey())
			           .append("=")
			           .append(entry.getValue())
			           .append("&");
		}
		
		if(queryString.length() > 0){
			queryString.replace(queryString.length() - 1, queryString.length(), "");
		}
		return queryString.toString();
	}
}
