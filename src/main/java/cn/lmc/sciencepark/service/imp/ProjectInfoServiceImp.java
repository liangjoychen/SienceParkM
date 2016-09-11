package cn.lmc.sciencepark.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lmc.sciencepark.dao.ProjectInfoDao;
import cn.lmc.sciencepark.pojo.GoodReviewInfo;
import cn.lmc.sciencepark.pojo.ProjectCommentListInfo;
import cn.lmc.sciencepark.pojo.ProjectDetailInfo;
import cn.lmc.sciencepark.pojo.ProjectImageInfo;
import cn.lmc.sciencepark.pojo.ProjectListInfo;
import cn.lmc.sciencepark.pojo.SubProjectInfo;
import cn.lmc.sciencepark.service.ProjectInfoService;

@Service
public class ProjectInfoServiceImp implements ProjectInfoService {
	@Autowired
	private ProjectInfoDao projectinfoDao;
	
	public ProjectInfoDao getProjectinfoDao() {
		return projectinfoDao;
	}

	public void setProjectinfoDao(ProjectInfoDao projectinfoDao) {
		this.projectinfoDao = projectinfoDao;
	}

	public List<ProjectListInfo> getProjectList(Integer city_id,
			String order_param, Integer type) {
		return projectinfoDao.getProjectList(city_id, order_param, type);
	}

	public Integer isProjectExist(Integer id) {
		return projectinfoDao.isProjectExist(id);
	}

	public Integer countSubProjectByRenqi(Integer project_id) {
		return projectinfoDao.countSubProjectByRenqi(project_id);
	}

	public List<ProjectImageInfo> getProjectImageInfo(Integer pid) {
		return projectinfoDao.getProjectImageInfo(pid);
	}

	public List<ProjectImageInfo> getSubProjectImageInfo(Integer pid) {
		return projectinfoDao.getSubProjectImageInfo(pid);
	}

	public List<ProjectDetailInfo> getProjectDetailInfo(Integer id) {
		return projectinfoDao.getProjectDetailInfo(id);
	}

	public Integer isProjectCollected(Integer pid, Integer user_id) {
		return projectinfoDao.isProjectCollected(pid, user_id);
	}

	public List<SubProjectInfo> getTopicInfo(Integer pid) {
		return projectinfoDao.getTopicInfo(pid);
	}

	public List<SubProjectInfo> getTemporaryInfo(Integer pid) {
		return projectinfoDao.getTemporaryInfo(pid);
	}

	public List<GoodReviewInfo> getGoodReviewInfo(Integer pid) {
		return projectinfoDao.getGoodReviewInfo(pid);
	}

	public List<SubProjectInfo> getPopularInfo(Integer pid) {
		return projectinfoDao.getPopularInfo(pid);
	}

	public List<ProjectDetailInfo> getProjectCollectInfo(Integer id) {
		return projectinfoDao.getProjectCollectInfo(id);
	}

	public void updateCollectionNum(Integer id, Integer col_num) {
		projectinfoDao.updateCollectionNum(id, col_num);
	}

	public Integer isSubProjectExist(Integer id) {
		return projectinfoDao.isSubProjectExist(id);
	}

	public Integer isActivityExist(Integer id) {
		return projectinfoDao.isActivityExist(id);
	}

	public List<ProjectCommentListInfo> getProjectCommentList(Integer pid,
			Integer type) {
		return projectinfoDao.getProjectCommentList(pid, type);
	}

	public List<SubProjectInfo> getTemporatyListInfo(Integer pid, Integer type) {
		return projectinfoDao.getTemporatyListInfo(pid, type);
	}

	public List<SubProjectInfo> getSubProjectCollectInfo(Integer id) {
		return projectinfoDao.getSubProjectCollectInfo(id);
	}

	public void updateSubCollectionNum(Integer id, Integer col_num) {
		projectinfoDao.updateSubCollectionNum(id, col_num);	
	}

	public List<SubProjectInfo> getSubCollectNum(Integer id) {
		return projectinfoDao.getSubCollectNum(id);
	}

}
