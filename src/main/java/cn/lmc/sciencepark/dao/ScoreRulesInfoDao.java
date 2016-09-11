package cn.lmc.sciencepark.dao;

import java.util.List;
import java.util.Map;

import cn.lmc.sciencepark.pojo.ScoreRulesInfo;

public interface ScoreRulesInfoDao {
	public Integer getActionScore(Map<String, Object> paramMap);
	public List<ScoreRulesInfo> getSignInScoreInfo();
}
