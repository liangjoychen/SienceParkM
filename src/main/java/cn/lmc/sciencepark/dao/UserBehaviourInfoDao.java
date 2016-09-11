package cn.lmc.sciencepark.dao;

import java.util.List;
import java.util.Map;

import cn.lmc.sciencepark.pojo.UserBehaviourBaseInfo;
import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.pojo.UserSignUpInfo;

public interface UserBehaviourInfoDao {
	
	public List<UserBehaviourBaseInfo> getMyProjectCollectionInfo(Integer user_id);
	public List<UserBehaviourBaseInfo> getMySubProjectCollectionInfo(Integer user_id);
	public List<UserBehaviourBaseInfo> getMyActivityCollectionInfo(Integer user_id);
	public List<UserBehaviourBaseInfo> getMyProjectCommentInfoList(Integer user_id);
	public List<UserBehaviourBaseInfo> getMySubProjectCommentInfoList(Integer user_id);
	public List<UserBehaviourBaseInfo> getMyActivityCommentInfoList(Integer user_id);
	public List<UserSignUpInfo> getMySignUpInfo(Integer user_id);
	public List<UserInfo> getMySignInInfo(Integer user_id);
	public Integer updateFirstSignInInfo(Map<String, Object> paramMap);
	public Integer updateSeriesSignInInfo(Map<String, Object> paramMap);
	public void saveUserFeedBack(Map<String, Object> paramMap);
	public void delMyCollectionInfo(Map<String, Object> paramMap);
	public void delMySignUpInfo(Map<String, Object> paramMap);
	public void delMyCommentInfo(Map<String, Object> paramMap);
	public void delMyRecordInfo(Map<String, Object> paramMap);
	public List<UserBehaviourBaseInfo> getMyProjectRecordInfo(Integer user_id);
	public List<UserBehaviourBaseInfo> getMySubProjectRecordInfo(Integer user_id);
	public List<UserBehaviourBaseInfo> getMyActivityRecordInfo(Integer user_id);
	
	public Integer isProjectParse(Integer user_id, Integer pid, Integer type);
	public void saveMyRecordInfo(Map<String, Object> paramMap);
	public Integer isMyRecordInfoExist(Integer user_id, Integer project_id, Integer type);
	public void updateMyRecodInfo(Integer user_id, Integer project_id, Integer type, Integer updated);
	public Integer isMyCollectionExist(Integer user_id, Integer project_id, Integer type);
	public void delMySingleCollection(Integer user_id, Integer project_id, Integer type);
	public void saveMyCollection(Map<String, Object> paramMap);
} 
