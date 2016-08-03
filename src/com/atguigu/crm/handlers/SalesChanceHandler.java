package com.atguigu.crm.handlers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.UserService;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/stop")
	public String stop(@RequestParam(value="id",required=false)Long chanceId){
		salesChanceService.stop(chanceId);
		return "redirect:/plan/list";
	}
	
	/**
	 * 根据查询条件分页
	 * @param pageNoStr
	 * @param session
	 * @param map
	 * @param custName
	 * @param title
	 * @param contact
	 * @return
	 */
	/*
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String showConditionPage(
			@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
			HttpSession session, Map<String, Object> map,HttpServletRequest request){
		//获取页面所有以Search_开头的参数
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//将当前用户作为createBy参数，按照固定格式写入map
		User loginUser = (User) session.getAttribute("user");
		params.put("EQL_createById", loginUser.getId());
		params.put("EQI_status", 1);
		Page<SalesChance> page = salesChanceService.getConditionPage(pageNoStr, params);
		map.put("page", page);
		map.putAll(params);
		return "pages/chance/list";
	}*/
	
	@RequestMapping("/finish/{id}")
	public String finish(@PathVariable("id")Long id,Map<String,Object> map){
		
		SalesChance chance = salesChanceService.finishDevelop(id);
		
		
		return "redirect:/plan/list";
	}
	
	@RequestMapping(value="/dispatch/{id}",method=RequestMethod.GET)
	public String todispatch(@PathVariable("id")Long id,Map<String,Object> map){
		
		SalesChance chance = salesChanceService.getSalesChanceById(id);
		chance.setDesigneeDate(new Date());
		chance.setStatus(2);
		List<User> userList = userService.getUserList();
		map.put("userList", userList);
		map.put("chance", chance);
		
		return "pages/chance/dispatch";
	}
	@RequestMapping(value="/dispatch/{id}",method=RequestMethod.PUT)
	public String dispatch(SalesChance chance){
		
		salesChanceService.dispatch(chance);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(SalesChance chance){
		
		salesChanceService.update(chance);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id")Long id,Map<String,Object> map){
		
		SalesChance chance = salesChanceService.getSalesChanceById(id);
		
		map.put("chance", chance);
		
		return "pages/chance/input";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Long id){
		
		salesChanceService.delete(id);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(SalesChance chance,HttpSession session,
			RedirectAttributes attributes){
		
		User createBy = (User) session.getAttribute("user");
		
		chance.setCreateBy(createBy);
		
		salesChanceService.input(chance);
		
		attributes.addFlashAttribute("message","操作成功！");
		
		return "redirect:/chance/list";
		
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String create(Map<String,Object> map){
		
		SalesChance chance = new SalesChance();
		
		chance.setStatus(1);
		
		chance.setCreateDate(new Date());
		
		map.put("chance", chance);
		
		return "pages/chance/input";
	}
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,HttpSession session,@RequestParam(value="pageNo",required=false)String pageNoStr){
		//需要从页面获取两个参数pageNo 和 user
		User createBy = (User) session.getAttribute("user");
		
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<SalesChance> page = salesChanceService.getPage(pageNo,createBy);
		
		map.put("page", page);
		
		return "pages/chance/list";
	}
}
