package com.spring.test.web.dao;

public enum SectorType {
	 BUILDING ("Building"),
	 DESIGN ("Design"),
	 SAFETY ("Safety"),
	 TEST ("Test");
	 
	 String sectorType;
	 public String getSectorType() {
			return sectorType;
		}

		private SectorType(String sectorType) {
			this.sectorType = sectorType;
		}


}
