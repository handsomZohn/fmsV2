package com.casic.fms.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.TestDepart;
import com.casic.fms.bean.TestUser;

/**
 * Urls:
 * List   page        : GET  /web/users/{pageIndex}?pageSize={pageSize}
 * Create page        : GET  /web/user/create
 * Create action      : POST /web/user/save
 * Update page        : GET  /web/user/update/{id}
 * Update action      : POST /web/user/save/{id}
 * Delete action      : POST /web/user/delete/{id}
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web/test")
public class TestUserController extends BaseWebController{

	
	private List<TestUser> getUsers(){
		List<TestUser> listusers = new ArrayList<TestUser>();
		listusers.add(new TestUser("1","wwww"));
		listusers.add(new TestUser("2","pppp"));
		listusers.add(new TestUser("3","aaaa"));
		return listusers;
	}
	
	private TestDepart  getDep(){
		TestDepart td = new TestDepart();
		td.setName("测试部门");
		td.setListUsers(getUsers().subList(0, 1));
		return td;
		
	}
	@RequestMapping(value = "/dep/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", getDep());
		model.addAttribute("usermodels", getUsers());
		return "depart/form";
	}
	@RequestMapping(value = "/dep/{id}",method=RequestMethod.GET)
	public String updateForm(Model model) {
		
		model.addAttribute("model", getDep());
		model.addAttribute("users", getUsers());
		return "depart/form";
	}
	
	@RequestMapping(value = "/dep/save",method=RequestMethod.POST)
	public String save(TestDepart model,RedirectAttributes redirectAttributes) {
		System.out.println(model.getListUsers());
		return "redirect:/dep/form";
	}



}
