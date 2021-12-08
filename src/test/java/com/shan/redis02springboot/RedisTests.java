package com.shan.redis02springboot;

import com.shan.redis02springboot.pojo.User;
import com.shan.redis02springboot.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTests {

	//注入自己的redisTemplate
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public	void test() {
		//获取连接信息
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		redisUtil.set("name","张三");
		System.out.println(redisUtil.get("name"));
	}
	@Test
	public	void test2() {
		//获取连接信息
		redisTemplate.opsForValue().set("name","张三");
		System.out.println(redisTemplate.opsForValue().get("name"));
		User user = User.builder().id("1").name("哈哈").build();
		//使用自定义redisTemplate 序列化后就可不序列话
		//String userStr = JSONObject.toJSONString(user);
		//redis 存对象必须序列化  或者转String
		redisTemplate.opsForValue().set("user",user);
		System.out.println(redisTemplate.opsForValue().get("user"));
	}

}
