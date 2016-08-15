package com.atguigu.crm.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerServiceService;
import com.atguigu.crm.service.UserService;

@RequestMapping("/service")
@Controller
public class CustomerServiceHandler {

	@Autowired
	private CustomerServiceService customerServiceService;

	@Autowired
	private UserService userService;

	
	@RequestMapping("/archive")
	public String toArchiveUI(
			@RequestParam(value = "id", required = false) Long csId,
			Map<String, Object> map) {
		CustomerService cs = customerServiceService.getArchiveEntityById(csId);
		map.put("cs", cs);
		return "pages/service/archive/look";
	}
	
	@RequestMapping("/archive/list")
	public String toArchiveList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerService> page = customerServiceService.getArchivePage(
				pageNo, params);
		map.put("page", page);

		return "pages/service/archive/list";
	}
	
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public String feedback(CustomerService cs) {
		customerServiceService.updateFeedbacl(cs);
		
		return "redirect:/service/feedback/list";
	}
	
	@RequestMapping("/feedback")
	public String toFeedbackUI(
			@RequestParam(value = "id", required = false) Long csId,
			Map<String, Object> map) {
		CustomerService cs = customerServiceService.getFeedbackEntityById(csId);
		map.put("cs", cs);
		//xuanxiang
		Map<String,String> optionMap = new LinkedHashMap<String, String>();
		optionMap.put("", "请选择...");
		optionMap.put("☆☆☆☆☆", "☆☆☆☆☆");
		optionMap.put("☆☆☆☆", "☆☆☆☆");
		optionMap.put("☆☆☆", "☆☆☆");
		optionMap.put("☆☆", "☆☆");
		optionMap.put("☆", "☆");
		
		map.put("optionMap", optionMap);
		return "pages/service/feedback/feedback";
	}

	@RequestMapping("/feedback/list")
	public String toFeedBackList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerService> page = customerServiceService.getFeedBackPage(
				pageNo, params);
		map.put("page", page);

		return "pages/service/feedback/list";
	}

	@RequestMapping(value = "/deal", method = RequestMethod.POST)
	public String deal(CustomerService cs) {
		customerServiceService.updateDeal(cs);
		return "redirect:/service/deal/list";
	}

	@RequestMapping("/deal")
	public String toDealUI(
			@RequestParam(value = "id", required = false) Long csId,
			Map<String, Object> map) {
		CustomerService cs = customerServiceService.getEntityById(csId);
		cs.setDealDate(new Date());
		map.put("cs", cs);
		return "pages/service/deal/deal";
	}

	@RequestMapping("/deal/list")
	public String toDealList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerService> page = customerServiceService.getDealPage(pageNo,
				params);
		map.put("page", page);

		return "pages/service/deal/list";
	}

	@ResponseBody
	@RequestMapping(value = "/allot", method = RequestMethod.PUT)
	public String allot(CustomerService cs) {
		/* cs.setServiceState("已分配"); */
		cs.setAllotDate(new Date());
		customerServiceService.allot(cs);

		return "1";
	}

	@RequestMapping("/allot/list")
	public String toAllotList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerService> page = customerServiceService.getPage(pageNo,
				params);
		map.put("page", page);

		List<User> userList = userService.getUserList();

		map.put("userList", userList);

		return "pages/service/allot/list";
	}

	private String parseRequestParams2QueryString(Map<String, Object> params) {
		StringBuilder queryString = new StringBuilder();

		if (params == null || params.size() == 0) {
			return "";
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() == null
					|| entry.getValue().toString().trim().equals("")) {
				continue;
			}

			queryString.append("search_").append(entry.getKey()).append("=")
					.append(entry.getValue()).append("&");
		}

		if (queryString.length() > 0) {
			queryString.replace(queryString.length() - 1, queryString.length(),
					"");
		}
		return queryString.toString();
	}

	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	private String toAdd(Map<String, Object> map, CustomerService cs) {

		cs.setCreateDate(new Date());

		List<Customer> customerList = customerServiceService.getAllCustomers();
		map.put("customerList", customerList);

		List<String> serviceTypeList = new ArrayList<>();
		
		serviceTypeList.add("请选择:...");
		serviceTypeList.add("咨询");
		serviceTypeList.add("投诉");
		serviceTypeList.add("建议");
		map.put("serviceTypeList", serviceTypeList);

		map.put("cs", cs);

		return "pages/service/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String add(CustomerService cs, HttpSession session) {

		User createdby = (User) session.getAttribute("user");

		cs.setCreatedby(createdby);

		cs.setServiceState("新创建");
		Long id = customerServiceService.save(cs);

		cs.setId(id);

		return "redirect:/service/allot/list";

	}

	/*
	 * @RequestMapping("/allot/list") public String
	 * toAllotListUI(Map<String,Object> map){ List<CustomerService>
	 * customerServiceList = customerServiceService.getAllCS();
	 * 
	 * map.put("customerServiceList", customerServiceList);
	 * 
	 * List<User> userList = userService.getUserList();
	 * 
	 * map.put("userList", userList);
	 * 
	 * map.put("cs", new CustomerService()); return "pages/service/allot/list";
	 * }
	 */
}
