package com.spring.test.web.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.test.web.dao.AssessmentDetails;

public class MatlevelCompletionCriteriaValidator implements ConstraintValidator<ValidMatlevelCompletion, AssessmentDetails>{

	@Override
	public void initialize(ValidMatlevelCompletion constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(AssessmentDetails value, ConstraintValidatorContext context) {
		List<String> matlevels=value.getMatlevels();
		List<String> completion_level=value.getCompletion_criteria();
		System.out.println(completion_level.toString());
		if (matlevels.size()>2)
		{
			context.buildConstraintViolationWithTemplate("You can ONLY select maximum of 2 indicators on adjacent maturity levels").addPropertyNode("matlevels").addConstraintViolation();
			return false;
		}
		if (matlevels.size()==2){
			int value1=Integer.valueOf(matlevels.get(0));
			int value2=Integer.valueOf(matlevels.get(1));
			if (Math.abs(value1-value2)!=1){
				context.buildConstraintViolationWithTemplate("You can ONLY select 2 indicators on adjacent maturity levels").addPropertyNode("matlevels").addConstraintViolation();
				return false;
			}
        
			if (!(completion_level.get(value1-1).equals(completion_level.get(value2-1))) || completion_level.get(value1-1).equals("FULLY")|| completion_level.get(value2-1).equals("FULLY")){
				context.buildConstraintViolationWithTemplate("You can ONLY select 2 indicators that satisfy partially complete criteria").addPropertyNode("completion_criteria").addConstraintViolation();
				return false;


			}
		}
		if (matlevels.size()==1){
		int value1=Integer.valueOf(matlevels.get(0));
		if (completion_level.get(value1-1).equals("")){
			context.buildConstraintViolationWithTemplate("Completion criteria cannot be blank for the indicators selected ").addPropertyNode("completion_criteria").addConstraintViolation();
			return false;
		}
		int n=0;
		for (String s:completion_level){
			if (!s.equals("")){
				n++;
			}
		}
		
		if (n>matlevels.size()){
			context.buildConstraintViolationWithTemplate("Please check you  have selected an indicator for the completion criteria chosen ").addPropertyNode("completion_criteria").addConstraintViolation();
			return false;
		}
		}
		//System.out.println("COMPLETRION CRITERIA   "+value.getCompletionCriteria().toString());
		System.out.println("CHECKBOX   "+value.getMatlevels().toString());
		return true;
	}

}
