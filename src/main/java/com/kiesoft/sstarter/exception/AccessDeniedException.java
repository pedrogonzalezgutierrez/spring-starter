package com.kiesoft.sstarter.exception;

public class AccessDeniedException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 2689075086409560459L;

   private String typeElement;

   public AccessDeniedException(String typeElement) {
      super(typeElement);
      this.typeElement = typeElement;
   }

   public String getTypeElement() {
      return typeElement;
   }

}
