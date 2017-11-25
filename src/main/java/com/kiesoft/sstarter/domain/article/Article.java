package com.kiesoft.sstarter.domain.article;

public interface Article<U> {

    /**
     * Get the body of the Article
     */
    String getBody();

    /**
     * Set the body of the Article
     */
    void setBody(String body);

    /**
     * Get the owner of the Article
     */
    U getOwner();

    /**
     * Set the owner of the Article
     */
    void setOwner(U user);

}
