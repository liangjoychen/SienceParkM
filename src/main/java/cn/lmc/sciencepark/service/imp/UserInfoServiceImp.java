package cn.lmc.sciencepark.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lmc.sciencepark.dao.UserInfoDao;
import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.service.UserInfoService;

@Service
public class UserInfoServiceImp implements UserInfoService {

	@Autowired
	private UserInfoDao userinfoDao;
	
	public UserInfoDao getUserinfoDao() {
		return userinfoDao;
	}

	public void setUserinfoDao(UserInfoDao userinfoDao) {
		this.userinfoDao = userinfoDao;
	}

	public List<UserInfo> getUserInfoByID(Integer id) {
		// TODO Auto-generated method stub
		return userinfoDao.getUserInfoByID(id);
	}

	public void saveUserInfo(Map<String, Object> paramMap) {
		userinfoDao.saveUserInfo(paramMap);
		
	}

	public Integer isUserExist(Map<String, Object> paramMap) {
		return userinfoDao.isUserExist(paramMap);
	}

	public List<UserInfo> isUserlegal(Map<String, Object> paramMap) {
		return userinfoDao.isUserlegal(paramMap);
	}

	public Integer isOldPasswordRight(Integer user_id, String password) {
		return userinfoDao.isOldPasswordRight(user_id, password);
	}

	public Integer updateOneParam(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return userinfoDao.updateOneParam(paramMap);
	}
}
