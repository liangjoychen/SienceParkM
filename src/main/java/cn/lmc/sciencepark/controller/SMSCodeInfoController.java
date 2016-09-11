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

import cn.lmc.sciencepark.service.IntegralInfoService;
import cn.lmc.sciencepark.service.SMSCodeInfoService;
import cn.lmc.sciencepark.service.ScoreRulesInfoService;
import cn.lmc.sciencepark.service.UserInfoService;
import cn.lmc.sciencepark.util.UserInfoUtil;

@Controller
@RequestMapping("/smscode")
public class SMSCodeInfoController {

	@Autowired
	private UserInfoService userinfoService = null;
	@Autowired
	private IntegralInfoService integralinfoService = null;
	@Autowired
	private ScoreRulesInfoService scorerulesinfoService = null;
	@Autowired
	private SMSCodeInfoService smscodeinfoService = null;

	private static final Logger log = LoggerFactory
			.getLogger(SMSCodeInfoController.class);

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void sendSMSCode(@RequestParam String mobile, HttpServletRequest request, HttpServletResponse response) throws IOException{
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		paramMap.put("paraName", "mobile");
		paramMap.put("paraValue", mobile);
		log.info("isMobileUsable的paramMap:" + paramMap.toString());
		Integer isMobileUsable = userinfoService.isUserExist(paramMap);
		
		if(isMobileUsable == 0){
			String smscode = UserInfoUtil.getSMSCode();
			
			Integer isSMSCodeUsable = smscodeinfoService.isSMSCodeusable(smscode, mobile, 1);
			
			if(isSMSCodeUsable > 0){
				smscodeinfoService.delSMSCode(smscode, mobile, 1);
				log.info("注册码及手机号已存在,删除操作成功");
			}
			
			paramMap.clear();
			paramMap.put("smscode", smscode);
			paramMap.put("mobile", mobile);
			paramMap.put("isused", 0);
			paramMap.put("addtime", System.currentTimeMillis() / 1000);
			paramMap.put("type", 1);
			paramMap.put("tableName", "ahshop_smscode");
			log.info("saveSMSCodeInfo的paramMap:" + paramMap.toString());
			
			smscodeinfoService.saveSMSCodeInfo(paramMap);
			
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("code", smscode);
			list.add(m);
			
			dataMap.put("success", true);
			dataMap.put("data", list);
			dataMap.put("msg", "");
			
			UserInfoUtil.sendSMSCode(mobile, new String[]{smscode, "1"}, "53499");
			
			log.info("验证码发送成功,发送内容:"+smscode);
		}else{
			dataMap.put("success", false);
			dataMap.put("data", "");
			dataMap.put("msg", "电话号码已注册");

			log.error("电话号码已注册:" + dataMap.toString());
		}
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
	
	@RequestMapping(value = "/repsend", method = RequestMethod.POST)
	public void sendREPSMSCode(@RequestParam String mobile, HttpServletRequest request, HttpServletResponse response) throws IOException{
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
	
		String smscode = UserInfoUtil.getSMSCode();
		
		Integer isSMSCodeUsable = smscodeinfoService.isSMSCodeusable(smscode, mobile, 1);
		
		if(isSMSCodeUsable > 0){
			smscodeinfoService.delSMSCode(smscode, mobile, 1);
			log.info("注册码及手机号已存在,删除操作成功");
		}
		
		paramMap.clear();
		paramMap.put("smscode", smscode);
		paramMap.put("mobile", mobile);
		paramMap.put("isused", 0);
		paramMap.put("addtime", System.currentTimeMillis() / 1000);
		paramMap.put("type", 1);
		paramMap.put("tableName", "ahshop_smscode");
		log.info("saveSMSCodeInfo的paramMap:" + paramMap.toString());
		
		smscodeinfoService.saveSMSCodeInfo(paramMap);
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("code", smscode);
		list.add(m);
		
		dataMap.put("success", true);
		dataMap.put("data", list);
		dataMap.put("msg", "");
		
		UserInfoUtil.sendSMSCode(mobile, new String[]{smscode, "1"}, "53499");
		
		log.info("验证码发送成功,发送内容:"+smscode);
		
		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
}
