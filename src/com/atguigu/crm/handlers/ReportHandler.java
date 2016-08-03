package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ReportService;

@RequestMapping("/report")
@Controller
public class ReportHandler {

	@Autowired
	private ReportService reportService;
	
	
	@RequestMapping("/consist")
	public String toConsistUI(){
		
		return "pages/report/consist";
	}
	
	@RequestMapping("/pay")
	public String listCustomerToalOrderMoneyPage(@RequestParam(value="pageNo",required=false) String pageNoStr,
			Map<String, Object> map,
			HttpSession session,
			HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		
		Page<Map<String, Object>> page = reportService.getCustomerToalOrderMoneyPage(pageNo, params);
		map.put("page", page);
		
		return "pages/report/money";
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
