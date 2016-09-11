package cn.lmc.sciencepark.service;

import java.util.List;
import java.util.Map;

import cn.lmc.sciencepark.pojo.UserInfo;

public interface UserInfoService {
	public List<UserInfo> getUserInfoByID(Integer id);
	public void saveUserInfo(Map<String, Object> paramMap);
	public Integer isUserExist(Map<String, Object> paramMap);
	public List<UserInfo> isUserlegal(Map<String, Object> paramMap);
	public Integer updateOneParam(Map<String, Object> paramMap);
	public Integer isOldPasswordRight(Integer user_id, String password);
}
