package ci.workshop.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT t FROM User t")
@Data
public class User {

	@Id
	private String username;
	
	private String password;
	
	private UserType type;
	
}
