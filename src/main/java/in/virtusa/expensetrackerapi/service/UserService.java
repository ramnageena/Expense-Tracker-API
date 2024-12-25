package in.virtusa.expensetrackerapi.service;

import in.virtusa.expensetrackerapi.entity.User;
import in.virtusa.expensetrackerapi.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser();
	
	User updateUser(UserModel user);
	
	void deleteUser();
	
	User getLoggedInUser();
}
