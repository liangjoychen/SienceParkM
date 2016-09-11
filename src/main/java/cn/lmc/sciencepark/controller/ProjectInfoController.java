package cn.lmc.sciencepark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import cn.lmc.sciencepark.pojo.GoodReviewInfo;
import cn.lmc.sciencepark.pojo.ProjectCommentListInfo;
import cn.lmc.sciencepark.pojo.ProjectDetailInfo;
import cn.lmc.sciencepark.pojo.ProjectImageInfo;
import cn.lmc.sciencepark.pojo.ProjectListInfo;
import cn.lmc.sciencepark.pojo.SubProjectInfo;
import cn.lmc.sciencepark.service.ProjectInfoService;
import cn.lmc.sciencepark.service.UserBehaviourInfoService;
import cn.lmc.sciencepark.service.UserInfoService;

@Controller
@RequestMapping("/project")
public class ProjectInfoController {

	@Autowired
	private ProjectInfoService projectinfoService = null;
	
	@Autowired
	private UserInfoService userinfoService = null;
	
	@Autowired
	private UserBehaviourInfoService userbehaviourinfoService = null;

	private static final Logger log = LoggerFactory
			.getLogger(ProjectInfoController.class);

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	@SuppressWarnings("null")
	@RequestMapping(value = "/projectlist", method = RequestMethod.POST)
	public void getProjectList(@RequestParam Integer type,
			@RequestParam Integer status, @RequestParam Integer city_id, @RequestParam Integer page,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String order_param = "created";
		if(status == 2){
			order_param = "rating_score";
		}
		
		List<ProjectListInfo> all = projectinfoService.getProjectList(city_id, order_param, type);
		
		if(all != null || all.size() > 0){
			List<ProjectListInfo> projectinfo = all.subList(page * 10, page * 10 + 10);
			
			dataMap.put("success", true);
			dataMap.put("data", projectinfo);
			dataMap.put("msg", "");

			log.info("基地信息查询成功");
		}else{
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有数据");
		}
		
		out.write(JSON.toJSONString(dataMap, SerializerFeature.WriteMapNullValue));

		out.close();
	}
	
	@RequestMapping(value = "/projectdetail", method = RequestMethod.POST)
	public void getProjectDetailInfo(@RequestParam Integer project_id,
			@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		Pattern p_html =  Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
		
		Map<String, Object> temp = new HashMap<String, Object>();
		
		Integer isProjectExist = projectinfoService.isProjectExist(project_id);
		
		if(isProjectExist == 0){
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "没有基地");
		}else{
			Integer isHaveRenqiSubPro = projectinfoService.countSubProjectByRenqi(project_id);
			
			List<ProjectImageInfo> pimage = new ArrayList<ProjectImageInfo>();
			if(isHaveRenqiSubPro == 0){
				pimage = projectinfoService.getProjectImageInfo(project_id);
				
				for(ProjectImageInfo p : pimage){
					p.setIs_renqi(0);
				}
			}else{
				pimage = projectinfoService.getSubProjectImageInfo(project_id);
				
				for(ProjectImageInfo p : pimage){
					p.setIs_renqi(1);
				}
			}
			
			List<ProjectDetailInfo> pdetail = projectinfoService.getProjectDetailInfo(project_id);
			
			
			Matcher m_html = p_html.matcher(pdetail.get(0).getDetails_description());
			String description = m_html.replaceAll("");
			pdetail.get(0).setDetails_description(description);
			
			Integer isCollected = projectinfoService.isProjectCollected(project_id, user_id);
			
			if(isCollected == 0){
				pdetail.get(0).setIs_shoucang(0);
			}else{
				pdetail.get(0).setIs_shoucang(1);
			}
			
			List<SubProjectInfo> ptopic = projectinfoService.getTopicInfo(project_id);
			
			String pd_description = "";
			for(SubProjectInfo pd : ptopic){
				m_html = p_html.matcher(pd.getDetails_description());
				pd_description = m_html.replaceAll("");
				pd.setDetails_description(pd_description);
			}
			
			List<SubProjectInfo> ptemp = projectinfoService.getTemporaryInfo(project_id);
			
			List<GoodReviewInfo> grinfo = projectinfoService.getGoodReviewInfo(project_id);
			
			List<SubProjectInfo> ppop = projectinfoService.getPopularInfo(project_id);
			
			for(SubProjectInfo pp : ppop){
				pp.setIs_praise(0);
			}
			
			if(user_id != null){
				paramMap.put("paraName", "id");
				paramMap.put("paraValue", user_id);
				log.info("isUserExist的paramMap:" + paramMap.toString());
				Integer isUserExist = userinfoService.isUserExist(paramMap);
				if(isUserExist > 0){
					for(SubProjectInfo pp : ppop){
						if(userbehaviourinfoService.isProjectParse(user_id, project_id, 6) > 0){
							pp.setIs_praise(1);
						}
					}
				}
			}
			
			temp.put("images_data", pimage);
			temp.put("project_data", pdetail);
			temp.put("zhuti_data", ptopic);
			temp.put("renqi_data", ppop);
			temp.put("linzhan_data", ptemp);
			temp.put("comment_data", grinfo);
			
			dataMap.put("success", true);
			dataMap.put("data", temp);
			dataMap.put("msg", "");

			log.info("基地信息查询成功");
			
			paramMap.clear();
			paramMap.put("user_id", user_id);
			paramMap.put("project_id", project_id);
			paramMap.put("type", 1);
			paramMap.put("created", System.currentTimeMillis() / 1000);
			paramMap.put("updated", System.currentTimeMillis() / 1000);
			
			Integer isMyRecordExist = userbehaviourinfoService.isMyRecordInfoExist(user_id, project_id, 1);
			if(isMyRecordExist == 0){
				userbehaviourinfoService.saveMyRecordInfo(paramMap);
				log.info("浏览记录信息保存成功");
			}else if(isMyRecordExist > 0){
				Integer updated = (int) (System.currentTimeMillis() / 1000);
				userbehaviourinfoService.updateMyRecodInfo(user_id, project_id, 1, updated);
				log.info("浏览记录信息更新成功");
			}
		}
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
	
	@RequestMapping(value = "/projectcellect", method = RequestMethod.POST)
	public void collectProject(@RequestParam Integer project_id,
			@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(user_id != null && project_id != null){
			List<ProjectDetailInfo> collectioninfo = projectinfoService.getProjectCollectInfo(project_id);
			Integer ismycollectionexist = userbehaviourinfoService.isMyCollectionExist(user_id, project_id, 1);
			
			Integer col_num = collectioninfo.get(0).getCollection_number();
			if(ismycollectionexist > 0){
				Integer new_num = col_num-1;
				collectioninfo.get(0).setCollection_number(new_num);
				projectinfoService.updateCollectionNum(project_id, new_num);
				userbehaviourinfoService.delMySingleCollection(user_id, project_id, 1);
				dataMap.put("success", true);
				dataMap.put("data", collectioninfo);
				dataMap.put("msg", "取消成功");
			}else{
				paramMap.put("user_id", user_id);
				paramMap.put("pid", project_id);
				paramMap.put("type", 1);
				paramMap.put("created", System.currentTimeMillis() / 1000);
				paramMap.put("updated", System.currentTimeMillis() / 1000);
				userbehaviourinfoService.saveMyCollection(paramMap);
				
				Integer new_num = col_num+1;
				collectioninfo.get(0).setCollection_number(new_num);
				
				projectinfoService.updateCollectionNum(project_id, new_num);
				
				dataMap.put("success", true);
				dataMap.put("data", collectioninfo);
				dataMap.put("msg", "收藏成功");
			}
			
			out.write(JSON.toJSONString(dataMap));

			out.close();
		}
	}
	
	@RequestMapping(value = "/projectcommentlist", method = RequestMethod.POST)
	public void projectCommentList(@RequestParam Integer project_id,
			@RequestParam Integer user_id, @RequestParam Integer type,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(project_id != null && type != null){
			Integer isactivity = projectinfoService.isActivityExist(project_id);
			Integer isproject = projectinfoService.isProjectExist(project_id);
			Integer issubproject = projectinfoService.isSubProjectExist(project_id);
			
			if(type == 1 && isproject == 0){
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有基地");
			}else if((type == 2 || type == 3) && issubproject == 0){
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有展项");
			}else if(type == 4 && isactivity == 0){
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有活动");
			}else if(isproject > 0 || isactivity > 0 || issubproject > 0){
				List<ProjectCommentListInfo> pclist = projectinfoService.getProjectCommentList(project_id, type);
				
				if(user_id != null){
					paramMap.put("paraName", "id");
					paramMap.put("paraValue", user_id);
					log.info("isUserExist的paramMap:" + paramMap.toString());
					if(userinfoService.isUserExist(paramMap) > 0){
						for(ProjectCommentListInfo pc : pclist){
							if(userbehaviourinfoService.isProjectParse(user_id, project_id, 5) > 0){
								pc.setIs_praise(1);
							}else{
								pc.setIs_praise(0);
							}
						}
					}
				}
				
				dataMap.put("success", true);
				dataMap.put("data", pclist);
				dataMap.put("msg", "");
			}
		}
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
	
	@RequestMapping(value = "/temporarylist", method = RequestMethod.POST)
	public void getTemporaryListInfo(@RequestParam Integer project_id,
			@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(project_id != null && user_id != null){
			if(projectinfoService.isProjectExist(project_id) == 0){
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有基地");
			}else{
				Map<String, Object> temp = new HashMap<String, Object>();
				List<SubProjectInfo> clist = projectinfoService.getTemporatyListInfo(project_id, 1);
				
				paramMap.put("paraName", "id");
				paramMap.put("paraValue", user_id);
				log.info("isUserExist的paramMap:" + paramMap.toString());
				if(userinfoService.isUserExist(paramMap) > 0){
					for(SubProjectInfo pc : clist){
						if(userbehaviourinfoService.isProjectParse(user_id, project_id, 3) > 0){
							pc.setIs_praise(1);
						}else{
							pc.setIs_praise(0);
						}
					}
				}
				
				List<SubProjectInfo> slist = projectinfoService.getTemporatyListInfo(project_id, 2);
				List<SubProjectInfo> flist = projectinfoService.getTemporatyListInfo(project_id, 3);
				
				temp.put("rezhan_data", clist);
				temp.put("soon_data", slist);
				temp.put("before_data", flist);
				
				dataMap.put("success", true);
				dataMap.put("data", temp);
				dataMap.put("msg", "");
			}
		}
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
	
	@RequestMapping(value = "/subprojectcellect", method = RequestMethod.POST)
	public void collectSubProject(@RequestParam Integer subproject_id,
			@RequestParam Integer user_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(subproject_id != null && user_id != null){
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);
			if(isUserExist == 0){
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有用户");
			}else{
				List<SubProjectInfo> cel_info = projectinfoService.getSubProjectCollectInfo(subproject_id);
				Integer type = 2;
				if(cel_info.get(0).getType() == 1){
					if(cel_info.get(0).getSuoshuzhuzhan_pid() == 0)
						type = 2;
					else
						type = 5;
				}else if(cel_info.get(0).getType() == 2){
					type = 3;
				}
				
				Integer ismycollectionexist = userbehaviourinfoService.isMyCollectionExist(user_id, subproject_id, type);
				
				Integer col_num = cel_info.get(0).getCollection_number();
				
				if(ismycollectionexist > 0){
					Integer new_num = col_num-1;
					projectinfoService.updateCollectionNum(subproject_id, new_num);
					userbehaviourinfoService.delMySingleCollection(user_id, subproject_id, 1);
					List<SubProjectInfo> collect_num = projectinfoService.getSubCollectNum(subproject_id);
					dataMap.put("success", true);
					dataMap.put("data", collect_num);
					dataMap.put("msg", "取消成功");
				}else{
					paramMap.clear();
					paramMap.put("user_id", user_id);
					paramMap.put("pid", subproject_id);
					paramMap.put("type", type);
					paramMap.put("created", System.currentTimeMillis() / 1000);
					paramMap.put("updated", System.currentTimeMillis() / 1000);
					userbehaviourinfoService.saveMyCollection(paramMap);
					
					Integer new_num = col_num+1;
					
					projectinfoService.updateCollectionNum(subproject_id, new_num);
					List<SubProjectInfo> collect_num = projectinfoService.getSubCollectNum(subproject_id);
					
					dataMap.put("success", true);
					dataMap.put("data", collect_num);
					dataMap.put("msg", "收藏成功");
				}
			}
		}else{
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "参数错误");
		}
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
}
