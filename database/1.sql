--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.4
-- Dumped by pg_dump version 9.6.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: pedrola
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO pedrola;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sstarter_language; Type: TABLE; Schema: public; Owner: pedrola
--

CREATE TABLE sstarter_language (
    id bigint NOT NULL,
    code character varying(255),
    flagcode character varying(255),
    languagename character varying(255)
);


ALTER TABLE sstarter_language OWNER TO pedrola;

--
-- Name: sstarter_role; Type: TABLE; Schema: public; Owner: pedrola
--

CREATE TABLE sstarter_role (
    id bigint NOT NULL,
    rolename character varying(255)
);


ALTER TABLE sstarter_role OWNER TO pedrola;

--
-- Name: sstarter_user; Type: TABLE; Schema: public; Owner: pedrola
--

CREATE TABLE sstarter_user (
    id bigint NOT NULL,
    enabled boolean,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE sstarter_user OWNER TO pedrola;

--
-- Name: sstarter_user_roles; Type: TABLE; Schema: public; Owner: pedrola
--

CREATE TABLE sstarter_user_roles (
    iduser bigint NOT NULL,
    idrole bigint NOT NULL
);


ALTER TABLE sstarter_user_roles OWNER TO pedrola;

--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: pedrola
--

SELECT pg_catalog.setval('hibernate_sequence', 10, true);


--
-- Data for Name: sstarter_language; Type: TABLE DATA; Schema: public; Owner: pedrola
--

COPY sstarter_language (id, code, flagcode, languagename) FROM stdin;
2	gb	gb	English
3	it	it	Italiano
1	es	es	Español
4	pt	pt	Português
5	de	de	Deutsch
6	ru	ru	Pусский
7	jp	jp	日本の
8	cn	cn	中国
9	fr	fr	Français
\.


--
-- Data for Name: sstarter_role; Type: TABLE DATA; Schema: public; Owner: pedrola
--

COPY sstarter_role (id, rolename) FROM stdin;
1	ROLE_ADMIN
2	ROLE_EDITOR
\.


--
-- Data for Name: sstarter_user; Type: TABLE DATA; Schema: public; Owner: pedrola
--

COPY sstarter_user (id, enabled, password, username) FROM stdin;
1	t	21232f297a57a5a743894a0e4a801fc3	admin
2	t	5aee9dbd2a188839105073571bee1b1f	editor
3	t	c25894ebba77ba392a5f9a67354ca257	pedrola
\.


--
-- Data for Name: sstarter_user_roles; Type: TABLE DATA; Schema: public; Owner: pedrola
--

COPY sstarter_user_roles (iduser, idrole) FROM stdin;
1	1
2	2
\.


--
-- Name: sstarter_language sstarter_language_pkey; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_language
    ADD CONSTRAINT sstarter_language_pkey PRIMARY KEY (id);


--
-- Name: sstarter_role sstarter_role_pkey; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_role
    ADD CONSTRAINT sstarter_role_pkey PRIMARY KEY (id);


--
-- Name: sstarter_user sstarter_user_pkey; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_user
    ADD CONSTRAINT sstarter_user_pkey PRIMARY KEY (id);


--
-- Name: sstarter_language uk2l11esgp1h3omspdi0mp13uxt; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_language
    ADD CONSTRAINT uk2l11esgp1h3omspdi0mp13uxt UNIQUE (languagename, code);


--
-- Name: sstarter_role uk9gaf2g9d41x7by05jl8i6l89d; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_role
    ADD CONSTRAINT uk9gaf2g9d41x7by05jl8i6l89d UNIQUE (rolename);


--
-- Name: sstarter_user ukdj30opkfl59k0u3v19te4ld; Type: CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_user
    ADD CONSTRAINT ukdj30opkfl59k0u3v19te4ld UNIQUE (username);


--
-- Name: sstarter_user_roles fkhip0ej84ok7j9imodu4ro4wui; Type: FK CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_user_roles
    ADD CONSTRAINT fkhip0ej84ok7j9imodu4ro4wui FOREIGN KEY (idrole) REFERENCES sstarter_role(id);


--
-- Name: sstarter_user_roles fkomob2va257r3i4sogj1lordlg; Type: FK CONSTRAINT; Schema: public; Owner: pedrola
--

ALTER TABLE ONLY sstarter_user_roles
    ADD CONSTRAINT fkomob2va257r3i4sogj1lordlg FOREIGN KEY (iduser) REFERENCES sstarter_user(id);


--
-- PostgreSQL database dump complete
--

