package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mappers.RoleMapper;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional
	public void updateRole(Role role){
		roleMapper.clearAuthorities(role.getId());
		roleMapper.addAuthorities(role);
		
	}
	
	@Transactional(readOnly=true)
	public List<Authority> getParentAuthorities(){
		return roleMapper.getParentAuthorities();
	}
	
	@Transactional(readOnly=true)
	public Role getById(Long id){
		return roleMapper.getById(id);
	}

	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

	public void addRole(Role role) {
		roleMapper.addRole(role);
		
	}

	public void deleteRole(Long id) {
		roleMapper.deleteRole(id);
		
	}
	
}
