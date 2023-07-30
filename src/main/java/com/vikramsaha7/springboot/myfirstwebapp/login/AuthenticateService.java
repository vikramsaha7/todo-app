package com.vikramsaha7.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {
	public boolean authenticate(String username,String password) {
		boolean validateUsername=username.equalsIgnoreCase("vikram");
		boolean validatePassword=password.equalsIgnoreCase("saha");
		if(validateUsername && validatePassword)return true;
		return false;
	}
}
