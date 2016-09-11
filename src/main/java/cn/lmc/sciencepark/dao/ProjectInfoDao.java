package cn.lmc.sciencepark.dao;

import java.util.List;

import cn.lmc.sciencepark.pojo.GoodReviewInfo;
import cn.lmc.sciencepark.pojo.ProjectCommentListInfo;
import cn.lmc.sciencepark.pojo.ProjectDetailInfo;
import cn.lmc.sciencepark.pojo.ProjectImageInfo;
import cn.lmc.sciencepark.pojo.ProjectListInfo;
import cn.lmc.sciencepark.pojo.SubProjectInfo;

public interface ProjectInfoDao {
	public List<ProjectListInfo> getProjectList(Integer city_id, String order_param, Integer type);
	public Integer isProjectExist(Integer id);
	public Integer isSubProjectExist(Integer id);
	public Integer isActivityExist(Integer id);
	public Integer countSubProjectByRenqi(Integer project_id);
	public List<ProjectImageInfo> getProjectImageInfo(Integer pid);
	public List<ProjectImageInfo> getSubProjectImageInfo(Integer pid);
	public List<ProjectDetailInfo> getProjectDetailInfo(Integer id);
	public List<ProjectDetailInfo> getProjectCollectInfo(Integer id);
	public Integer isProjectCollected(Integer pid, Integer user_id);
	public List<SubProjectInfo> getTopicInfo(Integer pid);
	public List<SubProjectInfo> getTemporaryInfo(Integer pid);
	public List<GoodReviewInfo> getGoodReviewInfo(Integer pid);
	public List<SubProjectInfo> getPopularInfo(Integer pid);
	public void updateCollectionNum(Integer id, Integer col_num);
	public List<ProjectCommentListInfo> getProjectCommentList(Integer pid, Integer type);
	public List<SubProjectInfo> getTemporatyListInfo(Integer pid, Integer type);
	public List<SubProjectInfo> getSubProjectCollectInfo(Integer id);
	public void updateSubCollectionNum(Integer id, Integer col_num);
	public List<SubProjectInfo> getSubCollectNum(Integer id);
}
