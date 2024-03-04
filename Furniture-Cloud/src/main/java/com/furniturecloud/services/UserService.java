package com.furniturecloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.furniturecloud.security_models.AppUserRepo;
@Service
public class UserService implements UserDetailsService{
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AppUserRepo auRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("in Userdetails service");
		return auRepo.findByUsername(username)
				.orElseThrow( ()->new UsernameNotFoundException("User doesnt exist"));
		
	
	}
	

}
