package com.atguigu.crm.handlers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;
import com.atguigu.crm.service.UserService;

@RequestMapping("/plan")
@Controller
public class SalesPlanHandler {
	@Autowired
	private SalesPlanService salesPlanService;
	
	@Autowired
	private SalesChanceService salesChanceService;
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/execute",method=RequestMethod.PUT)
	public String execute(@RequestParam(value="id",required=false)Long planId,
			@RequestParam(value="result",required=false)String result){
		salesPlanService.savePlanResult(planId,result);
		return "2";
	}
	
	@RequestMapping(value="/execution/{id}",method=RequestMethod.GET)
	public String toExecutUI(@PathVariable("id") Long chanceId,Map<String,Object> map){
		SalesChance chance = salesChanceService.getSalesChanceAndPlansById(chanceId);
		
		map.put("chance", chance);
		return "pages/plan/exec";
	}
	
	@RequestMapping(value="/delete-ajax",method=RequestMethod.DELETE)
	@ResponseBody
	public String deletePlan(@RequestParam(value="id",required=false)Long planId){
		salesPlanService.deletePlan(planId);
		
		return "1";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	@ResponseBody
	public String update(SalesPlan plan){
		salesPlanService.update(plan); 
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/make", method=RequestMethod.POST)
	public String make(SalesPlan plan){
		
		
		salesPlanService.savePlan(plan);
		
		/*long planId = plan.getId();
		String StrId = Long.toString(planId);
		System.out.println(StrId);*/
        return plan.getId() + "";
		
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="/make",method=RequestMethod.POST)
	public String make(Map<String,Object> map,@RequestParam(value="date",required=false)String date
			,@RequestParam(value="todo",required=false)String todo,
			@RequestParam(value="chance.id",required=false)Long id){
		
		SalesPlan plan = new SalesPlan();
		
		plan.setDate(date);
		
		plan.setTodo(todo);
		
		SalesChance chance = salesPlanService.getSalesChanceById(id);
		plan.setChance(chance);
		//保存
		salesPlanService.savePlan(plan);
		
		map.put("chance", chance);
		
		return "pages/plan/make";
	}*/
	@RequestMapping(value="/make/{id}",method=RequestMethod.GET)
	public String toMake(@PathVariable(value="id")Long id,Map<String,Object> map){
		
		SalesChance chance = salesChanceService.getSalesChanceById(id);
		
		User createBy = userService.getUserById(chance.getCreateBy().getId());
		User designee = userService.getUserById(chance.getDesignee().getId());
		chance.setCreateBy(createBy);
		chance.setDesignee(designee);
		
		List<SalesPlan> salesPlanList = salesPlanService.getAllPlan(id);
		
		map.put("chance", chance);
		
		map.put("salesPlanList", salesPlanList);
		
		return "pages/plan/make";
	}
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,HttpSession session,@RequestParam(value="pageNo",required=false)String pageNoStr){
		//需要从页面获取两个参数pageNo 和 user
		User createBy = (User) session.getAttribute("user");
		
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<SalesChance> page = salesPlanService.getPage(pageNo,createBy);
		
		map.put("page", page);
		
		return "pages/plan/list";
	}

}
