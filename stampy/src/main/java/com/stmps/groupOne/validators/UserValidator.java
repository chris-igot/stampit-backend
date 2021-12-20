package com.stmps.groupOne.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.UserService;

@Component
public class UserValidator implements Validator{
	@Autowired
	UserService usrServ;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Match");
		}
		
		if(usrServ.getByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Exists");
		}
	}
}
