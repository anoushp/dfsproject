package com.spring.test.web.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
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
import com.spring.test.web.dao.Assessment;
import com.spring.test.web.dao.AssessmentDetails;
import com.spring.test.web.dao.AssessmentForm;
import com.spring.test.web.dao.AssessmentId;
import com.spring.test.web.dao.Attribute;
import com.spring.test.web.dao.AttributeForm;
import com.spring.test.web.dao.CategoryConverter;
import com.spring.test.web.dao.Dfstest;
import com.spring.test.web.dao.DfstestForm;
import com.spring.test.web.dao.Offer;
import com.spring.test.web.dao.User;
import com.spring.test.web.service.AssessmentDetailsService;
import com.spring.test.web.service.AssessmentsService;
import com.spring.test.web.service.AttributesService;
import com.spring.test.web.service.DfstestsService;
import com.spring.test.web.service.IndicatorsService;
import com.spring.test.web.service.KPACategoryService;
import com.spring.test.web.service.OffersService;
import com.spring.test.web.service.UsersService;

@Controller
public class WebtestController {
	private OffersService offersService;
	private DfstestsService dfstestsService;
	private AssessmentsService assessmentsService;
	private AssessmentDetailsService assessmentDetailsService;
	private AttributesService attributesService;
	private IndicatorsService indicatorsService;
	private KPACategoryService kpaCategoryService;
	private static List<Indicator> indicators = new ArrayList<Indicator>();
	static {
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
		indicators.add(new Indicator());
	}

	@Autowired
	public void setDfstestsService(DfstestsService dfstestsService) {
		this.dfstestsService = dfstestsService;
	}

	@Autowired
	public void setAssessmentsService(AssessmentsService assessmentsService) {
		this.assessmentsService = assessmentsService;
	}

	@Autowired
	public void setAssessmentDetailsService(AssessmentDetailsService assessmentDetailsService) {
		this.assessmentDetailsService = assessmentDetailsService;
	}

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
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
	
	@RequestMapping(value = "/delete/assessment/{savedTitle}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Assessment deleteAssessment(@PathVariable String savedTitle, Principal principal) {
		System.out.println("WORKSSSSSSAssessmentDelete");
		String username = principal.getName();
		Assessment a=assessmentsService.getAssessment(username, savedTitle);
		assessmentsService.delete(username,savedTitle);
		return a;
	}

	
	@RequestMapping("/updateDfSAssessment/{savedTitle}")
	public String updateDfSAssessment(@PathVariable String savedTitle, Model model, @Valid @ModelAttribute("dfsassessform") AssessmentForm dfstestForm,
			BindingResult result, Principal principal) {
		List<AssessmentDetails> dfstests = dfstestForm.getAssessmentDetails();
		String username = principal.getName();
		
		if (result.hasErrors()) {
			HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
			List<Attribute> attrs = attributesService.getAllAttributes();

			for (Attribute attr : attrs) {
				int idattr = attr.getId();
				List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
				hm.put(attr, ind_list);
			}

			model.addAttribute("dfsassessform", dfstestForm);
			model.addAttribute("dfsmap", hm);
			model.addAttribute("formError", "error");
			System.out.println("ERRORS");
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}

			return "newassessment";
		}
			
			Assessment a= assessmentsService.getAssessment(username, savedTitle);
			AssessmentId asId=a.getId();
			asId.setTitle(dfstestForm.getTitle());
			asId.setUsername(username);
			a.setId(asId);
		//	assessmentsService.delete(username,savedTitle);
			assessmentsService.saveOrUpdate(a);
			
		
		for (AssessmentDetails dfs : dfstests) {
			
			dfs.getAssessment().setId(new AssessmentId(username, dfstestForm.getTitle()));
			assessmentDetailsService.saveOrUpdate(dfs);
		}
		if (!savedTitle.equals(dfstestForm.getTitle()))
		assessmentsService.delete(username,savedTitle);
		return "dfstestcompleted";
	}
	
	@RequestMapping("/createDfSAssessment")
	public String createDfSAssessment(Model model, @Valid @ModelAttribute("dfsassessform") AssessmentForm dfstestForm,
			BindingResult result, Principal principal) {
		List<AssessmentDetails> dfstests = dfstestForm.getAssessmentDetails();
		String username = principal.getName();
		if (assessmentsService.hasAssessment(username, dfstestForm.getTitle())){
			result.rejectValue("title", "DuplicateKey.assessment.id.name");
			
		}

		if (result.hasErrors()) {
			HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
			List<Attribute> attrs = attributesService.getAllAttributes();

			for (Attribute attr : attrs) {
				int idattr = attr.getId();
				List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
				hm.put(attr, ind_list);
			}

			model.addAttribute("dfsassessform", dfstestForm);
			model.addAttribute("dfsmap", hm);
			model.addAttribute("formError", "error");
			System.out.println("ERRORS");
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}

			return "newassessment";
		}
			AssessmentId asId=new AssessmentId(username,dfstestForm.getTitle());
			Assessment a= new Assessment();
			a.setId(asId);
			assessmentsService.create(a);
			
		
		for (AssessmentDetails dfs : dfstests) {
			
			dfs.getAssessment().setId(asId);
			assessmentDetailsService.saveOrUpdate(dfs);
		}
		return "dfstestcompleted";
	}

	@RequestMapping("/dfsassessments")
	public String getDfsAssessments(Model model, Principal principal) {
		String username = principal.getName();
		if (assessmentsService.hasAssessment(username)){
			model.addAttribute("assessment", "assessment");
			model.addAttribute("dfsassessments", assessmentsService.getAssessments(username));
			
		}
		return "dfsassessments";
	}
	
	@RequestMapping("/dfsassessments/update/{title}")
	public String updateDfstest(@PathVariable String title, Model model, Principal principal) {
		HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
		List<Attribute> attrs = attributesService.getAllAttributes();
		String username = principal.getName();
		List<AssessmentDetails> dfstests = assessmentDetailsService.getAssessmentDetails(username, title);

		
		AssessmentForm dfstestForm = new AssessmentForm();
		dfstestForm.setTitle(title);
		for (Attribute attr : attrs) {
			int idattr = attr.getId();
			List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
			hm.put(attr, ind_list);
		}

		
		if (dfstests == null) {
			dfstests = new ArrayList<AssessmentDetails>();
			for (int i = 0; i < attributesService.getAllAttributes().size(); i++) {
				dfstests.add(new AssessmentDetails());
			}
		}

		dfstestForm.setAssessmentDetails(dfstests);

		model.addAttribute("dfsassessform", dfstestForm);
		model.addAttribute("dfsmap", hm);
		model.addAttribute("savedTitle", title);
		
		return "updateassessment";
	}

	
	@RequestMapping("/newassessment")
	public String newDfstest(Model model, Principal principal) {
		HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
		List<Attribute> attrs = attributesService.getAllAttributes();
		List<AssessmentDetails> dfstests = null;
		
		AssessmentForm dfstestForm = new AssessmentForm();
		for (Attribute attr : attrs) {
			int idattr = attr.getId();
			List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
			hm.put(attr, ind_list);
		}

		
		if (dfstests == null) {
			dfstests = new ArrayList<AssessmentDetails>();
			for (int i = 0; i < attributesService.getAllAttributes().size(); i++) {
				dfstests.add(new AssessmentDetails());
			}
		}

		dfstestForm.setAssessmentDetails(dfstests);

		model.addAttribute("dfsassessform", dfstestForm);
		model.addAttribute("dfsmap", hm);
		return "newassessment";
	}

	@RequestMapping("/doadjustattributesweights")
	public String saveAdjustedWeights(Model model, @Valid @ModelAttribute("attributeForm") AttributeForm attributeForm,
			BindingResult result, Principal principal) {
		List<Attribute> attrs = attributeForm.getAttributes();
		List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
		model.addAttribute("attributeForm", attributeForm);
		model.addAttribute("cat_list", cat_list);
		if (result.hasErrors()) {
			System.out.println("AAAAAEEEEAAA");
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}
			return "adjustattributeweights";
		}
		for (Attribute attr : attrs) {

			attributesService.updateAttribute(attr);
		}
		model.addAttribute("adjusted", "adjusted");
		return "adjustattributeweights";
	}

	@RequestMapping("/adjustattributeweights")
	public String adjustWeights(Model model, Principal principal) {
		List<Attribute> attrs = attributesService.getAllAttributes();
		List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
		model.addAttribute("cat_list", cat_list);
		AttributeForm attrFrm = new AttributeForm();
		attrFrm.setAttributes(attrs);
		model.addAttribute("attributeForm", attrFrm);
		return "adjustattributeweights";

	}

	@RequestMapping("/proceedDfSTest")
	public String proceedTestResults(Model model, @Valid @ModelAttribute("dfstestform") DfstestForm dfstestForm,
			BindingResult result, Principal principal) {
		List<Dfstest> dfstests = dfstestForm.getDfstests();
		String username = principal.getName();

		if (result.hasErrors()) {
			HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
			List<Attribute> attrs = attributesService.getAllAttributes();

			for (Attribute attr : attrs) {
				int idattr = attr.getId();
				List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
				hm.put(attr, ind_list);
			}

			model.addAttribute("dfstestform", dfstestForm);
			model.addAttribute("dfsmap", hm);
			model.addAttribute("formError", "error");
			System.out.println("ERRORS");
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}

			return "showdfstest";
		}
		for (Dfstest dfs : dfstests) {
			dfs.getUser().setUsername(username);
			dfstestsService.saveOrUpdate(dfs);
		}
		return "dfstestcompleted";
	}

	@RequestMapping("/dfstest")
	public String createDfstest(Model model, Principal principal) {
		HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
		List<Attribute> attrs = attributesService.getAllAttributes();
		List<Dfstest> dfstests = null;
		String username = "";
		DfstestForm dfstestForm = new DfstestForm();
		for (Attribute attr : attrs) {
			int idattr = attr.getId();
			List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
			hm.put(attr, ind_list);
		}

		if (principal != null) {
			username = principal.getName();
			dfstests = dfstestsService.getDfstests(username);
		}
		if (dfstests == null) {
			dfstests = new ArrayList<Dfstest>();
			for (int i = 0; i < attributesService.getAllAttributes().size(); i++) {
				dfstests.add(new Dfstest());
			}
		}

		dfstestForm.setDfstests(dfstests);

		model.addAttribute("dfstestform", dfstestForm);
		model.addAttribute("dfsmap", hm);
		return "showdfstest";
	}

	/*@RequestMapping("/createoffer")
	public String createoffer(Model model, Principal principal) {
		Offer offer = null;
		if (principal != null) {
			String username = principal.getName();
			offer = offersService.getOffer(username);
		}
		if (offer == null)
			offer = new Offer();
		model.addAttribute("offer", offer);
		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(FormValidationGroup.class) Offer offer, BindingResult result,
			Principal principal, @RequestParam(value = "delete", required = false) String delete) {

		if (result.hasErrors()) {

			return "createoffer";
		}
		if (delete == null) {
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersService.saveOrUpdate(offer);
			return "offercreated";
		} else {
			offersService.delete(offer.getId());
			return "offerdeleted";
		}

	}*/

}
