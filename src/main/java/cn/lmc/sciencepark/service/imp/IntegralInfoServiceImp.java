package cn.lmc.sciencepark.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lmc.sciencepark.dao.IntegralInfoDao;
import cn.lmc.sciencepark.service.IntegralInfoService;

@Service
public class IntegralInfoServiceImp implements IntegralInfoService {
	@Autowired
	private IntegralInfoDao integralinfoDao;
	
	public IntegralInfoDao getIntegralinfoDao() {
		return integralinfoDao;
	}

	public void setIntegralinfoDao(IntegralInfoDao integralinfoDao) {
		this.integralinfoDao = integralinfoDao;
	}

	public void saveIntegralInfo(Map<String, Object> paramMap) {
		integralinfoDao.saveIntegralInfo(paramMap);
	}

}
