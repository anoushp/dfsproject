package com.spring.test.web.dao;

import java.io.Serializable;

public class AttributeWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4183469579771585544L;
    private String attrname;
    private double attrweight;
    
	public AttributeWeight(String aname, double aweight){
		attrname=aname;
		attrweight=aweight;
		
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public double getAttrweight() {
		return attrweight;
	}

	public void setAttrweight(double attrweight) {
		this.attrweight = attrweight;
	}
	

}
