package com.spring.test.web.controllers;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.test.web.dao.FormValidationGroup;
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
	public String createoffer(Model model, Principal principal){
		Offer offer=null;
		if (principal!=null){
			String username=principal.getName();
		    offer =offersService.getOffer(username);
		}
		if (offer==null)
			offer=new Offer();
		model.addAttribute("offer", offer);
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Validated(FormValidationGroup.class) Offer offer, BindingResult result, Principal principal, @RequestParam(value="delete", required=false) String delete){
		
		if (result.hasErrors())
		{
			
			return "createoffer";
		}
		if (delete==null){
		String username=principal.getName();
		offer.getUser().setUsername(username);
		offersService.saveOrUpdate(offer);
		return "offercreated";
		}
		else{
			offersService.delete(offer.getId());
			return "offerdeleted";
		}
		
	}

}
