package kr.pe.courage.hello.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.courage.hello.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/helloWorld.do")
	public String helloWorld(ModelMap model) throws Exception {
		Date today = new Date();
		model.addAttribute("today", today.toString());
		model.addAttribute("listData", helloService.retrieveHelloList());
		
		return "hello/hello";
	}
}
