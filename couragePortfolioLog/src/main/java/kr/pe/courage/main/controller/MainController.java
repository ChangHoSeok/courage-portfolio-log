package kr.pe.courage.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mainController")
public class MainController {
	
	@RequestMapping("/")
	public String retrieveMain() throws Exception {
		return "freelancer/index";
	}
}
