package com.kiesoft.sstarter.controller;

import com.kiesoft.sstarter.jpa.repository.LanguageRepository;
import com.kiesoft.sstarter.jpa.repository.RoleRepository;
import com.kiesoft.sstarter.jpa.repository.UserRepository;
import com.kiesoft.sstarter.service.language.LanguageService;
import com.kiesoft.sstarter.service.role.RoleService;
import com.kiesoft.sstarter.service.user.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(value = "integrationTestH2")
@SqlGroup({
        @Sql(
                scripts = "/com/kiesoft/sstarter/common-data.sql",
                config = @SqlConfig(transactionMode = ISOLATED),
                executionPhase = BEFORE_TEST_METHOD
        ),

        @Sql(
                scripts = "/com/kiesoft/sstarter/clean-db.sql",
                config = @SqlConfig(transactionMode = ISOLATED),
                executionPhase = AFTER_TEST_METHOD
        )
})
public abstract class TemplateIntegrationTest {

    protected final static String HOST = "*://*";

    protected final static Long ID_ROLE_ADMIN = 10000L;
    protected final static Long ID_ROLE_EDITOR = 10001L;
    protected final static Long ID_ROLE_STAFF = 10002L;

    protected final static Long ID_USER_ADMIN = 10000L;
    protected final static Long ID_USER_EDITOR = 10001L;
    protected final static Long ID_USER_PEDROLA = 10002L;
    protected final static Long ID_USER_MADARA = 10003L;

    protected final static Long ID_LANGUAGE_SPANISH = 10000L;
    protected final static Long ID_LANGUAGE_ENGLISH = 10001L;
    protected final static Long ID_LANGUAGE_ITALIANO = 10002L;
    protected final static Long ID_LANGUAGE_PORTUGUESE = 10003L;
    protected final static Long ID_LANGUAGE_GERMAN = 10004L;
    protected final static Long ID_LANGUAGE_RUSSIAN = 10005L;
    protected final static Long ID_LANGUAGE_JAPANESE = 10006L;
    protected final static Long ID_LANGUAGE_CHINESE = 10007L;
    protected final static Long ID_LANGUAGE_FRENCH = 10008L;

    protected MockHttpSession mockHttpSession;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected LanguageService languageService;

    @Autowired
    protected LanguageRepository languageRepository;

}
