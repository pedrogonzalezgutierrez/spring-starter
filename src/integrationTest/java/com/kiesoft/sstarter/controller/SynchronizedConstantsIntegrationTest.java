package com.kiesoft.sstarter.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SynchronizedConstantsIntegrationTest extends TemplateIntegrationTest {

    @Test
    public void give_constansInCode_When_arePopulatedInDatabase_Then_testsAreReady() {
        // Check Roles are populated in Database
        assertThat(roleService.findOne(ID_ROLE_ADMIN), is(notNullValue()));
        assertThat(roleService.findOne(ID_ROLE_EDITOR), is(notNullValue()));
        assertThat(roleService.findOne(ID_ROLE_STAFF), is(notNullValue()));

        // Check Users are populated in Database
        assertThat(userService.findOne(ID_USER_ADMIN), is(notNullValue()));
        assertThat(userService.findOne(ID_USER_EDITOR), is(notNullValue()));
        assertThat(userService.findOne(ID_USER_PEDROLA), is(notNullValue()));
        assertThat(userService.findOne(ID_USER_MADARA), is(notNullValue()));

        // Check Languages are populated in Database
        assertThat(languageService.findOne(ID_LANGUAGE_SPANISH), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_ENGLISH), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_ITALIANO), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_PORTUGUESE), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_GERMAN), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_RUSSIAN), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_JAPANESE), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_CHINESE), is(notNullValue()));
        assertThat(languageService.findOne(ID_LANGUAGE_FRENCH), is(notNullValue()));

    }

}
