package com.kiesoft.sstarter.exception;

public class ElementNotFoundException extends RuntimeException {

    /**
    * 
    */
    private static final long serialVersionUID = 2689075086409560459L;

    private String objectClass;
    private Long id;
    
	public ElementNotFoundException(String objectClass, Long id) {
		super();
		this.objectClass = objectClass;
		this.id = id;
	}
	
	public String getObjectClass() {
		return objectClass;
	}
	
	public Long getId() {
		return id;
	}
    
}
