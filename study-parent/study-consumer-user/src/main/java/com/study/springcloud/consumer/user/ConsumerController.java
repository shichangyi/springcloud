package com.study.springcloud.consumer.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger log = Logger.getLogger(this.getClass()); 
	@ApiOperation(value="获取用户信息", notes="根据ID获取用户信息")
	@GetMapping("/user/getInfo")
	public Object getInfo(Integer id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String data = restTemplate.getForObject("http://study-provider-user/user/getInfo?id="+id, String.class);
		resMap.put("data", data);
		resMap.put("code", 0);
		log.info("消费者日志 ....., resMap = " + resMap);
		return resMap;
	}
}
