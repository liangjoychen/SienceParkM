package cn.lmc.sciencepark.dao;

import java.util.Map;

public interface SMSCodeInfoDao {
	public Integer isSMSCodeusable(String smscode, String mobile, Integer type);
	public void delSMSCode(String smscode, String mobile, Integer type);
	public void saveSMSCodeInfo(Map<String, Object> paramMap);
}
