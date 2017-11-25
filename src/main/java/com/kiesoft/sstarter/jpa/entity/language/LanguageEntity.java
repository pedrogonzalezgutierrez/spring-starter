package com.kiesoft.sstarter.jpa.entity.language;

import com.kiesoft.sstarter.domain.language.Language;
import com.kiesoft.sstarter.jpa.entity.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sstarter_language", uniqueConstraints = {@UniqueConstraint(columnNames = {"languageName", "code"})})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LanguageEntity extends AbstractEntity implements Language {

    private String languageName;
    private String code;
    private String flagCode;

    @Override
    public String getLanguageName() {
        return languageName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getFlagCode() {
        return flagCode;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFlagCode(String flagCode) {
        this.flagCode = flagCode;
    }

}
