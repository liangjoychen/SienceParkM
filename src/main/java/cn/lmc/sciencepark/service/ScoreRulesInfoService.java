package cn.lmc.sciencepark.service;

import java.util.List;
import java.util.Map;

import cn.lmc.sciencepark.pojo.ScoreRulesInfo;

public interface ScoreRulesInfoService {
	public Integer getActionScore(Map<String, Object> paramMap);
	public List<ScoreRulesInfo> getSignInScoreInfo();
}
