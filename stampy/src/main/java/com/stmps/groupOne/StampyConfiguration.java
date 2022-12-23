package com.stmps.groupOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stmps.groupOne.models.Role;
import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.RoleService;
import com.stmps.groupOne.services.UserService;
import com.stmps.groupOne.utilities.misc.CreateFile;

@Component
public class StampyConfiguration {
	@Autowired
	RoleService roleServ;
	@Autowired
	UserService userServ;

	private String adminName = "admin";
	@Value("${ADMIN_EMAIL}")
	private String adminEmail;
	@Value("${ADMIN_PW}")
	private String adminPassword;
	@Value("${ADMIN_PATH}")
	private String adminFilePath;
	
	public void init() {
		if(userServ.getAll().size() == 0) {
			CreateFile.text(
					"Administrator Info\n******************" +
					"\nname: " + adminName +
					"\nemail: " + adminEmail +
					"\npw: " + adminPassword,
					adminFilePath,
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
