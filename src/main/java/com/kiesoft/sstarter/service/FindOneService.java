package com.kiesoft.sstarter.service;

public interface FindOneService<Entity> {

	/**
	 * Find entity by its id
	 * @param id
	 * @return
	 */
	Entity findOne(Long id);
	
}
