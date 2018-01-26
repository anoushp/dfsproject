package com.spring.test.web.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;

import org.springframework.util.StringUtils;

public class StringListConverter implements AttributeConverter<List<String>, String>  {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		
		return StringUtils.collectionToCommaDelimitedString(attribute);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData!=null)
		return new ArrayList<>(Arrays.asList(dbData.split(",")));
		else return new ArrayList<String>();
	}

}
