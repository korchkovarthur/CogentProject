package com.furniturecloud.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.furniturecloud.datalayer.User;
import com.furniturecloud.datalayer.UserRepo;
import com.furniturecloud.datalayer.UserRepository;
import com.furniturecloud.security.utils.LoginResponseDTO;
import com.furniturecloud.security_models.AppUserRepo;
import com.furniturecloud.security_models.ApplicationUser;
import com.furniturecloud.security_models.Role;
import com.furniturecloud.security_models.RoleRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
	@Autowired
	private AppUserRepo auRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserRepo userRepo1;
	@Autowired
	private UserRepository userRepo2;
	
	public LoginResponseDTO registerUser(User u, String password) {
		System.out.println(u.getUser_id());
		User user=new User(u.getFirstName(), u.getLastName(), u.getEmail(), u.getAddress());//Get ID
		String encodedPass= "{bcrypt}"+encoder.encode(password);
		Role userRole=roleRepo.findByAuthority("User").get();
		Set<Role> role = new HashSet<Role>();
		role.add(userRole);
		if(userRepo1.findByEmail(u.getEmail()).isPresent()) {
			return new LoginResponseDTO(u, "Failed: Invalid Email");
		}
		userRepo2.create(user);
		auRepo.save(new ApplicationUser(user.getEmail(), encodedPass, role,user));

		return new LoginResponseDTO(null, "Success");
	}
	public LoginResponseDTO loginUser(String email, String password) {
		try {
			System.out.println(email+" "+password);
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			String token = tokenService.generateJwt(auth);
			return new LoginResponseDTO(userRepo1.findByEmail(email).get(),token);
		}catch (AuthenticationException e) {
			// TODO: handle exception
			return new LoginResponseDTO(null, "");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			 return new LoginResponseDTO(null, "");
		}
	}
}
