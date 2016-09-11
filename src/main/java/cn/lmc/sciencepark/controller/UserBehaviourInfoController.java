package cn.lmc.sciencepark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.lmc.sciencepark.pojo.ScoreRulesInfo;
import cn.lmc.sciencepark.pojo.UserBehaviourBaseInfo;
import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.pojo.UserSignUpInfo;
import cn.lmc.sciencepark.service.IntegralInfoService;
import cn.lmc.sciencepark.service.ScoreRulesInfoService;
import cn.lmc.sciencepark.service.UserBehaviourInfoService;
import cn.lmc.sciencepark.service.UserInfoService;

@Controller
@RequestMapping("/userbehaviour")
public class UserBehaviourInfoController {

	@Autowired
	private UserInfoService userinfoService = null;
	@Autowired
	private UserBehaviourInfoService userbehaviourinfoService = null;
	@Autowired
	private ScoreRulesInfoService scorerulesinfoService = null;
	@Autowired
	private IntegralInfoService integralinfoService = null;

	private static final Logger log = LoggerFactory
			.getLogger(UserBehaviourInfoController.class);

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	@RequestMapping(value = "/mycollection", method = RequestMethod.POST)
	public void getMycollectionInfo(@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_id != null) {
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);

			if (isUserExist > 0) {
				List<UserBehaviourBaseInfo> mypCollectionInfo = userbehaviourinfoService
						.getMyProjectCollectionInfo(user_id);
				List<UserBehaviourBaseInfo> myspCollectionInfo = userbehaviourinfoService
						.getMySubProjectCollectionInfo(user_id);
				List<UserBehaviourBaseInfo> myaCollectionInfo = userbehaviourinfoService
						.getMyActivityCollectionInfo(user_id);

				mypCollectionInfo.addAll(myspCollectionInfo);
				mypCollectionInfo.addAll(myaCollectionInfo);

				dataMap.put("success", true);
				dataMap.put("data", mypCollectionInfo);
				dataMap.put("msg", "");

				log.info("数据查询成功");
			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap,
				SerializerFeature.WriteMapNullValue));

		out.close();
	}

	@RequestMapping(value = "/mycomment", method = RequestMethod.POST)
	public void getMyCommentList(@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_id != null) {
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);

			if (isUserExist > 0) {
				List<UserBehaviourBaseInfo> mypCommentInfo = userbehaviourinfoService
						.getMyProjectCommentInfoList(user_id);
				List<UserBehaviourBaseInfo> myspCommentInfo = userbehaviourinfoService
						.getMySubProjectCommentInfoList(user_id);
				List<UserBehaviourBaseInfo> myaCommentInfo = userbehaviourinfoService
						.getMyActivityCommentInfoList(user_id);

				mypCommentInfo.addAll(myspCommentInfo);
				mypCommentInfo.addAll(myaCommentInfo);

				dataMap.put("success", true);
				dataMap.put("data", mypCommentInfo);
				dataMap.put("msg", "");

				log.info("数据查询成功");
			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap,
				SerializerFeature.WriteMapNullValue));

		out.close();
	}

	@RequestMapping(value = "/mysignup", method = RequestMethod.POST)
	public void getMySignUpInfo(@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_id != null) {
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);

			if (isUserExist > 0) {
				List<UserSignUpInfo> mySignUpInfo = userbehaviourinfoService
						.getMySignUpInfo(user_id);

				dataMap.put("success", true);
				dataMap.put("data", mySignUpInfo);
				dataMap.put("msg", "");

				log.info("数据查询成功");
			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap,
				SerializerFeature.WriteMapNullValue));

		out.close();
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void userSignIn(@RequestParam Integer user_id,
			@RequestParam Integer lat, @RequestParam Integer lng,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		List<UserInfo> signininfo = userbehaviourinfoService
				.getMySignInInfo(user_id);
		List<ScoreRulesInfo> signinscoreinfo = scorerulesinfoService
				.getSignInScoreInfo();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		Integer signinType = 0;

		if (isUserExist > 0) {
			paramMap.clear();
			if (signinType == 1) {
				if (signininfo.get(0).getSite_signtime() == null
						|| signininfo.get(0).getSite_signtime() == 0) {
					// 首次现场签到
					Integer scores = signininfo.get(0).getScores()
							+ signinscoreinfo.get(0).getSite_scores();
					Integer site_signtime = (int) (System.currentTimeMillis() / 1000);

					paramMap.put("site_signtime", site_signtime);
					paramMap.put("scores", scores);
					paramMap.put("user_id", user_id);

					userbehaviourinfoService.updateFirstSignInInfo(paramMap);

					paramMap.clear();
					paramMap.put("user_id", user_id);
					paramMap.put("mark_description", "现场签到");
					paramMap.put("mark", scores);
					paramMap.put("type", 1);
					paramMap.put("created", System.currentTimeMillis() / 1000);
					paramMap.put("updated", System.currentTimeMillis() / 1000);

					paramMap.put("tableName", "ahshop_integral");
					log.info("saveIntegralInfo的paramMap:" + paramMap.toString());
					integralinfoService.saveIntegralInfo(paramMap);

					List<UserInfo> copysignininfo = new ArrayList<UserInfo>(
							signininfo);

					copysignininfo.get(0).setScores(scores);
					copysignininfo.get(0).setSite_signtime(site_signtime);

					dataMap.put("success", true);
					dataMap.put("data", copysignininfo);
					dataMap.put("msg", "");

					log.info("签到成功");
				} else if ((signininfo.get(0).getSite_signtime() - (System
						.currentTimeMillis() / 1000)) == 0) {
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "今日已签到");

					log.error("今日已签到:" + dataMap.toString());
				} else if ((signininfo.get(0).getSite_signtime() - (System
						.currentTimeMillis() / 1000)) >= 24 * 60 * 60) {
					Integer signscore = signininfo.get(0).getSignscore();
					if (signscore > signinscoreinfo.get(0).getFirst_scores() * 6) {
						signscore += signinscoreinfo.get(0).getFirst_scores();
					}

					Integer scores = signininfo.get(0).getScores() + signscore;
					Integer signtime = (int) (System.currentTimeMillis() / 1000);

					paramMap.put("signtime", signtime);
					paramMap.put("scores", scores);
					paramMap.put("user_id", user_id);

					userbehaviourinfoService.updateSeriesSignInInfo(paramMap);

					paramMap.clear();
					paramMap.put("user_id", user_id);
					paramMap.put("mark_description", "现场签到");
					paramMap.put("mark", scores);
					paramMap.put("type", 1);
					paramMap.put("created", System.currentTimeMillis() / 1000);
					paramMap.put("updated", System.currentTimeMillis() / 1000);

					paramMap.put("tableName", "ahshop_integral");
					log.info("saveIntegralInfo的paramMap:" + paramMap.toString());
					integralinfoService.saveIntegralInfo(paramMap);

					List<UserInfo> copysignininfo = new ArrayList<UserInfo>(
							signininfo);

					copysignininfo.get(0).setScores(scores);
					copysignininfo.get(0).setSigntime(signtime);

					dataMap.put("success", true);
					dataMap.put("data", copysignininfo);
					dataMap.put("msg", "");

					log.info("签到成功");
				}
			} else {
				dataMap.clear();
				paramMap.clear();
				System.out.println(System.currentTimeMillis() / 1000);
				if (signininfo.get(0).getSigntime() == null
						|| signininfo.get(0).getSigntime() == 0) {
					// 首次现场签到
					Integer scores = signininfo.get(0).getScores()
							+ signinscoreinfo.get(0).getFirst_scores();
					Integer signtime = (int) (System.currentTimeMillis() / 1000);

					paramMap.put("signtime", signtime);
					paramMap.put("scores", scores);
					paramMap.put("user_id", user_id);

					userbehaviourinfoService.updateFirstSignInInfo(paramMap);

					paramMap.clear();
					paramMap.put("user_id", user_id);
					paramMap.put("mark_description", "签到");
					paramMap.put("mark", scores);
					paramMap.put("type", 1);
					paramMap.put("created", System.currentTimeMillis() / 1000);
					paramMap.put("updated", System.currentTimeMillis() / 1000);

					paramMap.put("tableName", "ahshop_integral");
					log.info("saveIntegralInfo的paramMap:" + paramMap.toString());
					integralinfoService.saveIntegralInfo(paramMap);

					List<UserInfo> copysignininfo = new ArrayList<UserInfo>(
							signininfo);

					copysignininfo.get(0).setScores(scores);
					copysignininfo.get(0).setSigntime(signtime);

					dataMap.put("success", true);
					dataMap.put("data", copysignininfo);
					dataMap.put("msg", "");

					log.info("签到成功");
				} else if ((signininfo.get(0).getSigntime() - (System
						.currentTimeMillis() / 1000)) == 0) {
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "今日已签到");

					log.error("今日已签到:" + dataMap.toString());
				} else if (((System.currentTimeMillis() / 1000) - signininfo
						.get(0).getSigntime()) >= 24 * 60 * 60) {
					Integer signscore = signininfo.get(0).getSignscore();
					if (signscore > signinscoreinfo.get(0).getFirst_scores() * 6) {
						signscore += signinscoreinfo.get(0).getFirst_scores();
					}

					Integer scores = signininfo.get(0).getScores() + signscore;
					Integer signtime = (int) (System.currentTimeMillis() / 1000);

					paramMap.put("signtime", signtime);
					paramMap.put("scores", scores);
					paramMap.put("user_id", user_id);

					userbehaviourinfoService.updateSeriesSignInInfo(paramMap);

					paramMap.clear();
					paramMap.put("user_id", user_id);
					paramMap.put("mark_description", "现场签到");
					paramMap.put("mark", scores);
					paramMap.put("type", 1);
					paramMap.put("created", System.currentTimeMillis() / 1000);
					paramMap.put("updated", System.currentTimeMillis() / 1000);

					paramMap.put("tableName", "ahshop_integral");
					log.info("saveIntegralInfo的paramMap:" + paramMap.toString());
					integralinfoService.saveIntegralInfo(paramMap);

					List<UserInfo> copysignininfo = new ArrayList<UserInfo>(
							signininfo);

					copysignininfo.get(0).setScores(scores);
					copysignininfo.get(0).setSigntime(signtime);

					dataMap.put("success", true);
					dataMap.put("data", copysignininfo);
					dataMap.put("msg", "");

					log.info("签到成功");
				}
			}
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public void saveUserFeedBack(@RequestParam Integer user_id,
			@RequestParam String content, @RequestParam String email,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0) {
			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("content", content);
			paramMap.put("email", email);

			userbehaviourinfoService.saveUserFeedBack(paramMap);

			dataMap.put("success", true);
			dataMap.put("data", "");
			dataMap.put("msg", "提交意见反馈成功");

			log.error("提交意见反馈成功:" + dataMap.toString());
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/delmycollection", method = RequestMethod.POST)
	public void delMyCollectionInfo(@RequestParam Integer user_id,
			@RequestParam String collection_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0 && collection_id != null
				&& !(collection_id.equals(""))) {
			String[] col_arr = collection_id.split(",");
			Integer[] cids = new Integer[col_arr.length];

			for (int i = 0; i < cids.length; i++) {
				cids[i] = Integer.parseInt(col_arr[i]);
			}

			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("cids", cids);
			userbehaviourinfoService.delMyCollectionInfo(paramMap);

			dataMap.put("success", true);
			dataMap.put("data", "");
			dataMap.put("msg", "删除成功");

			log.info("删除成功:" + dataMap.toString());
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/delmysignup", method = RequestMethod.POST)
	public void delMySignUpInfo(@RequestParam Integer user_id,
			@RequestParam String baoming_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0 && baoming_id != null && !(baoming_id.equals(""))) {
			String[] sig_arr = baoming_id.split(",");
			Integer[] sids = new Integer[sig_arr.length];

			for (int i = 0; i < sids.length; i++) {
				sids[i] = Integer.parseInt(sig_arr[i]);
			}

			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("sids", sids);
			userbehaviourinfoService.delMySignUpInfo(paramMap);

			dataMap.put("success", true);
			dataMap.put("data", "");
			dataMap.put("msg", "删除成功");

			log.info("删除成功:" + dataMap.toString());
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/delmycomment", method = RequestMethod.POST)
	public void delMyCommentInfo(@RequestParam Integer user_id,
			@RequestParam String comment_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0 && comment_id != null && !(comment_id.equals(""))) {
			String[] col_arr = comment_id.split(",");
			Integer[] cids = new Integer[col_arr.length];

			for (int i = 0; i < cids.length; i++) {
				cids[i] = Integer.parseInt(col_arr[i]);
			}

			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("cids", cids);
			userbehaviourinfoService.delMyCommentInfo(paramMap);

			dataMap.put("success", true);
			dataMap.put("data", "");
			dataMap.put("msg", "删除成功");

			log.info("删除成功:" + dataMap.toString());
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/delmyrecord", method = RequestMethod.POST)
	public void delMyRecordInfo(@RequestParam Integer user_id,
			@RequestParam String record_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0 && record_id != null && !(record_id.equals(""))) {
			String[] rec_arr = record_id.split(",");
			Integer[] rids = new Integer[rec_arr.length];

			for (int i = 0; i < rids.length; i++) {
				rids[i] = Integer.parseInt(rec_arr[i]);
			}

			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("cids", rids);
			userbehaviourinfoService.delMyRecordInfo(paramMap);

			dataMap.put("success", true);
			dataMap.put("data", "");
			dataMap.put("msg", "删除成功");

			log.info("删除成功:" + dataMap.toString());
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/getmyrecord", method = RequestMethod.POST)
	public void getMyRecordInfo(@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		paramMap.put("paraName", "id");
		paramMap.put("paraValue", user_id);
		log.info("isUserExist的paramMap:" + paramMap.toString());

		Integer isUserExist = userinfoService.isUserExist(paramMap);

		if (isUserExist > 0) {
			List<UserBehaviourBaseInfo> mypRecordInfo = userbehaviourinfoService
					.getMyProjectRecordInfo(user_id);
			List<UserBehaviourBaseInfo> myspRecordInfo = userbehaviourinfoService
					.getMySubProjectRecordInfo(user_id);
			List<UserBehaviourBaseInfo> myaRecordnInfo = userbehaviourinfoService
					.getMyActivityRecordInfo(user_id);

			mypRecordInfo.addAll(myspRecordInfo);
			mypRecordInfo.addAll(myaRecordnInfo);

			dataMap.put("success", true);
			dataMap.put("data", mypRecordInfo);
			dataMap.put("msg", "");

			log.info("数据查询成功");
		} else {
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有此用户");

			log.error("没有此用户:" + dataMap.toString());
		}

		out.write(JSON.toJSONString(dataMap, SerializerFeature.WriteMapNullValue));

		out.close();
	}
}
