package com.kiesoft.sstarter.dto.language;

import com.kiesoft.sstarter.domain.language.Language;
import com.kiesoft.sstarter.dto.AbstractDTO;

public class LanguageDTO extends AbstractDTO implements Language {

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
