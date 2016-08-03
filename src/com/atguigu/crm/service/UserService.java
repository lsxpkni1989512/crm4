package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name,String password){
		
		User user = userMapper.getUserByName(name);
		
		if(user !=null &&
				user.getPassword().equals(password) &&
				user.getEnabled() == 1){
			
			return user;
		}
		
		return null;
	}
	/**
	 * 为ServiceChance获取指派对象的集合
	 * @return
	 */
	public List<User> getUserList() {
		List<User> list = userMapper.getUserList();
		return list;
	}
	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}
	public void update(User user) {
		userMapper.update(user);
		
	}
	public void delete(Long id) {
		userMapper.delete(id);
		
	}
	
}
