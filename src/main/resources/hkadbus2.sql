--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: advertisement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advertisement (
    id uuid NOT NULL,
    name text,
    thumbnail text,
    hash_key text NOT NULL,
    category_id uuid
);


ALTER TABLE public.advertisement OWNER TO postgres;

--
-- Name: bus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bus (
    id uuid NOT NULL,
    short_id integer
    bus_company text,
    fleet_prefix text,
    fleet_number text,
    license_plate_number text,
    bus_model_id uuid
);


ALTER TABLE public.bus OWNER TO postgres;

--
-- Name: bus_brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bus_brand (
    id uuid NOT NULL,
    name text,
    hash_key text NOT NULL
);


ALTER TABLE public.bus_brand OWNER TO postgres;

--
-- Name: bus_model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bus_model (
    id uuid NOT NULL,
    name text,
    thumbnail text,
    hash_key text NOT NULL,
    bus_brand_id uuid NOT NULL
);


ALTER TABLE public.bus_model OWNER TO postgres;

--
-- Name: bus_route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bus_route (
    id uuid NOT NULL,
    route_number text NOT NULL,
    start_location text,
    end_location text,
    bus_companies text,
    hash_key text
);


ALTER TABLE public.bus_route OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id uuid NOT NULL,
    name text,
    thumbnail text,
    hash_key text NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: photo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.photo (
    id uuid NOT NULL,
    short_id integer
    thumbnail text,
    image text,
    advertisement_id uuid,
    bus_id uuid,
    bus_route_id uuid,
    user_id uuid,
    uploaded_date date,
);


ALTER TABLE public.photo OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id uuid NOT NULL,
    username text,
    password_hash text,
    "group" text,
    last_logged_in_date date,
    registration_date date
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Data for Name: advertisement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.advertisement (id, name, thumbnail, hash_key, category_id) FROM stdin;
3cd33d5a-3913-4b0a-b7f1-7df5c5f35ffa	{"zh_hk":"紅圈牌","en_us":"Red Marubean Brand"}	https://lh3.googleusercontent.com/pw/ACtC-3dCytEoEKoKSRWYmoXHhAOElpd-CWYJEQsGDrPLnbQHJb_Wc4IuzwUSVEzP2u59ZJCnnJ_R_gmx6pDHjpM-ex3cADE1s7fdJVHqkpenV2e-tLtsW7dZbIU1MESP4taDAcjrREw5jEqPZDduQkhNsob6=w982-h685-no	red-marubean-brand	e0484e7c-3fb6-47db-8bf7-86e569ed05c6
6085c715-7a2e-402f-bd42-03d56f28c72c	{"zh_hk":"寶礦力水特","en_us":"Pocari Sweat"}	https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no	pocari-sweat	afb1c9e3-fafc-4cc7-a94d-cd6d37fcdaf4
\.


--
-- Data for Name: bus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bus (id, bus_company, fleet_number, license_plate_number, bus_model_id, fleet_prefix, short_id) FROM stdin;
0b8805b9-ae00-4c6f-a525-63d321f988dd	ctb	09	FZ1978	02a2bd9f-330e-429a-bbd3-4cd5df5f33e0	4	63412
\.


--
-- Data for Name: bus_brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bus_brand (id, name, hash_key) FROM stdin;
691e9d51-c52a-416c-a0ee-2205bca8742b	{"zh_hk":"富豪","en_us":"Volvo"}	volvo
\.


--
-- Data for Name: bus_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bus_model (id, name, thumbnail, hash_key, bus_brand_id) FROM stdin;
02a2bd9f-330e-429a-bbd3-4cd5df5f33e0	{"zh_hk":"奧林比安12米空調","en_us":"Olympian 12m Air-Conditioned"}	https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no	volvo-olympian-12m-ac	691e9d51-c52a-416c-a0ee-2205bca8742b
\.


--
-- Data for Name: bus_route; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bus_route (id, route_number, start_location, end_location, bus_companies, hash_key) FROM stdin;
b7128900-12c6-4529-a872-d1450d94c3aa	102	{"zh_hk":"筲箕灣","en_us":"Shau Kei Wan"}	{"zh_hk":"美孚","en_us":"Mei Foo"}	kmb,ctb	cross-harbour-102
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name, thumbnail, hash_key) FROM stdin;
afb1c9e3-fafc-4cc7-a94d-cd6d37fcdaf4	{"zh_hk":"飲品","en_us":"Drink"}	https://lh3.googleusercontent.com/pw/ACtC-3czH0bEoSjFhzCZsmHEacINipCy5Jo3JUxfXpygX2Lf_8dbvrdzobynUmnkoJMEsoN_iSRkgXO_ulllSjW0YP3_DEGUQCqLGcnJ9pQ984IivOI73ajpW_NvxvfrJIfIeG6zQYdunJ-guJNlRc2wZA97=w640-h480-no	drink
e0484e7c-3fb6-47db-8bf7-86e569ed05c6	{"zh_hk":"食品","en_us":"Food"}	https://lh3.googleusercontent.com/pw/ACtC-3dCytEoEKoKSRWYmoXHhAOElpd-CWYJEQsGDrPLnbQHJb_Wc4IuzwUSVEzP2u59ZJCnnJ_R_gmx6pDHjpM-ex3cADE1s7fdJVHqkpenV2e-tLtsW7dZbIU1MESP4taDAcjrREw5jEqPZDduQkhNsob6=w982-h685-no	food
\.


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.photo (id, thumbnail, image, advertisement_id, bus_id, bus_route_id, user_id, uploaded_date, short_id) FROM stdin;
d0e0edd6-7d5b-4d97-9126-7ecd98ab18dd	https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no	https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no	6085c715-7a2e-402f-bd42-03d56f28c72c	0b8805b9-ae00-4c6f-a525-63d321f988dd	b7128900-12c6-4529-a872-d1450d94c3aa	6914e44f-a64f-481a-b57c-e37367b733ae	2020-01-01	5435
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, username, password_hash, "group", last_logged_in_date, registration_date) FROM stdin;
6914e44f-a64f-481a-b57c-e37367b733ae	admin	test_hash	admin	\N	\N
\.


--
-- Name: advertisement advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisement
    ADD CONSTRAINT advertisement_pkey PRIMARY KEY (id);


--
-- Name: bus_brand bus_brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus_brand
    ADD CONSTRAINT bus_brand_pkey PRIMARY KEY (id);


--
-- Name: bus_model bus_model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus_model
    ADD CONSTRAINT bus_model_pkey PRIMARY KEY (id);


--
-- Name: bus bus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus
    ADD CONSTRAINT bus_pkey PRIMARY KEY (id);


--
-- Name: bus_route bus_route_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus_route
    ADD CONSTRAINT bus_route_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: photo photo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (id);


--
-- Name: photo unique_short_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT unique_short_id UNIQUE (short_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: fki_bus_model_bus_brand; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_bus_model_bus_brand ON public.bus_model USING btree (bus_brand_id);


--
-- Name: bus bus_bus_model; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus
    ADD CONSTRAINT bus_bus_model FOREIGN KEY (bus_model_id) REFERENCES public.bus_model(id);


--
-- Name: bus_model bus_model_bus_brand; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus_model
    ADD CONSTRAINT bus_model_bus_brand FOREIGN KEY (bus_brand_id) REFERENCES public.bus_brand(id);


--
-- Name: advertisement category_advertisement; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisement
    ADD CONSTRAINT category_advertisement FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: photo photo_advertisement; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_advertisement FOREIGN KEY (advertisement_id) REFERENCES public.advertisement(id) NOT VALID;


--
-- Name: photo photo_bus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_bus FOREIGN KEY (bus_id) REFERENCES public.bus(id) NOT VALID;


--
-- Name: photo photo_bus_route; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_bus_route FOREIGN KEY (bus_route_id) REFERENCES public.bus_route(id) NOT VALID;


--
-- Name: photo photo_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_user FOREIGN KEY (user_id) REFERENCES public."user"(id) NOT VALID;


--
-- Name: TABLE advertisement; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.advertisement TO hkadbus2;


--
-- Name: TABLE bus; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.bus TO hkadbus2;


--
-- Name: TABLE bus_brand; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.bus_brand TO hkadbus2;


--
-- Name: TABLE bus_model; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.bus_model TO hkadbus2;


--
-- Name: TABLE bus_route; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.bus_route TO hkadbus2;


--
-- Name: TABLE category; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.category TO hkadbus2;


--
-- Name: TABLE photo; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.photo TO hkadbus2;


--
-- Name: TABLE "user"; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public."user" TO hkadbus2;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES  TO hkadbus2;


--
-- PostgreSQL database dump complete
--

