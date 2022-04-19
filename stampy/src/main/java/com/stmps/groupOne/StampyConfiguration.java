package com.stmps.groupOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stmps.groupOne.models.Role;
import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.RoleService;
import com.stmps.groupOne.services.UserService;
import com.stmps.groupOne.utilities.misc.CreateFile;
import com.stmps.groupOne.utilities.misc.CreateRandom;

@Component
public class StampyConfiguration {
	@Autowired
	RoleService roleServ;
	@Autowired
	UserService userServ;
	
	public void init() {
		if(userServ.getAll().size() == 0) {
			String adminName = "admin";
			String adminEmail= "admin@stmpd.com";
//			String adminPassword = CreateRandom.password(3);
			String adminPassword = "asdfasdf";
			CreateFile.text(
					"Administrator Info\n******************" +
					"\nname: " + adminName +
					"\nemail: " + adminEmail +
					"\npw: " + adminPassword,
					"stampit_admin_info"
					);
			
			
			User newAdmin = new User();
			newAdmin.setUsername(adminName);
			newAdmin.setEmail(adminEmail);
			newAdmin.setPassword(adminPassword);
			newAdmin.addRole(new Role("admin_above_all"));
			newAdmin.addRole(new Role("admin"));
			
			userServ.add(newAdmin);
			
			roleServ.addRole("user");
			System.out.println("Admin init");
		}
	}
}
