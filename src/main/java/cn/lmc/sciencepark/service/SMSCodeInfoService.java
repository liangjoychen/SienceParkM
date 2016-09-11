package cn.lmc.sciencepark.service;

import java.util.Map;

public interface SMSCodeInfoService {
	public Integer isSMSCodeusable(String smscode, String mobile, Integer type);
	public void delSMSCode(String smscode, String mobile, Integer type);
	public void saveSMSCodeInfo(Map<String, Object> paramMap);
}
