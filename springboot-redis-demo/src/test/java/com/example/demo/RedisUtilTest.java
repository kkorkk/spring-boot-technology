package com.example.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

	@Autowired 
	RedisUtil redisUtil;
	
	@Test
	public void testStrSet() {
		redisUtil.setSrt("test_key", "test_value");
	}
	
	@Test
	public void testStrGet() {
		String value = redisUtil.getStr("test_key");
		System.out.println("============key:test_key,value:"+value+"====================");
	}
	
	@Test
	public void testStrDel() {
		redisUtil.delStr("test_key");
	}

	@Test
	public void testObjSet() {
		User user = new User();
		user.setId(1000L);
		user.setUsername("user_test");
		user.setAge(18);
		user.setBirthday(new Date());
		redisUtil.setObj("user1", user);
	}
	
	@Test
	public void testObjGet() {
		User user = (User)redisUtil.getObj("user1");
		System.out.println(user.toString());
	}
	
	@Test
	public void testObjDel() {
		redisUtil.delObj("user1");
	}
}
