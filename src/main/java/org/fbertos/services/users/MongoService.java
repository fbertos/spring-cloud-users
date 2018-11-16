package org.fbertos.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
	@Autowired
	private UserRepository repository;

	public User save(User u) {
		return repository.save(u);
    }
}
