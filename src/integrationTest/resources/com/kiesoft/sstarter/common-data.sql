-- Roles
INSERT INTO sstarter_role (id, rolename) VALUES(10000, 'ROLE_ADMIN');
INSERT INTO sstarter_role (id, rolename) VALUES(10001, 'ROLE_EDITOR');
INSERT INTO sstarter_role (id, rolename) VALUES(10002, 'ROLE_STAFF');

-- Users
INSERT INTO sstarter_user (id, enabled, password, username) VALUES(10000, true, '21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO sstarter_user (id, enabled, password, username) VALUES(10001, true, '3be8c5739d8f056b124838de345dec56', 'editor');
INSERT INTO sstarter_user (id, enabled, password, username) VALUES(10002, true, 'c25894ebba77ba392a5f9a67354ca257', 'pedrola');
INSERT INTO sstarter_user (id, enabled, password, username) VALUES(10003, true, 'ca0ac6f71f426aa63a4bbfabea06d1a2', 'madara');

-- Roles for Users
INSERT INTO sstarter_user_roles (iduser, idrole) VALUES(10000, 10000);
INSERT INTO sstarter_user_roles (iduser, idrole) VALUES(10001, 10001);

-- Languages
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10000, 'es', 'es', 'Español');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10001, 'gb', 'gb', 'English');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10002, 'it', 'it', 'Italiano');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10003, 'pt', 'pt', 'Português');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10004, 'de', 'de', 'Deutsch');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10005, 'ru', 'ru', 'Pусский');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10006, 'jp', 'jp', '日本の');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10007, 'cn', 'cn', '中国');
INSERT INTO sstarter_language (id, code, flagcode, languagename) VALUES(10008, 'fr', 'fr', 'Français');

