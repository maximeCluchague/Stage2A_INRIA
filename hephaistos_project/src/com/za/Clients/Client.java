package com.za.Clients;

public abstract class Client {
	protected String Id;
	
	public Client(String id) {
		super();
		Id = id;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
	
}
