package com.kiesoft.sstarter.exception;

public class BadRequestException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7980827994068183754L;

    private String typeElement;

    public BadRequestException(String typeElement) {
	super(typeElement);
	this.typeElement = typeElement;
    }

    public String getTypeElement() {
	return typeElement;
    }

}
