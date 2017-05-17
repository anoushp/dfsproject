package com.spring.test.web.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.test.web.dao.Offer;
import com.spring.test.web.service.OffersService;

@Controller
public class WebtestController {
	private OffersService offersService;
	
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}


	@RequestMapping("/offers")
	public String showOffers(Model model){
		List<Offer> offers=offersService.getCurrent();
		model.addAttribute("offers", offers);
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String createoffer(Model model){
		model.addAttribute("offer", new Offer());
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result){
		if (result.hasErrors())
		{
			
			return "createoffer";
		}
		offersService.create(offer);
		return "offercreated";
	}

}
