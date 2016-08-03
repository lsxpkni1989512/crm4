package com.atguigu.crm.mappers;

import java.util.List;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	void clearAuthorities(Long id);

	void addAuthorities(Role role);

	List<Authority> getParentAuthorities();

	Role getById(Long id);

	List<Role> getRoleList();

	void addRole(Role role);

	void deleteRole(Long id);

}
