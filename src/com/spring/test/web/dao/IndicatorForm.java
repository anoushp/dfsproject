package com.spring.test.web.dao;

import java.util.List;

import javax.validation.Valid;

public class IndicatorForm {
	@Valid
	private List<Indicator> indicators;

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
	
	

}
