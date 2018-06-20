package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author kkorkk
 *
 */
/**
 * @author kkorkk
 *
 */
/**
 * @author kkorkk
 *
 */
@Component
public class RedisUtil {

	@Autowired 
	private StringRedisTemplate strRedisTemplate;
	
	@Autowired 
	private RedisTemplate<Object,Object> redisTemplate;
	
	
	/**
	 * 保存string
	 * @param key
	 * @param value
	 */
	public void setSrt(String key,String value){
		strRedisTemplate.opsForValue().set(key, value);
	}
	
	
	/**
	 * 获取string
	 * @param key
	 * @return
	 */
	public String getStr(String key) {
		return strRedisTemplate.opsForValue().get(key);
	}
	
	
	/**
	 * 根据key删除
	 * @param key
	 */
	public void delStr(String key) {
		strRedisTemplate.delete(key);
	}
	
	
	/**
	 * 保存object
	 * @param key
	 * @param value
	 */
	public void setObj(Object key,Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	
	/**
	 * 根据key获取object
	 * @param key
	 */
	public Object getObj(Object key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	
	/**
	 * 删除object
	 * @param key
	 */
	public void delObj(Object key) {
		redisTemplate.delete(key);
	}
	
}
