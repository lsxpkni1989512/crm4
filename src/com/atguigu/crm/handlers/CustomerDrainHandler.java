package com.atguigu.crm.handlers;

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

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerDrainService;

@RequestMapping("/drain")
@Controller
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService customerDrainService;

	@ResponseBody
	@RequestMapping(value="",method=RequestMethod.POST)
	public String save(CustomerDrain cd){
		
		customerDrainService.save(cd);
		
		return "2";
	}
	
	@RequestMapping("/delay")
	public String toDelay(@RequestParam(value="id",required=false)Long drainId
			,Map<String,Object> map){
		CustomerDrain custDrain = customerDrainService.getCustDrainById(drainId);
		
		map.put("drain", custDrain);
		return "pages/drain/delay";
	}
	
	@RequestMapping("/list")
	public String toDrainList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			HttpSession session, HttpServletRequest request,
			Map<String, Object> map) {
		// 把已经连续 6 个月和公司没有任何的业务往来客户的状态修改为流失预警状态
		//customerDrainService.test();该方法由配置完成调用

		// 待条件的分页查询
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");

		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, params);

		map.put("page", page);
		return "pages/drain/list";
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
