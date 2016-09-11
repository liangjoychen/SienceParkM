package cn.lmc.sciencepark.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class UserInfoUtil {
	private static final Logger log = LoggerFactory.getLogger(UserInfoUtil.class);
	
	private static byte charToByte(char c){
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	private static byte[] getImageFileByte(String imageStr) {
		int len = (imageStr.length() / 2);
		byte[] imagebyte = new byte[len];
		
		char[] imagechar = imageStr.toCharArray();
		
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			imagebyte[i] = (byte) (charToByte(imagechar[pos]) << 4 | charToByte(imagechar[pos + 1]));
		}
		
		return imagebyte;
	}
	
	public static String encrypPassword(String password){
		try {  
            MessageDigest md = MessageDigest.getInstance("MD5");
            String newpassword = "aohuan.ahshop"+password;
            md.update(newpassword.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            log.info("密码32位加密成功。密码内容:"+buf.toString());
            
            return buf.toString();  

        } catch (NoSuchAlgorithmException e) {
        	log.error("密码32位加密失敗");
            e.printStackTrace();  
            return null;  
        }  
	}
	
	public static String getSMSCode(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++)
		    result = result * 10 + array[i];
        
        return String.valueOf(result);
          
	}
	
	//**************************************举例说明***********************************************************************
 	//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
 	//*result = sendTemplateSMS("13800000000" ,array('6532','5'),"1");																		  *
 	//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
 	//*********************************************************************************************************************
 	//sendTemplateSMS("",array('',''),"");//手机号码，替换内容数组，模板ID
	public static void sendSMSCode(String mobile, String[] data, String tempID){
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		//主帐号,对应开官网发者主账号下的 ACCOUNT SID
		String accountSid= "aaf98f89510f639f015113c154340dc7";
		//主帐号令牌,对应官网开发者主账号下的 AUTH TOKEN
		String accountToken= "e74d6111cb3148f3ae300bd211041ada";
		//应用Id，在官网应用列表中点击应用，对应应用详情中的APP ID
		//在开发调试的时候，可以使用官网自动为您分配的测试Demo的APP ID
		String appId="8a48b5515147eb6d01516164702a4092";
		//沙盒环境（用于应用开发调试）：sandboxapp.cloopen.com
		//生产环境（用户应用上线使用）：app.cloopen.com
		String serverIP="app.cloopen.com";
		//请求端口，生产环境和沙盒环境一致
		String serverPort="8883";
		
		restAPI.init(serverIP, serverPort);
		restAPI.setAccount(accountSid, accountToken);
		restAPI.setAppId(appId);
		
		restAPI.sendTemplateSMS(mobile, tempID, data);
	}
	
	public static boolean isMobileAsUsername(String username){
		String regEx = "/(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}?/";
		
		Pattern pattern = Pattern.compile(regEx);
		
		Matcher matcher = pattern.matcher(username);
		
		return matcher.matches();
	}
	
	public static void uploadImage(String imagefile, String realpathdir, String filename) throws IOException{
		if (imagefile != null && !(imagefile.equals(""))) {  
			   // 创建文件目录  
			   File savedir = new File(realpathdir);  
			   // 如果目录不存在就创建  
			   if (!savedir.exists()) {  
			    savedir.mkdirs();  
			   }  
			   File imageFile = new File(savedir, filename);  
			   FileOutputStream fops = new FileOutputStream(imageFile);  
			   // 将上传的文件信息保存到相应的文件目录里  
			   fops.write(getImageFileByte(imagefile));
			   
			   fops.close(); 
		}
	}
}
