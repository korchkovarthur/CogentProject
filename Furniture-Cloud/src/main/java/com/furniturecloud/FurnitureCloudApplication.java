package com.furniturecloud;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.furniturecloud.datalayer.User;
import com.furniturecloud.datalayer.UserRepository;
import com.furniturecloud.security_models.AppUserRepo;
import com.furniturecloud.security_models.ApplicationUser;
import com.furniturecloud.security_models.Role;
import com.furniturecloud.security_models.RoleRepo;

@SpringBootApplication
public class FurnitureCloudApplication {

	public static void main(String[] args) {

		SpringApplication.run(FurnitureCloudApplication.class, args);//Arthur's
	}
	@Bean
	CommandLineRunner run(RoleRepo r, AppUserRepo u,UserRepository ur,PasswordEncoder p) {
		
		return args->{
			if(r.findByAuthority("ADMIN").isPresent())return;
			Role adminRole= r.save(new Role("ADMIN"));
			User user = new User();
			user.setEmail("admin123@fc.com");
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setAddress("Cloud-9");
			Set<Role> roles= new HashSet<Role>();
			roles.add(adminRole);
			roles.add(r.save(new Role("USER")));
			ApplicationUser admin = new ApplicationUser(1, user.getEmail(),"{bcrypt}"+p.encode("1234"), roles,user);
			ur.create(user);
			u.save(admin);
		};
		
	}
}
