package com.spring.test.web.controllers;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.test.web.dao.FormValidationGroup;
import com.spring.test.web.dao.Indicator;
import com.spring.test.web.dao.IndicatorForm;
import com.spring.test.web.dao.KPACategory;
import com.spring.test.web.dao.Attribute;
import com.spring.test.web.dao.CategoryConverter;
import com.spring.test.web.dao.Offer;
import com.spring.test.web.dao.User;
import com.spring.test.web.service.AttributesService;
import com.spring.test.web.service.IndicatorsService;
import com.spring.test.web.service.KPACategoryService;
import com.spring.test.web.service.OffersService;
import com.spring.test.web.service.UsersService;

@Controller
public class WebtestController {
	private OffersService offersService;
	private UsersService usersService;
	private AttributesService attributesService;
	private IndicatorsService indicatorsService;
	private KPACategoryService kpaCategoryService;
	private static List<Indicator> indicators = new ArrayList<Indicator>();
	static{
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
	}

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	@Autowired
	public void setAttributesService(AttributesService attributesService) {
		this.attributesService = attributesService;
	}
	@Autowired
	public void setKPACategoryService(KPACategoryService kpaCategoryService) {
		this.kpaCategoryService = kpaCategoryService;
	}
	@Autowired
	public void setIndicatorsService(IndicatorsService indicatorsService) {
		this.indicatorsService = indicatorsService;
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
	@RequestMapping("/updateuser")
	public String updateuser(Model model, Principal principal){
		User user=null;
		if (principal!=null){
			String username=principal.getName();
			System.out.println("USERNAME  "+username);
			//User u=(User)principal;
			user =usersService.getUser(username);
		}
		if (user==null)
			user=new User();
		model.addAttribute("user", user);
		return "updateuser";
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
	@RequestMapping(value="/attributes/{attr_id}/docreateindicator", method=RequestMethod.POST)
	public String doCreateIndicator(Model model,@PathVariable int attr_id, @ModelAttribute("indicatorForm") IndicatorForm indicatorForm, BindingResult result, Principal principal, @RequestParam(value="delete", required=false) String delete){
		System.out.println("Attr_ID"+attr_id);
		List<Indicator> indicators = indicatorForm.getIndicators();

		if (result.hasErrors())
		{

			return "addindicator";
		}
		if (delete==null){
			System.out.println("INDICATOR");
			Attribute attr=attributesService.getAttribute(attr_id);
			if(null != indicators && indicators.size() > 0) {
				for (Indicator ind : indicators) {
					System.out.println("INDICATOR"+ind.getText());
					Indicator indic=indicatorsService.getIndicator(ind.getId());
					if (indic!=null)
						model.addAttribute("updateFlag", "updated");
					else
						model.addAttribute("updateFlag", "created");
					ind.getAttribute().setId(attr_id);
					indicatorsService.saveOrUpdate(ind);
					
				}
			}
			
			
			model.addAttribute("attribute", attr);
			//offer.getUser().setUsername(username);
			//offersService.saveOrUpdate(offer);
			return "indicatorcreated";
		}
		else{
			
		//	indicatorsService.delete(indicator.getId());
			//offersService.delete(offer.getId());
			return "indicatordeleted";
		}

	}
	@RequestMapping(value="/attributes/{attr_id}/editattribute/{id}", method=RequestMethod.GET)
	public String editIndicator(@PathVariable("attr_id") String attr_id, @PathVariable("id") String id, Model model){
		Indicator ind=null;
		
		Attribute attr=attributesService.getAttribute(Integer.valueOf(attr_id).intValue());	
		System.out.println("ATTR_ID"+ attr.getId());
		ind=indicatorsService.getIndicator(Integer.valueOf(id).intValue());
		model.addAttribute("indicator", ind);		
		model.addAttribute("attribute", attr);
		return "addindicator";
	}
	@RequestMapping("/attributes")
	public String createAttribute(Model model, Principal principal){
		Attribute attribute=null;
		List<KPACategory> cat_list=kpaCategoryService.getKPACategory();
		System.out.println("AAAAAAAAAAAAAA");
		if (attribute==null)
			attribute=new Attribute();
		List<Attribute> attributes=attributesService.getAllAttributes();
		model.addAttribute("attributes", attributes);
		model.addAttribute("attribute", attribute);
		model.addAttribute("attributecreated", null);
		model.addAttribute("cat_list", cat_list);

		return "createattribute";
	}
	@RequestMapping(value="/docreateattribute", method=RequestMethod.POST)
	public String doCreateIndicator(Model model, @Validated(FormValidationGroup.class) Attribute attribute, BindingResult result, Principal principal, @RequestParam(value="delete", required=false) String delete){
		List<Attribute> attributes=attributesService.getAllAttributes();

		if (attributesService.getAttribute(attribute.getName())!=null)
		{

			model.addAttribute("attribute", attribute);
			model.addAttribute("attributes", attributes);
			result.rejectValue("name", "DuplicateKey.attribute.name");
			return "createattribute";
		}
		if (result.hasErrors())
		{
			System.out.println("ERROR"+result.getFieldError());
			model.addAttribute("attributes", attributes);
			return "createattribute";
		}

		attributesService.saveOrUpdate(attribute);
		attributes=attributesService.getAllAttributes();
		model.addAttribute("attributes", attributes);
		model.addAttribute("attribute", new Attribute());
		model.addAttribute("attributecreated", "Attribute Successfully Created");
		return "createattribute";	


	}
	@RequestMapping(value="/attributes/{id}/viewindicators", method=RequestMethod.GET)
	public String viewIndicator(@PathVariable int id, Model model){
		Attribute attr=attributesService.getAttribute(id);
		int idattr=attr.getId();
		List<Indicator> ind_list=indicatorsService.getIndicators(idattr);

		model.addAttribute("attribute", attr);
		model.addAttribute("indicators", ind_list);

		return "viewindicators";
	}
	@RequestMapping(value="/attributes/{id}/addindicator", method=RequestMethod.GET)
	public String addIndicator(@PathVariable int id, 
			Model model) {
		System.out.println("zzzzzzz");
		Attribute attr=attributesService.getAttribute(id);	
		IndicatorForm ind_form =new IndicatorForm();
		
		int idattr=attr.getId();
		List<Indicator> ind_list=indicatorsService.getIndicators(idattr);
		System.out.println("yyyyyy");
		if (null!=ind_list){
			System.out.println("iffff");
			ind_form.setIndicators(ind_list);
		}
		else{	
			System.out.println("else");
		ind_form.setIndicators(indicators);}
		
		
		model.addAttribute("indicatorForm", ind_form);		
		model.addAttribute("attribute", attr);
		return "addindicator";




	}

	@RequestMapping(value="/attributes/editattribute/{id}", method=RequestMethod.GET)

	public String editAttribute(@PathVariable int id, 
			Model model) {
		System.out.println("WORKSSSSSS");
		Attribute attr=attributesService.getAttribute(id);
		List<KPACategory> cat_list=kpaCategoryService.getKPACategory();
		List<KPACategory>kpa=attr.getKpaCategories();



		model.addAttribute("attribute", attr);
		model.addAttribute("cat_list", cat_list);
		//	attribute.setId(id);
		//	attributesService.saveOrUpdate(attribute);
		return "editattribute";
	}
	@RequestMapping(value="/attributes/editattribute", method=RequestMethod.POST)
	public String doeditattribute(@Valid Attribute attr, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "editattribute";
		}
		attributesService.updateAttribute(attr);
		model.addAttribute("success", "Attribute " + attr.getName() +  " updated successfully");
		return "attributeupdatesuccess";


	}

	@RequestMapping(value="/attributes/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Attribute deleteAttribute(@PathVariable int id) {
		System.out.println("WORKSSSSSS");
		Attribute attr=attributesService.getAttribute(id);
		attributesService.delete(id);
		return attr;
	}
	@RequestMapping(value="/indicators/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Indicator deleteIndicator(@PathVariable int id) {
		System.out.println("WORKSSSSSS");
		Indicator ind=indicatorsService.getIndicator(id);
		indicatorsService.delete(id);
		return ind;
	}

	@RequestMapping(value="/doupdateuser", method=RequestMethod.POST)
	public String doUpdateUser(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result, Principal principal){

		if (result.hasErrors())
		{
			for (Object object : result.getAllErrors()) {
				if(object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
					if (!fieldError.getField().equals("password"))
						return "updateuser";
				}


			}


		}


		usersService.saveOrUpdate(user);
		return "userupdated";

	}

}
