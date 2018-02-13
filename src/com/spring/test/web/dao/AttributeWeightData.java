package com.spring.test.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttributeWeightData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2323707206969170467L;


	private List<String> attrnames;
	private List<Double> attrweights;
	public List<String> getAttrnames() {
		return attrnames;
	}

	public void setAttrnames(List<String> attrnames) {
		this.attrnames = attrnames;
	}

	public List<Double> getAttrweights() {
		return attrweights;
	}

	public void setAttrweights(List<Double> attrweights) {
		this.attrweights = attrweights;
		
	}

	public AttributeWeightData(){
		
		attrnames=new ArrayList<String>();
		attrweights=new ArrayList<Double>();
	}
	
	

	
	
	

}
