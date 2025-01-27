package com.security.testing.dto;

import com.security.testing.security.Status;

public class LoginResponseDTO {
	
	private Status status;
	private String token;
	private String username;
	
	 public LoginResponseDTO(Status status, String token, String username) {
	        this.status = status;
	        this.token = token;
	        this.username = username;
	    }

	    // Getters and setters
	    public Status getStatus() {
	        return status;
	    }

	    public void setStatus(Status status) {
	        this.status = status;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
	}
	
	
