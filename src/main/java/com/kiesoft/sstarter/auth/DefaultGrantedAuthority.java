package com.kiesoft.sstarter.auth;

import org.springframework.security.core.GrantedAuthority;

public class DefaultGrantedAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131362792478370582L;
	
	private String authority;
	
	public DefaultGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}

}