package org.fbertos.services.users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	public ObjectId _id;
	  	
	private String name;

	public User() {
	}
	
	public User(String name) {
		this._id = new ObjectId(new byte[24]);
		this.name = name;
	}
	
	public String get_id() { 
		return _id.toHexString(); 
	}
	
	public void set_id(ObjectId _id) { 
		this._id = _id; 
	}
	  	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
}
