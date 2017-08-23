package com.spring.test.web.controllers;



import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.test.web.dao.Offer;
import com.spring.test.web.service.OffersService;


@Controller
public class HomeController {
	@Autowired 
	private OffersService offersService;
	private static Logger logger=Logger.getLogger(HomeController.class);
	@RequestMapping("/")
	public String showHome(Model model, Principal principal){
		boolean hasOffer=false;
		List<Offer> offers=offersService.getCurrent();
		model.addAttribute("offers", offers);
		if (principal!=null){
			hasOffer=offersService.hasOffer(principal.getName());
			model.addAttribute("userDetails", principal.getName());
	
		} 
		else
			model.addAttribute("userDetails", "");
		model.addAttribute("hasOffer", hasOffer);
		logger.info("showing home page");
		
		return "home";
	}

}
