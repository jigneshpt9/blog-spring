package com.cisco.blogger.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.blogger.api.DuplicateUserException;
import com.cisco.blogger.api.User;
import com.cisco.blogger.data.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	 @Autowired
	  public void setUserRepository(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	 
	public void registerUser(User user) {
		System.out.println("in registerUser entry");
		
		if (ifUserExists(user.getEmailId())){
			throw new DuplicateUserException("Duplicate user register request received with email id"+ user.getEmailId());
		} else {
		System.out.println("in registerUser exit");
		this.userRepository.insert(user);
		}
	}

	public void updateUser(User user) {
		this.userRepository.save(user);
	}

	public User validateUser(String emailId, String password) {
		System.out.println("in validate user");
		return null;
		
	}

	public boolean ifUserExists(String emailId)
	{
		boolean userExists = false;
		User foundUser = this.userRepository.getUserByEmailId(emailId);
		if(null!=foundUser) {
			userExists = true;
		}
		
		return userExists;
		
	}

}
