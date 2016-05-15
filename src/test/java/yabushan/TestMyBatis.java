package yabushan;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yabushan.test.pojo.UserInfo;
import com.yabushan.test.service.IUserService;
import com.yabushan.test.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {
	private static Logger logger=Logger.getLogger(TestMyBatis.class);
	
	@Resource
	private IUserService userService=null;
	//private ApplicationContext ac=null;
	//@Before
	//public void Before(){
	//	ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		//userService = (IUserService) ac.getBean("userService");  
	//}
	
	@Test
	public void test1(){
		UserInfo userInfo=userService.getUserById(1);
		logger.info(JSON.toJSONString(userInfo));
	}
}
