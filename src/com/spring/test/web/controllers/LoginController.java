package com.spring.test.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.test.web.dao.FormValidationGroup;
import com.spring.test.web.dao.Message;
import com.spring.test.web.dao.User;
import com.spring.test.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;
	

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";

	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		String[] locales = Locale.getISOCountries();
		List<String> countryList=new ArrayList<String>();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}
		model.addAttribute("user", new User());
		model.addAttribute("countries", countryList);
		return "newaccount";

	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {

		return "loggedout";

	}

	@RequestMapping("/denied")
	public String showDenied() {

		return "denied";

	}

	@RequestMapping("/messages")
	public String showMessages() {

		return "messages";

	}

	

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result) {
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}
		if (result.hasErrors()) {
			System.out.println("ERROR" + result.getFieldError());
			model.addAttribute("countries", countryList);
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
		    model.addAttribute("countries", countryList);
			return "newaccount";
		}
		try {
			usersService.create(user);

		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			model.addAttribute("countries", countryList);
			return "newaccount";

		}
		return "accountcreated";

	}

	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		List<Message> messages = null;
		if (principal == null)
			messages = new ArrayList();
		else {
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;

	}

	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal, @RequestBody Map<String, Object> data) {
		String text = (String) data.get("text");
		String name = (String) data.get("email");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");
		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);
		rval.put("target", target);
		return rval;

	}

	@RequestMapping("/updateuser")
	public String updateuser(Model model, Principal principal) {
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}

		User user = null;
		if (principal != null) {
			String username = principal.getName();
			System.out.println("USERNAME  " + username);
			// User u=(User)principal;
			user = usersService.getUser(username);
		}
		if (user == null)
			user = new User();
		model.addAttribute("user", user);
		model.addAttribute("countries", countryList);
		return "updateuser";
	}

	@RequestMapping(value = "/doupdateuser", method = RequestMethod.POST)
	public String doUpdateUser(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result,
			Principal principal) {
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}

		if (result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
					if (!fieldError.getField().equals("password"))
					{   model.addAttribute("countries", countryList);
						return "updateuser";
					}
				}

			}

		}

		usersService.saveOrUpdate(user);
		return "userupdated";

	}

}
