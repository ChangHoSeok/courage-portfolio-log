package kr.pe.courage.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.courage.exception.DataNotFoundException;
import kr.pe.courage.setup.domain.SetupVO;
import kr.pe.courage.setup.service.SetupService;

@Controller("mainController")
public class MainController {
	@Autowired
	private SetupService setupService;
	
	@RequestMapping("/intro.html")
	public String retrieveMain(Model model) throws Exception {
		SetupVO setupVO = null;
		
		try {
			setupVO = setupService.retrieveSetup();
			
			if (setupVO != null && setupVO.getAboutMe() != null) {
				setupVO.setAboutMe(setupVO.getAboutMe().replaceAll(" ", "&nbsp;").replaceAll("\r\n", "<br/>"));
			}
		} catch (DataNotFoundException e) {
			setupVO = new SetupVO();
		}
		
		model.addAttribute("introInfo", setupVO);
		return "freelancer/intro";
	}
}
