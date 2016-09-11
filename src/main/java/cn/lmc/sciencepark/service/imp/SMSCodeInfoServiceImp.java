package cn.lmc.sciencepark.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.lmc.sciencepark.dao.SMSCodeInfoDao;
import cn.lmc.sciencepark.service.SMSCodeInfoService;

public class SMSCodeInfoServiceImp implements SMSCodeInfoService {
	@Autowired
	private SMSCodeInfoDao smscodeinfoDao;
	
	public SMSCodeInfoDao getSmscodeinfoDao() {
		return smscodeinfoDao;
	}

	public void setSmscodeinfoDao(SMSCodeInfoDao smscodeinfoDao) {
		this.smscodeinfoDao = smscodeinfoDao;
	}

	public Integer isSMSCodeusable(String smscode, String mobile, Integer type) {
		return smscodeinfoDao.isSMSCodeusable(smscode, mobile, type);
	}

	public void delSMSCode(String smscode, String mobile, Integer type) {
		smscodeinfoDao.delSMSCode(smscode, mobile, type);
		
	}

	public void saveSMSCodeInfo(Map<String, Object> paramMap) {
		smscodeinfoDao.saveSMSCodeInfo(paramMap);
	}

}
