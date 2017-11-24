package com.spring.test.web.dao;

import java.util.List;

import javax.validation.Valid;

public class DfstestForm {
	@Valid
	private List<Dfstest> dfstests;

	public List<Dfstest> getDfstests() {
		return dfstests;
	}

	public void setDfstests(List<Dfstest> dfstests) {
		this.dfstests = dfstests;
	}

}
