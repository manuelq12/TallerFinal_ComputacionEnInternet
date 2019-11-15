package ci.workshop.test.service;

import ci.workshop.test.model.User;

public interface IUserService {

	
	public User saveUser (User user);
	public boolean validUser(User user);
}
