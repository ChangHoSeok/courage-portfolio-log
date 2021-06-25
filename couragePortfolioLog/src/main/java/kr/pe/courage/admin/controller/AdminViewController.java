package kr.pe.courage.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
	
	@RequestMapping("/dashboard")
	public String viewDashboard() throws Exception {
		return "admin/dashboard";
	}
}
