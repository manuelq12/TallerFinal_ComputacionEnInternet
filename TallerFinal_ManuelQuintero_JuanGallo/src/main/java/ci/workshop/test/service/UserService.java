package ci.workshop.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.model.User;
import ci.workshop.test.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepos;

	@Override
	public User saveUser(User user) {	
		boolean valid = validUser(user);
		if(valid) return userRepos.save(user);
		return null;
	}

	@Override
	public boolean validUser(User user) {
		// TODO Auto-generated method stub
		if(user.getUsername().equals(null) || user.getUsername().equals("")) return false;
		else if(user.getPassword().equals(null)|| user.getPassword().equals("")) return false;
		else if(user.getType().equals(null)) return false;
		return true;
	}
	
	
	
}
