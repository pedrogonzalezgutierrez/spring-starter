package com.kiesoft.sstarter.service.language;

import com.kiesoft.sstarter.dto.language.LanguageDTO;
import com.kiesoft.sstarter.service.*;

public interface LanguageService extends
        FindOneService<LanguageDTO>,
        FindAllPaginationService<LanguageDTO>,
        FindAllService<LanguageDTO>,
        SaveEntityService<LanguageDTO>,
        DeleteEntityService<LanguageDTO> {

    /**
     * Search a Language given its code
     *
     * @param code
     * @return
     */
    LanguageDTO findByCode(String code);

}
