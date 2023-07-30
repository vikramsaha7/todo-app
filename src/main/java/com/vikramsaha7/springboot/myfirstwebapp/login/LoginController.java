package com.vikramsaha7.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
@Controller
@SessionAttributes("name")
public class LoginController {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	private AuthenticateService auth;
	
	public LoginController() {
		super();
		this.auth = new AuthenticateService() ;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage() {
		//model.put("name", name);
		//System.out.println("The name is: "+name);
		//logger.info("The name is:"+name);
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String getWelcomePage(@RequestParam String name,@RequestParam String password,ModelMap model) {
		if(auth.authenticate(name, password)) {
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		}
		else {
			model.put("errorMsg","Invalid credentials");
			return "login";
		}
			
	}
}
