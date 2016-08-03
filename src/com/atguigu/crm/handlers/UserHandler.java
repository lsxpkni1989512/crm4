package com.atguigu.crm.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Navigation;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;

@RequestMapping("/user")
@Controller
public class UserHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/navigate") public List<Navigation> navigate() {
	 * List<Navigation> navigations = new ArrayList<Navigation>();
	 * 
	 * Navigation top = new Navigation(); top.setId(Long.MAX_VALUE);
	 * top.setText("客户关系管理系统"); navigations.add(top);
	 * 
	 * for (Authority authority : roleService.getParentAuthorities()) {
	 * Navigation parentNavigation = new Navigation();
	 * parentNavigation.setId(authority.getId());
	 * parentNavigation.setText(authority.getDisplayName());
	 * parentNavigation.setState("closed");
	 * 
	 * top.getChildren().add(parentNavigation);
	 * 
	 * for (Authority sub : authority.getSubAuthorities()) { Navigation
	 * subNavigation = new Navigation(); subNavigation.setId(sub.getId());
	 * subNavigation.setText(sub.getDisplayName());
	 * subNavigation.setUrl(sub.getUrl());
	 * 
	 * parentNavigation.getChildren().add(subNavigation); } }
	 * 
	 * return navigations; }
	 */
	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigation> navigate(HttpSession session) {
		List<Navigation> navigations = new ArrayList<Navigation>();

		Navigation top = new Navigation();
		top.setId(Long.MAX_VALUE);
		top.setText("客户关系管理系统");
		navigations.add(top);

		User user = (User) session.getAttribute("user");
		String contextPath = session.getServletContext().getContextPath();

		Map<Long, Navigation> parentNavigations = new HashMap<Long, Navigation>();
		Navigation parentNavigation = null;

		for (Authority authority : user.getRole().getAuthorities()) {
			Navigation subNavigation = new Navigation();
			subNavigation.setId(authority.getId());
			subNavigation.setText(authority.getDisplayName());
			subNavigation.setUrl(contextPath + authority.getUrl());

			Authority parentAuthority = authority.getParentAuthority();
			parentNavigation = parentNavigations.get(authority
					.getParentAuthority().getId());
			if (parentNavigation == null) {
				parentNavigation = new Navigation();
				parentNavigation.setId(parentAuthority.getId());
				parentNavigation.setText(parentAuthority.getDisplayName());
				parentNavigation.setState("closed");
				top.getChildren().add(parentNavigation);

				parentNavigations
						.put(parentAuthority.getId(), parentNavigation);
			}

			parentNavigation.getChildren().add(subNavigation);
		}

		return navigations;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/navigate") public List<Navigation> navigate(HttpSession
	 * session) {
	 * 
	 * 
	 * User user = (User) session.getAttribute("user");
	 * System.out.println(user); Role role = user.getRole();
	 * 
	 * List<Authority> authorities = role.getAuthorities();
	 * 
	 * List<Navigation> navigations = new ArrayList<Navigation>(); Navigation
	 * top = new Navigation(); top.setId(Long.MAX_VALUE);
	 * top.setText("客户关系管理系统"); navigations.add(top); top.setState("closed");
	 * for (Authority authority : roleService.getParentAuthorities()) {
	 * Navigation parentNavigation = new Navigation();
	 * parentNavigation.setId(authority.getId());
	 * parentNavigation.setText(authority.getDisplayName());
	 * parentNavigation.setState("closed");
	 * 
	 * top.getChildren().add(parentNavigation);
	 * 
	 * for (Authority sub : authority.getSubAuthorities()) { Navigation
	 * subNavigation = new Navigation(); subNavigation.setId(sub.getId());
	 * subNavigation.setText(sub.getDisplayName());
	 * subNavigation.setUrl(sub.getUrl());
	 * 
	 * parentNavigation.getChildren().add(subNavigation); } } return
	 * navigations; }
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id") Long id) {

		userService.delete(id);

		return "1";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable("id") Long id, User user) {

		userService.update(user);

		return "redirect:/user/list";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(@RequestParam(value = "id") Long userId,
			Map<String, Object> map) {

		User user = userService.getUserById(userId);

		map.put("user", user);

		return "pages/user/update";
	}

	@RequestMapping("/list")
	public String toList(Map<String, Object> map) {

		List<User> userList = userService.getUserList();

		map.put("userList", userList);

		return "pages/user/list";
	}

	@RequestMapping(value = "/shiroLogin", method = RequestMethod.POST)
	public String shiroLogin(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			HttpSession session, RedirectAttributes attributes, Locale locale) {
		Subject currentUser = SecurityUtils.getSubject();

		// let's login the current user so we can check against roles and
		// permissions:
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(name,
					password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (AuthenticationException ae) {
				attributes.addFlashAttribute("message", "登录失败，请重新登录！");
				return "redirect:/index";
			}
		}

		// 若成功通过 Shiro 的验证. 则应该可以从 Shiro 的上下文中得到登录的信息.
		Object user = SecurityUtils.getSubject().getPrincipals()
				.getPrimaryPrincipal();
		session.setAttribute("user", user);
		return "redirect:/success";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			HttpSession session, RedirectAttributes attributes) {

		User user = userService.login(name, password);

		if (user != null) {

			session.setAttribute("user", user);

			return "redirect:/success";
		}

		attributes.addFlashAttribute("message", "登录失败，请重新登录！");

		return "redirect:/index";
	}
}
