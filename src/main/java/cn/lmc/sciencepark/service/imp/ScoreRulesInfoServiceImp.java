package cn.lmc.sciencepark.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.lmc.sciencepark.dao.ScoreRulesInfoDao;
import cn.lmc.sciencepark.pojo.ScoreRulesInfo;
import cn.lmc.sciencepark.service.ScoreRulesInfoService;

public class ScoreRulesInfoServiceImp implements ScoreRulesInfoService {

	@Autowired
	private ScoreRulesInfoDao scorerulesinfoDao;
	
	public ScoreRulesInfoDao getScorerulesinfoDao() {
		return scorerulesinfoDao;
	}

	public void setScorerulesinfoDao(ScoreRulesInfoDao scorerulesinfoDao) {
		this.scorerulesinfoDao = scorerulesinfoDao;
	}

	public Integer getActionScore(Map<String, Object> paramMap) {
		return scorerulesinfoDao.getActionScore(paramMap);
	}

	public List<ScoreRulesInfo> getSignInScoreInfo() {
		return scorerulesinfoDao.getSignInScoreInfo();
	}

}
