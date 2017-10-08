package com.kiesoft.sstarter.crud;

public interface FindOneService<Entity> {

	/**
	 * Find entity by its id
	 * @param id
	 * @return
	 */
	Entity findOne(Long id);
	
}
