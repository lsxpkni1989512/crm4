package com.atguigu.crm.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.User;

public interface UserMapper {

	User getUserByName(@Param("name")String name);

	List<User> getUserList();

	User getUserById(Long id);

	void update(User user);

	void delete(Long id);
}
