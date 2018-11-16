package org.fbertos.services.users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	public ObjectId id;
	  	
	private String name;

	public User() {
	}
	
	public User(String name) {
		this.id = new ObjectId(new byte[24]);
		this.name = name;
	}
	
	public String getId() { 
		return id.toHexString(); 
	}
	
	public void setId(ObjectId id) { 
		this.id = id; 
	}
	  	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
}
