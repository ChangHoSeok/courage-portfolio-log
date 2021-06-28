package kr.pe.courage.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
	
	@RequestMapping("/dashboard.html")
	public String viewDashboard() throws Exception {
		return "admin/dashboard";
	}
	
	@RequestMapping("/portfolio.html")
	public String viewPortfolio() throws Exception {
		return "admin/portfolio";
	}
	
	@RequestMapping("/contact.html")
	public String viewContact() throws Exception {
		return "admin/contact";
	}
	
	@RequestMapping("/memo.html")
	public String viewMemo() throws Exception {
		return "admin/memo";
	}
	
	@RequestMapping("/setup.html")
	public String viewSetup() throws Exception {
		return "admin/setup";
	}
	
	@RequestMapping("/account.html")
	public String accountSetup() throws Exception {
		return "admin/account";
	}
}
