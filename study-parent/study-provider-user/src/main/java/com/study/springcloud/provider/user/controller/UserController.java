package com.study.springcloud.provider.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.study.springcloud.provider.user.entity.User;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	private Map<Integer, User> userRep;
	private Logger log = Logger.getLogger(this.getClass());
	
	
	public UserController() {
		userRep = new HashMap<Integer, User>();
		for (int i = 1; i < 10000; i++) {
			User user = new User();
			Integer id = i + 1000;
			user.setId(id);
			user.setName("shicy_" + i);
			user.setSex(i % 3);
			userRep.put(id, user);
		}
	}

	@ApiOperation(value="获取用户信息", notes="根据ID获取用户信息")
	@GetMapping("/user/getInfo")
	public Object getInfo(Integer id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		User user = userRep.get(id);
		resMap.put("data", user);
		resMap.put("code", 0);
		
		log.info("生产者日志 ....., resMap = " + resMap);
		return resMap;

	}

}
