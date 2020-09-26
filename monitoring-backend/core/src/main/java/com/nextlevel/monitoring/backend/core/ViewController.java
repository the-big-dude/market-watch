package com.nextlevel.monitoring.backend.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping({ "/client/**","/dashboard/**" })
	public String index() {
		return "forward:/index.html";
	}
}