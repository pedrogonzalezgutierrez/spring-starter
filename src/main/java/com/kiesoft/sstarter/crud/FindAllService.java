package com.kiesoft.sstarter.crud;

import java.util.List;

public interface FindAllService<Entity> {
	
	List<Entity> findAll();

}
