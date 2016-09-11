package cn.lmc.sciencepark.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lmc.sciencepark.pojo.UserInfo;
import cn.lmc.sciencepark.service.UserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class Test {

	private static Logger log = Logger.getLogger(Test.class);

	@Autowired
	private UserInfoService userinfoService = null;

	@org.junit.Test
	public void testSelect(){
		List<UserInfo> u = userinfoService.getUserInfoByID(63);
		
		log.info(u.get(0));
	}

}
