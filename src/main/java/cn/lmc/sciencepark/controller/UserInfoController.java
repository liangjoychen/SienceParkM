package cn.lmc.sciencepark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.service.IntegralInfoService;
import cn.lmc.sciencepark.service.SMSCodeInfoService;
import cn.lmc.sciencepark.service.ScoreRulesInfoService;
import cn.lmc.sciencepark.service.UserInfoService;
import cn.lmc.sciencepark.util.UserInfoUtil;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userinfoService = null;
	@Autowired
	private IntegralInfoService integralinfoService = null;
	@Autowired
	private ScoreRulesInfoService scorerulesinfoService = null;
	@Autowired
	private SMSCodeInfoService smscodeinfoService = null;

	private static final Logger log = LoggerFactory
			.getLogger(UserInfoController.class);
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd");

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	@RequestMapping(value = "/userinfo", method = RequestMethod.POST)
	public void showUserInfo(@RequestParam Integer userid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		Integer type = 0;
		String is_signday = "0";
		String is_sitesignday = "0";

		List<UserInfo> u = userinfoService.getUserInfoByID(userid);
		String sign_date = format.format(new Date(u.get(0).getSigntime()));
		String site_sign_date = format.format(new Date(u.get(0)
				.getSite_signtime()));
		String now_date = format.format(new Date(System.currentTimeMillis()));

		if (sign_date.equals(now_date)) {
			is_signday = "1";
		}

		if (site_sign_date.equals(now_date)) {
			is_sitesignday = "1";
		}

		u.get(0).setType(type);
		u.get(0).setIs_signday(is_signday);
		u.get(0).setIs_sitesignday(is_sitesignday);
		u.get(0).setSite_signtime(null);

		dataMap.put("success", true);
		dataMap.put("data", u);
		dataMap.put("msg", "");

		log.info("用户信息查询成功");

		PrintWriter out = response.getWriter();

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void userRegister(@RequestParam String user_name,
			@RequestParam String mobile, @RequestParam String login_type,
			@RequestParam String smscode, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_name != null && !(user_name.equals("")) && mobile != null
				&& !(mobile.equals("")) && login_type != null
				&& !(login_type.equals("")) && smscode != null
				&& !(smscode.equals("")) && password != null
				&& !(password.equals(""))) {
			Integer isSMSCodeUsable = smscodeinfoService.isSMSCodeusable(
					smscode, mobile, 1);

			paramMap.put("paraName", "name");
			paramMap.put("paraValue", user_name);
			log.info("isUsernameUsable的paramMap:" + paramMap.toString());
			Integer isUsernameUsable = userinfoService.isUserExist(paramMap);

			paramMap.clear();
			paramMap.put("paraName", "mobile");
			paramMap.put("paraValue", mobile);
			log.info("isMobileUsable的paramMap:" + paramMap.toString());
			Integer isMobileUsable = userinfoService.isUserExist(paramMap);

			if (isSMSCodeUsable > 0 && isUsernameUsable == 0
					&& isMobileUsable == 0) {
				paramMap.clear();
				paramMap.put("paraName", "register_scores");
				paramMap.put("tableName", "ahshop_score_rules");
				log.info("getActionScore的paramMap:" + paramMap.toString());
				Integer score = scorerulesinfoService.getActionScore(paramMap);

				paramMap.clear();
				paramMap.put("id", 0);
				paramMap.put("name", user_name);
				paramMap.put("mobile", mobile);
				paramMap.put("login_type", Integer.parseInt(login_type));
				paramMap.put("password", UserInfoUtil.encrypPassword(password));
				paramMap.put("scores", score);
				paramMap.put("created", System.currentTimeMillis() / 1000);
				paramMap.put("updated", System.currentTimeMillis() / 1000);
				paramMap.put("tableName", "ahshop_user");

				log.info("saveUserInfo的paramMap:" + paramMap.toString());

				userinfoService.saveUserInfo(paramMap);
				Integer userid = (Integer) paramMap.get("id");
				log.info("saveUserInfo的id:" + userid);

				paramMap.clear();
				paramMap.put("user_id", userid);
				paramMap.put("mark_description", "新用户注册");
				paramMap.put("mark", score);
				paramMap.put("type", 2);
				paramMap.put("created", System.currentTimeMillis() / 1000);
				paramMap.put("updated", System.currentTimeMillis() / 1000);

				paramMap.put("tableName", "ahshop_integral");
				log.info("saveIntegralInfo的paramMap:" + paramMap.toString());

				integralinfoService.saveIntegralInfo(paramMap);
				smscodeinfoService.delSMSCode(smscode, mobile, 1);

				dataMap.put("success", true);
				dataMap.put("data", "");
				dataMap.put("msg", "注册成功" + dataMap.toString());

				log.info("用户注册成功" + dataMap.toString());

			} else if (isSMSCodeUsable <= 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "验证码不正确");

				log.error("验证码不正确:" + dataMap.toString());
			} else if (isUsernameUsable > 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "用户名已注册");

				log.error("用户名已注册:" + dataMap.toString());
			} else if (isMobileUsable > 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "电话号码已注册");

				log.error("电话号码已注册:" + dataMap.toString());
			} else {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "注册失败");

				log.error("注册失败:" + dataMap.toString());
			}

			out.write(JSON.toJSONString(dataMap));

			out.close();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void userLogin(@RequestParam String user_name,
			@RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String paraName = "name";

		if (user_name != null && !(user_name.equals("")) && password != null
				&& !(password.equals(""))) {
			String epassword = UserInfoUtil.encrypPassword(password);

			if (UserInfoUtil.isMobileAsUsername(user_name)) {
				paraName = "mobile";
			}

			paramMap.put("id", 0);
			paramMap.put("paraName", paraName);
			paramMap.put("paraValue", user_name);
			paramMap.put("password", epassword);

			List<UserInfo> user = userinfoService.isUserlegal(paramMap);

			if (user == null || user.size() == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "账号或密码错误");

				log.error("账号或密码错误:" + dataMap.toString());
			} else if (user.get(0).getStatus() == 2) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "您已被管理员禁用了");

				log.error("您已被管理员禁用了:" + dataMap.toString());
			} else {
				user.get(0).setStatus(null);

				dataMap.put("success", true);
				dataMap.put("data", user);
				dataMap.put("msg", "");

				log.info("登录成功:" + dataMap.toString());
			}

			out.write(JSON.toJSONString(dataMap));

			out.close();
		}
	}

	@RequestMapping(value = "/getpasswordback", method = RequestMethod.POST)
	public void getPassWordBack(@RequestParam String mobile,
			@RequestParam String password, @RequestParam String smscode,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		Integer flag = 0;
		if (mobile != null && !(mobile.equals("")) && password != null
				&& !(password.equals("")) && smscode != null
				&& !(smscode.equals(""))) {

			paramMap.put("paraName", "mobile");
			paramMap.put("paraValue", mobile);
			log.info("isMobileUsable的paramMap:" + paramMap.toString());
			Integer isMobileUsable = userinfoService.isUserExist(paramMap);

			Integer isSMSCodeUsable = smscodeinfoService.isSMSCodeusable(
					smscode, mobile, 1);

			if (isMobileUsable > 0 && isSMSCodeUsable > 0) {
				String epassword = UserInfoUtil.encrypPassword(password);

				paramMap.clear();
				paramMap.put("paraName", "mobile");
				paramMap.put("paraValue", mobile);
				paramMap.put("mparaName", "password");
				paramMap.put("mparaValue", epassword);
				log.info("updatePassword的paramMap:" + paramMap.toString());

				flag = userinfoService.updateOneParam(paramMap);
				smscodeinfoService.delSMSCode(smscode, mobile, 1);

				if (flag == 0) {
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "修改失败");

					log.error("修改失败:" + dataMap.toString());
				} else {
					dataMap.put("success", true);
					dataMap.put("data", "");
					dataMap.put("msg", "修改成功");

					log.info("修改成功:" + dataMap.toString());
				}
			} else if (isMobileUsable == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "电话号码未注册");

				log.error("电话号码未注册:" + dataMap.toString());
			} else if (isSMSCodeUsable == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "验证码不正确");

				log.error("验证码不正确:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/modifiedpassword", method = RequestMethod.POST)
	public void modifiedPassword(@RequestParam Integer user_id,
			@RequestParam String old_password, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_id != null && password != null && !(password.equals(""))
				&& old_password != null && !(old_password.equals(""))) {
			Integer isOldPasswordRight = userinfoService.isOldPasswordRight(
					user_id, UserInfoUtil.encrypPassword(old_password));

			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);

			if (isUserExist > 0 && isOldPasswordRight > 0) {
				paramMap.clear();
				paramMap.put("paraName", "id");
				paramMap.put("paraValue", user_id);
				paramMap.put("mparaName", "password");
				paramMap.put("mparaValue",
						UserInfoUtil.encrypPassword(password));
				log.info("updatePassword的paramMap:" + paramMap.toString());

				Integer isPasswordModified = userinfoService
						.updateOneParam(paramMap);

				if (isPasswordModified == 0) {
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "修改失败");

					log.error("修改失败:" + dataMap.toString());
				} else {
					dataMap.put("success", true);
					dataMap.put("data", "");
					dataMap.put("msg", "修改成功");

					log.info("修改成功:" + dataMap.toString());
				}
			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			} else if (isOldPasswordRight == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "原密码不正确");

				log.error("原密码不正确:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/modifiedusername", method = RequestMethod.POST)
	public void modifiedUsername(@RequestParam Integer user_id,
			@RequestParam String user_name, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		if (user_id != null && user_name != null && !(user_name.equals(""))) {
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);

			paramMap.clear();
			paramMap.put("paraName", "name");
			paramMap.put("paraValue", user_name);
			log.info("isUsernameExist的paramMap:" + paramMap.toString());
			Integer isUsernameExist = userinfoService.isUserExist(paramMap);

			if (isUserExist > 0 && isUsernameExist == 0) {
				paramMap.clear();
				paramMap.put("paraName", "id");
				paramMap.put("paraValue", user_id);
				paramMap.put("mparaName", "name");
				paramMap.put("mparaValue", user_name);
				log.info("updatePassword的paramMap:" + paramMap.toString());

				Integer isUpdate = userinfoService.updateOneParam(paramMap);

				if (isUpdate == 0) {
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "修改失败");

					log.error("修改失败:" + dataMap.toString());
				} else {
					dataMap.put("success", true);
					dataMap.put("data", "");
					dataMap.put("msg", "用户名修改成功");

					log.info("用户名修改成功:" + dataMap.toString());
				}
			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			} else if (isUsernameExist > 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "用户名已注册");

				log.error("用户名已注册:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}

	@RequestMapping(value = "/modifieduserimg", method = RequestMethod.POST)
	public void modifiedUserImg(@RequestParam Integer user_id,
			@RequestParam String user_img, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		dataMap.clear();
		paramMap.clear();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		Calendar cal = Calendar.getInstance();
		
		if (user_id != null && user_img != null && !(user_img.equals(""))) {
			paramMap.put("paraName", "id");
			paramMap.put("paraValue", user_id);
			log.info("isUserExist的paramMap:" + paramMap.toString());
			Integer isUserExist = userinfoService.isUserExist(paramMap);


			if (isUserExist > 0) {
				user_img.replaceAll(" ", "");
				user_img.replaceAll("<", "");
				user_img.replaceAll(">", "");
				Integer year = cal.get(Calendar.YEAR);
				Integer month = cal.get(Calendar.MONTH) + 1;
				Integer day = cal.get(Calendar.DAY_OF_MONTH);
				String path = "/upload/"+year+"/"+month+"/"+day+"/";
				
				String realpathdir = request.getSession().getServletContext().getRealPath(path);
				
				log.info("图片上传路径:"+realpathdir);
				
				String filename = System.currentTimeMillis() / 1000 + ".jpg";
				
				UserInfoUtil.uploadImage(user_img, realpathdir, filename);
				
				paramMap.clear();
				paramMap.put("paraName", "id");
				paramMap.put("paraValue", user_id);
				paramMap.put("mparaName", "user_img");
				paramMap.put("mparaValue", user_img);
				log.info("updateUserImg的paramMap:" + paramMap.toString());

				Integer isUpdate = userinfoService.updateOneParam(paramMap);
				
				if(isUpdate > 0){
					dataMap.put("success", true);
					dataMap.put("data", "");
					dataMap.put("msg", "头像上传成功");

					log.info("头像上传成功:" + dataMap.toString());
				}else{
					dataMap.put("success", false);
					dataMap.put("data", "");
					dataMap.put("msg", "修改失败");

					log.error("修改失败:" + dataMap.toString());
				}

			} else if (isUserExist == 0) {
				dataMap.put("success", false);
				dataMap.put("data", "");
				dataMap.put("msg", "没有此用户");

				log.error("没有此用户:" + dataMap.toString());
			}
		}

		out.write(JSON.toJSONString(dataMap));

		out.close();
	}
}
