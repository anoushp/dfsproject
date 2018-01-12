package com.spring.test.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.spring.test.web.service.OperationSectorService;



public class SectorConverter implements Converter<Object, OperationSector> {
	private OperationSectorService operationSectorService;
	@Autowired
    public SectorConverter(OperationSectorService operationSectorService){
		this.operationSectorService=operationSectorService;
	}
 
    /**
     * Gets Sector by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public OperationSector convert(Object element) {
        int id = Integer.parseInt((String)element);
        OperationSector sec= operationSectorService.findById(id);
        System.out.println("Sector: "+sec);
        return sec;
    }
     

}
