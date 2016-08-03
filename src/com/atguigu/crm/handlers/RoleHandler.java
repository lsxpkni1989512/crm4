package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.service.RoleService;

@RequestMapping("/role")
@Controller
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)		
	public String update(Role role){
		roleService.updateRole(role);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/assign/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id,Map<String,Object> map){
		
		map.put("role", roleService.getById(id));
		map.put("parentAuthorities", roleService.getParentAuthorities());
		
		
		return "pages/role/edit";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)	
	public String deleteRole(@PathVariable("id")Long id){
		
		roleService.deleteRole(id);
		
		return "1";
	}
	
	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	public String addRole(Role role){
		
		roleService.addRole(role);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping("/toAddRole")
	public String toAddRole(){
		
		return "pages/role/input";
	}
	
	@RequestMapping("/list")
	public String toList(Map<String,Object> map){
		
		List<Role> roleList = roleService.getRoleList();
		
		map.put("roleList", roleList);
		
		return "pages/role/list";
	}
	
}
