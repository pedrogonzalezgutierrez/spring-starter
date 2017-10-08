CREATE TABLE sstarter_language (
    id bigint NOT NULL,
    code character varying(255),
    flagcode character varying(255),
    languagename character varying(255)
);

CREATE TABLE sstarter_role (
    id bigint NOT NULL,
    rolename character varying(255)
);

CREATE TABLE sstarter_user (
    id bigint NOT NULL,
    enabled boolean,
    password character varying(255),
    points integer,
    username character varying(255)
);

CREATE TABLE sstarter_user_roles (
    iduser bigint NOT NULL,
    idrole bigint NOT NULL
);

ALTER TABLE ONLY sstarter_language
    ADD CONSTRAINT sstarter_language_pkey PRIMARY KEY (id);

ALTER TABLE ONLY sstarter_role
    ADD CONSTRAINT sstarter_role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY sstarter_user
    ADD CONSTRAINT sstarter_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY sstarter_language
    ADD CONSTRAINT ukbc98gw1rbxttj27eu4fwyaq9f UNIQUE (languagename, code);

ALTER TABLE ONLY sstarter_role
    ADD CONSTRAINT ukflq09ws11udgv2waoxkst5gcb UNIQUE (rolename);

ALTER TABLE ONLY sstarter_user
    ADD CONSTRAINT ukn5jjx02ycdtq4h83p1dawhimp UNIQUE (username);

ALTER TABLE ONLY sstarter_user_roles
    ADD CONSTRAINT fkeudx34cyrfpuyiikaibnrruqj FOREIGN KEY (idrole) REFERENCES sstarter_role(id);

ALTER TABLE ONLY sstarter_user_roles
    ADD CONSTRAINT fkjeq9wkj2ud0q4vkitn59drob7 FOREIGN KEY (iduser) REFERENCES sstarter_user(id);