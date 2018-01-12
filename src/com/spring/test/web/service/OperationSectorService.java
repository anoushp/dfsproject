package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.OperationSector;
import com.spring.test.web.dao.OperationSectorDao;


@Service("operationSectorService")
public class OperationSectorService {
	
		private OperationSectorDao operationSectorDao;
		
		@Autowired
		public void setOperationSectorDao(OperationSectorDao operationSectorDao) {
			this.operationSectorDao = operationSectorDao;
		}

		public void saveOrUpdate(OperationSector osector) {
			operationSectorDao.saveOrUpdate(osector);
		}

		public void delete(int id) {
			operationSectorDao.delete(id);
			
		}
		
	   public List<OperationSector> getOperationSector() {
			
			List<OperationSector> os=operationSectorDao.getOperationSector();
			if (os.size()==0) return null;
			return os;
		}
	public int getOperationSectorId(String sector){
		return operationSectorDao.getOperationSectorId(sector).getId();
	}
	public OperationSector findById(int id) {
		return operationSectorDao.findById(id);
		
	}

}
