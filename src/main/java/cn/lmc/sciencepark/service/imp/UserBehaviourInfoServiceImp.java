package cn.lmc.sciencepark.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.lmc.sciencepark.dao.UserBehaviourInfoDao;
import cn.lmc.sciencepark.pojo.UserBehaviourBaseInfo;
import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.pojo.UserSignUpInfo;
import cn.lmc.sciencepark.service.UserBehaviourInfoService;

public class UserBehaviourInfoServiceImp implements UserBehaviourInfoService {

	@Autowired
	private UserBehaviourInfoDao userbehaviourinfoDao;
	
	public UserBehaviourInfoDao getUserbehaviourinfoDao() {
		return userbehaviourinfoDao;
	}

	public void setUserbehaviourinfoDao(UserBehaviourInfoDao userbehaviourinfoDao) {
		this.userbehaviourinfoDao = userbehaviourinfoDao;
	}

	public List<UserBehaviourBaseInfo> getMyProjectCollectionInfo(Integer user_id) {
		return userbehaviourinfoDao.getMyProjectCollectionInfo(user_id);
	}

	public List<UserBehaviourBaseInfo> getMySubProjectCollectionInfo(Integer user_id) {
		return userbehaviourinfoDao.getMySubProjectCollectionInfo(user_id);
	}

	public List<UserBehaviourBaseInfo> getMyActivityCollectionInfo(Integer user_id) {
		return userbehaviourinfoDao.getMyActivityCollectionInfo(user_id);
	}

	public List<UserBehaviourBaseInfo> getMyProjectCommentInfoList(Integer user_id) {
		return userbehaviourinfoDao.getMyProjectCommentInfoList(user_id);
	}

	public List<UserBehaviourBaseInfo> getMySubProjectCommentInfoList(Integer user_id) {
		return userbehaviourinfoDao.getMySubProjectCommentInfoList(user_id);
	}

	public List<UserBehaviourBaseInfo> getMyActivityCommentInfoList(Integer user_id) {
		return userbehaviourinfoDao.getMyActivityCommentInfoList(user_id);
	}

	public List<UserSignUpInfo> getMySignUpInfo(Integer user_id) {
		return userbehaviourinfoDao.getMySignUpInfo(user_id);
	}

	public List<UserInfo> getMySignInInfo(Integer user_id) {
		return userbehaviourinfoDao.getMySignInInfo(user_id);
	}

	public Integer updateFirstSignInInfo(Map<String, Object> paramMap) {
		return userbehaviourinfoDao.updateFirstSignInInfo(paramMap);
	}

	public Integer updateSeriesSignInInfo(Map<String, Object> paramMap) {
		return userbehaviourinfoDao.updateSeriesSignInInfo(paramMap);
	}

	public void saveUserFeedBack(Map<String, Object> paramMap) {
		userbehaviourinfoDao.saveUserFeedBack(paramMap);
	}

	public void delMyCollectionInfo(Map<String, Object> paramMap) {
		userbehaviourinfoDao.delMyCollectionInfo(paramMap);
		
	}

	public void delMySignUpInfo(Map<String, Object> paramMap) {
		userbehaviourinfoDao.delMySignUpInfo(paramMap);	
	}

	public void delMyCommentInfo(Map<String, Object> paramMap) {
		userbehaviourinfoDao.delMyCommentInfo(paramMap);
	}

	public void delMyRecordInfo(Map<String, Object> paramMap) {
		userbehaviourinfoDao.delMyRecordInfo(paramMap);
	}

	public List<UserBehaviourBaseInfo> getMyProjectRecordInfo(Integer user_id) {
		return userbehaviourinfoDao.getMyProjectRecordInfo(user_id);
	}

	public List<UserBehaviourBaseInfo> getMySubProjectRecordInfo(Integer user_id) {
		return userbehaviourinfoDao.getMySubProjectRecordInfo(user_id);
	}

	public List<UserBehaviourBaseInfo> getMyActivityRecordInfo(Integer user_id) {
		return userbehaviourinfoDao.getMyActivityRecordInfo(user_id);
	}

	public Integer isProjectParse(Integer user_id, Integer pid, Integer type) {
		return userbehaviourinfoDao.isProjectParse(user_id, pid, type);
	}

	public void saveMyRecordInfo(Map<String, Object> paramMap) {
		userbehaviourinfoDao.saveMyRecordInfo(paramMap);
		
	}
	
	public Integer isMyRecordInfoExist(Integer user_id, Integer project_id, Integer type){
		return userbehaviourinfoDao.isMyRecordInfoExist(user_id, project_id, type);
	}
	
	public void updateMyRecodInfo(Integer user_id, Integer project_id, Integer type, Integer updated){
		userbehaviourinfoDao.updateMyRecodInfo(user_id, project_id, type, updated);
	}

	public Integer isMyCollectionExist(Integer user_id, Integer project_id,
			Integer type) {
		return userbehaviourinfoDao.isMyCollectionExist(user_id, project_id, type);
	}

	public void delMySingleCollection(Integer user_id, Integer project_id,
			Integer type) {
		userbehaviourinfoDao.delMySingleCollection(user_id, project_id, type);
	}

	public void saveMyCollection(Map<String, Object> paramMap) {
		userbehaviourinfoDao.saveMyCollection(paramMap);	
	}
}
