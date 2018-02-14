package com.spring.test.web.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Iterator;

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
import com.spring.test.web.dao.MaturityCompliance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.web.dao.Assessment;
import com.spring.test.web.dao.AssessmentCompany;
import com.spring.test.web.dao.AssessmentDetails;
import com.spring.test.web.dao.AssessmentDetailsForm;
import com.spring.test.web.dao.AssessmentForm;
import com.spring.test.web.dao.AssessmentId;
import com.spring.test.web.dao.Attribute;
import com.spring.test.web.dao.AttributeForm;
import com.spring.test.web.dao.AttributeWeight;
import com.spring.test.web.dao.AttributeWeightData;
import com.spring.test.web.dao.CategoryConverter;
import com.spring.test.web.dao.Dfstest;
import com.spring.test.web.dao.DfstestForm;
import com.spring.test.web.dao.Offer;
import com.spring.test.web.dao.OperationSector;
import com.spring.test.web.dao.User;
import com.spring.test.web.service.AssessmentCompanyService;
import com.spring.test.web.service.AssessmentDetailsService;
import com.spring.test.web.service.AssessmentsService;
import com.spring.test.web.service.AttributesService;
import com.spring.test.web.service.DfstestsService;
import com.spring.test.web.service.IndicatorsService;
import com.spring.test.web.service.KPACategoryService;
import com.spring.test.web.service.OffersService;
import com.spring.test.web.service.OperationSectorService;
import com.spring.test.web.service.UsersService;

@Controller
public class WebtestController {
	
	private DfstestsService dfstestsService;
	private AssessmentsService assessmentsService;
	private AssessmentDetailsService assessmentDetailsService;
	private AttributesService attributesService;
	private IndicatorsService indicatorsService;
	private KPACategoryService kpaCategoryService;
	private AssessmentCompanyService assessmentCompanyService;
	private OperationSectorService operationSectorService;
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
	
	@Autowired
	public void setAssessmentCompanyService(AssessmentCompanyService assessmentCompanyService) {
		this.assessmentCompanyService =assessmentCompanyService;
	}
	

	@Autowired
	public void setOperationSectorService(OperationSectorService operationSectorService) {
		this.operationSectorService =operationSectorService;
	}

	@RequestMapping(value = "/delete/assessment/{savedTitle}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Assessment deleteAssessment(@PathVariable String savedTitle, Principal principal) {
		System.out.println("WORKSSSSSSAssessmentDelete");
		String username = principal.getName();
		Assessment a = assessmentsService.getAssessment(username, savedTitle);
		assessmentsService.delete(username, savedTitle);
		return a;
	}
	@RequestMapping(value = "/docreatecompany", method = RequestMethod.POST)
	public String doCreateIndicator(Model model, @Valid AssessmentCompany ac, BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {
		
		
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}
		List<OperationSector> os_list = operationSectorService.getOperationSector();

		
		if (result.hasErrors()) {
			System.out.println("ERROR" + result.getFieldError());
	
			return "createcompany";
		}

		assessmentCompanyService.saveOrUpdate(ac);
		model.addAttribute("company", ac);
		 model.addAttribute("countries", countryList);
		model.addAttribute("os_list", os_list);
		model.addAttribute("attributecreated", "Attribute Successfully Created");
		
		return "createcompany";

	}
	@RequestMapping("/newcompany")
	public String createcompany(Model model, Principal principal) {
		AssessmentCompany acompany = null;
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}
		List<OperationSector> os_list = operationSectorService.getOperationSector();
		System.out.println("AAAAAAAAAAAAAA");
		if (acompany == null)
			acompany = new AssessmentCompany();
		List<Attribute> attributes = attributesService.getAllAttributes();
		
		model.addAttribute("company", acompany);
		 model.addAttribute("countries", countryList);
		model.addAttribute("os_list", os_list);

		return "createcompany";
	}
	@RequestMapping("/dfsassessments/assessment/{username}/{title}")
	public String generateDfstestResults(@PathVariable String username, @PathVariable String title, Model model) {
		Assessment assessment=assessmentsService.getAssessment(username, title);
		List<AssessmentDetails> dfstests = assessmentDetailsService.getAssessmentDetails(username, title);
		List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
		List<String> matlevels;
		List<String> completion_criteria;
		
		double score=0;
		double max_score=0;
		//dictionary maps scores to category. Category score is the sum of scores of attributes withing that category
		HashMap<String, Double> hm_score=new HashMap<String, Double>();
		//dictionary that maps attributes and their corresponding scores to category. each category can have multiple attributes.e.g. collaboration.
		HashMap<String, AttributeWeightData> hm_weight=new HashMap<String, AttributeWeightData>();
		//stores corresponding for attributes in List<>
		AttributeWeightData aweightdata;
		double attr_score;
		for (KPACategory cat: cat_list){
			hm_score.put(cat.getCategory(), new Double(0));
			hm_weight.put(cat.getCategory(), new AttributeWeightData());
		}
		
		for (AssessmentDetails dfstest: dfstests){
			attr_score=0;
			matlevels=dfstest.getMatlevels();
			completion_criteria=dfstest.getCompletion_criteria();
			max_score+=dfstest.getAttribute().getWeight()*5/100.0;
			
			String cat=attributesService.getAttribute(dfstest.getAttribute().getId()).getKpaCategories().get(0).getCategory();
			 aweightdata=hm_weight.get(cat);
			    
			    List<String>attrnames=aweightdata.getAttrnames();
			    List<Double>attrweights=aweightdata.getAttrweights();
			    
			//if only one maturity level option is selected    
			if (matlevels.size()==1){
				
				int value1=Integer.valueOf(matlevels.get(0));
				if (completion_criteria.get(value1-1).equals("FULLY")){
					score+=value1*(dfstest.getAttribute().getWeight()/100.0);
				    hm_score.put(cat, Double.sum(hm_score.get(cat).doubleValue(),value1*(dfstest.getAttribute().getSector_weight()/100.0)));
				   
				    attrnames.add(dfstest.getAttribute().getName());
				    
				    attr_score=value1*(dfstest.getAttribute().getSector_weight()/100.0);
				    attrweights.add(attr_score);
				    hm_weight.put(cat, aweightdata);
				}
				//if one maturity level is selected on "Partially" completion criteria
				else if (completion_criteria.get(value1-1).equals("PARTIALLY")){
					int value2=value1-1;
					double mean=(value1+value2)/2.0;
					score+=mean*(dfstest.getAttribute().getWeight()/100.0);
					hm_score.put(cat, Double.sum(hm_score.get(cat).doubleValue(),mean*(dfstest.getAttribute().getSector_weight()/100.0)));
				    aweightdata=hm_weight.get(cat);
				    attrnames.add(dfstest.getAttribute().getName());
				    
				    attr_score=mean*(dfstest.getAttribute().getSector_weight()/100.0);
				    attrweights.add(attr_score);
				    hm_weight.put(cat, aweightdata);
				}
				
				
					
			}
			//if two adjacent maturity levels are selected
			else if (matlevels.size()==2){
				int value1=Integer.valueOf(matlevels.get(0)); 
				int value2=Integer.valueOf(matlevels.get(1)); 
				double mean=(value1+value2)/2.0;
				score+=mean*(dfstest.getAttribute().getWeight()/100.0);
				hm_score.put(cat, Double.sum(hm_score.get(cat).doubleValue(),mean*(dfstest.getAttribute().getSector_weight()/100.0)));
			    aweightdata=hm_weight.get(cat);
			    attrnames.add(dfstest.getAttribute().getName());
			    attr_score=mean*(dfstest.getAttribute().getSector_weight()/100.0);
			    attrweights.add(attr_score);
			    hm_weight.put(cat, aweightdata);
			}
			//sets the score for each attribute of the assessment
			dfstest.setScore(new Double(attr_score));
			assessmentDetailsService.saveOrUpdate(dfstest);
			
		}
		//sets the total score of the assessment
		assessment.setScore(new Double(score));
		assessmentsService.saveOrUpdate(assessment);
		
		System.out.println(max_score);
		ArrayList<String> mapkeys=new ArrayList<String>();
		ArrayList<String> categories=new ArrayList<String>();
		ArrayList<Double> mapvalues=new ArrayList<Double>();
		Iterator iterator = hm_score.keySet().iterator();
		Iterator iterator1 = hm_weight.keySet().iterator();

		while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   mapkeys.add('"'+key+'"');
		   Double value = hm_score.get(key);
		   mapvalues.add(value);

		   System.out.println(key + " " + value);
		}
		while (iterator1.hasNext()) {
			   String key = iterator1.next().toString();
			   categories.add(key);
			   List<String> an=hm_weight.get(key).getAttrnames();
			   List<Double> aw=hm_weight.get(key).getAttrweights();
			   System.out.println(key);
			   for (int i=0;i<an.size();i++ ){
				   System.out.println(an.get(i)+" "+aw.get(i));
			   }
			}
		String jsonInString="";
		ObjectMapper mapper = new ObjectMapper();
		try{
		jsonInString = mapper.writeValueAsString(hm_weight);
		
		//System.out.println(jsonInString);
		}
		catch (JsonProcessingException e) { System.out.println("JSON EXCEPTION");}
		model.addAttribute("title", title);
		model.addAttribute("mapkeys", mapkeys);
		model.addAttribute("mapvalues", mapvalues);
		model.addAttribute("categories", categories);

		model.addAttribute("percentage", score*100/max_score);
		model.addAttribute("score",score);
		model.addAttribute("hashmap",hm_score);
		model.addAttribute("hashmapweight",hm_weight);
		return "viewassessmentresults";
	}
	
	@RequestMapping("/updateDfSAssessment/{savedTitle}")
	public String updateDfSAssessment(@PathVariable String savedTitle, Model model,
			@Valid @ModelAttribute("dfsassessform") AssessmentForm dfstestForm, BindingResult result,
			Principal principal) {
		List<AssessmentDetailsForm> dfstests = dfstestForm.getAssessmentDetailsForm();
		List<OperationSector> os_list = operationSectorService.getOperationSector();
		MaturityCompliance[] matcompliancevalues=MaturityCompliance.values();
		String username = principal.getName();

		if (result.hasErrors()) {
			System.out.println("ERROR" + result.getFieldError());

			List<Attribute> attrs = attributesService.getAllAttributes();
			HashMap<String, HashMap<Attribute, List<Indicator>>> cat_attr = new HashMap<String, HashMap<Attribute, List<Indicator>>>();
			List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
			for (KPACategory cat : cat_list) {
				HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
				for (Attribute att : attrs) {

					if (att.getKpaCategories().get(0).getCategory().equals(cat.getCategory())) {

						int idattr = att.getId();
						List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
						hm.put(att, ind_list);

					}

				}
				cat_attr.put(cat.getCategory(), hm);

			}

			model.addAttribute("dfsassessform", dfstestForm);
			model.addAttribute("dfsmap", cat_attr);
			model.addAttribute("os_list", os_list);
			model.addAttribute("mat_compliance", matcompliancevalues);
			
			model.addAttribute("countries", getCountryList());
			model.addAttribute("formError", "error");
			System.out.println("ERRORS");
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}

			return "updateassessment";
		}

		Assessment a = assessmentsService.getAssessment(username, savedTitle);
		AssessmentCompany ac=assessmentCompanyService.getAssessmentCompany(dfstestForm.getAssessmentCompany().getId());
		assessmentCompanyService.saveOrUpdate(dfstestForm.getAssessmentCompany());
		
		a.setCompany(ac);
		AssessmentId asId = a.getId();
		asId.setTitle(dfstestForm.getTitle());
		asId.setUsername(username);
		a.setId(asId);
		a.setScore(null);
		// assessmentsService.delete(username,savedTitle);
		assessmentsService.saveOrUpdate(a);

		for (AssessmentDetailsForm dfsform : dfstests) {

			AssessmentDetails dfs=dfsform.getAssessmentDetails();
			dfs.getAssessment().setId(new AssessmentId(username, dfstestForm.getTitle()));
			dfs.setScore(null);
			assessmentDetailsService.saveOrUpdate(dfs);
		}
		if (!savedTitle.equals(dfstestForm.getTitle()))
			assessmentsService.delete(username, savedTitle);
		return "dfstestcompleted";
	}

	@RequestMapping("/createDfSAssessment")
	public String createDfSAssessment(Model model, @Valid @ModelAttribute("dfsassessform") AssessmentForm dfstestForm,
			BindingResult result, Principal principal) {
		List<AssessmentDetailsForm> dfstests = dfstestForm.getAssessmentDetailsForm();
		List<OperationSector> os_list = operationSectorService.getOperationSector();
		String username = principal.getName();
		if (assessmentsService.hasAssessment(username, dfstestForm.getTitle())) {
			result.rejectValue("title", "DuplicateKey.assessment.id.name");

		}

		if (result.hasErrors()) {

			List<Attribute> attrs = attributesService.getAllAttributes();
			HashMap<String, HashMap<Attribute, List<Indicator>>> cat_attr = new HashMap<String, HashMap<Attribute, List<Indicator>>>();
			List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
			MaturityCompliance[] matcompliancevalues=MaturityCompliance.values();
			for (KPACategory cat : cat_list) {
				HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
				for (Attribute att : attrs) {

					if (att.getKpaCategories().get(0).getCategory().equals(cat.getCategory())) {

						int idattr = att.getId();
						List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
						hm.put(att, ind_list);

					}

				}
				cat_attr.put(cat.getCategory(), hm);

			}

			model.addAttribute("dfsassessform", dfstestForm);
			model.addAttribute("dfsmap", cat_attr);
			model.addAttribute("formError", "error");
			model.addAttribute("os_list", os_list);
			model.addAttribute("countries", getCountryList());
			model.addAttribute("mat_compliance", matcompliancevalues);
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
		AssessmentId asId = new AssessmentId(username, dfstestForm.getTitle());
		Assessment a = new Assessment();
		a.setId(asId);
		AssessmentCompany ac=dfstestForm.getAssessmentCompany();
		assessmentCompanyService.create(ac);
		a.setCompany(dfstestForm.getAssessmentCompany());
		assessmentsService.create(a);

		for (AssessmentDetailsForm dfsform : dfstests) {

			AssessmentDetails dfs=dfsform.getAssessmentDetails();
			dfs.getAssessment().setId(asId);
			assessmentDetailsService.saveOrUpdate(dfs);
		}
		return "dfstestcompleted";
	}

	@RequestMapping("/dfsassessments")
	public String getDfsAssessments(Model model, Principal principal) {
		String username = principal.getName();
		if (assessmentsService.hasAssessment(username)) {
			model.addAttribute("assessment", "assessment");
			model.addAttribute("username", username);
			model.addAttribute("dfsassessments", assessmentsService.getAssessments(username));

		}
		return "dfsassessments";
	}

	@RequestMapping("/dfsassessments/update/{title}")
	public String updateDfstest(@PathVariable String title, Model model, Principal principal) {

		List<Attribute> attrs = attributesService.getAllAttributes();
		String username = principal.getName();
		MaturityCompliance[] matcompliancevalues=MaturityCompliance.values();
		Assessment assessment=assessmentsService.getAssessment(username, title);
		AssessmentCompany ac=assessment.getCompany();
		if (ac==null) ac=new AssessmentCompany();
		
		List<AssessmentDetails> dfstests = assessmentDetailsService.getAssessmentDetails(username, title);
		List<AssessmentDetailsForm> assessmentdetails=new ArrayList<AssessmentDetailsForm>();
		
		List<OperationSector> os_list = operationSectorService.getOperationSector();
		HashMap<String, HashMap<Attribute, List<Indicator>>> cat_attr = new HashMap<String, HashMap<Attribute, List<Indicator>>>();
		List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
		for (KPACategory cat : cat_list) {
			HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
			for (Attribute att : attrs) {

				if (att.getKpaCategories().get(0).getCategory().equals(cat.getCategory())) {

					int idattr = att.getId();
					List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
					hm.put(att, ind_list);

				}

			}
			cat_attr.put(cat.getCategory(), hm);

		}

		AssessmentForm dfstestForm = new AssessmentForm();
		dfstestForm.setTitle(title);
        dfstestForm.setAssessmentCompany(assessmentCompanyService.getAssessmentCompany(ac.getId()));
		if (dfstests == null) {
			dfstests = new ArrayList<AssessmentDetails>();
			for (int i = 0; i < attributesService.getAllAttributes().size(); i++) {
				dfstests.add(new AssessmentDetails());
			}
		}
		for (int i=0; i<dfstests.size();i++){
			AssessmentDetailsForm assessmentDetailsForm=new AssessmentDetailsForm();
			assessmentDetailsForm.setAssessmentDetails(dfstests.get(i));
			assessmentdetails.add(assessmentDetailsForm);
		}

		dfstestForm.setAssessmentDetailsForm(assessmentdetails);

		model.addAttribute("dfsassessform", dfstestForm);
		model.addAttribute("dfsmap", cat_attr);
		model.addAttribute("savedTitle", title);
		model.addAttribute("os_list", os_list);
		model.addAttribute("countries", getCountryList());
		model.addAttribute("mat_compliance", matcompliancevalues);

		return "updateassessment";
	}

	@RequestMapping("/newassessment")
	
	public String newDfstest(Model model, Principal principal) {
		List<Attribute> attrs = attributesService.getAllAttributes();
		HashMap<String, HashMap<Attribute, List<Indicator>>> cat_attr = new HashMap<String, HashMap<Attribute, List<Indicator>>>();
		List<KPACategory> cat_list = kpaCategoryService.getKPACategory();
		List<OperationSector> os_list = operationSectorService.getOperationSector();
		List<AssessmentDetailsForm> assessmentdetails=new ArrayList<AssessmentDetailsForm>();
		MaturityCompliance[] matcompliancevalues=MaturityCompliance.values();
		for (KPACategory cat : cat_list) {
			HashMap<Attribute, List<Indicator>> hm = new HashMap<Attribute, List<Indicator>>();
			for (Attribute att : attrs) {

				if (att.getKpaCategories().get(0).getCategory().equals(cat.getCategory())) {

					int idattr = att.getId();
					List<Indicator> ind_list = indicatorsService.getIndicators(idattr);
					hm.put(att, ind_list);

				}

			}
			cat_attr.put(cat.getCategory(), hm);

		}

		List<AssessmentDetails> dfstests = null;
        AssessmentCompany ac=new AssessmentCompany();
		AssessmentForm dfstestForm = new AssessmentForm();

		if (dfstests == null) {
			dfstests = new ArrayList<AssessmentDetails>();
			for (int i = 0; i < attributesService.getAllAttributes().size(); i++) {
				dfstests.add(new AssessmentDetails());
			}
		}
		for (int i=0; i<dfstests.size();i++){
			AssessmentDetailsForm assessmentDetailsForm=new AssessmentDetailsForm();
			assessmentDetailsForm.setAssessmentDetails(dfstests.get(i));
			assessmentdetails.add(assessmentDetailsForm);
		}
		dfstestForm.setAssessmentDetailsForm(assessmentdetails);
        dfstestForm.setAssessmentCompany(ac);
		model.addAttribute("dfsassessform", dfstestForm);
		model.addAttribute("countries", getCountryList());
		model.addAttribute("dfsmap", cat_attr);
		model.addAttribute("os_list", os_list);
		model.addAttribute("mat_compliance", matcompliancevalues);
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

			attributesService.updateAttributeWeight(attr);
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
	private static List<String> getCountryList(){
		List<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
			
		}
		return countryList;
	}
	/*finds max score for maturity for assessment */
   private double maxScoreMaturity(List<Attribute> attrs){
	   double sum=0;
	   for (Attribute attr: attrs){
		   sum+=attr.getWeight()*4;
	   }
	   return sum;
	   
   }
   /*finds max score for maturity for given sector */
   private double maxScoreMaturityByCategory(List<Attribute> attrs, String category){
	   double sum=0;
	   for (Attribute attr: attrs){
		   if (attr.getKpaCategories().get(0).getCategory().equals(category))
			   sum+=4*attr.getSector_weight()/100;
		   
	   }
	   return sum;
	   
   }
	/*
	 * @RequestMapping("/createoffer") public String createoffer(Model model,
	 * Principal principal) { Offer offer = null; if (principal != null) {
	 * String username = principal.getName(); offer =
	 * offersService.getOffer(username); } if (offer == null) offer = new
	 * Offer(); model.addAttribute("offer", offer); return "createoffer"; }
	 * 
	 * @RequestMapping(value = "/docreate", method = RequestMethod.POST) public
	 * String doCreate(Model model, @Validated(FormValidationGroup.class) Offer
	 * offer, BindingResult result, Principal principal, @RequestParam(value =
	 * "delete", required = false) String delete) {
	 * 
	 * if (result.hasErrors()) {
	 * 
	 * return "createoffer"; } if (delete == null) { String username =
	 * principal.getName(); offer.getUser().setUsername(username);
	 * offersService.saveOrUpdate(offer); return "offercreated"; } else {
	 * offersService.delete(offer.getId()); return "offerdeleted"; }
	 * 
	 * }
	 */

}
