package com.za.Clients;

public class Sensor extends Client{
	
	private String name=null;
	private String Description=null;
	private String password;
	
	public Sensor(String id,String name, String Description,String password){
		super(id);
		this.name = name;
		this.Description = Description;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
