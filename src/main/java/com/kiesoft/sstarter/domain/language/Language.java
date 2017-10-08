package com.kiesoft.sstarter.domain.language;

import com.kiesoft.sstarter.domain.BaseEntity;

public interface Language extends BaseEntity {

	String getLanguageName();
	String getCode();
	String getFlagCode();
    
}
