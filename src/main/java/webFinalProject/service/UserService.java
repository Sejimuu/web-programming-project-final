package webFinalProject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import webFinalProject.dto.UserRegistrationDto;
import webFinalProject.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
