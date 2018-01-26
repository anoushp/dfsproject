package com.spring.test.web.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {com.spring.test.web.validation.MatlevelCompletionCriteriaValidator.class })
public @interface ValidMatlevelCompletion {
	 String message() default "Please check your selection of maturity and their completion levels is correct";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	   

}
