--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)

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
    bus_company text,
    fleet_number text,
    license_plate_number text,
    bus_model_id uuid,
    fleet_prefix text,
    short_id integer
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
    thumbnail text,
    image text,
    advertisement_id uuid,
    bus_id uuid,
    bus_route_id uuid,
    user_id uuid,
    uploaded_date date,
    short_id integer
);


ALTER TABLE public.photo OWNER TO postgres;

--
-- Name: search_record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.search_record (
    photo_short_id integer NOT NULL,
    advertisement_hash_key text,
    advertisement_name text,
    category_hash_key text,
    category_name text,
    bus_company text,
    bus_model_hash_key text,
    bus_model_name text,
    route_hash_key text,
    route_number text,
    license_plate_number text,
    fleet_prefix text,
    fleet_number text,
    thumbnail text,
    username text,
    uploaded_date bigint,
    language text NOT NULL,
    tags text
);


ALTER TABLE public.search_record OWNER TO postgres;

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

INSERT INTO public.advertisement VALUES ('6085c715-7a2e-402f-bd42-03d56f28c72c', '{"zh_hk":"寶礦力水特","en_us":"Pocari Sweat"}', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', 'pocari-sweat', 'afb1c9e3-fafc-4cc7-a94d-cd6d37fcdaf4');
INSERT INTO public.advertisement VALUES ('effaa352-e73b-457f-88a1-d2a717e68912', '{"en_us":"AXA Insurance","zh_hk":"國衛保險"}', 'https://lh3.googleusercontent.com/ne8YokHGjc5zqOxcbh5uIDdb5ZTVWwfXKOi93ykRU0VUZkdPEmq7la9Cjj1IcAsXJlL-uihNnKNpWHdf8w=w965-h657-rw-no', 'axa-insurance', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('638fb02f-e5b7-4181-9af7-cf16e7b5e3ec', '{"en_us":"Red Marubean Brand","zh_hk":"紅圈牌"}', 'https://lh3.googleusercontent.com/XUkiQTB5nvqyqvjeNT7hGLVzJjGANs7-kvnKuZExWmRBOusC3lSTxr3Vp2hiqFjNNqhFZAyI2FDlircb5g=w982-h685-rw-no', 'red-marubean-brand', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('a90585d1-31ed-4cd9-83cd-cd253510f0c6', '{"en_us":"Nestle Ice Cream","zh_hk":"雀巢雪糕"}', 'https://lh3.googleusercontent.com/idBnd1EpQ0Fs3Berqbjs8slNuudLeWhh5uUf4M1coaKuBXGHaF_5aIMPD8woX0cZUpM-88QKAkY-beeEMw=w982-h679-rw-no', 'nestle-ice-cream', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('16a2dd48-d3a9-4eb6-a192-91cf66d69372', '{"en_us":"Lion Toothpaste","zh_hk":"獅王牙膏"}', 'https://lh3.googleusercontent.com/vXhoqbSYlvyqUcvpy5Ts5W7s6XM_E4oyMgoHBfxu4zqZpMkGTT9WwWDWl99nlXHBuMS4DqyuSxvPsTUjDA=w987-h679-rw-no', 'lion-toothpaste', '2f1ac914-90a0-4b9d-b3ae-51edb3e7c4ee');
INSERT INTO public.advertisement VALUES ('83c5ab57-2b04-44fe-820b-6eb9b62e519c', '{"en_us":"Flower Paint","zh_hk":"菊花牌油漆"}', 'https://lh3.googleusercontent.com/PgDgVMCO8NIX644K3mBRtxVOodQvhJZ-JzDPDvhJFNnLz7V_9mO7yUay4YUXvRtSgxg6U2NrSMLrVfuzHg=w982-h657-rw-no', 'flower-paint', 'dce20c24-ae94-4db2-83aa-ff3b284e7955');
INSERT INTO public.advertisement VALUES ('31a30d10-22b3-462b-bd43-bd6eb7b5147c', '{"en_us":"Hutchison IDD","zh_hk":"和記 IDD"}', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', 'hutchison-idd', '939e5688-6f78-48fd-b5d5-acb818c1b9b6');
INSERT INTO public.advertisement VALUES ('4dc960bc-7f9a-4302-8879-7843051664b9', '{"en_us":"Visa","zh_hk":"Visa 信用卡"}', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', 'visa-credit-card', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('7444208c-deff-4a85-b091-aa3147383a67', '{"en_us":"Shell Motor Oil","zh_hk":"蜆殼機油"}', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', 'shell-motor-oil', 'd9f7ced2-22c6-406d-8268-a594267fe6fd');
INSERT INTO public.advertisement VALUES ('1e0e60e0-315f-4ac1-b65e-262d3038918e', '{"en_us":"Calbee","zh_hk":"卡樂B"}', 'https://lh3.googleusercontent.com/u15v7img11Z6BRppN6tIBjeWjwTawBO_k6FZZ4N09MB7lc6R59VjZk2BocMJlmrnCF9zymFQDOaJalj0rw=w982-h681-rw-no', 'calbee', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('71e0b863-17d1-4257-8017-8d49627224e9', '{"en_us":"Pearl River Bridge Brand","zh_hk":"珠江橋牌"}', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', 'pearl-river-bridge', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('21fd7847-1ebd-4de5-9295-321ebc0e6205', '{"en_us":"Canon","zh_hk":"佳能"}', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', 'canon', '60adcf4c-004f-49e7-b7cc-3c13186048bd');
INSERT INTO public.advertisement VALUES ('f1d7c06d-370a-49f4-a1f3-d33e61d7752b', '{"en_us":"Wonderful Arts Wedding Services","zh_hk":"新天地婚紗攝影"}', 'https://lh3.googleusercontent.com/DWxAyyz-hLLfii3raxDmVwkCPxXmHfLQkTxkLakTqPnE-YOyXVYF6yIF-8QLhixEEfbThPLUYMMuJYhEMg=w982-h679-rw-no', 'wonderful-arts-wedding', '60adcf4c-004f-49e7-b7cc-3c13186048bd');
INSERT INTO public.advertisement VALUES ('55728746-ea87-477a-bf65-a64ff93ae7e9', '{"en_us":"China Insurance","zh_hk":"中國保險"}', 'https://lh3.googleusercontent.com/7W4NVOFdeic6uuv-OMThv0RGIb7P6WoDYNwnDIRW9GobQboeB9bM7Z0oPrm6OiObuNeu9t9JO1l2lHhc_Q=w982-h674-rw-no', 'china-insurance', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('fa01e9cb-2e0d-4166-8cc0-282f16cc7144', '{"en_us":"One Tel","zh_hk":"一電通"}', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', 'one-tel', '939e5688-6f78-48fd-b5d5-acb818c1b9b6');
INSERT INTO public.advertisement VALUES ('f5df0f4f-9f27-40cc-9eaa-e283fafce941', '{"en_us":"Titoni","zh_hk":"梅花表"}', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', 'titoni', 'b713e310-d854-4804-8b1d-009c473c7ab5');
INSERT INTO public.advertisement VALUES ('01f4d64d-d843-48d5-94d7-51a2f43e5a81', '{"en_us":"Laser Computer","zh_hk":"鐳射電腦"}', 'https://lh3.googleusercontent.com/rXP3OrZryrT0-clTBrfonsprM__0yTudhJXO4DTl9VycFraG1-e_I5Y4LYa5yh6MTNCa96lw7u7_kFmvXQ=w982-h685-rw-no', 'laser-computer', 'f8379750-fb04-4301-8e01-98342f4f40ed');
INSERT INTO public.advertisement VALUES ('80d8e1b3-dedc-4e7d-a3dc-3f0c4c8b1db5', '{"en_us":"Madame Tussauds","zh_hk":"杜莎夫人蠟像館"}', 'https://lh3.googleusercontent.com/iMsi4VfSOYlIUeu3qogbZKwLP-Uckuos9OvckLffyBE-l8jYd6vPc4GhfEapE07Tz9T3Ml3995bTZGEeGQ=w982-h674-rw-no', 'madame-tussauds', 'a90aacb0-fb62-417a-8b09-9f99383738f5');
INSERT INTO public.advertisement VALUES ('5f8092c0-879d-4b04-9980-0e9ef79299ba', '{"en_us":"Chung Kiu Chinese Products","zh_hk":"中僑國貨"}', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', 'chung-kiu-chinese-products', '7c033553-f81b-467c-9a92-1311dc9f799d');
INSERT INTO public.advertisement VALUES ('66fdb916-c37d-4a0b-b300-94bc3d2c67e7', '{"en_us":"WWPKG","zh_hk":"緃橫遊"}', 'https://lh3.googleusercontent.com/k5bJArW9ZujUc14v7StPd48tGuiJ7i3ci291IShN4QLK5xVm_ad7TMIMa6EjabQdeP8fwQtksdVcW4JTKQ=w987-h685-rw-no', 'wwpkg', '115d650c-8b18-4013-b2df-85ca0f30dab8');
INSERT INTO public.advertisement VALUES ('878e2726-13e4-4409-a9ea-748fd0fe9bb2', '{"en_us":"United Daily News","zh_hk":"香港聯合報"}', 'https://lh3.googleusercontent.com/0GwBn8dsoNPnkHEIE6HpQy4aZdbGwRk2Zr4UtxoBwbd1cRVB7zQhqwPsxSO3VcH1b3YO8JY5Pw0RFnA1Tg=w976-h690-rw-no', 'united-daily-news', '5df28682-6810-4be3-b7ac-cf8a2e191301');
INSERT INTO public.advertisement VALUES ('0d13ca82-a012-47c6-a905-9c501f04bfa8', '{"en_us":"McDonald\u0027s","zh_hk":"麥當勞"}', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', 'mcdonalds', '346e1532-c553-4a51-9b4a-f938319a44b6');
INSERT INTO public.advertisement VALUES ('425f047d-c2d5-4e50-be3b-8ab2cc32c954', '{"en_us":"Body Shop","zh_hk":"Body Shop"}', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', 'body-shop', '4edd8281-c300-4ea1-ab1d-068e088bd72b');
INSERT INTO public.advertisement VALUES ('2f5fa09e-4736-47dc-bf15-9801e60e670c', '{"en_us":"Year of the Rooster 2005","zh_hk":"乙酉雞年2005"}', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', 'year-of-the-rooster-2005', 'fd2ef837-7bff-44de-a803-f06c602a3666');
INSERT INTO public.advertisement VALUES ('d5ac5e15-7409-4f32-9c89-651306b61e85', '{"en_us":"Japan Home","zh_hk":"日本城"}', 'https://lh3.googleusercontent.com/C_2zBX0b-YArauu1RqTvJNY63aVIrdHht-LFzbCzjrPKmfIVXID_Pc6WZvedAVRNnLq952aGX4ZA3NPdtw=w987-h674-rw-no', 'japan-home', '7c033553-f81b-467c-9a92-1311dc9f799d');
INSERT INTO public.advertisement VALUES ('470aa057-0180-40fa-b4d0-36da736defe1', '{"en_us":"UNICEF","zh_hk":"聯合國兒童基金會"}', 'https://lh3.googleusercontent.com/YFYgSVXmoSw_al6BU-RC4dmxNCupDKIzsVYBA0pfB9cpU_dPqxn41568UrX6o20ePBYPmGh3E7QkOzPknw=w982-h696-rw-no', 'unicef', 'b0e58fd6-c11a-45fa-8dc6-dd35c89b4cbc');
INSERT INTO public.advertisement VALUES ('4ebb56f4-0f49-454d-9ffb-d0575f9963ac', '{"en_us":"Thai Airways","zh_hk":"泰國航空"}', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', 'thai-airways', 'bc90de09-09ee-4c5a-a4b0-bcf9f2c71ee1');
INSERT INTO public.advertisement VALUES ('6e002695-e156-4a81-937e-dbe8bdc0a462', '{"en_us":"Ulfenbo","zh_hk":"歐化寶"}', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', 'ulfenbo', 'dce20c24-ae94-4db2-83aa-ff3b284e7955');
INSERT INTO public.advertisement VALUES ('385cac8d-5651-4622-b150-6c9a9229c86b', '{"en_us":"Pocari Sweat","zh_hk":"寶礦力水特"}', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', 'pocari-sweat', '4e35b3a4-195a-43bb-9bba-40f2df86ceda');
INSERT INTO public.advertisement VALUES ('c90b25dd-d35f-4c8e-ad77-88ae66fbd979', '{"en_us":"Green Peace","zh_hk":"綠色和平"}', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', 'green-peace', 'b0e58fd6-c11a-45fa-8dc6-dd35c89b4cbc');
INSERT INTO public.advertisement VALUES ('66f04919-c480-46c6-8ed2-9c0c61202bf0', '{"en_us":"OSIM","zh_hk":"OSIM"}', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', 'osim', '644c0379-7956-4572-8a71-1deb57ad652a');
INSERT INTO public.advertisement VALUES ('f5878e5c-b0b6-4c7d-92f0-165373c386d5', '{"en_us":"Cable TV","zh_hk":"有線電視"}', 'https://lh3.googleusercontent.com/g2ZNTNi0HKAu_EtxbFwW8PkgZQSMQzkWtyPHE2Bvc9siZ0V4FamdCaKkrt4BE8wnkXtV4YtGZcrmC5pLlQ=w982-h668-rw-no', 'cable-tv', '5df28682-6810-4be3-b7ac-cf8a2e191301');
INSERT INTO public.advertisement VALUES ('82318fd1-bde4-4378-909a-e61a3707c722', '{"en_us":"Airland Mattress","zh_hk":"雅蘭床褥"}', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', 'airland-mattress', 'dce20c24-ae94-4db2-83aa-ff3b284e7955');
INSERT INTO public.advertisement VALUES ('601f4d01-5c9f-4a85-8369-2846150693e2', '{"en_us":"Goldstar Cassette","zh_hk":"金星卡式磁帶"}', 'https://lh3.googleusercontent.com/VLW_xTBV9XKqI26uLnjp_dzb0I6Pkc8VWjE9_3ZCOvsBzvzpfZ9Lts20VlMypirrTYzJZKAQdyMYNWJaYQ=w982-h674-rw-no', 'goldstar-cassette', 'f8379750-fb04-4301-8e01-98342f4f40ed');
INSERT INTO public.advertisement VALUES ('27ac3b6c-ecfe-4f12-b674-3a5042624708', '{"en_us":"Po Sum On Medicine","zh_hk":"保心安油"}', 'https://lh3.googleusercontent.com/MbevV9UNf-qC5zP0xhDuiRuB0t0OjqRnQQLYPr_GGf_20hHxQtdq7G4NyFQosITZZmO2_BeKfA0ddll5sw=w987-h685-rw-no', 'po-sum-on', '72c09975-479b-4ab4-bb1e-445230d37b17');
INSERT INTO public.advertisement VALUES ('01dd5199-89b7-4fb9-8583-63f64834b619', '{"en_us":"Yaohan","zh_hk":"八佰伴"}', 'https://lh3.googleusercontent.com/4drY_32MJW4X3WqN6RhObKtD8Xq6RSfO_ZX6lXp07uCyD1upm8E9Q1rN7J6WGWN3C4MekeUwyUuLtM65jQ=w982-h690-rw-no', 'yaohan', '7c033553-f81b-467c-9a92-1311dc9f799d');
INSERT INTO public.advertisement VALUES ('1f4ac689-977b-4dea-85ae-d74e0b38c9f6', '{"en_us":"Demae Ichou","zh_hk":"出前一丁"}', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', 'demae-ichou', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('8fbc247b-b451-4b86-8efc-1bea17d72a9d', '{"en_us":"Red A","zh_hk":"紅A"}', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', 'red-a', '2f1ac914-90a0-4b9d-b3ae-51edb3e7c4ee');
INSERT INTO public.advertisement VALUES ('e63bedb9-3967-4adf-bed1-c759496380eb', '{"en_us":"Tourism Malaysia","zh_hk":"馬來西亞旅遊局"}', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', 'tourism-malaysia', '115d650c-8b18-4013-b2df-85ca0f30dab8');
INSERT INTO public.advertisement VALUES ('d2c50a78-49f8-4104-9e51-3b3ca75537bc', '{"en_us":"Paolo Sartori","zh_hk":"Paolo Sartori"}', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', 'paolo-sartori', '0c9bd30e-73e7-4dbd-b5bb-76f7ebbfa3d0');
INSERT INTO public.advertisement VALUES ('09d55536-e301-4217-a321-f147d36f0e03', '{"en_us":"KGI Securities","zh_hk":"凱基證券"}', 'https://lh3.googleusercontent.com/AYvRn3qtvefVRii_oqUfIG3CsM6x0Y7wwPP65nFVoZBWh9ZPWK6wiZpry4_sfshRtTA7HMFNj3h2dM2ceQ=w987-h684-rw-no', 'kgi-securities', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('bc1923e0-0f0a-419d-b329-d82e385cb5bd', '{"en_us":"San Shui Wah Seafood Restaurant","zh_hk":"新瑞華海鮮酒家"}', 'https://lh3.googleusercontent.com/RQk4Gc1kkk54XbgB1W6FMNHvPOVCWHdr3iOYTmJsb-_WwBpht0vvAmudskdQIBBHQBZZmQxxIhb1_BqUMw=w971-h685-rw-no', 'san-shui-wah-seafood-restaurant', '346e1532-c553-4a51-9b4a-f938319a44b6');
INSERT INTO public.advertisement VALUES ('c1fbbf98-1bfc-4915-818a-6a11a42a56df', '{"en_us":"Eagles Deer Essence","zh_hk":"鷹牌鹿精"}', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', 'eagles-deer-essence', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('6f22cf89-4a19-4ae7-81a6-474cea2151e6', '{"en_us":"Meiji Ice Cream Bar","zh_hk":"明治雪條"}', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', 'meiji-ice-cream', 'c2cd1bb1-2935-4967-90d5-d5fc35615ce6');
INSERT INTO public.advertisement VALUES ('9051681a-bcc2-46dd-a7e2-458beea672f8', '{"en_us":"Principal MPF","zh_hk":"信安強積金"}', 'https://lh3.googleusercontent.com/tW3ZBFHxF8_YXBQB_rq1Pkk4f45bPCfaWwpFB2-oZan_Wk5iBQzJ4ifcx5HmlBOhMRlk-zCPbFkebEHsMA=w987-h674-rw-no', 'principal-mpf', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('ba8144b5-565c-4c86-a160-2d4c2904dca2', '{"en_us":"VST Computers","zh_hk":"偉仕電腦"}', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', 'vst-computers', 'f8379750-fb04-4301-8e01-98342f4f40ed');
INSERT INTO public.advertisement VALUES ('c821c1ce-bb6e-46d4-b6ff-1455d49ec689', '{"en_us":"M-Channel","zh_hk":"M 頻道"}', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', 'm-channel', '5df28682-6810-4be3-b7ac-cf8a2e191301');
INSERT INTO public.advertisement VALUES ('c4c32884-9e56-4c82-85ea-2e23c6cf5c72', '{"en_us":"L.J. Hooker","zh_hk":"六正行"}', 'https://lh3.googleusercontent.com/Gn_BImdwwNhPekt4OxRVjNjC8djZUYyleAVLNLVX6sSXQy990-DNqiPRCPthllslY5mOffaUlYG6k8wmcw=w987-h668-rw-no', 'lj-hooker', '57a48e53-4104-4684-8842-1a4fc8ff1537');
INSERT INTO public.advertisement VALUES ('c9d4ef3c-ab54-4400-9f4e-1e581d6a9d3d', '{"en_us":"Macquarie Warrants","zh_hk":"麥格理認股證"}', 'https://lh3.googleusercontent.com/u5Ij72KOJQ_-91l9EHY7sjwGNq8YeudojS2cOSd5xFz2sCYdEfDwewKXW2bmAbUuDPQQxy3BkjhpDbaDnA=w987-h674-rw-no', 'macquarie-warrants', 'baba9cd2-7200-4bce-9b0c-7cbe0b0443b0');
INSERT INTO public.advertisement VALUES ('d498e029-3fc0-485e-8686-6a24d72bc887', '{"en_us":"NEC Air Conditioner","zh_hk":"NEC 冷氣機"}', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', 'nec-ac', '644c0379-7956-4572-8a71-1deb57ad652a');
INSERT INTO public.advertisement VALUES ('40a72634-7d0e-4561-9923-aef0b5c97eb7', '{"en_us":"Fujifilm VHS Tape","zh_hk":"富士錄影帶"}', 'https://lh3.googleusercontent.com/B1oL30QNkKnHzhJyvLU23pSfSUr6vpdySqq-pejXRAsQTq4YrzYnP9qS_QPGD4F5zz0FiOrYjNohvk9KdA=w987-h679-rw-no', 'fuji-vhs-tape', 'f8379750-fb04-4301-8e01-98342f4f40ed');
INSERT INTO public.advertisement VALUES ('b8b946bc-bdb1-4160-b666-dcd955c778a9', '{"en_us":"Ma Pak Leung","zh_hk":"馬百良"}', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', 'ma-pak-leung', '72c09975-479b-4ab4-bb1e-445230d37b17');
INSERT INTO public.advertisement VALUES ('8c91e795-d727-4943-bed3-70016ca36a5d', '{"en_us":"New World First Bus","zh_hk":"上樂新巴"}', 'https://lh3.googleusercontent.com/HGw_e0XkGQobb85BcBnPq06kRhZU46-4GXpnCwgFuVqOVxaK0slj5TWGQdLcJ6Nm1R-oEAMm4LiV7MedOg=w982-h679-rw-no', 'nwfb', 'fd2ef837-7bff-44de-a803-f06c602a3666');
INSERT INTO public.advertisement VALUES ('b247dcb0-16ef-4915-811d-ae9aa173aea6', '{"en_us":"King To Nin Jiom","zh_hk":"京都念慈菴"}', 'https://lh3.googleusercontent.com/yhF6RSccEhypBMj7tZ3styWpRcZkVfzCjZn-o-SreRMPL7jJyQ4hLBWA8U-hC9PcTOMQ2_OZAAPTkVAVwQ=w987-h674-rw-no', 'king-to-nin-jiom', '72c09975-479b-4ab4-bb1e-445230d37b17');
INSERT INTO public.advertisement VALUES ('cf8297b5-52cf-4dbb-8baf-3516f56d3974', '{"en_us":"Cold Fire","zh_hk":"噴即滅"}', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', 'cold-fire', '2f1ac914-90a0-4b9d-b3ae-51edb3e7c4ee');
INSERT INTO public.advertisement VALUES ('2e501eec-c0ea-4278-9373-b575f3ffbe85', '{"en_us":"Siemens","zh_hk":"西門子"}', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', 'siemens', '939e5688-6f78-48fd-b5d5-acb818c1b9b6');
INSERT INTO public.advertisement VALUES ('c053b051-6b7d-4a7c-833f-29b4a84b9303', '{"en_us":"Weisen U","zh_hk":"胃仙U"}', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', 'weisen-u', '72c09975-479b-4ab4-bb1e-445230d37b17');
INSERT INTO public.advertisement VALUES ('77132aca-6819-4c25-989c-450cf3aa9250', '{"en_us":"Columbia International Removals","zh_hk":"安寧國際裝運"}', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', 'columbia-international-removal', 'a90aacb0-fb62-417a-8b09-9f99383738f5');
INSERT INTO public.advertisement VALUES ('e4f97a64-c7bd-45cc-bda8-fe21fede69a9', '{"en_us":"Tung Wah Group of Hospitals","zh_hk":"東華三院"}', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', 'tung-wah-group-of-hospitals', 'b0e58fd6-c11a-45fa-8dc6-dd35c89b4cbc');
INSERT INTO public.advertisement VALUES ('89401e75-3112-415b-a9a9-01cae3b777f6', '{"en_us":"Park Island","zh_hk":"珀麗灣"}', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', 'park-island', '57a48e53-4104-4684-8842-1a4fc8ff1537');


--
-- Data for Name: bus; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bus VALUES ('0b8805b9-ae00-4c6f-a525-63d321f988dd', 'ctb', '09', 'FZ1978', '02a2bd9f-330e-429a-bbd3-4cd5df5f33e0', '4', 63412);
INSERT INTO public.bus VALUES ('d99f94ff-526f-415d-9f3f-f9ec6692d13f', 'kmb', '213', 'DT5392', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('9372b0e1-8416-4524-99e1-42e48b926fce', 'ctb', '54', 'FR5671', '8a1898f0-7e24-4abf-8e5f-3d8e9331728a', '3', NULL);
INSERT INTO public.bus VALUES ('6950bad6-24ad-417e-bf5f-e24f0228714f', 'ctb', '50', 'GP8266', '2fe96d00-d831-484c-83fb-1b9f95295217', '8', NULL);
INSERT INTO public.bus VALUES ('87f8f6ef-942a-4675-8e27-4002f84fa8bb', 'kmb', '1', 'JX6659', '113af6bf-324b-4720-b376-461617dece6d', 'ASV', NULL);
INSERT INTO public.bus VALUES ('260efcdd-6a6a-4d60-9368-e3a5d3857f19', 'kmb', '406', 'EU6010', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('ba275da7-a21a-4bc6-a37a-134721909708', 'ctb', '43', 'FR3093', '8a1898f0-7e24-4abf-8e5f-3d8e9331728a', '3', NULL);
INSERT INTO public.bus VALUES ('181782f4-c442-4c59-950e-914f8c815ad2', 'kmb', '216', 'DT5992', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('4fff2895-0ab3-45bc-a0b2-d2e1b004d7fb', 'kmb', '45', 'FB9728', '4fe14297-56c3-452f-8e47-d5dc2636b3c4', 'AD', NULL);
INSERT INTO public.bus VALUES ('28a4de47-7775-4097-880b-b842dea03313', 'kmb', '338', 'FZ7935', 'ebc1c3d8-fae6-4372-afe4-976ca6e1a0cc', 'S3N', NULL);
INSERT INTO public.bus VALUES ('64f608e1-89c2-4dc7-8eb3-273534b98a0f', 'kmb', '114', 'GW5789', '8a0d3954-e808-4318-bfa5-175cb7b5ef33', 'AV', NULL);
INSERT INTO public.bus VALUES ('a626abde-6a87-489f-9603-c7e92c9ccab5', 'kmb', '225', 'JP5185', '50e96d15-666c-4d73-b7a0-1fd78da86c7a', 'ATR', NULL);
INSERT INTO public.bus VALUES ('2d6964ea-caf4-43ed-9fab-96dc4f581e5e', 'kmb', '315', 'EA4350', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('763b2464-2c3c-48d7-98f4-d271b17c6d7f', 'ctb', '11', 'HA9347', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '5', NULL);
INSERT INTO public.bus VALUES ('714d902c-7a97-4bef-8779-869626db1e66', 'kmb', '512', 'HU162', '8a0d3954-e808-4318-bfa5-175cb7b5ef33', 'AV', NULL);
INSERT INTO public.bus VALUES ('17c42a94-5c7a-4159-b52f-f7e3cbf59c19', 'nwfb', '01', 'JH6164', '575d9b1a-422e-4b7a-b5f5-b6c7cc0759f9', '33', NULL);
INSERT INTO public.bus VALUES ('c8f7d660-564e-4f68-a5ec-710bebed876a', 'kmb', '196', 'CM1385', 'da9f00d3-215e-43ff-a2f4-fa062d471b66', 'N', NULL);
INSERT INTO public.bus VALUES ('fec581b4-6e39-47f2-9bcc-b598ec52e014', 'kmb', '388', 'KP7430', '9263b4a2-6a74-4db4-9b06-44314da5aa82', '3ASV', NULL);
INSERT INTO public.bus VALUES ('9db54ff5-81e6-42f6-85e5-1b82d0d6f421', 'kmb', '63', 'DE2928', 'f8812fdb-fe54-44e8-8e01-3dae7cc914b5', 'BL', NULL);
INSERT INTO public.bus VALUES ('f67ee873-249e-49ff-aa78-38d36d4caa1b', 'kmb', '357', 'GA5145', 'ebc1c3d8-fae6-4372-afe4-976ca6e1a0cc', 'S3N', NULL);
INSERT INTO public.bus VALUES ('dd9714ba-b24c-4b65-a818-370e564b62a0', 'ctb', '09', 'FZ1978', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '4', NULL);
INSERT INTO public.bus VALUES ('fba92e6e-16c7-4bd7-a97b-374deb3a273b', 'kmb', '166', 'DP8978', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('5d06ea28-b3d6-4d80-a6d9-4098ce6e4560', 'cmb', '32', 'DA397', '2745cacc-150c-4a76-8b53-e05294dd21de', 'ML', NULL);
INSERT INTO public.bus VALUES ('0472bb08-962b-4d8a-a25d-cd984de33c0c', 'kmb', '42', 'GC7236', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '3AV', NULL);
INSERT INTO public.bus VALUES ('ae4afc0a-51c3-40e5-84f7-282e8fe3ec50', 'cmb', '168', 'CZ9485', '00d95ca3-04e7-48ad-b3fd-719e5b8e94f6', 'XF', NULL);
INSERT INTO public.bus VALUES ('481bab4e-54c2-4a68-95f1-68a9f0c61aab', 'kmb', '27', 'HW6015', 'b1e412b7-5be2-4ae4-87f4-f67673d7fa49', 'ATR', NULL);
INSERT INTO public.bus VALUES ('225bc1ce-242c-41f5-9dc1-6a800b6cd64b', 'ctb', '68', 'HE457', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '5', NULL);
INSERT INTO public.bus VALUES ('bad10e8a-ae93-4027-9ed4-5ef0586b90d2', 'nwfb', '68', 'JB9058', 'b1e412b7-5be2-4ae4-87f4-f67673d7fa49', '11', NULL);
INSERT INTO public.bus VALUES ('600e3b9d-0d64-4d27-94a8-6108381d6517', 'nwfb', '14', 'JJ9818', '575d9b1a-422e-4b7a-b5f5-b6c7cc0759f9', '33', NULL);
INSERT INTO public.bus VALUES ('133713cc-c2bd-4b7e-814f-a264c1388b9f', 'nwfb', '89', 'HY1164', 'b1e412b7-5be2-4ae4-87f4-f67673d7fa49', '10', NULL);
INSERT INTO public.bus VALUES ('4fc73a9b-8792-4aac-b3b9-bc4182300090', 'cmb', '207', 'BR3177', 'cf121d9c-a920-4ab5-b6f7-1584e439ca60', 'LF', NULL);
INSERT INTO public.bus VALUES ('b71e446b-b9a5-4e92-a050-1551fc3c0fc3', 'nwfb', '02', 'JC2381', '4d45c8e1-c7d6-4269-a48f-3e05313974cd', '14', NULL);
INSERT INTO public.bus VALUES ('ec468ef9-6fc0-4b09-ab61-b06ea5c4b8bd', 'kmb', '1', 'HJ2127', 'b1e412b7-5be2-4ae4-87f4-f67673d7fa49', 'ATR', NULL);
INSERT INTO public.bus VALUES ('01b95852-539b-46cc-80cc-2e38de3bb5c3', 'kmb', '105', 'CH7454', 'da9f00d3-215e-43ff-a2f4-fa062d471b66', 'N', NULL);
INSERT INTO public.bus VALUES ('0aaf0759-6c9b-4cdb-bb89-621112de1a07', 'ctb', '20', 'GF8124', '12d8ee4f-9c15-433b-867b-193404f9f90e', '7', NULL);
INSERT INTO public.bus VALUES ('876717b1-53f5-418f-8143-9219c58f72a2', 'ctb', '53', 'FS1819', '8a1898f0-7e24-4abf-8e5f-3d8e9331728a', '3', NULL);
INSERT INTO public.bus VALUES ('94de6655-1a8a-44ab-9047-66c7f06b5149', 'cmb', '61', 'DV542', '2745cacc-150c-4a76-8b53-e05294dd21de', 'ML', NULL);
INSERT INTO public.bus VALUES ('2abe2252-865d-42a3-9b1f-776472d60491', 'ctb', '55', 'GR2793', '2fe96d00-d831-484c-83fb-1b9f95295217', '8', NULL);
INSERT INTO public.bus VALUES ('b56cdfef-7da3-46d9-a1c1-99e9e58ed683', 'cmb', '11', 'EN6522', '4fe14297-56c3-452f-8e47-d5dc2636b3c4', 'DA', NULL);
INSERT INTO public.bus VALUES ('55084fae-653e-45f1-8970-c555479ecfee', 'cmb', '49', 'DT9375', '2745cacc-150c-4a76-8b53-e05294dd21de', 'ML', NULL);
INSERT INTO public.bus VALUES ('6ee76594-ae71-43ec-9e50-68ba7ae7504a', 'ctb', '24', 'GL4186', '2fe96d00-d831-484c-83fb-1b9f95295217', '8', NULL);
INSERT INTO public.bus VALUES ('b6cc8a28-e07e-40d9-a9f6-c03091ad7297', 'ctb', '96', 'FX4237', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '3', NULL);
INSERT INTO public.bus VALUES ('7f404ade-1994-47d7-a8fc-ccee204c0fb0', 'kmb', '51', 'FC5745', '4fe14297-56c3-452f-8e47-d5dc2636b3c4', 'AD', NULL);
INSERT INTO public.bus VALUES ('fd1f9c38-c8bb-4ed3-88f0-0b2c725bfbec', 'ctb', '56', 'GS4109', '2fe96d00-d831-484c-83fb-1b9f95295217', '8', NULL);
INSERT INTO public.bus VALUES ('be1ea6de-cc30-4d2f-969f-ba4fc321a210', 'kmb', '22', 'DG2612', 'ea1f000e-2ceb-4e89-ac1f-2088cc42360a', 'ME', NULL);
INSERT INTO public.bus VALUES ('aac9dfb4-fd53-439a-aa4b-525420645619', 'ctb', '58', 'GM236', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '4', NULL);
INSERT INTO public.bus VALUES ('64134eb8-085b-40b4-8f8a-7e9e4775cb71', 'nwfb', '07', 'HY1508', '50e96d15-666c-4d73-b7a0-1fd78da86c7a', '30', NULL);
INSERT INTO public.bus VALUES ('32393bc5-142c-4792-aa20-1417f295721a', 'ctb', '68', 'FR5220', '8a1898f0-7e24-4abf-8e5f-3d8e9331728a', '3', NULL);
INSERT INTO public.bus VALUES ('e4636975-dd53-42bd-8216-6e79bdd41efc', 'kmb', '204', 'HD8712', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '3AV', NULL);
INSERT INTO public.bus VALUES ('571f8a92-5b05-4ffe-b55c-7a140a3c4c7d', 'ctb', '09', 'GD5150', '12d8ee4f-9c15-433b-867b-193404f9f90e', '7', NULL);
INSERT INTO public.bus VALUES ('057fdc5a-5536-4fd7-92af-f826be8f36b2', 'ctb', '26', 'HB2027', '11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '5', NULL);
INSERT INTO public.bus VALUES ('d3864639-b553-4de5-b8b1-36d615461dd1', 'kmb', '365', 'GA5685', 'ebc1c3d8-fae6-4372-afe4-976ca6e1a0cc', 'S3N', NULL);
INSERT INTO public.bus VALUES ('b59bafb9-3df4-47a3-a80d-0ca4da0432e3', 'kmb', '92', 'LZ8705', 'ee3ca2f9-588c-485a-a1e6-6721be459fd2', 'AVW', NULL);
INSERT INTO public.bus VALUES ('59294900-20a3-4462-99fe-49cc5f1a0575', 'nwfb', '09', 'HY2877', 'b1e412b7-5be2-4ae4-87f4-f67673d7fa49', '11', NULL);
INSERT INTO public.bus VALUES ('1593cd68-e34a-4b2e-80e0-90c1eb81779d', 'nwfb', '05', 'HY2357', '50e96d15-666c-4d73-b7a0-1fd78da86c7a', '30', NULL);
INSERT INTO public.bus VALUES ('8e4e07cc-021f-4b65-b3c8-ed8f141912eb', 'cmb', '73', 'HA9736', '4fe14297-56c3-452f-8e47-d5dc2636b3c4', 'DA', NULL);
INSERT INTO public.bus VALUES ('16e6f0d9-681a-43ff-be67-c9c6e04a0c72', 'kmb', '213', 'JC3853', '65ca8ada-698a-4682-8dcb-bff909f19a79', 'ADS', NULL);
INSERT INTO public.bus VALUES ('c050041c-11b0-42ae-ac57-cd1677f28e85', 'cmb', '213', 'BR5378', 'cf121d9c-a920-4ab5-b6f7-1584e439ca60', 'LF', NULL);
INSERT INTO public.bus VALUES ('a444aed5-c54a-4a34-9480-41c3172fa9ce', 'nwfb', '11', 'HY2077', '50e96d15-666c-4d73-b7a0-1fd78da86c7a', '30', NULL);
INSERT INTO public.bus VALUES ('51e32f75-b082-43aa-8c1a-9a3b7495663e', 'kmb', '71', 'DH5849', 'e5fe3188-2b4c-46a4-9f3d-7d49a9c0c74d', '3BL', NULL);
INSERT INTO public.bus VALUES ('702283ba-d0ee-4fb1-ba84-e7c23bacebce', 'kmb', '181', 'DR2006', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('ea1eb5fd-3fb1-4620-8c6b-9b104ca8a755', 'kmb', '199', 'DR6008', 'ee496347-0d81-45eb-96a3-5c1603428aa3', 'S3BL', NULL);
INSERT INTO public.bus VALUES ('9d50baa4-cfd2-47f0-a8e9-83e9382881c0', 'kmb', '326', 'HL9881', '8a0d3954-e808-4318-bfa5-175cb7b5ef33', 'AV', NULL);
INSERT INTO public.bus VALUES ('d158c1a8-8778-4d39-bb29-30bbd1a7ce10', 'ctb', '12', 'GH469', '2fe96d00-d831-484c-83fb-1b9f95295217', '8', NULL);


--
-- Data for Name: bus_brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bus_brand VALUES ('691e9d51-c52a-416c-a0ee-2205bca8742b', '{"zh_hk":"富豪","en_us":"Volvo"}', 'volvo');
INSERT INTO public.bus_brand VALUES ('e2b70b7d-a408-4f05-9213-68a14cc141c9', '{"en_us":"MCW","zh_hk":"都城嘉慕"}', 'mcw');
INSERT INTO public.bus_brand VALUES ('beb9e6d9-38cd-4482-8f3b-15ab6273105d', '{"en_us":"Mercedes Benz","zh_hk":"梅斯特斯平治"}', 'mercedes-benz');
INSERT INTO public.bus_brand VALUES ('ac8b8dcc-e653-49be-a654-ef5218aa3a51', '{"en_us":"Daimler","zh_hk":"丹拿"}', 'daimler');
INSERT INTO public.bus_brand VALUES ('b8f39463-6daa-4750-87b3-6e5664df6153', '{"en_us":"Dennis","zh_hk":"丹尼士"}', 'dennis');
INSERT INTO public.bus_brand VALUES ('25d77fcb-dd51-4470-a601-819390f06fb1', '{"en_us":"Leyland","zh_hk":"利蘭"}', 'leyland');
INSERT INTO public.bus_brand VALUES ('6d7688ad-a7cc-48c1-9402-132be5060e5a', '{"en_us":"Volvo","zh_hk":"富豪"}', 'volvo');


--
-- Data for Name: bus_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bus_model VALUES ('02a2bd9f-330e-429a-bbd3-4cd5df5f33e0', '{"zh_hk":"奧林比安12米空調","en_us":"Olympian 12m Air-Conditioned"}', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', 'volvo-olympian-12m-ac', '691e9d51-c52a-416c-a0ee-2205bca8742b');
INSERT INTO public.bus_model VALUES ('575d9b1a-422e-4b7a-b5f5-b6c7cc0759f9', '{"en_us":"Trident 10.3m (Duple Metsec Body)","zh_hk":"三叉戟十點三米 (都普車身)"}', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', 'dennis-trident-10.3m-duple', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('8a0d3954-e808-4318-bfa5-175cb7b5ef33', '{"en_us":"Olympian 11.3m Air-Conditioned","zh_hk":"奧林比安十一點三米空調"}', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', 'volvo-olympian-11m-ac', '6d7688ad-a7cc-48c1-9402-132be5060e5a');
INSERT INTO public.bus_model VALUES ('f8812fdb-fe54-44e8-8e01-3dae7cc914b5', '{"en_us":"Olympian 9.5m","zh_hk":"奧林比安九點五米"}', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', 'leyland-olympian-9.5m', '25d77fcb-dd51-4470-a601-819390f06fb1');
INSERT INTO public.bus_model VALUES ('12d8ee4f-9c15-433b-867b-193404f9f90e', '{"en_us":"Dragon 10.3m Air-Conditioned","zh_hk":"巨龍十點三米空調"}', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', 'dennis-dragon-10.3m-ac', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('b1e412b7-5be2-4ae4-87f4-f67673d7fa49', '{"en_us":"Trident 12m (Alexendar Body)","zh_hk":"三叉戟十二米 (亞歷山大車身)"}', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', 'dennis-trident-12m-alexander', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('2fe96d00-d831-484c-83fb-1b9f95295217', '{"en_us":"Dragon 12m Air-Conditioned","zh_hk":"巨龍十二米空調"}', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', 'dennis-dragon-12m-ac', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('00d95ca3-04e7-48ad-b3fd-719e5b8e94f6', '{"en_us":"Fleetline DMS","zh_hk":"二手珍寶九點四米"}', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', 'daimler-dms-9.4m', 'ac8b8dcc-e653-49be-a654-ef5218aa3a51');
INSERT INTO public.bus_model VALUES ('65ca8ada-698a-4682-8dcb-bff909f19a79', '{"en_us":"Dragon 9.9m Air-Conditioned","zh_hk":"巨龍九點九米空調"}', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', 'dennis-dragon-9.9m-ac', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('ee3ca2f9-588c-485a-a1e6-6721be459fd2', '{"en_us":"Super Olympian 12m (Wright Body)","zh_hk":"前衛巴士"}', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', 'super-olympian-12m-wright-body', '6d7688ad-a7cc-48c1-9402-132be5060e5a');
INSERT INTO public.bus_model VALUES ('ebc1c3d8-fae6-4372-afe4-976ca6e1a0cc', '{"en_us":"Dragon 11.3m","zh_hk":"巨龍十一點三米"}', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', 'dennis-dragon-11m', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('2745cacc-150c-4a76-8b53-e05294dd21de', '{"en_us":"Metrobus 12m","zh_hk":"都城巴士十二米"}', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', 'metrobus-12m', 'e2b70b7d-a408-4f05-9213-68a14cc141c9');
INSERT INTO public.bus_model VALUES ('ee496347-0d81-45eb-96a3-5c1603428aa3', '{"en_us":"Olympian 11.3m","zh_hk":"奧林比安十一點三米"}', 'https://lh3.googleusercontent.com/gFo8AaHO0SPdiul3N-8CEXXElbcto--V5SzpU2D8_i8RP3zuprqLfs9DjR_uACTMm5CIiw3dU-IfZsj-Yw=w987-h679-rw-no', 'leyland-olympian-11m', '25d77fcb-dd51-4470-a601-819390f06fb1');
INSERT INTO public.bus_model VALUES ('4d45c8e1-c7d6-4269-a48f-3e05313974cd', '{"en_us":"Trident 11.3m","zh_hk":"三叉戟十一點三米"}', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', 'dennis-trident-11m', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('e5fe3188-2b4c-46a4-9f3d-7d49a9c0c74d', '{"en_us":"Olympian 12m","zh_hk":"奧林比安十二米"}', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', 'leyland-olympian-12m', '25d77fcb-dd51-4470-a601-819390f06fb1');
INSERT INTO public.bus_model VALUES ('50e96d15-666c-4d73-b7a0-1fd78da86c7a', '{"en_us":"Trident 12m (Duple Metsec Body)","zh_hk":"三叉戟十二米 (都普車身)"}', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', 'dennis-trident-12m-duple', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('cf121d9c-a920-4ab5-b6f7-1584e439ca60', '{"en_us":"Fleetline (Long Version)","zh_hk":"長陣珍寶 (亞歷山大車身)"}', 'https://lh3.googleusercontent.com/mRXtMv6wjQ0E1rZm0CM1Ky2ToG70oAX5NDn9wloKRtiPsc_k_80P5ePi39PeBdLCLQr9VwZGLmyI855iGA=w987-h679-rw-no', 'leyland-fleetline-long', '25d77fcb-dd51-4470-a601-819390f06fb1');
INSERT INTO public.bus_model VALUES ('da9f00d3-215e-43ff-a2f4-fa062d471b66', '{"en_us":"Jubilant","zh_hk":"祖比倫"}', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', 'dennis-jubilant', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('11fe05c0-9fd9-4c1c-bc87-6b22fdf730f7', '{"en_us":"Olympian 12m Air-Conditioned","zh_hk":"奧林比安十二米空調"}', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', 'volvo-olympian-12m-ac', '6d7688ad-a7cc-48c1-9402-132be5060e5a');
INSERT INTO public.bus_model VALUES ('9263b4a2-6a74-4db4-9b06-44314da5aa82', '{"en_us":"Super Olympian 12m","zh_hk":"超級奧林比安十二米"}', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', 'volvo-super-olympian-12m', '6d7688ad-a7cc-48c1-9402-132be5060e5a');
INSERT INTO public.bus_model VALUES ('ea1f000e-2ceb-4e89-ac1f-2088cc42360a', '{"en_us":"O305","zh_hk":"O305十一米"}', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', 'mercedes-benz-o305', 'beb9e6d9-38cd-4482-8f3b-15ab6273105d');
INSERT INTO public.bus_model VALUES ('4fe14297-56c3-452f-8e47-d5dc2636b3c4', '{"en_us":"Dragon/Condor 11m Air-Conditioned","zh_hk":"巨龍/禿鷹十一米空調"}', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', 'dennis-dragon-11m-ac', 'b8f39463-6daa-4750-87b3-6e5664df6153');
INSERT INTO public.bus_model VALUES ('8a1898f0-7e24-4abf-8e5f-3d8e9331728a', '{"en_us":"Olympian 12m Air-Conditioned","zh_hk":"奧林比安十二米空調"}', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', 'leyland-olympian-12m-ac', '25d77fcb-dd51-4470-a601-819390f06fb1');
INSERT INTO public.bus_model VALUES ('113af6bf-324b-4720-b376-461617dece6d', '{"en_us":"Super Olympian 10.6m","zh_hk":"超級奧林比安十點六米"}', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', 'volvo-super-olympian-10m', '6d7688ad-a7cc-48c1-9402-132be5060e5a');


--
-- Data for Name: bus_route; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bus_route VALUES ('b7128900-12c6-4529-a872-d1450d94c3aa', '102', '{"zh_hk":"筲箕灣","en_us":"Shau Kei Wan"}', '{"zh_hk":"美孚","en_us":"Mei Foo"}', 'kmb,ctb', 'cross-harbour-102');
INSERT INTO public.bus_route VALUES ('9683ec97-49dd-4401-8398-711691c3fb36', '115', '{"en_us":"Kowloon City Ferry","zh_hk":"九龍城碼頭"}', '{"en_us":"Central (Hong Kong Macau Ferry)","zh_hk":"中環 (港澳碼頭)"}', 'kmb,nwfb', 'cross-harbour-115');
INSERT INTO public.bus_route VALUES ('0421961b-47ec-447d-85f2-b1f25c35813a', '23', '{"en_us":"Pokfield Road","zh_hk":"蒲飛路"}', '{"en_us":"North Point Pier","zh_hk":"北角碼頭"}', 'nwfb', 'nwfb-23');
INSERT INTO public.bus_route VALUES ('0947b2c7-a4f0-40a2-8037-146df0bb6532', '114', '{"en_us":"Sham Shui Po Ferry","zh_hk":"深水埗碼頭"}', '{"en_us":"Central (Hong Kong Macau Ferry)","zh_hk":"中環 (港澳碼頭)"}', 'kmb,cmb', 'cross-harbour-114');
INSERT INTO public.bus_route VALUES ('e3ec3724-1214-4ea0-af1f-7bc6e96ee241', '43B', '{"en_us":"Tsuen Wan West Station","zh_hk":"荃灣西站"}', '{"en_us":"Cheung Tsing","zh_hk":"長青"}', 'kmb', 'kmb-43b');
INSERT INTO public.bus_route VALUES ('e41ce86e-9ab7-4a10-bbbc-17db11282f72', '8X', '{"en_us":"Siu Sai Wan (Island Resort)","zh_hk":"小西灣 (藍灣半島)"}', '{"en_us":"Happy Valley (Lower)","zh_hk":"跑馬地 (下)"}', 'ctb', 'ctb-8x');
INSERT INTO public.bus_route VALUES ('9e4b7ae7-5875-4cd5-bb62-3572de6d5205', '113', '{"en_us":"Choi Hung","zh_hk":"彩虹"}', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', 'kmb,nwfb', 'cross-harbour-113');
INSERT INTO public.bus_route VALUES ('f1febac1-b552-429f-afb0-cc3ba0327a3e', '234X', '{"en_us":"Bayview Garden","zh_hk":"灣景花園"}', '{"en_us":"Tsim Sha Tsui East","zh_hk":"尖沙咀東"}', 'kmb', 'kmb-234x');
INSERT INTO public.bus_route VALUES ('3ba62c5b-2621-47da-8e28-37a6215e594b', '112', '{"en_us":"So Uk","zh_hk":"蘇屋"}', '{"en_us":"North Point","zh_hk":"北角"}', 'kmb,nwfb', 'cross-harbour-112');
INSERT INTO public.bus_route VALUES ('3c13a24c-c0af-4ece-a0b9-03886aa12d2d', '85', '{"en_us":"Braemar Hill","zh_hk":"寶馬山"}', '{"en_us":"Siu Sai Wan (Island Resort)","zh_hk":"小西灣 (藍灣半島)"}', 'ctb', 'ctb-85');
INSERT INTO public.bus_route VALUES ('8d4f5d2f-b3c6-424c-b0fc-e153e1a1fd16', '690', '{"en_us":"Hong Shing Garden","zh_hk":"康盛花園"}', '{"en_us":"Central (Exchange Square)","zh_hk":"中環 (交易廣場)"}', 'kmb,ctb', 'cross-harbour-690');
INSERT INTO public.bus_route VALUES ('06805225-51be-461c-b975-332b8999b3fa', '171', '{"en_us":"Lai Chi Kok","zh_hk":"荔枝角"}', '{"en_us":"South Horizon","zh_hk":"海怡半島"}', 'kmb,ctb', 'cross-harbour-171');
INSERT INTO public.bus_route VALUES ('1c944c0e-cfd3-48f1-b634-1edf71181869', '170', '{"en_us":"Sha Tin Station","zh_hk":"沙田站"}', '{"en_us":"Wah Fu","zh_hk":"華富"}', 'kmb,ctb', 'cross-harbour-170');
INSERT INTO public.bus_route VALUES ('3bfde7ba-c874-4799-b238-0d2f7e4683ea', '73A', '{"en_us":"Wah Ming","zh_hk":"華明"}', '{"en_us":"Yu Chui Court","zh_hk":"愉翠苑"}', 'kmb', 'kmb-73a');
INSERT INTO public.bus_route VALUES ('dc8f800f-04b3-488f-8c78-fb9cdbffc85a', '42', '{"en_us":"Wah Fu","zh_hk":"華富"}', '{"en_us":"North Point Pier","zh_hk":"北角碼頭"}', 'nwfb', 'nwfb-42');
INSERT INTO public.bus_route VALUES ('6f3a5d90-1122-4ecd-a65a-4731b7ba5e41', 'Unknown', '{"en_us":"Unknown","zh_hk":"不明"}', '{"en_us":"Unknown","zh_hk":"不明"}', 'kmb,ctb,nwfb,cmb', 'unknown');
INSERT INTO public.bus_route VALUES ('9c8c83cf-b6d1-4809-ac3d-8c785a2bf838', '5', '{"en_us":"Felix Villas","zh_hk":"摩星嶺"}', '{"en_us":"Causeway Bay","zh_hk":"銅鑼灣"}', 'ctb', 'ctb-5');
INSERT INTO public.bus_route VALUES ('6fa2b6d2-e6c8-449a-aee0-748f611d701c', '90', '{"en_us":"Ap Lei Chau Estate","zh_hk":"鴨脷洲邨"}', '{"en_us":"Central (Exchange Square)","zh_hk":"中環 (交易廣場)"}', 'cmb', 'cmb-90');
INSERT INTO public.bus_route VALUES ('d3075c9e-1c3b-4c68-a7bd-29f55a9fba20', '7', '{"en_us":"Shek Pai Wan","zh_hk":"石排灣"}', '{"en_us":"Central Ferry","zh_hk":"中環碼頭"}', 'ctb', 'ctb-7');
INSERT INTO public.bus_route VALUES ('bb273720-a720-4c69-b5a0-cea51b692675', '66M', '{"en_us":"Tai Hing","zh_hk":"大興"}', '{"en_us":"Tsuen Wan Station","zh_hk":"荃灣站"}', 'kmb', 'kmb-66m');
INSERT INTO public.bus_route VALUES ('daaa4eec-6e85-4230-b67c-25fafbee777e', '86K', '{"en_us":"Kam Ying Court","zh_hk":"錦英苑"}', '{"en_us":"Sha Tin Station","zh_hk":"沙田站"}', 'kmb', 'kmb-86k');
INSERT INTO public.bus_route VALUES ('ee98f154-e92a-48ee-abf5-502641ae6677', '70R', '{"en_us":"Fanling KCR Station","zh_hk":"粉嶺火車站"}', '{"en_us":"Wo Hop Shek","zh_hk":"和合石"}', 'kmb', 'kmb-70r');
INSERT INTO public.bus_route VALUES ('883a8d0c-14c2-40a4-8952-e1f153d68617', '6A', '{"en_us":"Star Ferry","zh_hk":"尖沙咀碼頭"}', '{"en_us":"Lai Chi Kok","zh_hk":"荔枝角"}', 'kmb', 'kmb-6a');
INSERT INTO public.bus_route VALUES ('e460c7c3-9d1b-40e2-ba55-5ca51713388d', '30X', '{"en_us":"Whampoa Garden","zh_hk":"黃埔花園"}', '{"en_us":"Allway Gardens","zh_hk":"荃威花園"}', 'kmb', 'kmb-30x');
INSERT INTO public.bus_route VALUES ('d9c25efd-8f23-4f7d-9230-ae8563c5823c', '68X', '{"en_us":"Hung Fuk Estate","zh_hk":"洪福邨"}', '{"en_us":"Mongkok (Park Avenue)","zh_hk":"旺角 (栢景灣)"}', 'kmb', 'kmb-68x');
INSERT INTO public.bus_route VALUES ('6bb9e452-d3cd-42d9-9f76-3f0d06f540de', '601', '{"en_us":"Po Tat","zh_hk":"寶達"}', '{"en_us":"Admiralty (East)","zh_hk":"金鐘 (東)"}', 'kmb,nwfb', 'cross-harbour-601');
INSERT INTO public.bus_route VALUES ('9fb99fe6-3641-461a-a2ba-274ddb2dbbfd', '105', '{"en_us":"Sai Wan","zh_hk":"西環"}', '{"en_us":"Lai Chi Kok","zh_hk":"荔枝角"}', 'kmb,cmb', 'cross-harbour-105');
INSERT INTO public.bus_route VALUES ('8225bac9-ebce-43b4-80e0-f37de1fe34c8', '5X', '{"en_us":"Causeway Bay (Whitfield Road)","zh_hk":"銅鑼灣 (威非路道)"}', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', 'ctb', 'ctb-5x');
INSERT INTO public.bus_route VALUES ('353fc1cb-3e3d-494c-a0ae-6cd410036ed5', '104', '{"en_us":"Pak Tin","zh_hk":"白田"}', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', 'kmb,nwfb', 'cross-harbour-104');
INSERT INTO public.bus_route VALUES ('d87a63a2-d65d-411f-9666-8c5f885c2cf7', '103', '{"en_us":"Pokfield Road","zh_hk":"蒲飛路"}', '{"en_us":"Chuk Yuen Estate","zh_hk":"竹園邨"}', 'kmb,ctb', 'cross-harbour-103');
INSERT INTO public.bus_route VALUES ('9295a67e-0c40-4277-91d1-6255c6727112', '102', '{"en_us":"Mei Foo","zh_hk":"美孚"}', '{"en_us":"Shau Kei Wan","zh_hk":"筲箕灣"}', 'kmb,ctb', 'cross-harbour-102');
INSERT INTO public.bus_route VALUES ('e08c0ccc-e08b-44b8-9d42-c11037997dbb', '15', '{"en_us":"Central Ferry","zh_hk":"中環碼頭"}', '{"en_us":"The Peak","zh_hk":"山頂"}', 'nwfb', 'nwfb-15');
INSERT INTO public.bus_route VALUES ('7e40ec41-d626-40a1-a1c7-e4bb4e1a3fc4', '10', '{"en_us":"North Point Ferry","zh_hk":"北角碼頭"}', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', 'ctb', 'ctb-10');
INSERT INTO public.bus_route VALUES ('74524cb5-935d-47c3-9c2f-92505e2d2af5', '101', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', '{"en_us":"Kwun Tong (Yue Man Square)","zh_hk":"觀塘 (裕民坊)"}', 'kmb,nwfb', 'cross-harbour-101');
INSERT INTO public.bus_route VALUES ('2da085c6-0840-4a94-9187-c9dd15f7c7a7', '592', '{"en_us":"Moreton Terrace","zh_hk":"摩頓台"}', '{"en_us":"South Horizon","zh_hk":"海怡半島"}', 'ctb', 'ctb-592');
INSERT INTO public.bus_route VALUES ('605c9083-ebc1-45b1-aef9-8ab611dd7e7e', '680', '{"en_us":"Admiralty","zh_hk":"金鐘"}', '{"en_us":"Lee On","zh_hk":"利安"}', 'kmb,nwfb', 'cross-harbour-680');
INSERT INTO public.bus_route VALUES ('5957d4cc-5a4a-4d27-93e5-ac05e3908485', '58M', '{"en_us":"Leung King","zh_hk":"良景"}', '{"en_us":"Kwai Fong Station","zh_hk":"葵芳站"}', 'kmb', 'kmb-58m');
INSERT INTO public.bus_route VALUES ('5c499456-bc13-42ed-8a6e-e4b2f44bab3a', '70', '{"en_us":"Wah Kwai","zh_hk":"華貴"}', '{"en_us":"Central (Exchange Square)","zh_hk":"中環 (交易廣場)"}', 'ctb', 'ctb-70');
INSERT INTO public.bus_route VALUES ('adeedb11-5f4c-4e56-86bb-f9f09e290707', '68M', '{"en_us":"Tsuen Wan Station","zh_hk":"荃灣站"}', '{"en_us":"Yuen Long (West)","zh_hk":"元朗 (西)"}', 'kmb', 'kmb-68m');
INSERT INTO public.bus_route VALUES ('4c2e49a4-6632-46e5-b9f9-ce2362831685', '182', '{"en_us":"Yu Chui Court","zh_hk":"愉翠苑"}', '{"en_us":"Central (Hong Kong Macau Ferry)","zh_hk":"中環 (港澳碼頭)"}', 'kmb,ctb', 'cross-harbour-182');
INSERT INTO public.bus_route VALUES ('392e6fc5-de3e-40a9-b21d-08b8d75f9c54', '1', '{"en_us":"Chuk Yuen Estate","zh_hk":"竹園邨"}', '{"en_us":"Star Ferry","zh_hk":"尖沙咀碼頭"}', 'kmb', 'kmb-1');
INSERT INTO public.bus_route VALUES ('c9207dd4-1a1e-495e-a033-71d0044b0c47', '5B', '{"en_us":"Kennedy Town","zh_hk":"堅尼地城"}', '{"en_us":"Hong Kong Stadium","zh_hk":"香港大球場"}', 'ctb', 'ctb-5b');
INSERT INTO public.bus_route VALUES ('495cfb6d-eaa9-4d37-a12e-7fb56d08d8f6', '73A', '{"en_us":"Tsz Wan Shan (Central)","zh_hk":"慈雲山 (中)"}', '{"en_us":"Star Ferry","zh_hk":"尖沙咀碼頭"}', 'kmb', 'kmb-5c');
INSERT INTO public.bus_route VALUES ('b9adbdfc-a25f-4244-bfec-10cdc7d2a954', '9', '{"en_us":"Tsim Sha Tsui East","zh_hk":"尖沙咀東"}', '{"en_us":"Choi Fook","zh_hk":"彩福"}', 'kmb', 'kmb-9');
INSERT INTO public.bus_route VALUES ('a42ad9a9-f986-4214-8570-1dcd10dead53', '63X', '{"en_us":"Hung Fuk Estate","zh_hk":"洪福邨"}', '{"en_us":"West Kowloon Station","zh_hk":"西九龍站"}', 'kmb', 'kmb-63x');
INSERT INTO public.bus_route VALUES ('1d7f401f-fcfc-4a6e-8e60-57197564b916', '1A', '{"en_us":"Sau Mau Ping (Central)","zh_hk":"中秀茂坪"}', '{"en_us":"Star Ferry","zh_hk":"尖沙咀碼頭"}', 'kmb', 'kmb-1a');
INSERT INTO public.bus_route VALUES ('a168e6af-ced7-4a18-9a13-99f5847c56f9', '118', '{"en_us":"Cheung Sha Wan (Sham Mong Road)","zh_hk":"長沙灣 (深旺道)"}', '{"en_us":"Siu Sai Wan (Island Resort)","zh_hk":"小西灣 (藍灣半島)"}', 'kmb,ctb', 'cross-harbour-118');


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category VALUES ('afb1c9e3-fafc-4cc7-a94d-cd6d37fcdaf4', '{"zh_hk":"飲品","en_us":"Drink"}', 'https://lh3.googleusercontent.com/pw/ACtC-3czH0bEoSjFhzCZsmHEacINipCy5Jo3JUxfXpygX2Lf_8dbvrdzobynUmnkoJMEsoN_iSRkgXO_ulllSjW0YP3_DEGUQCqLGcnJ9pQ984IivOI73ajpW_NvxvfrJIfIeG6zQYdunJ-guJNlRc2wZA97=w640-h480-no', 'drink');
INSERT INTO public.category VALUES ('b713e310-d854-4804-8b1d-009c473c7ab5', '{"en_us":"Watches","zh_hk":"鐘錶"}', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', 'watches');
INSERT INTO public.category VALUES ('57a48e53-4104-4684-8842-1a4fc8ff1537', '{"en_us":"Real Estate","zh_hk":"地產"}', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', 'real-estate');
INSERT INTO public.category VALUES ('fd2ef837-7bff-44de-a803-f06c602a3666', '{"en_us":"Bus Company","zh_hk":"巴士公司"}', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', 'bus-company');
INSERT INTO public.category VALUES ('115d650c-8b18-4013-b2df-85ca0f30dab8', '{"en_us":"Travel Agency/Tourism","zh_hk":"旅行社/旅遊"}', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', 'tourism');
INSERT INTO public.category VALUES ('72c09975-479b-4ab4-bb1e-445230d37b17', '{"en_us":"Medicine","zh_hk":"藥物"}', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', 'medicine');
INSERT INTO public.category VALUES ('5df28682-6810-4be3-b7ac-cf8a2e191301', '{"en_us":"Mass Media","zh_hk":"傳媒"}', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', 'mass-media');
INSERT INTO public.category VALUES ('939e5688-6f78-48fd-b5d5-acb818c1b9b6', '{"en_us":"Telecommunication","zh_hk":"通訊"}', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', 'telecommunication');
INSERT INTO public.category VALUES ('c2cd1bb1-2935-4967-90d5-d5fc35615ce6', '{"en_us":"Food","zh_hk":"食品"}', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', 'food');
INSERT INTO public.category VALUES ('4edd8281-c300-4ea1-ab1d-068e088bd72b', '{"en_us":"Cosmetics","zh_hk":"化妝品"}', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', 'cosmetics');
INSERT INTO public.category VALUES ('f8379750-fb04-4301-8e01-98342f4f40ed', '{"en_us":"Computer","zh_hk":"電腦"}', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', 'computer');
INSERT INTO public.category VALUES ('b0e58fd6-c11a-45fa-8dc6-dd35c89b4cbc', '{"en_us":"Government/Public Organizations","zh_hk":"政府/公共機構"}', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', 'government');
INSERT INTO public.category VALUES ('644c0379-7956-4572-8a71-1deb57ad652a', '{"en_us":"Electronic Appliances","zh_hk":"電器"}', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', 'electronic-appliances');
INSERT INTO public.category VALUES ('dce20c24-ae94-4db2-83aa-ff3b284e7955', '{"en_us":"Furniture/Home Improvement","zh_hk":"傢具/建材"}', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', 'furniture');
INSERT INTO public.category VALUES ('4e35b3a4-195a-43bb-9bba-40f2df86ceda', '{"en_us":"Beverage","zh_hk":"飲品"}', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', 'beverage');
INSERT INTO public.category VALUES ('2f1ac914-90a0-4b9d-b3ae-51edb3e7c4ee', '{"en_us":"Household Goods","zh_hk":"家庭用品"}', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', 'household-goods');
INSERT INTO public.category VALUES ('0c9bd30e-73e7-4dbd-b5bb-76f7ebbfa3d0', '{"en_us":"Clothing/Footwear","zh_hk":"衣物/鞋具"}', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', 'clothing');
INSERT INTO public.category VALUES ('bc90de09-09ee-4c5a-a4b0-bcf9f2c71ee1', '{"en_us":"Airline Company","zh_hk":"航空公司"}', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', 'airline');
INSERT INTO public.category VALUES ('d9f7ced2-22c6-406d-8268-a594267fe6fd', '{"en_us":"Petroleum","zh_hk":"石油"}', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', 'petroleum');
INSERT INTO public.category VALUES ('a90aacb0-fb62-417a-8b09-9f99383738f5', '{"en_us":"Others","zh_hk":"其他"}', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', 'others');
INSERT INTO public.category VALUES ('baba9cd2-7200-4bce-9b0c-7cbe0b0443b0', '{"en_us":"Finance/Insurance","zh_hk":"金融/保險"}', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', 'finance');
INSERT INTO public.category VALUES ('60adcf4c-004f-49e7-b7cc-3c13186048bd', '{"en_us":"Photography","zh_hk":"攝影"}', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', 'photography');
INSERT INTO public.category VALUES ('7c033553-f81b-467c-9a92-1311dc9f799d', '{"en_us":"Shopping","zh_hk":"購物"}', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', 'shopping');
INSERT INTO public.category VALUES ('346e1532-c553-4a51-9b4a-f938319a44b6', '{"en_us":"Restaurants","zh_hk":"餐廳"}', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', 'restaurant');


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.photo VALUES ('d0e0edd6-7d5b-4d97-9126-7ecd98ab18dd', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', '6085c715-7a2e-402f-bd42-03d56f28c72c', '0b8805b9-ae00-4c6f-a525-63d321f988dd', 'b7128900-12c6-4529-a872-d1450d94c3aa', '6914e44f-a64f-481a-b57c-e37367b733ae', '2020-01-01', 5435);
INSERT INTO public.photo VALUES ('86e98b80-595e-4a10-8078-f12e56acd0a6', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', '0d13ca82-a012-47c6-a905-9c501f04bfa8', 'a626abde-6a87-489f-9603-c7e92c9ccab5', '6bb9e452-d3cd-42d9-9f76-3f0d06f540de', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 78625);
INSERT INTO public.photo VALUES ('00cf329f-f232-440c-ab33-47d4f79fb34e', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', '21fd7847-1ebd-4de5-9295-321ebc0e6205', 'e4636975-dd53-42bd-8216-6e79bdd41efc', 'b9adbdfc-a25f-4244-bfec-10cdc7d2a954', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 60915);
INSERT INTO public.photo VALUES ('4a6ea0f8-c5ea-424b-ad0a-73f866675811', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', 'd498e029-3fc0-485e-8686-6a24d72bc887', 'aac9dfb4-fd53-439a-aa4b-525420645619', '06805225-51be-461c-b975-332b8999b3fa', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 87833);
INSERT INTO public.photo VALUES ('1672221a-d28e-4ddf-9f66-fd2ccbe912f2', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', '7444208c-deff-4a85-b091-aa3147383a67', '6ee76594-ae71-43ec-9e50-68ba7ae7504a', 'a168e6af-ced7-4a18-9a13-99f5847c56f9', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 65287);
INSERT INTO public.photo VALUES ('c911de21-3648-4f4f-b0d5-c3005f5cec6d', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', '4dc960bc-7f9a-4302-8879-7843051664b9', '057fdc5a-5536-4fd7-92af-f826be8f36b2', '9c8c83cf-b6d1-4809-ac3d-8c785a2bf838', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 7712);
INSERT INTO public.photo VALUES ('acc45754-57fe-47ee-9d86-4bf72232e3ad', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', '89401e75-3112-415b-a9a9-01cae3b777f6', '87f8f6ef-942a-4675-8e27-4002f84fa8bb', 'f1febac1-b552-429f-afb0-cc3ba0327a3e', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 3781);
INSERT INTO public.photo VALUES ('434f4aa0-380f-4b8b-858d-b6832d46e303', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', '31a30d10-22b3-462b-bd43-bd6eb7b5147c', 'ba275da7-a21a-4bc6-a37a-134721909708', '2da085c6-0840-4a94-9187-c9dd15f7c7a7', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 53278);
INSERT INTO public.photo VALUES ('d3ea898d-4d05-464c-80fe-296391657eaf', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', 'c821c1ce-bb6e-46d4-b6ff-1455d49ec689', 'b71e446b-b9a5-4e92-a050-1551fc3c0fc3', '0421961b-47ec-447d-85f2-b1f25c35813a', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 398);
INSERT INTO public.photo VALUES ('1cfcf8eb-73d5-4d8c-9157-0f6d3a36df41', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', 'c053b051-6b7d-4a7c-833f-29b4a84b9303', 'aac9dfb4-fd53-439a-aa4b-525420645619', '9295a67e-0c40-4277-91d1-6255c6727112', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 62793);
INSERT INTO public.photo VALUES ('684f7dfd-e1b7-4060-b443-e9caf536531e', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', 'f5df0f4f-9f27-40cc-9eaa-e283fafce941', '9372b0e1-8416-4524-99e1-42e48b926fce', '5c499456-bc13-42ed-8a6e-e4b2f44bab3a', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 15826);
INSERT INTO public.photo VALUES ('3af5e7b7-95b6-4a3a-98d9-4654fae43e09', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', 'e4f97a64-c7bd-45cc-bda8-fe21fede69a9', '51e32f75-b082-43aa-8c1a-9a3b7495663e', 'daaa4eec-6e85-4230-b67c-25fafbee777e', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 96887);
INSERT INTO public.photo VALUES ('62ab00d4-4b65-430c-9201-59f703c5096b', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', '8fbc247b-b451-4b86-8efc-1bea17d72a9d', 'c8f7d660-564e-4f68-a5ec-710bebed876a', '3bfde7ba-c874-4799-b238-0d2f7e4683ea', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 76671);
INSERT INTO public.photo VALUES ('fa2a54f6-1b39-48ef-802e-882dd26e4252', 'https://lh3.googleusercontent.com/gFo8AaHO0SPdiul3N-8CEXXElbcto--V5SzpU2D8_i8RP3zuprqLfs9DjR_uACTMm5CIiw3dU-IfZsj-Yw=w987-h679-rw-no', 'https://lh3.googleusercontent.com/gFo8AaHO0SPdiul3N-8CEXXElbcto--V5SzpU2D8_i8RP3zuprqLfs9DjR_uACTMm5CIiw3dU-IfZsj-Yw=w987-h679-rw-no', '8fbc247b-b451-4b86-8efc-1bea17d72a9d', '2d6964ea-caf4-43ed-9fab-96dc4f581e5e', '5957d4cc-5a4a-4d27-93e5-ac05e3908485', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 32585);
INSERT INTO public.photo VALUES ('3882760b-6211-4064-864a-efcc2a701d74', 'https://lh3.googleusercontent.com/mRXtMv6wjQ0E1rZm0CM1Ky2ToG70oAX5NDn9wloKRtiPsc_k_80P5ePi39PeBdLCLQr9VwZGLmyI855iGA=w987-h679-rw-no', 'https://lh3.googleusercontent.com/mRXtMv6wjQ0E1rZm0CM1Ky2ToG70oAX5NDn9wloKRtiPsc_k_80P5ePi39PeBdLCLQr9VwZGLmyI855iGA=w987-h679-rw-no', '8fbc247b-b451-4b86-8efc-1bea17d72a9d', '4fc73a9b-8792-4aac-b3b9-bc4182300090', '8d4f5d2f-b3c6-424c-b0fc-e153e1a1fd16', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 70107);
INSERT INTO public.photo VALUES ('4d1ce329-d355-4b2d-af23-9da455aba44a', 'https://lh3.googleusercontent.com/5s1cUalDFCiAF-HpN2nDxaKzDbNAWLZHyRRPyjNQgKoI2WVWJuv4iEdAVvNvDNcSGjeap2C8Q0S5_dcGGA=w982-h685-rw-no', 'https://lh3.googleusercontent.com/5s1cUalDFCiAF-HpN2nDxaKzDbNAWLZHyRRPyjNQgKoI2WVWJuv4iEdAVvNvDNcSGjeap2C8Q0S5_dcGGA=w982-h685-rw-no', '8fbc247b-b451-4b86-8efc-1bea17d72a9d', '01b95852-539b-46cc-80cc-2e38de3bb5c3', '495cfb6d-eaa9-4d37-a12e-7fb56d08d8f6', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 6993);
INSERT INTO public.photo VALUES ('e2c4fc2f-01e3-484a-9230-63a82b610f78', 'https://lh3.googleusercontent.com/HkNpnggv81Xb6VKy7tfviM4UNDb1ZtMvX0Uy2-WhIrnua6XYVrUKGZNFO-Oubn9JIQ2DQfkJzfUB48VFWA=w987-h679-rw-no', 'https://lh3.googleusercontent.com/HkNpnggv81Xb6VKy7tfviM4UNDb1ZtMvX0Uy2-WhIrnua6XYVrUKGZNFO-Oubn9JIQ2DQfkJzfUB48VFWA=w987-h679-rw-no', '8fbc247b-b451-4b86-8efc-1bea17d72a9d', '01b95852-539b-46cc-80cc-2e38de3bb5c3', '495cfb6d-eaa9-4d37-a12e-7fb56d08d8f6', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 70304);
INSERT INTO public.photo VALUES ('a7527409-f4fc-45f2-bcb5-28fca702643e', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', '6e002695-e156-4a81-937e-dbe8bdc0a462', 'b56cdfef-7da3-46d9-a1c1-99e9e58ed683', '74524cb5-935d-47c3-9c2f-92505e2d2af5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 41118);
INSERT INTO public.photo VALUES ('80f6dc60-37cc-4960-a8c4-89f0630b9766', 'https://lh3.googleusercontent.com/vXhoqbSYlvyqUcvpy5Ts5W7s6XM_E4oyMgoHBfxu4zqZpMkGTT9WwWDWl99nlXHBuMS4DqyuSxvPsTUjDA=w987-h679-rw-no', 'https://lh3.googleusercontent.com/vXhoqbSYlvyqUcvpy5Ts5W7s6XM_E4oyMgoHBfxu4zqZpMkGTT9WwWDWl99nlXHBuMS4DqyuSxvPsTUjDA=w987-h679-rw-no', '16a2dd48-d3a9-4eb6-a192-91cf66d69372', '4fff2895-0ab3-45bc-a0b2-d2e1b004d7fb', '9295a67e-0c40-4277-91d1-6255c6727112', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 77887);
INSERT INTO public.photo VALUES ('6d360004-221a-4313-bad7-d5093a11f597', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', 'cf8297b5-52cf-4dbb-8baf-3516f56d3974', '94de6655-1a8a-44ab-9047-66c7f06b5149', '74524cb5-935d-47c3-9c2f-92505e2d2af5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 72937);
INSERT INTO public.photo VALUES ('0453886a-cb41-4947-98e5-120c2752d40d', 'https://lh3.googleusercontent.com/MbevV9UNf-qC5zP0xhDuiRuB0t0OjqRnQQLYPr_GGf_20hHxQtdq7G4NyFQosITZZmO2_BeKfA0ddll5sw=w987-h685-rw-no', 'https://lh3.googleusercontent.com/MbevV9UNf-qC5zP0xhDuiRuB0t0OjqRnQQLYPr_GGf_20hHxQtdq7G4NyFQosITZZmO2_BeKfA0ddll5sw=w987-h685-rw-no', '27ac3b6c-ecfe-4f12-b674-3a5042624708', 'd158c1a8-8778-4d39-bb29-30bbd1a7ce10', '1c944c0e-cfd3-48f1-b634-1edf71181869', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 7371);
INSERT INTO public.photo VALUES ('5e1effa5-b138-45f4-995e-3c31ac72d03c', 'https://lh3.googleusercontent.com/PgDgVMCO8NIX644K3mBRtxVOodQvhJZ-JzDPDvhJFNnLz7V_9mO7yUay4YUXvRtSgxg6U2NrSMLrVfuzHg=w982-h657-rw-no', 'https://lh3.googleusercontent.com/PgDgVMCO8NIX644K3mBRtxVOodQvhJZ-JzDPDvhJFNnLz7V_9mO7yUay4YUXvRtSgxg6U2NrSMLrVfuzHg=w982-h657-rw-no', '83c5ab57-2b04-44fe-820b-6eb9b62e519c', '2abe2252-865d-42a3-9b1f-776472d60491', '4c2e49a4-6632-46e5-b9f9-ce2362831685', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 28862);
INSERT INTO public.photo VALUES ('562001d0-9202-4c75-9359-1a7f6e2e7c67', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', 'fa01e9cb-2e0d-4166-8cc0-282f16cc7144', '16e6f0d9-681a-43ff-be67-c9c6e04a0c72', 'f1febac1-b552-429f-afb0-cc3ba0327a3e', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 92688);
INSERT INTO public.photo VALUES ('1a198931-8873-4a53-945c-76c3cc6f73f2', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', '82318fd1-bde4-4378-909a-e61a3707c722', '714d902c-7a97-4bef-8779-869626db1e66', 'd87a63a2-d65d-411f-9666-8c5f885c2cf7', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 23740);
INSERT INTO public.photo VALUES ('15375d6e-e5ae-4a68-b65e-bf0c6383ef9f', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', '2e501eec-c0ea-4278-9373-b575f3ffbe85', '0aaf0759-6c9b-4cdb-bb89-621112de1a07', '3c13a24c-c0af-4ece-a0b9-03886aa12d2d', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 18855);
INSERT INTO public.photo VALUES ('6ee2a2f6-28a1-4126-af16-1575d954ddbd', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', '77132aca-6819-4c25-989c-450cf3aa9250', 'ae4afc0a-51c3-40e5-84f7-282e8fe3ec50', '6f3a5d90-1122-4ecd-a65a-4731b7ba5e41', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 59379);
INSERT INTO public.photo VALUES ('808fde15-e45a-4807-b609-8612f09f39f2', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', '385cac8d-5651-4622-b150-6c9a9229c86b', 'dd9714ba-b24c-4b65-a818-370e564b62a0', '9295a67e-0c40-4277-91d1-6255c6727112', '6f3a2920-ab89-4cb9-94e3-42720116e2b7', '2021-09-26', 28542);
INSERT INTO public.photo VALUES ('25867a09-fcf7-46ab-94ec-c230c68ea77c', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', '5f8092c0-879d-4b04-9980-0e9ef79299ba', '181782f4-c442-4c59-950e-914f8c815ad2', '392e6fc5-de3e-40a9-b21d-08b8d75f9c54', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 82659);
INSERT INTO public.photo VALUES ('47c0017c-0279-49fe-8a33-4b4cb3f727b4', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', '1f4ac689-977b-4dea-85ae-d74e0b38c9f6', '763b2464-2c3c-48d7-98f4-d271b17c6d7f', '8225bac9-ebce-43b4-80e0-f37de1fe34c8', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 38514);
INSERT INTO public.photo VALUES ('65ae511d-00e0-4dfe-b1a7-87a4ecc235f9', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', '2f5fa09e-4736-47dc-bf15-9801e60e670c', '9d50baa4-cfd2-47f0-a8e9-83e9382881c0', 'e460c7c3-9d1b-40e2-ba55-5ca51713388d', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 87357);
INSERT INTO public.photo VALUES ('be0bb63e-dd6e-45d8-8019-108f6b212555', 'https://lh3.googleusercontent.com/yhF6RSccEhypBMj7tZ3styWpRcZkVfzCjZn-o-SreRMPL7jJyQ4hLBWA8U-hC9PcTOMQ2_OZAAPTkVAVwQ=w987-h674-rw-no', 'https://lh3.googleusercontent.com/yhF6RSccEhypBMj7tZ3styWpRcZkVfzCjZn-o-SreRMPL7jJyQ4hLBWA8U-hC9PcTOMQ2_OZAAPTkVAVwQ=w987-h674-rw-no', 'b247dcb0-16ef-4915-811d-ae9aa173aea6', 'fd1f9c38-c8bb-4ed3-88f0-0b2c725bfbec', '4c2e49a4-6632-46e5-b9f9-ce2362831685', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 79428);
INSERT INTO public.photo VALUES ('ca58d39e-0ff3-4f74-8ac3-ed31af672ef6', 'https://lh3.googleusercontent.com/g2ZNTNi0HKAu_EtxbFwW8PkgZQSMQzkWtyPHE2Bvc9siZ0V4FamdCaKkrt4BE8wnkXtV4YtGZcrmC5pLlQ=w982-h668-rw-no', 'https://lh3.googleusercontent.com/g2ZNTNi0HKAu_EtxbFwW8PkgZQSMQzkWtyPHE2Bvc9siZ0V4FamdCaKkrt4BE8wnkXtV4YtGZcrmC5pLlQ=w982-h668-rw-no', 'f5878e5c-b0b6-4c7d-92f0-165373c386d5', '260efcdd-6a6a-4d60-9368-e3a5d3857f19', 'bb273720-a720-4c69-b5a0-cea51b692675', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 57582);
INSERT INTO public.photo VALUES ('04cac074-a690-4b9c-8bb7-08c5efab77cc', 'https://lh3.googleusercontent.com/u5Ij72KOJQ_-91l9EHY7sjwGNq8YeudojS2cOSd5xFz2sCYdEfDwewKXW2bmAbUuDPQQxy3BkjhpDbaDnA=w987-h674-rw-no', 'https://lh3.googleusercontent.com/u5Ij72KOJQ_-91l9EHY7sjwGNq8YeudojS2cOSd5xFz2sCYdEfDwewKXW2bmAbUuDPQQxy3BkjhpDbaDnA=w987-h674-rw-no', 'c9d4ef3c-ab54-4400-9f4e-1e581d6a9d3d', '225bc1ce-242c-41f5-9dc1-6a800b6cd64b', '4c2e49a4-6632-46e5-b9f9-ce2362831685', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 14271);
INSERT INTO public.photo VALUES ('b822a247-7e33-44d7-a209-feeee555cbaa', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', 'ba8144b5-565c-4c86-a160-2d4c2904dca2', '64f608e1-89c2-4dc7-8eb3-273534b98a0f', '353fc1cb-3e3d-494c-a0ae-6cd410036ed5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 86629);
INSERT INTO public.photo VALUES ('6a129471-e45a-46ac-a869-da7f9ff55eb6', 'https://lh3.googleusercontent.com/AYvRn3qtvefVRii_oqUfIG3CsM6x0Y7wwPP65nFVoZBWh9ZPWK6wiZpry4_sfshRtTA7HMFNj3h2dM2ceQ=w987-h684-rw-no', 'https://lh3.googleusercontent.com/AYvRn3qtvefVRii_oqUfIG3CsM6x0Y7wwPP65nFVoZBWh9ZPWK6wiZpry4_sfshRtTA7HMFNj3h2dM2ceQ=w987-h684-rw-no', '09d55536-e301-4217-a321-f147d36f0e03', '876717b1-53f5-418f-8143-9219c58f72a2', '7e40ec41-d626-40a1-a1c7-e4bb4e1a3fc4', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 79730);
INSERT INTO public.photo VALUES ('fdbd2bf5-48ff-49c2-ae41-596dda949110', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', 'c90b25dd-d35f-4c8e-ad77-88ae66fbd979', 'f67ee873-249e-49ff-aa78-38d36d4caa1b', 'a42ad9a9-f986-4214-8570-1dcd10dead53', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 56122);
INSERT INTO public.photo VALUES ('4290fba8-ad3e-4ba1-94f1-e5e1a00a2732', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', 'c1fbbf98-1bfc-4915-818a-6a11a42a56df', '133713cc-c2bd-4b7e-814f-a264c1388b9f', '605c9083-ebc1-45b1-aef9-8ab611dd7e7e', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 43638);
INSERT INTO public.photo VALUES ('f2bc5ff2-838d-4e12-8071-86306e8b056b', 'https://lh3.googleusercontent.com/u15v7img11Z6BRppN6tIBjeWjwTawBO_k6FZZ4N09MB7lc6R59VjZk2BocMJlmrnCF9zymFQDOaJalj0rw=w982-h681-rw-no', 'https://lh3.googleusercontent.com/u15v7img11Z6BRppN6tIBjeWjwTawBO_k6FZZ4N09MB7lc6R59VjZk2BocMJlmrnCF9zymFQDOaJalj0rw=w982-h681-rw-no', '1e0e60e0-315f-4ac1-b65e-262d3038918e', 'ea1eb5fd-3fb1-4620-8c6b-9b104ca8a755', '0947b2c7-a4f0-40a2-8037-146df0bb6532', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 13460);
INSERT INTO public.photo VALUES ('a10ab157-2672-4346-915c-5c280fdb0d0a', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', 'd2c50a78-49f8-4104-9e51-3b3ca75537bc', '702283ba-d0ee-4fb1-ba84-e7c23bacebce', '392e6fc5-de3e-40a9-b21d-08b8d75f9c54', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 31550);
INSERT INTO public.photo VALUES ('fadae75c-6a5c-43ab-b9b3-799f3adab3de', 'https://lh3.googleusercontent.com/7W4NVOFdeic6uuv-OMThv0RGIb7P6WoDYNwnDIRW9GobQboeB9bM7Z0oPrm6OiObuNeu9t9JO1l2lHhc_Q=w982-h674-rw-no', 'https://lh3.googleusercontent.com/7W4NVOFdeic6uuv-OMThv0RGIb7P6WoDYNwnDIRW9GobQboeB9bM7Z0oPrm6OiObuNeu9t9JO1l2lHhc_Q=w982-h674-rw-no', '55728746-ea87-477a-bf65-a64ff93ae7e9', 'b6cc8a28-e07e-40d9-a9f6-c03091ad7297', '06805225-51be-461c-b975-332b8999b3fa', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 13453);
INSERT INTO public.photo VALUES ('18c222fd-0439-4654-a601-b830f4871d4c', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', 'e63bedb9-3967-4adf-bed1-c759496380eb', 'a444aed5-c54a-4a34-9480-41c3172fa9ce', '9683ec97-49dd-4401-8398-711691c3fb36', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 75660);
INSERT INTO public.photo VALUES ('c6e09f71-5ecb-477b-9a8e-bbbf8c16c854', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', '6f22cf89-4a19-4ae7-81a6-474cea2151e6', '9db54ff5-81e6-42f6-85e5-1b82d0d6f421', '9e4b7ae7-5875-4cd5-bb62-3572de6d5205', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 83595);
INSERT INTO public.photo VALUES ('66c72951-7ee7-45db-9d39-3141b39281cc', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', 'b8b946bc-bdb1-4160-b666-dcd955c778a9', 'be1ea6de-cc30-4d2f-969f-ba4fc321a210', 'd9c25efd-8f23-4f7d-9230-ae8563c5823c', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 65233);
INSERT INTO public.photo VALUES ('88bfa603-d3cb-43c7-9a1d-467ee3e0c64e', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', '66f04919-c480-46c6-8ed2-9c0c61202bf0', '600e3b9d-0d64-4d27-94a8-6108381d6517', 'e08c0ccc-e08b-44b8-9d42-c11037997dbb', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 47587);
INSERT INTO public.photo VALUES ('dde07208-0d8d-4b9b-a6de-975976588e66', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', '71e0b863-17d1-4257-8017-8d49627224e9', 'fec581b4-6e39-47f2-9bcc-b598ec52e014', '9e4b7ae7-5875-4cd5-bb62-3572de6d5205', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 2827);
INSERT INTO public.photo VALUES ('110cb2c7-d753-47cc-bf2a-3ac1b5627365', 'https://lh3.googleusercontent.com/4drY_32MJW4X3WqN6RhObKtD8Xq6RSfO_ZX6lXp07uCyD1upm8E9Q1rN7J6WGWN3C4MekeUwyUuLtM65jQ=w982-h690-rw-no', 'https://lh3.googleusercontent.com/4drY_32MJW4X3WqN6RhObKtD8Xq6RSfO_ZX6lXp07uCyD1upm8E9Q1rN7J6WGWN3C4MekeUwyUuLtM65jQ=w982-h690-rw-no', '01dd5199-89b7-4fb9-8583-63f64834b619', 'fba92e6e-16c7-4bd7-a97b-374deb3a273b', '9fb99fe6-3641-461a-a2ba-274ddb2dbbfd', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 62737);
INSERT INTO public.photo VALUES ('b69aee27-5a7d-4911-9b45-ca1fd6d578ab', 'https://lh3.googleusercontent.com/ne8YokHGjc5zqOxcbh5uIDdb5ZTVWwfXKOi93ykRU0VUZkdPEmq7la9Cjj1IcAsXJlL-uihNnKNpWHdf8w=w965-h657-rw-no', 'https://lh3.googleusercontent.com/ne8YokHGjc5zqOxcbh5uIDdb5ZTVWwfXKOi93ykRU0VUZkdPEmq7la9Cjj1IcAsXJlL-uihNnKNpWHdf8w=w965-h657-rw-no', 'effaa352-e73b-457f-88a1-d2a717e68912', '64134eb8-085b-40b4-8f8a-7e9e4775cb71', '3ba62c5b-2621-47da-8e28-37a6215e594b', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 15195);
INSERT INTO public.photo VALUES ('c4dc8021-9a97-4c98-848d-882133b5accd', 'https://lh3.googleusercontent.com/idBnd1EpQ0Fs3Berqbjs8slNuudLeWhh5uUf4M1coaKuBXGHaF_5aIMPD8woX0cZUpM-88QKAkY-beeEMw=w982-h679-rw-no', 'https://lh3.googleusercontent.com/idBnd1EpQ0Fs3Berqbjs8slNuudLeWhh5uUf4M1coaKuBXGHaF_5aIMPD8woX0cZUpM-88QKAkY-beeEMw=w982-h679-rw-no', 'a90585d1-31ed-4cd9-83cd-cd253510f0c6', '571f8a92-5b05-4ffe-b55c-7a140a3c4c7d', 'd3075c9e-1c3b-4c68-a7bd-29f55a9fba20', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 65771);
INSERT INTO public.photo VALUES ('476efa45-81c8-4552-a0cb-b85ed7de8d9a', 'https://lh3.googleusercontent.com/I3uuW2w5zBrXgXZzzddMUY2NHF5ucmLysDkXLJkOdLJCjCj-p7gL-A61XTM6nell7jxSe39bo8LhPzSqHA=w982-h685-rw-no', 'https://lh3.googleusercontent.com/I3uuW2w5zBrXgXZzzddMUY2NHF5ucmLysDkXLJkOdLJCjCj-p7gL-A61XTM6nell7jxSe39bo8LhPzSqHA=w982-h685-rw-no', 'c053b051-6b7d-4a7c-833f-29b4a84b9303', '5d06ea28-b3d6-4d80-a6d9-4098ce6e4560', '6fa2b6d2-e6c8-449a-aee0-748f611d701c', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 91301);
INSERT INTO public.photo VALUES ('a9fff5a5-2b78-45db-b357-2a11c55c804c', 'https://lh3.googleusercontent.com/iMsi4VfSOYlIUeu3qogbZKwLP-Uckuos9OvckLffyBE-l8jYd6vPc4GhfEapE07Tz9T3Ml3995bTZGEeGQ=w982-h674-rw-no', 'https://lh3.googleusercontent.com/iMsi4VfSOYlIUeu3qogbZKwLP-Uckuos9OvckLffyBE-l8jYd6vPc4GhfEapE07Tz9T3Ml3995bTZGEeGQ=w982-h674-rw-no', '80d8e1b3-dedc-4e7d-a3dc-3f0c4c8b1db5', '17c42a94-5c7a-4159-b52f-f7e3cbf59c19', 'e08c0ccc-e08b-44b8-9d42-c11037997dbb', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 4351);
INSERT INTO public.photo VALUES ('bfbdd5bc-49d7-464e-86b5-f0763812e3f3', 'https://lh3.googleusercontent.com/rXP3OrZryrT0-clTBrfonsprM__0yTudhJXO4DTl9VycFraG1-e_I5Y4LYa5yh6MTNCa96lw7u7_kFmvXQ=w982-h685-rw-no', 'https://lh3.googleusercontent.com/rXP3OrZryrT0-clTBrfonsprM__0yTudhJXO4DTl9VycFraG1-e_I5Y4LYa5yh6MTNCa96lw7u7_kFmvXQ=w982-h685-rw-no', '01f4d64d-d843-48d5-94d7-51a2f43e5a81', 'd99f94ff-526f-415d-9f3f-f9ec6692d13f', '883a8d0c-14c2-40a4-8952-e1f153d68617', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 61382);
INSERT INTO public.photo VALUES ('c4550f0e-7b21-4cb9-a113-9f9630dadc2d', 'https://lh3.googleusercontent.com/tW3ZBFHxF8_YXBQB_rq1Pkk4f45bPCfaWwpFB2-oZan_Wk5iBQzJ4ifcx5HmlBOhMRlk-zCPbFkebEHsMA=w987-h674-rw-no', 'https://lh3.googleusercontent.com/tW3ZBFHxF8_YXBQB_rq1Pkk4f45bPCfaWwpFB2-oZan_Wk5iBQzJ4ifcx5HmlBOhMRlk-zCPbFkebEHsMA=w987-h674-rw-no', '9051681a-bcc2-46dd-a7e2-458beea672f8', '481bab4e-54c2-4a68-95f1-68a9f0c61aab', '3ba62c5b-2621-47da-8e28-37a6215e594b', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 95818);
INSERT INTO public.photo VALUES ('fbc9dda9-bb53-4255-95a9-a3d9f12f5a09', 'https://lh3.googleusercontent.com/XUkiQTB5nvqyqvjeNT7hGLVzJjGANs7-kvnKuZExWmRBOusC3lSTxr3Vp2hiqFjNNqhFZAyI2FDlircb5g=w982-h685-rw-no', 'https://lh3.googleusercontent.com/XUkiQTB5nvqyqvjeNT7hGLVzJjGANs7-kvnKuZExWmRBOusC3lSTxr3Vp2hiqFjNNqhFZAyI2FDlircb5g=w982-h685-rw-no', '638fb02f-e5b7-4181-9af7-cf16e7b5e3ec', '59294900-20a3-4462-99fe-49cc5f1a0575', '74524cb5-935d-47c3-9c2f-92505e2d2af5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 65701);
INSERT INTO public.photo VALUES ('85472b5a-6cfc-4326-a452-9c7fadce7dcf', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', '4ebb56f4-0f49-454d-9ffb-d0575f9963ac', '6950bad6-24ad-417e-bf5f-e24f0228714f', '4c2e49a4-6632-46e5-b9f9-ce2362831685', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 28893);
INSERT INTO public.photo VALUES ('d9bd24dd-3470-4e01-8641-afbe41ce9011', 'https://lh3.googleusercontent.com/C_2zBX0b-YArauu1RqTvJNY63aVIrdHht-LFzbCzjrPKmfIVXID_Pc6WZvedAVRNnLq952aGX4ZA3NPdtw=w987-h674-rw-no', 'https://lh3.googleusercontent.com/C_2zBX0b-YArauu1RqTvJNY63aVIrdHht-LFzbCzjrPKmfIVXID_Pc6WZvedAVRNnLq952aGX4ZA3NPdtw=w987-h674-rw-no', 'd5ac5e15-7409-4f32-9c89-651306b61e85', 'd158c1a8-8778-4d39-bb29-30bbd1a7ce10', 'e41ce86e-9ab7-4a10-bbbc-17db11282f72', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 50753);
INSERT INTO public.photo VALUES ('7580fd5f-47c8-49f9-a103-45c74510cd62', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', '425f047d-c2d5-4e50-be3b-8ab2cc32c954', 'b59bafb9-3df4-47a3-a80d-0ca4da0432e3', '74524cb5-935d-47c3-9c2f-92505e2d2af5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 31120);
INSERT INTO public.photo VALUES ('221b1cea-8575-459e-94d8-eb335bd3a814', 'https://lh3.googleusercontent.com/k5bJArW9ZujUc14v7StPd48tGuiJ7i3ci291IShN4QLK5xVm_ad7TMIMa6EjabQdeP8fwQtksdVcW4JTKQ=w987-h685-rw-no', 'https://lh3.googleusercontent.com/k5bJArW9ZujUc14v7StPd48tGuiJ7i3ci291IShN4QLK5xVm_ad7TMIMa6EjabQdeP8fwQtksdVcW4JTKQ=w987-h685-rw-no', '66fdb916-c37d-4a0b-b300-94bc3d2c67e7', '1593cd68-e34a-4b2e-80e0-90c1eb81779d', '3ba62c5b-2621-47da-8e28-37a6215e594b', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 62803);
INSERT INTO public.photo VALUES ('6c1c1773-7d3f-409e-adc0-961b6f69021e', 'https://lh3.googleusercontent.com/Gn_BImdwwNhPekt4OxRVjNjC8djZUYyleAVLNLVX6sSXQy990-DNqiPRCPthllslY5mOffaUlYG6k8wmcw=w987-h668-rw-no', 'https://lh3.googleusercontent.com/Gn_BImdwwNhPekt4OxRVjNjC8djZUYyleAVLNLVX6sSXQy990-DNqiPRCPthllslY5mOffaUlYG6k8wmcw=w987-h668-rw-no', 'c4c32884-9e56-4c82-85ea-2e23c6cf5c72', '7f404ade-1994-47d7-a8fc-ccee204c0fb0', 'adeedb11-5f4c-4e56-86bb-f9f09e290707', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 24576);
INSERT INTO public.photo VALUES ('6fb0a336-24ca-432c-ace0-d7c4c7a950d3', 'https://lh3.googleusercontent.com/YFYgSVXmoSw_al6BU-RC4dmxNCupDKIzsVYBA0pfB9cpU_dPqxn41568UrX6o20ePBYPmGh3E7QkOzPknw=w982-h696-rw-no', 'https://lh3.googleusercontent.com/YFYgSVXmoSw_al6BU-RC4dmxNCupDKIzsVYBA0pfB9cpU_dPqxn41568UrX6o20ePBYPmGh3E7QkOzPknw=w982-h696-rw-no', '470aa057-0180-40fa-b4d0-36da736defe1', 'd3864639-b553-4de5-b8b1-36d615461dd1', 'e3ec3724-1214-4ea0-af1f-7bc6e96ee241', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 22658);
INSERT INTO public.photo VALUES ('13f517dc-c6e0-4071-847e-ffaec33af2c9', 'https://lh3.googleusercontent.com/HGw_e0XkGQobb85BcBnPq06kRhZU46-4GXpnCwgFuVqOVxaK0slj5TWGQdLcJ6Nm1R-oEAMm4LiV7MedOg=w982-h679-rw-no', 'https://lh3.googleusercontent.com/HGw_e0XkGQobb85BcBnPq06kRhZU46-4GXpnCwgFuVqOVxaK0slj5TWGQdLcJ6Nm1R-oEAMm4LiV7MedOg=w982-h679-rw-no', '8c91e795-d727-4943-bed3-70016ca36a5d', 'bad10e8a-ae93-4027-9ed4-5ef0586b90d2', '605c9083-ebc1-45b1-aef9-8ab611dd7e7e', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 19356);
INSERT INTO public.photo VALUES ('2ef10a8f-190d-400a-911e-4272e0022eed', 'https://lh3.googleusercontent.com/0GwBn8dsoNPnkHEIE6HpQy4aZdbGwRk2Zr4UtxoBwbd1cRVB7zQhqwPsxSO3VcH1b3YO8JY5Pw0RFnA1Tg=w976-h690-rw-no', 'https://lh3.googleusercontent.com/0GwBn8dsoNPnkHEIE6HpQy4aZdbGwRk2Zr4UtxoBwbd1cRVB7zQhqwPsxSO3VcH1b3YO8JY5Pw0RFnA1Tg=w976-h690-rw-no', '878e2726-13e4-4409-a9ea-748fd0fe9bb2', 'c050041c-11b0-42ae-ac57-cd1677f28e85', 'dc8f800f-04b3-488f-8c78-fb9cdbffc85a', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 50517);
INSERT INTO public.photo VALUES ('e80c3320-69a0-439d-84fa-02991f516ea2', 'https://lh3.googleusercontent.com/_OVFvvcvqXLWvxSqXRhfRNF7upj_YglLKno8De74nVZYNkKGYhj09e2ozFFfapZMne4toYIzkrCPRaov_g=w982-h679-rw-no', 'https://lh3.googleusercontent.com/_OVFvvcvqXLWvxSqXRhfRNF7upj_YglLKno8De74nVZYNkKGYhj09e2ozFFfapZMne4toYIzkrCPRaov_g=w982-h679-rw-no', 'b247dcb0-16ef-4915-811d-ae9aa173aea6', '8e4e07cc-021f-4b65-b3c8-ed8f141912eb', '9fb99fe6-3641-461a-a2ba-274ddb2dbbfd', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 38859);
INSERT INTO public.photo VALUES ('3a7b5f7d-b369-4846-af5a-87a60a89891a', 'https://lh3.googleusercontent.com/EO6r25d1p7VHvIOhB0RsdyBPO8JfwRwq7ZLkMchfvfbtvQicwzlsf23yPiEB778qkziQfKDBXJbhHiS_Cg=w982-h674-rw-no', 'https://lh3.googleusercontent.com/EO6r25d1p7VHvIOhB0RsdyBPO8JfwRwq7ZLkMchfvfbtvQicwzlsf23yPiEB778qkziQfKDBXJbhHiS_Cg=w982-h674-rw-no', '55728746-ea87-477a-bf65-a64ff93ae7e9', '32393bc5-142c-4792-aa20-1417f295721a', 'c9207dd4-1a1e-495e-a033-71d0044b0c47', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 30088);
INSERT INTO public.photo VALUES ('7075043d-4834-4226-a776-ccc8d1b3329c', 'https://lh3.googleusercontent.com/FktZBoZBf0_FKUiyeVetDewJMOmszfVvtcJfh4FbirhK-zrKh2HWwgGOxnVXgmLToTfCKeuDbMgLOZFDVg=w982-h685-rw-no', 'https://lh3.googleusercontent.com/FktZBoZBf0_FKUiyeVetDewJMOmszfVvtcJfh4FbirhK-zrKh2HWwgGOxnVXgmLToTfCKeuDbMgLOZFDVg=w982-h685-rw-no', '7444208c-deff-4a85-b091-aa3147383a67', '6ee76594-ae71-43ec-9e50-68ba7ae7504a', 'e41ce86e-9ab7-4a10-bbbc-17db11282f72', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 66914);
INSERT INTO public.photo VALUES ('d449b1b3-3692-453e-b5f7-6e1052546354', 'https://lh3.googleusercontent.com/DWxAyyz-hLLfii3raxDmVwkCPxXmHfLQkTxkLakTqPnE-YOyXVYF6yIF-8QLhixEEfbThPLUYMMuJYhEMg=w982-h679-rw-no', 'https://lh3.googleusercontent.com/DWxAyyz-hLLfii3raxDmVwkCPxXmHfLQkTxkLakTqPnE-YOyXVYF6yIF-8QLhixEEfbThPLUYMMuJYhEMg=w982-h679-rw-no', 'f1d7c06d-370a-49f4-a1f3-d33e61d7752b', 'ec468ef9-6fc0-4b09-ab61-b06ea5c4b8bd', '1d7f401f-fcfc-4a6e-8e60-57197564b916', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 97312);
INSERT INTO public.photo VALUES ('d20e0500-f7bd-49bf-909b-c12492422e78', 'https://lh3.googleusercontent.com/VLW_xTBV9XKqI26uLnjp_dzb0I6Pkc8VWjE9_3ZCOvsBzvzpfZ9Lts20VlMypirrTYzJZKAQdyMYNWJaYQ=w982-h674-rw-no', 'https://lh3.googleusercontent.com/VLW_xTBV9XKqI26uLnjp_dzb0I6Pkc8VWjE9_3ZCOvsBzvzpfZ9Lts20VlMypirrTYzJZKAQdyMYNWJaYQ=w982-h674-rw-no', '601f4d01-5c9f-4a85-8369-2846150693e2', '0472bb08-962b-4d8a-a25d-cd984de33c0c', '3ba62c5b-2621-47da-8e28-37a6215e594b', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 28302);
INSERT INTO public.photo VALUES ('31c903a9-fd58-4ff9-bbfe-e46d7e20b636', 'https://lh3.googleusercontent.com/B1oL30QNkKnHzhJyvLU23pSfSUr6vpdySqq-pejXRAsQTq4YrzYnP9qS_QPGD4F5zz0FiOrYjNohvk9KdA=w987-h679-rw-no', 'https://lh3.googleusercontent.com/B1oL30QNkKnHzhJyvLU23pSfSUr6vpdySqq-pejXRAsQTq4YrzYnP9qS_QPGD4F5zz0FiOrYjNohvk9KdA=w987-h679-rw-no', '40a72634-7d0e-4561-9923-aef0b5c97eb7', '55084fae-653e-45f1-8970-c555479ecfee', '74524cb5-935d-47c3-9c2f-92505e2d2af5', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 18627);
INSERT INTO public.photo VALUES ('c5e8441a-6ab2-4312-9f2a-0d6883d809b7', 'https://lh3.googleusercontent.com/RQk4Gc1kkk54XbgB1W6FMNHvPOVCWHdr3iOYTmJsb-_WwBpht0vvAmudskdQIBBHQBZZmQxxIhb1_BqUMw=w971-h685-rw-no', 'https://lh3.googleusercontent.com/RQk4Gc1kkk54XbgB1W6FMNHvPOVCWHdr3iOYTmJsb-_WwBpht0vvAmudskdQIBBHQBZZmQxxIhb1_BqUMw=w971-h685-rw-no', 'bc1923e0-0f0a-419d-b329-d82e385cb5bd', '28a4de47-7775-4097-880b-b842dea03313', 'ee98f154-e92a-48ee-abf5-502641ae6677', '625807f1-2994-4845-862f-8e9c6b4d17cb', '2021-09-26', 9839);


--
-- Data for Name: search_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.search_record VALUES (5435, 'pocari-sweat', 'Pocari Sweat', 'drink', 'Drink', 'ctb', 'volvo-olympian-12m-ac', 'Volvo Olympian 12m Air-Conditioned', 'cross-harbour-102', '102', 'FZ1978', '4', '09', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', 'admin', 1577836800000, 'en_us', '寶礦力水特.pocari,sweat,102,409,fz1978,admin');
INSERT INTO public.search_record VALUES (5435, 'pocari-sweat', '寶礦力水特', 'drink', '飲品', 'ctb', 'volvo-olympian-12m-ac', '富豪奧林比安12米空調', 'cross-harbour-102', '102', 'FZ1978', '4', '09', 'https://lh3.googleusercontent.com/pw/ACtC-3c4ai9JkfMhdDOorfr50eH0BKA4bMK9GEo8msdRoMZVtt4MLosqzNnS2zOrlNOgzQuzWZR5h-brvKUL8g2Ii-dEUQ1BdRqgAVUQXfD8aIKsqtB2EIUN319OpIHxVUcegX7X3BqUL9vXlamd5r4jBzRZ=w640-h480-no', 'admin', 1577836800000, 'zh_hk', '寶礦力水特,pocari,sweat,102,409,fz1978,admin');
INSERT INTO public.search_record VALUES (78625, 'mcdonalds', 'McDonald''s', 'restaurants', 'Restaurants', 'kmb', 'dennis-trident-12m-duple', 'Trident 12m (Duple Metsec Body)', 'cross-harbour-601', '601', 'JP5185', 'ATR', '225', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', '606', 1632691548042, 'en_us', 'mcdonalds,restaurant,jp5185,atr,atr225,601,麥當勞,餐廳,三叉戟,dennis,trident');
INSERT INTO public.search_record VALUES (78625, 'mcdonalds', '麥當勞', 'restaurants', '餐廳', 'kmb', 'dennis-trident-12m-duple', '三叉戟十二米 (都普車身)', 'cross-harbour-601', '601', 'JP5185', 'ATR', '225', 'https://lh3.googleusercontent.com/9NE34qLdKPjkzW6R_3hJPZ-C_k1mwHbfGfQiUqpwg-n83LASJxQUMHZrXZMmRQK2uAMmr7PbkWKK57tmLQ=w984-h677-rw-no', '606', 1632691552585, 'zh_hk', 'mcdonalds,restaurant,jp5185,atr,atr225,601,麥當勞,餐廳,三叉戟,dennis,trident');
INSERT INTO public.search_record VALUES (60915, 'canon', 'Canon', 'photography', 'Photography', 'kmb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'kmb-9', '9', 'HD8712', '3AV', '204', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', '606', 1632691557466, 'en_us', 'canon,photography,hd8712,3av,205,9,佳能,相機,攝影,富豪,奧林比安,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (60915, 'canon', '佳能', 'photography', '攝影', 'kmb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'kmb-9', '9', 'HD8712', '3AV', '204', 'https://lh3.googleusercontent.com/pnokLOpI6E6DxCytq_BBmqiEt8pA9_onAJxLZDfRPJC54gaTQogM2rs2eXB7FbLSkJM0p_AHcOi4VHW0Hw=w986-h683-rw-no', '606', 1632691557466, 'zh_hk', 'canon,photography,hd8712,3av,205,9,佳能,相機,攝影,富豪,奧林比安,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (87833, 'nec-ac', 'NEC Air Conditioner', 'electronic-appliances', 'Electronic Appliances', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-171', '171', 'GM236', '4', '58', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', '606', 1632691557466, 'en_us', 'nec,electronic,appliances,eletronics,gm236,171,458,冷氣機,空調,電器,奧林比安,富豪,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (87833, 'nec-ac', 'NEC 冷氣機', 'electronic-appliances', '電器', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-171', '171', 'GM236', '4', '58', 'https://lh3.googleusercontent.com/EhkWGre_mXi89JLUc4wniVq2_N_uCDxWxdOW91uW5d2ff140EEfduUM7_eTluclb49q78X-kvsCCIcb8-A=w984-h678-rw-no', '606', 1632691557467, 'zh_hk', 'nec,electronic,appliances,eletronics,gm236,171,458,冷氣機,空調,電器,奧林比安,富豪,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (65287, 'shell-motor-oil', 'Shell Motor Oil', 'petroleum', 'Petroleum', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'cross-harbour-118', '118', 'GL4186', '8', '24', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', '606', 1632691557467, 'en_us', 'shell,motor,oil,petroleum,gas,gl4186,118,824,機油,蜆殼,丹尼士,巨龍,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (65287, 'shell-motor-oil', '蜆殼機油', 'petroleum', '石油', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'cross-harbour-118', '118', 'GL4186', '8', '24', 'https://lh3.googleusercontent.com/7uytKQNlr5K5jdVV-KziZJnTllZpPQIDcQvLpqM2v8961a7lDnoLwJWJgDCAeVIWRAu0CvspaGp44s4RRw=w986-h679-rw-no', '606', 1632691557467, 'zh_hk', 'shell,motor,oil,petroleum,gas,gl4186,118,824,機油,蜆殼,丹尼士,巨龍,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (7712, 'visa-credit-card', 'Visa', 'finance', 'Finance/Insurance', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-5', '5', 'HB2027', '5', '26', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', '606', 1632691557467, 'en_us', 'visa,credit,card,finance,hb2027,5,526,信用卡,金融,富豪,奧林比安,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (7712, 'visa-credit-card', 'Visa 信用卡', 'finance', '金融/保險', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'ctb-5', '5', 'HB2027', '5', '26', 'https://lh3.googleusercontent.com/01xJO6OPTdo1lCM2FEwja65sKhngtIE2uJkiZts7O0mJG95sVxjjbKdONFucXK6FZ4yYSnZxvEG8Y2c31w=w984-h683-rw-no', '606', 1632691557467, 'zh_hk', 'visa,credit,card,finance,hb2027,5,526,信用卡,金融,富豪,奧林比安,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (3781, 'park-island', 'Park Island', 'real-estate', 'Real Estate', 'kmb', 'volvo-super-olympian-10m', 'Super Olympian 10.6m', 'kmb-234x', '234X', 'JX6659', 'ASV', '1', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', '606', 1632691557467, 'en_us', 'park,island,real,estate,asv1,jx6659,asv,234x,super,olympian,珀麗灣,地產,樓盤,超級奧林比安,豬扒');
INSERT INTO public.search_record VALUES (3781, 'park-island', '珀麗灣', 'real-estate', '地產', 'kmb', 'volvo-super-olympian-10m', '超級奧林比安十點六米', 'kmb-234x', '234X', 'JX6659', 'ASV', '1', 'https://lh3.googleusercontent.com/AXYahWDpYucJXLYi5VRS1uQffH74K1ySXCWmSMECOXKRgbAI1Arta3FpH0yWammRDWyaoHnSkHcsiv-w0w=w984-h682-rw-no', '606', 1632691557468, 'zh_hk', 'park,island,real,estate,asv1,jx6659,asv,234x,super,olympian,珀麗灣,地產,樓盤,超級奧林比安,豬扒');
INSERT INTO public.search_record VALUES (53278, 'hutchison-idd', 'Hutchison IDD', 'telecommunication', 'Telecommunication', 'ctb', 'leyland-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-592', '592', 'FR3093', '3', '43', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', '606', 1632691557468, 'en_us', 'idd,hutchison,telecommunication,phone,fr3093,592,343,和記,通訊,電話,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (53278, 'hutchison-idd', '和記 IDD', 'telecommunication', '通訊', 'ctb', 'leyland-olympian-12m-ac', '奧林比安十二米空調', 'ctb-592', '592', 'FR3093', '3', '43', 'https://lh3.googleusercontent.com/_26xkFq1DyUE-7wOVArLwrO9RiB9JO7qDXUAc2EdU7_YZOoboGAuVMUjAg5AhAE_idWd1JHNWgPnWxfnDQ=w985-h682-rw-no', '606', 1632691557468, 'zh_hk', 'idd,hutchison,telecommunication,phone,fr3093,592,343,和記,通訊,電話,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (398, 'm-channel', 'M-Channel', 'mass-media', 'Mass Media', 'nwfb', 'dennis-trident-11m', 'Trident 11.3m', 'nwfb-23', '23', 'JC2381', '14', '02', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', '606', 1632691557468, 'en_us', 'mass,media,m-channel,channel,jc2381,1402,14xx,23,trident,dennis,傳媒,頻道,丹尼士,三叉戟,三鋒,11.3m');
INSERT INTO public.search_record VALUES (398, 'm-channel', 'M 頻道', 'mass-media', '傳媒', 'nwfb', 'dennis-trident-11m', '三叉戟十一點三米', 'nwfb-23', '23', 'JC2381', '14', '02', 'https://lh3.googleusercontent.com/4eY8-a20mPvBCnBqmHel9Z6RWVTIbmA89M0Ae5M5ghkRRasNm4Aoz8GKt0WWM1tItYWZX4FJkg86GNyHPQ=w985-h676-rw-no', '606', 1632691557468, 'zh_hk', 'mass,media,m-channel,channel,jc2381,1402,14xx,23,trident,dennis,傳媒,頻道,丹尼士,三叉戟,三鋒,11.3m');
INSERT INTO public.search_record VALUES (62793, 'weisen-u', 'Weisen U', 'medicine', 'Medicine', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-102', '102', 'GM236', '4', '58', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', '606', 1632691557468, 'en_us', 'weisen,u,medicine,gm236,102,458,胃仙U,藥物,胃藥,奧林比安,富豪,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (62793, 'weisen-u', '胃仙U', 'medicine', '藥物', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-102', '102', 'GM236', '4', '58', 'https://lh3.googleusercontent.com/6HhfVPMPR_6yP2f7Eqyu-M9XSyMO0Ay7tn1wAJ2m-ahrVDGD7TuidbwpawbHN1OuVi_v_FQUpzNiNQ8_vw=w981-h675-rw-no', '606', 1632691557468, 'zh_hk', 'weisen,u,medicine,gm236,102,458,胃仙U,藥物,胃藥,奧林比安,富豪,volvo,olympian,12m');
INSERT INTO public.search_record VALUES (15826, 'titoni', 'Titoni', 'watches', 'Watches', 'ctb', 'leyland-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-70', '70', 'FR5671', '3', '54', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', '606', 1632691557469, 'en_us', 'watch,watches,titoni,梅花表,手錶,鐘錶,fr5671,70,354,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (15826, 'titoni', '梅花表', 'watches', '鐘錶', 'ctb', 'leyland-olympian-12m-ac', '奧林比安十二米空調', 'ctb-70', '70', 'FR5671', '3', '54', 'https://lh3.googleusercontent.com/jG6WEHjnp9K_0NxSRJGoa2bYzXjUy5Dy9i95vgASm-qq11qHpRn_10182oANopI_x43yCIgWt4xtyL3Uyw=w988-h682-rw-no', '606', 1632691557469, 'zh_hk', 'watch,watches,titoni,梅花表,手錶,鐘錶,fr5671,70,354,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (96887, 'tung-wah-group-of-hospitals', 'Tung Wah Group of Hospitals', 'government', 'Government/Public Organizations', 'kmb', 'leyland-olympian-12m', 'Olympian 12m', 'kmb-86k', '86K', 'DH5849', '3BL', '71', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', '606', 1632691557469, 'en_us', 'non-profit,organization,tung,wah,hospital,東華三院,醫院,非牟利,機構,86k,dh5849,3bl71,3bl,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (96887, 'tung-wah-group-of-hospitals', '東華三院', 'government', '政府/公共機構', 'kmb', 'leyland-olympian-12m', '奧林比安十二米', 'kmb-86k', '86K', 'DH5849', '3BL', '71', 'https://lh3.googleusercontent.com/WvJqRCDmQLVW27iqu_-XuEv1NV6dEohgM2D2dIst94kBSpaHPlFo4SM3F7GATpusxlMyrn3gQSBAXRggSw=w984-h687-rw-no', '606', 1632691557469, 'zh_hk', 'non-profit,organization,tung,wah,hospital,東華三院,醫院,非牟利,機構,86k,dh5849,3bl71,3bl,奧林比安,利蘭,leyland,olympian,12m');
INSERT INTO public.search_record VALUES (76671, 'red-a', 'Red A', 'household-goods', 'Household Goods', 'kmb', 'dennis-jubilant', 'Jubilant', 'kmb-73a', '73A', 'CM1385', 'N', '196', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', '606', 1632691557469, 'en_us', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,73a,kmb,丹尼士,祖比倫,N196,cm1385');
INSERT INTO public.search_record VALUES (76671, 'red-a', '紅A', 'household-goods', '家庭用品', 'kmb', 'dennis-jubilant', '祖比倫', 'kmb-73a', '73A', 'CM1385', 'N', '196', 'https://lh3.googleusercontent.com/oP21K5Ruyv43nTWZ7NIyr1ubRfJk7mIPUR0lJtXWyFIhPJ_OWsP12TZat64LqVq4BWIu0QsBWl4ZJSIo3Q=w976-h679-rw-no', '606', 1632691557470, 'zh_hk', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,73a,kmb,丹尼士,祖比倫,N196,cm1385');
INSERT INTO public.search_record VALUES (32585, 'red-a', 'Red A', 'household-goods', 'Household Goods', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'kmb-58m', '58M', 'EA4350', 'S3BL', '315', 'https://lh3.googleusercontent.com/gFo8AaHO0SPdiul3N-8CEXXElbcto--V5SzpU2D8_i8RP3zuprqLfs9DjR_uACTMm5CIiw3dU-IfZsj-Yw=w987-h679-rw-no', '606', 1632691557470, 'en_us', 'household,supplies,red,a,紅A,leyland,olympian,s3bl,家庭用品,58m,kmb,利蘭,奧林比安,ea4350');
INSERT INTO public.search_record VALUES (32585, 'red-a', '紅A', 'household-goods', '家庭用品', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'kmb-58m', '58M', 'EA4350', 'S3BL', '315', 'https://lh3.googleusercontent.com/gFo8AaHO0SPdiul3N-8CEXXElbcto--V5SzpU2D8_i8RP3zuprqLfs9DjR_uACTMm5CIiw3dU-IfZsj-Yw=w987-h679-rw-no', '606', 1632691557470, 'zh_hk', 'household,supplies,red,a,紅A,leyland,olympian,s3bl,家庭用品,58m,kmb,利蘭,奧林比安,ea4350');
INSERT INTO public.search_record VALUES (70107, 'red-a', 'Red A', 'household-goods', 'Household Goods', 'cmb', 'leyland-fleetline-long', 'Fleetline (Long Version)', 'cross-harbour-690', '690', 'BR3177', 'LF', '207', 'https://lh3.googleusercontent.com/mRXtMv6wjQ0E1rZm0CM1Ky2ToG70oAX5NDn9wloKRtiPsc_k_80P5ePi39PeBdLCLQr9VwZGLmyI855iGA=w987-h679-rw-no', '606', 1632691557470, 'en_us', 'household,supplies,red,a,紅A,daimler,fleetline,lf,lf205,家庭用品,690,ctb,利蘭,丹拿,珍寶,br3177');
INSERT INTO public.search_record VALUES (70107, 'red-a', '紅A', 'household-goods', '家庭用品', 'cmb', 'leyland-fleetline-long', '長陣珍寶 (亞歷山大車身)', 'cross-harbour-690', '690', 'BR3177', 'LF', '207', 'https://lh3.googleusercontent.com/mRXtMv6wjQ0E1rZm0CM1Ky2ToG70oAX5NDn9wloKRtiPsc_k_80P5ePi39PeBdLCLQr9VwZGLmyI855iGA=w987-h679-rw-no', '606', 1632691557470, 'zh_hk', 'household,supplies,red,a,紅A,daimler,fleetline,lf,lf205,家庭用品,690,ctb,利蘭,丹拿,珍寶,br3177');
INSERT INTO public.search_record VALUES (6993, 'red-a', 'Red A', 'household-goods', 'Household Goods', 'kmb', 'dennis-jubilant', 'Jubilant', 'kmb-5c', '73A', 'CH7454', 'N', '105', 'https://lh3.googleusercontent.com/5s1cUalDFCiAF-HpN2nDxaKzDbNAWLZHyRRPyjNQgKoI2WVWJuv4iEdAVvNvDNcSGjeap2C8Q0S5_dcGGA=w982-h685-rw-no', '606', 1632691557470, 'en_us', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,5c,kmb,丹尼士,祖比倫,n105,ch7454');
INSERT INTO public.search_record VALUES (6993, 'red-a', '紅A', 'household-goods', '家庭用品', 'kmb', 'dennis-jubilant', '祖比倫', 'kmb-5c', '73A', 'CH7454', 'N', '105', 'https://lh3.googleusercontent.com/5s1cUalDFCiAF-HpN2nDxaKzDbNAWLZHyRRPyjNQgKoI2WVWJuv4iEdAVvNvDNcSGjeap2C8Q0S5_dcGGA=w982-h685-rw-no', '606', 1632691557470, 'zh_hk', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,5c,kmb,丹尼士,祖比倫,n105,ch7454');
INSERT INTO public.search_record VALUES (70304, 'red-a', 'Red A', 'household-goods', 'Household Goods', 'kmb', 'dennis-jubilant', 'Jubilant', 'kmb-5c', '73A', 'CH7454', 'N', '105', 'https://lh3.googleusercontent.com/HkNpnggv81Xb6VKy7tfviM4UNDb1ZtMvX0Uy2-WhIrnua6XYVrUKGZNFO-Oubn9JIQ2DQfkJzfUB48VFWA=w987-h679-rw-no', '606', 1632691557470, 'en_us', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,5c,kmb,丹尼士,祖比倫,n105,ch7454');
INSERT INTO public.search_record VALUES (70304, 'red-a', '紅A', 'household-goods', '家庭用品', 'kmb', 'dennis-jubilant', '祖比倫', 'kmb-5c', '73A', 'CH7454', 'N', '105', 'https://lh3.googleusercontent.com/HkNpnggv81Xb6VKy7tfviM4UNDb1ZtMvX0Uy2-WhIrnua6XYVrUKGZNFO-Oubn9JIQ2DQfkJzfUB48VFWA=w987-h679-rw-no', '606', 1632691557470, 'zh_hk', 'household,supplies,red,a,紅A,dennis,jubilant,鴨車,家庭用品,5c,kmb,丹尼士,祖比倫,n105,ch7454');
INSERT INTO public.search_record VALUES (41118, 'ulfenbo', 'Ulfenbo', 'furniture', 'Furniture/Home Improvement', 'cmb', 'dennis-dragon-11m-ac', 'Dragon/Condor 11m Air-Conditioned', 'cross-harbour-101', '101', 'EN6522', 'DA', '11', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', '606', 1632691557470, 'en_us', 'furniture,ulfenbo,傢具,歐化寶,dennis,dragon,condor,101,cmb,中巴,丹尼士,巨龍,禿鷹,da11,en6522');
INSERT INTO public.search_record VALUES (41118, 'ulfenbo', '歐化寶', 'furniture', '傢具/建材', 'cmb', 'dennis-dragon-11m-ac', '巨龍/禿鷹十一米空調', 'cross-harbour-101', '101', 'EN6522', 'DA', '11', 'https://lh3.googleusercontent.com/qyHi8yyfStlVq2rf18BUm8zknyi7LJHMtOc553UDtOl8KQt4eyVCVtcDdKzZdQFokF5FsIow8waDRWE8ww=w993-h679-rw-no', '606', 1632691557470, 'zh_hk', 'furniture,ulfenbo,傢具,歐化寶,dennis,dragon,condor,101,cmb,中巴,丹尼士,巨龍,禿鷹,da11,en6522');
INSERT INTO public.search_record VALUES (77887, 'lion-toothpaste', 'Lion Toothpaste', 'household-goods', 'Household Goods', 'kmb', 'dennis-dragon-11m-ac', 'Dragon/Condor 11m Air-Conditioned', 'cross-harbour-102', '102', 'FB9728', 'AD', '45', 'https://lh3.googleusercontent.com/vXhoqbSYlvyqUcvpy5Ts5W7s6XM_E4oyMgoHBfxu4zqZpMkGTT9WwWDWl99nlXHBuMS4DqyuSxvPsTUjDA=w987-h679-rw-no', '606', 1632691557471, 'en_us', 'household,supplies,lion,toothpaste,家庭用品,獅王牙膏,dennis,dragon,condor,102,kmb,九巴,丹尼士,巨龍,禿鷹,ad45,fb9728');
INSERT INTO public.search_record VALUES (77887, 'lion-toothpaste', '獅王牙膏', 'household-goods', '家庭用品', 'kmb', 'dennis-dragon-11m-ac', '巨龍/禿鷹十一米空調', 'cross-harbour-102', '102', 'FB9728', 'AD', '45', 'https://lh3.googleusercontent.com/vXhoqbSYlvyqUcvpy5Ts5W7s6XM_E4oyMgoHBfxu4zqZpMkGTT9WwWDWl99nlXHBuMS4DqyuSxvPsTUjDA=w987-h679-rw-no', '606', 1632691557471, 'zh_hk', 'household,supplies,lion,toothpaste,家庭用品,獅王牙膏,dennis,dragon,condor,102,kmb,九巴,丹尼士,巨龍,禿鷹,ad45,fb9728');
INSERT INTO public.search_record VALUES (72937, 'cold-fire', 'Cold Fire', 'household-goods', 'Household Goods', 'cmb', 'metrobus-12m', 'Metrobus 12m', 'cross-harbour-101', '101', 'DV542', 'ML', '61', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', '606', 1632691557471, 'en_us', 'household,supplies,cold,fire,噴即滅,滅火劑,mcw,metrobus,12m,101,cmb,中巴,都城嘉慕,都城巴士,ml61,dv542');
INSERT INTO public.search_record VALUES (72937, 'cold-fire', '噴即滅', 'household-goods', '家庭用品', 'cmb', 'metrobus-12m', '都城巴士十二米', 'cross-harbour-101', '101', 'DV542', 'ML', '61', 'https://lh3.googleusercontent.com/6LhNLiKXHnwNVgnpyWMXkHTNKN7Y13ran41MDvfs-EN4qHlb5U0gCatWveinXQzlBDgyVHHMewajSag6LQ=w993-h685-rw-no', '606', 1632691557471, 'zh_hk', 'household,supplies,cold,fire,噴即滅,滅火劑,mcw,metrobus,12m,101,cmb,中巴,都城嘉慕,都城巴士,ml61,dv542');
INSERT INTO public.search_record VALUES (7371, 'po-sum-on', 'Po Sum On Medicine', 'medicine', 'Medicine', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'cross-harbour-170', '170', 'GH469', '8', '12', 'https://lh3.googleusercontent.com/MbevV9UNf-qC5zP0xhDuiRuB0t0OjqRnQQLYPr_GGf_20hHxQtdq7G4NyFQosITZZmO2_BeKfA0ddll5sw=w987-h685-rw-no', '606', 1632691557471, 'en_us', 'posumon,medicine,gh469,170,812,保心安油,藥物,藥油,巨龍,丹尼士,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (7371, 'po-sum-on', '保心安油', 'medicine', '藥物', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'cross-harbour-170', '170', 'GH469', '8', '12', 'https://lh3.googleusercontent.com/MbevV9UNf-qC5zP0xhDuiRuB0t0OjqRnQQLYPr_GGf_20hHxQtdq7G4NyFQosITZZmO2_BeKfA0ddll5sw=w987-h685-rw-no', '606', 1632691557471, 'zh_hk', 'posumon,medicine,gh469,170,812,保心安油,藥物,藥油,巨龍,丹尼士,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (28862, 'flower-paint', 'Flower Paint', 'furniture', 'Furniture/Home Improvement', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'cross-harbour-182', '182', 'GR2793', '8', '55', 'https://lh3.googleusercontent.com/PgDgVMCO8NIX644K3mBRtxVOodQvhJZ-JzDPDvhJFNnLz7V_9mO7yUay4YUXvRtSgxg6U2NrSMLrVfuzHg=w982-h657-rw-no', '606', 1632691557471, 'en_us', 'flower,home,improvement,latex,paint,gr2793,182,855,菊花牌,油漆,牆面漆,乳膠漆,巨龍,丹尼士,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (28862, 'flower-paint', '菊花牌油漆', 'furniture', '傢具/建材', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'cross-harbour-182', '182', 'GR2793', '8', '55', 'https://lh3.googleusercontent.com/PgDgVMCO8NIX644K3mBRtxVOodQvhJZ-JzDPDvhJFNnLz7V_9mO7yUay4YUXvRtSgxg6U2NrSMLrVfuzHg=w982-h657-rw-no', '606', 1632691557471, 'zh_hk', 'flower,home,improvement,latex,paint,gr2793,182,855,菊花牌,油漆,牆面漆,乳膠漆,巨龍,丹尼士,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (92688, 'one-tel', 'One Tel', 'telecommunication', 'Telecommunication', 'kmb', 'dennis-dragon-9.9m-ac', 'Dragon 9.9m Air-Conditioned', 'kmb-234x', '234X', 'JC3853', 'ADS', '213', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', '606', 1632691557471, 'en_us', 'one,tel,telecommunication,phone,一電通,電話,234x,dennis,dragon,9.9m,丹尼士,巨龍,jc3853,ads213,ads');
INSERT INTO public.search_record VALUES (92688, 'one-tel', '一電通', 'telecommunication', '通訊', 'kmb', 'dennis-dragon-9.9m-ac', '巨龍九點九米空調', 'kmb-234x', '234X', 'JC3853', 'ADS', '213', 'https://lh3.googleusercontent.com/bLLWb3nq3XfsPTFCTuyhJ6Xqd_GN86N7jR4xD_N8M7PFHzJIN7JnCCCdQtoDeZhsWJedoGrZ3amN7o8kmQ=w993-h679-rw-no', '606', 1632691557472, 'zh_hk', 'one,tel,telecommunication,phone,一電通,電話,234x,dennis,dragon,9.9m,丹尼士,巨龍,jc3853,ads213,ads');
INSERT INTO public.search_record VALUES (23740, 'airland-mattress', 'Airland Mattress', 'furniture', 'Furniture/Home Improvement', 'kmb', 'volvo-olympian-11m-ac', 'Olympian 11.3m Air-Conditioned', 'cross-harbour-103', '103', 'HU162', 'AV', '512', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', '606', 1632691557472, 'en_us', 'furniture,airland,mattress,雅蘭床褥,床上用品,volvo,olympian,11m,kmb,103,九巴,富豪,奧林比安,空調巴士,av512,hu162');
INSERT INTO public.search_record VALUES (23740, 'airland-mattress', '雅蘭床褥', 'furniture', '傢具/建材', 'kmb', 'volvo-olympian-11m-ac', '奧林比安十一點三米空調', 'cross-harbour-103', '103', 'HU162', 'AV', '512', 'https://lh3.googleusercontent.com/mbfgYEUejVCcQnY8ct8wQae9ltJQdJcmAruvHZswAHL7aqQ44bGy3UG8wMCiPrXMrRtjezTYZAyJNocwBQ=w987-h679-rw-no', '606', 1632691557472, 'zh_hk', 'furniture,airland,mattress,雅蘭床褥,床上用品,volvo,olympian,11m,kmb,103,九巴,富豪,奧林比安,空調巴士,av512,hu162');
INSERT INTO public.search_record VALUES (18855, 'siemens', 'Siemens', 'telecommunication', 'Telecommunication', 'ctb', 'dennis-dragon-10.3m-ac', 'Dragon 10.3m Air-Conditioned', 'ctb-85', '85', 'GF8124', '7', '20', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', '606', 1632691557472, 'en_us', 'siemens,telecommunication,phone,西門子,通訊,85,dennis,dragon,10.3m,丹尼士,巨龍,gf8124,720,ctb');
INSERT INTO public.search_record VALUES (18855, 'siemens', '西門子', 'telecommunication', '通訊', 'ctb', 'dennis-dragon-10.3m-ac', '巨龍十點三米空調', 'ctb-85', '85', 'GF8124', '7', '20', 'https://lh3.googleusercontent.com/SfEm4IYs9DdPg2PhW4MbxQNSdnRJYLuZrorCpCWxfFu7-nN1QmyjM9ZPk4Rg2XMMZo9iyBrlTnsPSQdNwg=w987-h668-rw-no', '606', 1632691557472, 'zh_hk', 'siemens,telecommunication,phone,西門子,通訊,85,dennis,dragon,10.3m,丹尼士,巨龍,gf8124,720,ctb');
INSERT INTO public.search_record VALUES (59379, 'columbia-international-removal', 'Columbia International Removals', 'others', 'Others', 'cmb', 'daimler-dms-9.4m', 'Fleetline DMS', 'unknown', 'Unknown', 'CZ9485', 'XF', '168', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', '606', 1632691557472, 'en_us', 'columbia,international,removal,移民,裝運,安寧,搬運,中巴,二手寶,丹拿,珍寶巴士,daimler,xf,xf168,dms,fleetline,cz9485');
INSERT INTO public.search_record VALUES (59379, 'columbia-international-removal', '安寧國際裝運', 'others', '其他', 'cmb', 'daimler-dms-9.4m', '二手珍寶九點四米', 'unknown', 'Unknown', 'CZ9485', 'XF', '168', 'https://lh3.googleusercontent.com/3xqzzgvBU3K5qrPjSKzo0PRTsyvWSV6Cq8fuYL2luCommvuucgJtQlHnOil8L23GYRfCLNUJWHYCVstYcg=w987-h685-rw-no', '606', 1632691557472, 'zh_hk', 'columbia,international,removal,移民,裝運,安寧,搬運,中巴,二手寶,丹拿,珍寶巴士,daimler,xf,xf168,dms,fleetline,cz9485');
INSERT INTO public.search_record VALUES (28542, 'pocari-sweat', 'Pocari Sweat', 'beverage', 'Beverage', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-102', '102', 'FZ1978', '4', '09', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', 'KR7295', 1632691557472, 'en_us', 'pocari,sweat,beverage,drink,volvo,olympian,12m,fz1978,409,城巴,富豪,奧林比安,寶礦力水特,飲品,能量');
INSERT INTO public.search_record VALUES (28542, 'pocari-sweat', '寶礦力水特', 'beverage', '飲品', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-102', '102', 'FZ1978', '4', '09', 'https://lh3.googleusercontent.com/ioP3JWYuF557JqfdqA71MHV3wmTvlthvnsns665Wo2UnpkXEjiIrtf8QVEcvCEGxQK3Mc2rJN8IQZ4_zAg=w640-h480-rw-no', 'KR7295', 1632691557472, 'zh_hk', 'pocari,sweat,beverage,drink,volvo,olympian,12m,fz1978,409,城巴,富豪,奧林比安,寶礦力水特,飲品,能量');
INSERT INTO public.search_record VALUES (82659, 'chung-kiu-chinese-products', 'Chung Kiu Chinese Products', 'shopping', 'Shopping', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'kmb-1', '1', 'DT5992', 'S3BL', '216', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', '606', 1632691557472, 'en_us', 'department,store,百貨公司,中僑國貨,購物,利蘭,奧林比安,S3BL,216,DT5992,kmb,1,chung,kiu,chinese,products');
INSERT INTO public.search_record VALUES (82659, 'chung-kiu-chinese-products', '中僑國貨', 'shopping', '購物', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'kmb-1', '1', 'DT5992', 'S3BL', '216', 'https://lh3.googleusercontent.com/GkoM_xtzaDNyB5En4aQEyugLWmTJCeiRgUA7UUfv9B3RjZ2QSYmqUXqf_sbIS-wCPpTQwJ8h-QnJM1HMnA=w993-h685-rw-no', '606', 1632691557472, 'zh_hk', 'department,store,百貨公司,中僑國貨,購物,利蘭,奧林比安,S3BL,216,DT5992,kmb,1,chung,kiu,chinese,products');
INSERT INTO public.search_record VALUES (38514, 'demae-ichou', 'Demae Ichou', 'food', 'Food', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-5x', '5X', 'HA9347', '5', '11', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', '606', 1632691557472, 'en_us', 'food,demae,ichou,instant,noodles,volvo,olympian,511,ha9347,5x,食品,即食麵,出前一丁,奧林比安,富豪');
INSERT INTO public.search_record VALUES (38514, 'demae-ichou', '出前一丁', 'food', '食品', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'ctb-5x', '5X', 'HA9347', '5', '11', 'https://lh3.googleusercontent.com/ecb5JE1eMuIEcqIQqxypOgEPjyZAMWPhQH9h6LkDOFsv3wCQtaeFZPOMfMaZy88utGNDMF8Lf6aLBUmGlg=w987-h668-rw-no', '606', 1632691557472, 'zh_hk', 'food,demae,ichou,instant,noodles,volvo,olympian,511,ha9347,5x,食品,即食麵,出前一丁,奧林比安,富豪');
INSERT INTO public.search_record VALUES (87357, 'year-of-the-rooster-2005', 'Year of the Rooster 2005', 'bus-company', 'Bus Company', 'kmb', 'volvo-olympian-11m-ac', 'Olympian 11.3m Air-Conditioned', 'kmb-30x', '30X', 'HL9881', 'AV', '326', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', '606', 1632691557472, 'en_us', 'bus,company,rooster,year,2005,lunar,new,year,巴士公司,雞年,乙酉,農曆新年,奧林比安,富豪,30x,av326,hl9881');
INSERT INTO public.search_record VALUES (87357, 'year-of-the-rooster-2005', '乙酉雞年2005', 'bus-company', '巴士公司', 'kmb', 'volvo-olympian-11m-ac', '奧林比安十一點三米空調', 'kmb-30x', '30X', 'HL9881', 'AV', '326', 'https://lh3.googleusercontent.com/0lKAp_DIwzVV5DTxRc0CDwC2NdYNSeKkGKJtk6wP3swhew-wA4v9gLcE7a16KTHbIBbeQYWAf1bS1Pp_YQ=w982-h696-rw-no', '606', 1632691557472, 'zh_hk', 'bus,company,rooster,year,2005,lunar,new,year,巴士公司,雞年,乙酉,農曆新年,奧林比安,富豪,30x,av326,hl9881');
INSERT INTO public.search_record VALUES (79428, 'king-to-nin-jiom', 'King To Nin Jiom', 'medicine', 'Medicine', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'cross-harbour-182', '182', 'GS4109', '8', '56', 'https://lh3.googleusercontent.com/yhF6RSccEhypBMj7tZ3styWpRcZkVfzCjZn-o-SreRMPL7jJyQ4hLBWA8U-hC9PcTOMQ2_OZAAPTkVAVwQ=w987-h674-rw-no', '606', 1632691557472, 'en_us', 'medicine,king,to,nin,jiom,藥物,京都念慈菴,川貝枇杷膏,丹尼士,巨龍,gs4109,856,182,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (79428, 'king-to-nin-jiom', '京都念慈菴', 'medicine', '藥物', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'cross-harbour-182', '182', 'GS4109', '8', '56', 'https://lh3.googleusercontent.com/yhF6RSccEhypBMj7tZ3styWpRcZkVfzCjZn-o-SreRMPL7jJyQ4hLBWA8U-hC9PcTOMQ2_OZAAPTkVAVwQ=w987-h674-rw-no', '606', 1632691557472, 'zh_hk', 'medicine,king,to,nin,jiom,藥物,京都念慈菴,川貝枇杷膏,丹尼士,巨龍,gs4109,856,182,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (57582, 'cable-tv', 'Cable TV', 'mass-media', 'Mass Media', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'kmb-66m', '66M', 'EU6010', 'S3BL', '406', 'https://lh3.googleusercontent.com/g2ZNTNi0HKAu_EtxbFwW8PkgZQSMQzkWtyPHE2Bvc9siZ0V4FamdCaKkrt4BE8wnkXtV4YtGZcrmC5pLlQ=w982-h668-rw-no', '606', 1632691557472, 'en_us', 'television,cable,tv,s3bl,66m,eu6010,s3bl406,leyland,olympian,11m,利蘭,奧林比安,有線電視,媒體');
INSERT INTO public.search_record VALUES (57582, 'cable-tv', '有線電視', 'mass-media', '傳媒', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'kmb-66m', '66M', 'EU6010', 'S3BL', '406', 'https://lh3.googleusercontent.com/g2ZNTNi0HKAu_EtxbFwW8PkgZQSMQzkWtyPHE2Bvc9siZ0V4FamdCaKkrt4BE8wnkXtV4YtGZcrmC5pLlQ=w982-h668-rw-no', '606', 1632691557473, 'zh_hk', 'television,cable,tv,s3bl,66m,eu6010,s3bl406,leyland,olympian,11m,利蘭,奧林比安,有線電視,媒體');
INSERT INTO public.search_record VALUES (14271, 'macquarie-warrants', 'Macquarie Warrants', 'finance', 'Finance/Insurance', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-182', '182', 'HE457', '5', '68', 'https://lh3.googleusercontent.com/u5Ij72KOJQ_-91l9EHY7sjwGNq8YeudojS2cOSd5xFz2sCYdEfDwewKXW2bmAbUuDPQQxy3BkjhpDbaDnA=w987-h674-rw-no', '606', 1632691557473, 'en_us', 'finance,金融,麥格理認股證,macquarie,warrants,volvo,olympian,12m,富豪,奧林比安,182,he457,568,城巴');
INSERT INTO public.search_record VALUES (14271, 'macquarie-warrants', '麥格理認股證', 'finance', '金融/保險', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-182', '182', 'HE457', '5', '68', 'https://lh3.googleusercontent.com/u5Ij72KOJQ_-91l9EHY7sjwGNq8YeudojS2cOSd5xFz2sCYdEfDwewKXW2bmAbUuDPQQxy3BkjhpDbaDnA=w987-h674-rw-no', '606', 1632691557473, 'zh_hk', 'finance,金融,麥格理認股證,macquarie,warrants,volvo,olympian,12m,富豪,奧林比安,182,he457,568,城巴');
INSERT INTO public.search_record VALUES (86629, 'vst-computers', 'VST Computers', 'computer', 'Computer', 'kmb', 'volvo-olympian-11m-ac', 'Olympian 11.3m Air-Conditioned', 'cross-harbour-104', '104', 'GW5789', 'AV', '114', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', '606', 1632691557473, 'en_us', 'computer,vst,偉仕電腦,volvo,olympian,11m,富豪,奧林比安,104,九巴,電腦,硬碟,av114,gw5789');
INSERT INTO public.search_record VALUES (86629, 'vst-computers', '偉仕電腦', 'computer', '電腦', 'kmb', 'volvo-olympian-11m-ac', '奧林比安十一點三米空調', 'cross-harbour-104', '104', 'GW5789', 'AV', '114', 'https://lh3.googleusercontent.com/J0vX_76tDWx2nKLP4bVUl2YJMZFU1iv-q0-pG5p0g5p4b5_YsPLuaB61278opLM2B-ndfCci5aT9txCPOw=w993-h685-rw-no', '606', 1632691557473, 'zh_hk', 'computer,vst,偉仕電腦,volvo,olympian,11m,富豪,奧林比安,104,九巴,電腦,硬碟,av114,gw5789');
INSERT INTO public.search_record VALUES (79730, 'kgi-securities', 'KGI Securities', 'finance', 'Finance/Insurance', 'ctb', 'leyland-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-10', '10', 'FS1819', '3', '53', 'https://lh3.googleusercontent.com/AYvRn3qtvefVRii_oqUfIG3CsM6x0Y7wwPP65nFVoZBWh9ZPWK6wiZpry4_sfshRtTA7HMFNj3h2dM2ceQ=w987-h684-rw-no', '606', 1632691557473, 'en_us', 'finance,金融,凱基證券,kgi,securities,leyland,olympian,12m,利蘭,奧林比安,10,fs1819,353,城巴');
INSERT INTO public.search_record VALUES (79730, 'kgi-securities', '凱基證券', 'finance', '金融/保險', 'ctb', 'leyland-olympian-12m-ac', '奧林比安十二米空調', 'ctb-10', '10', 'FS1819', '3', '53', 'https://lh3.googleusercontent.com/AYvRn3qtvefVRii_oqUfIG3CsM6x0Y7wwPP65nFVoZBWh9ZPWK6wiZpry4_sfshRtTA7HMFNj3h2dM2ceQ=w987-h684-rw-no', '606', 1632691557473, 'zh_hk', 'finance,金融,凱基證券,kgi,securities,leyland,olympian,12m,利蘭,奧林比安,10,fs1819,353,城巴');
INSERT INTO public.search_record VALUES (56122, 'green-peace', 'Green Peace', 'government', 'Government/Public Organizations', 'kmb', 'dennis-dragon-11m', 'Dragon 11.3m', 'kmb-63x', '63X', 'GA5145', 'S3N', '357', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', '606', 1632691557473, 'en_us', 'organization,non,profit,green,peace,綠色和平,機構,非牟利,環保團體,dennis,dragon,11m,開龍,巨龍,丹尼士,ga5145,s3n357,63x,kmb');
INSERT INTO public.search_record VALUES (56122, 'green-peace', '綠色和平', 'government', '政府/公共機構', 'kmb', 'dennis-dragon-11m', '巨龍十一點三米', 'kmb-63x', '63X', 'GA5145', 'S3N', '357', 'https://lh3.googleusercontent.com/V08QAruHjp2QqWmC8IFzjnu4yIcM2ngaivoF0mml5NAiNZmSiAiaC3N7L9lCqLu4k_R5sAgSHvV-VoFFbQ=w989-h685-rw-no', '606', 1632691557473, 'zh_hk', 'organization,non,profit,green,peace,綠色和平,機構,非牟利,環保團體,dennis,dragon,11m,開龍,巨龍,丹尼士,ga5145,s3n357,63x,kmb');
INSERT INTO public.search_record VALUES (43638, 'eagles-deer-essence', 'Eagles Deer Essence', 'food', 'Food', 'nwfb', 'dennis-trident-12m-alexander', 'Trident 12m (Alexendar Body)', 'cross-harbour-680', '680', 'HY1164', '10', '89', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', '606', 1632691557473, 'en_us', 'food,essence,deer,eagles,鷹牌,鹿精,補健品,health,product,dennis,trident,12m,680,nwfb,新巴,丹尼士,三叉戟,hy1164,1089');
INSERT INTO public.search_record VALUES (43638, 'eagles-deer-essence', '鷹牌鹿精', 'food', '食品', 'nwfb', 'dennis-trident-12m-alexander', '三叉戟十二米 (亞歷山大車身)', 'cross-harbour-680', '680', 'HY1164', '10', '89', 'https://lh3.googleusercontent.com/wXRlRJc7hBFTSPEZnb1aGB00XE1LtTriVJd7SgtAH6SLdmAmhHhGohdwKAxsHOMuZrWJmuvLPwDyC9EVHQ=w982-h685-rw-no', '606', 1632691557474, 'zh_hk', 'food,essence,deer,eagles,鷹牌,鹿精,補健品,health,product,dennis,trident,12m,680,nwfb,新巴,丹尼士,三叉戟,hy1164,1089');
INSERT INTO public.search_record VALUES (13460, 'calbee', 'Calbee', 'food', 'Food', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'cross-harbour-114', '114', 'DR6008', 'S3BL', '199', 'https://lh3.googleusercontent.com/u15v7img11Z6BRppN6tIBjeWjwTawBO_k6FZZ4N09MB7lc6R59VjZk2BocMJlmrnCF9zymFQDOaJalj0rw=w982-h681-rw-no', '606', 1632691557474, 'en_us', 'food,potato,chips,calbee,leyland,olympian,11m,114,kmb,dr6008,s3bl199,食品,零食,卡樂B,熱浪,薯片,九巴,利蘭,奧林比安');
INSERT INTO public.search_record VALUES (13460, 'calbee', '卡樂B', 'food', '食品', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'cross-harbour-114', '114', 'DR6008', 'S3BL', '199', 'https://lh3.googleusercontent.com/u15v7img11Z6BRppN6tIBjeWjwTawBO_k6FZZ4N09MB7lc6R59VjZk2BocMJlmrnCF9zymFQDOaJalj0rw=w982-h681-rw-no', '606', 1632691557474, 'zh_hk', 'food,potato,chips,calbee,leyland,olympian,11m,114,kmb,dr6008,s3bl199,食品,零食,卡樂B,熱浪,薯片,九巴,利蘭,奧林比安');
INSERT INTO public.search_record VALUES (31550, 'paolo-sartori', 'Paolo Sartori', 'clothing', 'Clothing/Footwear', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'kmb-1', '1', 'DR2006', 'S3BL', '181', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', '606', 1632691557474, 'en_us', 'footwear,shoes,paolo,sartori,鞋履,皮鞋,利蘭,奧林比安,九巴,leyland,olympian,11m,1,dr2006,s3bl181');
INSERT INTO public.search_record VALUES (31550, 'paolo-sartori', 'Paolo Sartori', 'clothing', '衣物/鞋具', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'kmb-1', '1', 'DR2006', 'S3BL', '181', 'https://lh3.googleusercontent.com/xvgw8iKYvi329I5teo1BBPa_a_ppa6K69ffZJenXEvJQ1X-ttnpNxOFBfKX22uMh-AW8j2SNEw_NyMwy3A=w971-h687-rw-no', '606', 1632691557474, 'zh_hk', 'footwear,shoes,paolo,sartori,鞋履,皮鞋,利蘭,奧林比安,九巴,leyland,olympian,11m,1,dr2006,s3bl181');
INSERT INTO public.search_record VALUES (13453, 'china-insurance', 'China Insurance', 'finance', 'Finance/Insurance', 'ctb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-171', '171', 'FX4237', '3', '96', 'https://lh3.googleusercontent.com/7W4NVOFdeic6uuv-OMThv0RGIb7P6WoDYNwnDIRW9GobQboeB9bM7Z0oPrm6OiObuNeu9t9JO1l2lHhc_Q=w982-h674-rw-no', '606', 1632691557474, 'en_us', 'insurance,china,volvo,olympian,12m,171,cross,harbour,城巴,富豪,奧林比安,空調,保險,中國,396,fx4237');
INSERT INTO public.search_record VALUES (13453, 'china-insurance', '中國保險', 'finance', '金融/保險', 'ctb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-171', '171', 'FX4237', '3', '96', 'https://lh3.googleusercontent.com/7W4NVOFdeic6uuv-OMThv0RGIb7P6WoDYNwnDIRW9GobQboeB9bM7Z0oPrm6OiObuNeu9t9JO1l2lHhc_Q=w982-h674-rw-no', '606', 1632691557474, 'zh_hk', 'insurance,china,volvo,olympian,12m,171,cross,harbour,城巴,富豪,奧林比安,空調,保險,中國,396,fx4237');
INSERT INTO public.search_record VALUES (75660, 'tourism-malaysia', 'Tourism Malaysia', 'tourism', 'Travel Agency/Tourism', 'nwfb', 'dennis-trident-12m-duple', 'Trident 12m (Duple Metsec Body)', 'cross-harbour-115', '115', 'HY2077', '30', '11', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', '606', 1632691557474, 'en_us', 'tourism,malaysia,dennis,trident,12m,duple,metsec,nwfb,115,hy2077,3011,新巴,三叉戟,馬來西亞,旅遊,丹尼士');
INSERT INTO public.search_record VALUES (75660, 'tourism-malaysia', '馬來西亞旅遊局', 'tourism', '旅行社/旅遊', 'nwfb', 'dennis-trident-12m-duple', '三叉戟十二米 (都普車身)', 'cross-harbour-115', '115', 'HY2077', '30', '11', 'https://lh3.googleusercontent.com/zTjwceXfopQbQvaW87ZhATJQslkZwbhc9EoH5qXa7uRrub6HMWWv9A7-4vHlsIv4FSy0-dImUIrtrjDI-A=w982-h679-rw-no', '606', 1632691557474, 'zh_hk', 'tourism,malaysia,dennis,trident,12m,duple,metsec,nwfb,115,hy2077,3011,新巴,三叉戟,馬來西亞,旅遊,丹尼士');
INSERT INTO public.search_record VALUES (83595, 'meiji-ice-cream', 'Meiji Ice Cream Bar', 'food', 'Food', 'kmb', 'leyland-olympian-9.5m', 'Olympian 9.5m', 'cross-harbour-113', '113', 'DE2928', 'BL', '63', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', '606', 1632691557474, 'en_us', 'food,ice,cream,bar,meiji,leyland,olympian,9.5m,113,bl63,de2928,明治,雪條,雪糕,九巴,利蘭,奧林比安');
INSERT INTO public.search_record VALUES (83595, 'meiji-ice-cream', '明治雪條', 'food', '食品', 'kmb', 'leyland-olympian-9.5m', '奧林比安九點五米', 'cross-harbour-113', '113', 'DE2928', 'BL', '63', 'https://lh3.googleusercontent.com/mbMSCr8cLvVzVWCapurwxKI_zbsBce4tLexaN9n4aYnnnJnADZ_vTBDK9h0cAj9Dw3iAPtGARfE3yeAlrA=w982-h679-rw-no', '606', 1632691557474, 'zh_hk', 'food,ice,cream,bar,meiji,leyland,olympian,9.5m,113,bl63,de2928,明治,雪條,雪糕,九巴,利蘭,奧林比安');
INSERT INTO public.search_record VALUES (65233, 'ma-pak-leung', 'Ma Pak Leung', 'medicine', 'Medicine', 'kmb', 'mercedes-benz-o305', 'O305', 'kmb-68x', '68X', 'DG2612', 'ME', '22', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', '606', 1632691557474, 'en_us', 'ma,pak,leung,medicine,mercedes,benz,o305,me22,dg2612,平治,九巴,68x,公路王,馬百良,藥物,八寶鹽蛇散');
INSERT INTO public.search_record VALUES (65233, 'ma-pak-leung', '馬百良', 'medicine', '藥物', 'kmb', 'mercedes-benz-o305', 'O305十一米', 'kmb-68x', '68X', 'DG2612', 'ME', '22', 'https://lh3.googleusercontent.com/CNPqzOqN38dlCU6ANTQaxA_dC7_7YLKXespkQLTf6QhmXr2sS1O9Lh__klNNGNgTn5KifSgPCVJn3D3now=w982-h679-rw-no', '606', 1632691557474, 'zh_hk', 'ma,pak,leung,medicine,mercedes,benz,o305,me22,dg2612,平治,九巴,68x,公路王,馬百良,藥物,八寶鹽蛇散');
INSERT INTO public.search_record VALUES (47587, 'osim', 'OSIM', 'electronic-appliances', 'Electronic Appliances', 'nwfb', 'dennis-trident-10.3m-duple', 'Trident 10.3m (Duple Metsec Body)', 'nwfb-15', '15', 'JJ9818', '33', '14', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', '606', 1632691557474, 'en_us', '按摩椅,osim,massage,chair,15,nwfb,trident,dennis,3314,JJ9818,新巴,15,電器');
INSERT INTO public.search_record VALUES (47587, 'osim', 'OSIM', 'electronic-appliances', '電器', 'nwfb', 'dennis-trident-10.3m-duple', '三叉戟十點三米 (都普車身)', 'nwfb-15', '15', 'JJ9818', '33', '14', 'https://lh3.googleusercontent.com/7Ls_gF7BFHQ0FuiikcMNHaoB0ah5bvQxdcFS-_xSFH7-30cMaP6Bu3ruY2WcoWfqnyQXhPQ3Fl_uG3DCKQ=w987-h679-rw-no', '606', 1632691557474, 'zh_hk', '按摩椅,osim,massage,chair,15,nwfb,trident,dennis,3314,JJ9818,新巴,15,電器');
INSERT INTO public.search_record VALUES (2827, 'pearl-river-bridge', 'Pearl River Bridge Brand', 'food', 'Food', 'kmb', 'volvo-super-olympian-12m', 'Super Olympian 12m', 'cross-harbour-113', '113', 'KP7430', '3ASV', '388', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', '606', 1632691557474, 'en_us', '豉油,珠江橋牌,生抽王,九巴,富豪,超級奧林比安,調味料,3asv388,kp7430,113,volvo,super,olympian,condiment,pearl,river,bridge,soy,sauce');
INSERT INTO public.search_record VALUES (2827, 'pearl-river-bridge', '珠江橋牌', 'food', '食品', 'kmb', 'volvo-super-olympian-12m', '超級奧林比安十二米', 'cross-harbour-113', '113', 'KP7430', '3ASV', '388', 'https://lh3.googleusercontent.com/pGs5NVUD3xREij3elY60bdCFo1oiVJPFIpGLo08g_WccFmZBgdNEf2xMgwEVblu3CVs5yuLhwyRQrUdkWg=w987-h668-rw-no', '606', 1632691557474, 'zh_hk', '豉油,珠江橋牌,生抽王,九巴,富豪,超級奧林比安,調味料,3asv388,kp7430,113,volvo,super,olympian,condiment,pearl,river,bridge,soy,sauce');
INSERT INTO public.search_record VALUES (62737, 'yaohan', 'Yaohan', 'shopping', 'Shopping', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'cross-harbour-105', '105', 'DP8978', 'S3BL', '166', 'https://lh3.googleusercontent.com/4drY_32MJW4X3WqN6RhObKtD8Xq6RSfO_ZX6lXp07uCyD1upm8E9Q1rN7J6WGWN3C4MekeUwyUuLtM65jQ=w982-h690-rw-no', '606', 1632691557474, 'en_us', '八佰伴,百貨公司,利蘭,奧林比安,九巴,105,yaohan,s3bl166,dp8978,leyland,olympian,11m,department,store');
INSERT INTO public.search_record VALUES (62737, 'yaohan', '八佰伴', 'shopping', '購物', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'cross-harbour-105', '105', 'DP8978', 'S3BL', '166', 'https://lh3.googleusercontent.com/4drY_32MJW4X3WqN6RhObKtD8Xq6RSfO_ZX6lXp07uCyD1upm8E9Q1rN7J6WGWN3C4MekeUwyUuLtM65jQ=w982-h690-rw-no', '606', 1632691557474, 'zh_hk', '八佰伴,百貨公司,利蘭,奧林比安,九巴,105,yaohan,s3bl166,dp8978,leyland,olympian,11m,department,store');
INSERT INTO public.search_record VALUES (15195, 'axa-insurance', 'AXA Insurance', 'finance', 'Finance/Insurance', 'nwfb', 'dennis-trident-12m-duple', 'Trident 12m (Duple Metsec Body)', 'cross-harbour-112', '112', 'HY1508', '30', '07', 'https://lh3.googleusercontent.com/ne8YokHGjc5zqOxcbh5uIDdb5ZTVWwfXKOi93ykRU0VUZkdPEmq7la9Cjj1IcAsXJlL-uihNnKNpWHdf8w=w965-h657-rw-no', '606', 1632691557475, 'en_us', '國衛,保險,AXA,丹尼士,三叉戟,都普車身,十二米,112,新巴,dennis,trident,duple,metsec,insurance,3007,hy1508');
INSERT INTO public.search_record VALUES (15195, 'axa-insurance', '國衛保險', 'finance', '金融/保險', 'nwfb', 'dennis-trident-12m-duple', '三叉戟十二米 (都普車身)', 'cross-harbour-112', '112', 'HY1508', '30', '07', 'https://lh3.googleusercontent.com/ne8YokHGjc5zqOxcbh5uIDdb5ZTVWwfXKOi93ykRU0VUZkdPEmq7la9Cjj1IcAsXJlL-uihNnKNpWHdf8w=w965-h657-rw-no', '606', 1632691557475, 'zh_hk', '國衛,保險,AXA,丹尼士,三叉戟,都普車身,十二米,112,新巴,dennis,trident,duple,metsec,insurance,3007,hy1508');
INSERT INTO public.search_record VALUES (65771, 'nestle-ice-cream', 'Nestle Ice Cream', 'food', 'Food', 'ctb', 'dennis-dragon-10.3m-ac', 'Dragon 10.3m Air-Conditioned', 'ctb-7', '7', 'GD5150', '7', '09', 'https://lh3.googleusercontent.com/idBnd1EpQ0Fs3Berqbjs8slNuudLeWhh5uUf4M1coaKuBXGHaF_5aIMPD8woX0cZUpM-88QKAkY-beeEMw=w982-h679-rw-no', '606', 1632691557475, 'en_us', '雀巢雪糕,雪條,城巴,丹尼士,巨龍,甜品,食品,706,gd5150,dennis,dragon,10.3m,nestle,ice,cream,food,ctb,7');
INSERT INTO public.search_record VALUES (65771, 'nestle-ice-cream', '雀巢雪糕', 'food', '食品', 'ctb', 'dennis-dragon-10.3m-ac', '巨龍十點三米空調', 'ctb-7', '7', 'GD5150', '7', '09', 'https://lh3.googleusercontent.com/idBnd1EpQ0Fs3Berqbjs8slNuudLeWhh5uUf4M1coaKuBXGHaF_5aIMPD8woX0cZUpM-88QKAkY-beeEMw=w982-h679-rw-no', '606', 1632691557475, 'zh_hk', '雀巢雪糕,雪條,城巴,丹尼士,巨龍,甜品,食品,706,gd5150,dennis,dragon,10.3m,nestle,ice,cream,food,ctb,7');
INSERT INTO public.search_record VALUES (91301, 'weisen-u', 'Weisen U', 'medicine', 'Medicine', 'cmb', 'metrobus-12m', 'Metrobus 12m', 'cmb-90', '90', 'DA397', 'ML', '32', 'https://lh3.googleusercontent.com/I3uuW2w5zBrXgXZzzddMUY2NHF5ucmLysDkXLJkOdLJCjCj-p7gL-A61XTM6nell7jxSe39bo8LhPzSqHA=w982-h685-rw-no', '606', 1632691557475, 'en_us', '藥物,胃仙U,都城嘉慕,都城型,十二米,90,中巴,胃藥,ml32,da397,weisin,u,medicine,mcw,metrobus,12m');
INSERT INTO public.search_record VALUES (91301, 'weisen-u', '胃仙U', 'medicine', '藥物', 'cmb', 'metrobus-12m', '都城巴士十二米', 'cmb-90', '90', 'DA397', 'ML', '32', 'https://lh3.googleusercontent.com/I3uuW2w5zBrXgXZzzddMUY2NHF5ucmLysDkXLJkOdLJCjCj-p7gL-A61XTM6nell7jxSe39bo8LhPzSqHA=w982-h685-rw-no', '606', 1632691557475, 'zh_hk', '藥物,胃仙U,都城嘉慕,都城型,十二米,90,中巴,胃藥,ml32,da397,weisin,u,medicine,mcw,metrobus,12m');
INSERT INTO public.search_record VALUES (4351, 'madame-tussauds', 'Madame Tussauds', 'others', 'Others', 'nwfb', 'dennis-trident-10.3m-duple', 'Trident 10.3m (Duple Metsec Body)', 'nwfb-15', '15', 'JH6164', '33', '01', 'https://lh3.googleusercontent.com/iMsi4VfSOYlIUeu3qogbZKwLP-Uckuos9OvckLffyBE-l8jYd6vPc4GhfEapE07Tz9T3Ml3995bTZGEeGQ=w982-h674-rw-no', '606', 1632691557475, 'en_us', '杜莎夫人蠟像館,新巴,山頂,丹尼士,三叉戟,都普車身,十點三米,dennis,trident,duple,metsec,madame,tussauds,peak,jh6164,3301,3301L,nwfb,15');
INSERT INTO public.search_record VALUES (4351, 'madame-tussauds', '杜莎夫人蠟像館', 'others', '其他', 'nwfb', 'dennis-trident-10.3m-duple', '三叉戟十點三米 (都普車身)', 'nwfb-15', '15', 'JH6164', '33', '01', 'https://lh3.googleusercontent.com/iMsi4VfSOYlIUeu3qogbZKwLP-Uckuos9OvckLffyBE-l8jYd6vPc4GhfEapE07Tz9T3Ml3995bTZGEeGQ=w982-h674-rw-no', '606', 1632691557475, 'zh_hk', '杜莎夫人蠟像館,新巴,山頂,丹尼士,三叉戟,都普車身,十點三米,dennis,trident,duple,metsec,madame,tussauds,peak,jh6164,3301,3301L,nwfb,15');
INSERT INTO public.search_record VALUES (61382, 'laser-computer', 'Laser Computer', 'computer', 'Computer', 'kmb', 'leyland-olympian-11m', 'Olympian 11.3m', 'kmb-6a', '6A', 'DT5392', 'S3BL', '213', 'https://lh3.googleusercontent.com/rXP3OrZryrT0-clTBrfonsprM__0yTudhJXO4DTl9VycFraG1-e_I5Y4LYa5yh6MTNCa96lw7u7_kFmvXQ=w982-h685-rw-no', '606', 1632691557475, 'en_us', '電腦,鐳射,家用電腦,利蘭,奧林比安,九巴,6A,dt5392,s3bl213,leyland,olympian,11m,laser,computer,kmb');
INSERT INTO public.search_record VALUES (61382, 'laser-computer', '鐳射電腦', 'computer', '電腦', 'kmb', 'leyland-olympian-11m', '奧林比安十一點三米', 'kmb-6a', '6A', 'DT5392', 'S3BL', '213', 'https://lh3.googleusercontent.com/rXP3OrZryrT0-clTBrfonsprM__0yTudhJXO4DTl9VycFraG1-e_I5Y4LYa5yh6MTNCa96lw7u7_kFmvXQ=w982-h685-rw-no', '606', 1632691557476, 'zh_hk', '電腦,鐳射,家用電腦,利蘭,奧林比安,九巴,6A,dt5392,s3bl213,leyland,olympian,11m,laser,computer,kmb');
INSERT INTO public.search_record VALUES (95818, 'principal-mpf', 'Principal MPF', 'finance', 'Finance/Insurance', 'kmb', 'dennis-trident-12m-alexander', 'Trident 12m (Alexendar Body)', 'cross-harbour-112', '112', 'HW6015', 'ATR', '27', 'https://lh3.googleusercontent.com/tW3ZBFHxF8_YXBQB_rq1Pkk4f45bPCfaWwpFB2-oZan_Wk5iBQzJ4ifcx5HmlBOhMRlk-zCPbFkebEHsMA=w987-h674-rw-no', '606', 1632691557476, 'en_us', '信安,強積金,丹尼士,三叉戟,十二米,九巴,亞歷山大車身,保險,金融,dennis,trident,12m,kmb,112,atr27,hw6015,alexendar,principal,mpf,finance');
INSERT INTO public.search_record VALUES (95818, 'principal-mpf', '信安強積金', 'finance', '金融/保險', 'kmb', 'dennis-trident-12m-alexander', '三叉戟十二米 (亞歷山大車身)', 'cross-harbour-112', '112', 'HW6015', 'ATR', '27', 'https://lh3.googleusercontent.com/tW3ZBFHxF8_YXBQB_rq1Pkk4f45bPCfaWwpFB2-oZan_Wk5iBQzJ4ifcx5HmlBOhMRlk-zCPbFkebEHsMA=w987-h674-rw-no', '606', 1632691557476, 'zh_hk', '信安,強積金,丹尼士,三叉戟,十二米,九巴,亞歷山大車身,保險,金融,dennis,trident,12m,kmb,112,atr27,hw6015,alexendar,principal,mpf,finance');
INSERT INTO public.search_record VALUES (65701, 'red-marubean-brand', 'Red Marubean Brand', 'food', 'Food', 'nwfb', 'dennis-trident-12m-alexander', 'Trident 12m (Alexendar Body)', 'cross-harbour-101', '101', 'HY2877', '11', '09', 'https://lh3.googleusercontent.com/XUkiQTB5nvqyqvjeNT7hGLVzJjGANs7-kvnKuZExWmRBOusC3lSTxr3Vp2hiqFjNNqhFZAyI2FDlircb5g=w982-h685-rw-no', '606', 1632691557476, 'en_us', '紅圈牌,食品,罐頭,丹尼士,三叉戟,十二米,亞歷山大車身,新巴,nwfb,dennis,trident,12m,101,hy2877,1109,red,marubean,brand,canned,food');
INSERT INTO public.search_record VALUES (65701, 'red-marubean-brand', '紅圈牌', 'food', '食品', 'nwfb', 'dennis-trident-12m-alexander', '三叉戟十二米 (亞歷山大車身)', 'cross-harbour-101', '101', 'HY2877', '11', '09', 'https://lh3.googleusercontent.com/XUkiQTB5nvqyqvjeNT7hGLVzJjGANs7-kvnKuZExWmRBOusC3lSTxr3Vp2hiqFjNNqhFZAyI2FDlircb5g=w982-h685-rw-no', '606', 1632691557476, 'zh_hk', '紅圈牌,食品,罐頭,丹尼士,三叉戟,十二米,亞歷山大車身,新巴,nwfb,dennis,trident,12m,101,hy2877,1109,red,marubean,brand,canned,food');
INSERT INTO public.search_record VALUES (28893, 'thai-airways', 'Thai Airways', 'airline', 'Airline Company', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'cross-harbour-182', '182', 'GP8266', '8', '50', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', '606', 1632691557476, 'en_us', '航空公司,泰國航空,丹尼士,巨龍,十二米,空調,城巴,182,airline,company,thai,airways,dennis,dragon,12m,ctb,gp8266,850');
INSERT INTO public.search_record VALUES (28893, 'thai-airways', '泰國航空', 'airline', '航空公司', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'cross-harbour-182', '182', 'GP8266', '8', '50', 'https://lh3.googleusercontent.com/y96X7t7dbh_VTjxjuLuVaThFU7B5mnZ6n98JHNsZgTvQ6e8J7opdQWm-bqeWsDYP-vW0NUxwB6Qg7EOUFw=w982-h652-rw-no', '606', 1632691557476, 'zh_hk', '航空公司,泰國航空,丹尼士,巨龍,十二米,空調,城巴,182,airline,company,thai,airways,dennis,dragon,12m,ctb,gp8266,850');
INSERT INTO public.search_record VALUES (50753, 'japan-home', 'Japan Home', 'shopping', 'Shopping', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'ctb-8x', '8X', 'GH469', '8', '12', 'https://lh3.googleusercontent.com/C_2zBX0b-YArauu1RqTvJNY63aVIrdHht-LFzbCzjrPKmfIVXID_Pc6WZvedAVRNnLq952aGX4ZA3NPdtw=w987-h674-rw-no', '606', 1632691557476, 'en_us', '日本城,購物,城巴,丹尼士,巨龍,十二米,巴士,空調,8x,dennis,dragon,12m,japan,home,city,shopping,gh469,812');
INSERT INTO public.search_record VALUES (50753, 'japan-home', '日本城', 'shopping', '購物', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'ctb-8x', '8X', 'GH469', '8', '12', 'https://lh3.googleusercontent.com/C_2zBX0b-YArauu1RqTvJNY63aVIrdHht-LFzbCzjrPKmfIVXID_Pc6WZvedAVRNnLq952aGX4ZA3NPdtw=w987-h674-rw-no', '606', 1632691557476, 'zh_hk', '日本城,購物,城巴,丹尼士,巨龍,十二米,巴士,空調,8x,dennis,dragon,12m,japan,home,city,shopping,gh469,812');
INSERT INTO public.search_record VALUES (31120, 'body-shop', 'Body Shop', 'cosmetics', 'Cosmetics', 'kmb', 'super-olympian-12m-wright-body', 'Super Olympian 12m (Wright Body)', 'cross-harbour-101', '101', 'LZ8705', 'AVW', '92', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', '606', 1632691557476, 'en_us', 'body,shop,volvo,super,olympian,wright,body,九巴,富豪,前衛巴士,十二米,化妝品,沐浴露,LZ8705,avw92,101');
INSERT INTO public.search_record VALUES (31120, 'body-shop', 'Body Shop', 'cosmetics', '化妝品', 'kmb', 'super-olympian-12m-wright-body', '前衛巴士', 'cross-harbour-101', '101', 'LZ8705', 'AVW', '92', 'https://lh3.googleusercontent.com/-l28NGbARjnjqnlkcuy_5UNFskHkbw3Lz0W5QvX0VwwxipWnAJMJW4uHHKaIBOrdAC9UVOUazwFTcgu9yw=w976-h685-rw-no', '606', 1632691557476, 'zh_hk', 'body,shop,volvo,super,olympian,wright,body,九巴,富豪,前衛巴士,十二米,化妝品,沐浴露,LZ8705,avw92,101');
INSERT INTO public.search_record VALUES (62803, 'wwpkg', 'WWPKG', 'tourism', 'Travel Agency/Tourism', 'nwfb', 'dennis-trident-12m-duple', 'Trident 12m (Duple Metsec Body)', 'cross-harbour-112', '112', 'HY2357', '30', '05', 'https://lh3.googleusercontent.com/k5bJArW9ZujUc14v7StPd48tGuiJ7i3ci291IShN4QLK5xVm_ad7TMIMa6EjabQdeP8fwQtksdVcW4JTKQ=w987-h685-rw-no', '606', 1632691557476, 'en_us', 'wwpkg,nwfb,dennis,trident,duple,metsec,body,112,hy2357,3005,緃橫遊,旅行社,丹尼士,三叉戟,十二米,都普車身,新巴');
INSERT INTO public.search_record VALUES (62803, 'wwpkg', '緃橫遊', 'tourism', '旅行社/旅遊', 'nwfb', 'dennis-trident-12m-duple', '三叉戟十二米 (都普車身)', 'cross-harbour-112', '112', 'HY2357', '30', '05', 'https://lh3.googleusercontent.com/k5bJArW9ZujUc14v7StPd48tGuiJ7i3ci291IShN4QLK5xVm_ad7TMIMa6EjabQdeP8fwQtksdVcW4JTKQ=w987-h685-rw-no', '606', 1632691557476, 'zh_hk', 'wwpkg,nwfb,dennis,trident,duple,metsec,body,112,hy2357,3005,緃橫遊,旅行社,丹尼士,三叉戟,十二米,都普車身,新巴');
INSERT INTO public.search_record VALUES (24576, 'lj-hooker', 'L.J. Hooker', 'real-estate', 'Real Estate', 'kmb', 'dennis-dragon-11m-ac', 'Dragon/Condor 11m Air-Conditioned', 'kmb-68m', '68M', 'FC5745', 'AD', '51', 'https://lh3.googleusercontent.com/Gn_BImdwwNhPekt4OxRVjNjC8djZUYyleAVLNLVX6sSXQy990-DNqiPRCPthllslY5mOffaUlYG6k8wmcw=w987-h668-rw-no', '606', 1632691557476, 'en_us', 'lj,hooker,dennis,dragon,11m,ad51,fc5745,kmb,68m,real,estate,agency,六正行,地產經紀行,九巴,丹尼士,巨龍,十一米,空調');
INSERT INTO public.search_record VALUES (24576, 'lj-hooker', '六正行', 'real-estate', '地產', 'kmb', 'dennis-dragon-11m-ac', '巨龍/禿鷹十一米空調', 'kmb-68m', '68M', 'FC5745', 'AD', '51', 'https://lh3.googleusercontent.com/Gn_BImdwwNhPekt4OxRVjNjC8djZUYyleAVLNLVX6sSXQy990-DNqiPRCPthllslY5mOffaUlYG6k8wmcw=w987-h668-rw-no', '606', 1632691557476, 'zh_hk', 'lj,hooker,dennis,dragon,11m,ad51,fc5745,kmb,68m,real,estate,agency,六正行,地產經紀行,九巴,丹尼士,巨龍,十一米,空調');
INSERT INTO public.search_record VALUES (22658, 'unicef', 'UNICEF', 'government', 'Government/Public Organizations', 'kmb', 'dennis-dragon-11m', 'Dragon 11.3m', 'kmb-43b', '43B', 'GA5685', 'S3N', '365', 'https://lh3.googleusercontent.com/YFYgSVXmoSw_al6BU-RC4dmxNCupDKIzsVYBA0pfB9cpU_dPqxn41568UrX6o20ePBYPmGh3E7QkOzPknw=w982-h696-rw-no', '606', 1632691557476, 'en_us', 'unicef,charity,organization,dennis,dragon,11m,s3n365,ga5685,kmb,聯合國,兒童基金會,丹尼士,巨龍,十一米,開龍,慈善機構,九巴');
INSERT INTO public.search_record VALUES (22658, 'unicef', '聯合國兒童基金會', 'government', '政府/公共機構', 'kmb', 'dennis-dragon-11m', '巨龍十一點三米', 'kmb-43b', '43B', 'GA5685', 'S3N', '365', 'https://lh3.googleusercontent.com/YFYgSVXmoSw_al6BU-RC4dmxNCupDKIzsVYBA0pfB9cpU_dPqxn41568UrX6o20ePBYPmGh3E7QkOzPknw=w982-h696-rw-no', '606', 1632691557476, 'zh_hk', 'unicef,charity,organization,dennis,dragon,11m,s3n365,ga5685,kmb,聯合國,兒童基金會,丹尼士,巨龍,十一米,開龍,慈善機構,九巴');
INSERT INTO public.search_record VALUES (19356, 'nwfb', 'New World First Bus', 'bus-company', 'Bus Company', 'nwfb', 'dennis-trident-12m-alexander', 'Trident 12m (Alexendar Body)', 'cross-harbour-680', '680', 'JB9058', '11', '68', 'https://lh3.googleusercontent.com/HGw_e0XkGQobb85BcBnPq06kRhZU46-4GXpnCwgFuVqOVxaK0slj5TWGQdLcJ6Nm1R-oEAMm4LiV7MedOg=w982-h679-rw-no', '606', 1632691557476, 'en_us', '上樂新巴,水果,丹尼士,三叉戟,十二米,亞歷山大車身,680,nwfb,fruit,dennis,trident,12m,alexander,body,jb9058,1168');
INSERT INTO public.search_record VALUES (19356, 'nwfb', '上樂新巴', 'bus-company', '巴士公司', 'nwfb', 'dennis-trident-12m-alexander', '三叉戟十二米 (亞歷山大車身)', 'cross-harbour-680', '680', 'JB9058', '11', '68', 'https://lh3.googleusercontent.com/HGw_e0XkGQobb85BcBnPq06kRhZU46-4GXpnCwgFuVqOVxaK0slj5TWGQdLcJ6Nm1R-oEAMm4LiV7MedOg=w982-h679-rw-no', '606', 1632691557476, 'zh_hk', '上樂新巴,水果,丹尼士,三叉戟,十二米,亞歷山大車身,680,nwfb,fruit,dennis,trident,12m,alexander,body,jb9058,1168');
INSERT INTO public.search_record VALUES (50517, 'united-daily-news', 'United Daily News', 'mass-media', 'Mass Media', 'cmb', 'leyland-fleetline-long', 'Fleetline (Long Version)', 'nwfb-42', '42', 'BR5378', 'LF', '213', 'https://lh3.googleusercontent.com/0GwBn8dsoNPnkHEIE6HpQy4aZdbGwRk2Zr4UtxoBwbd1cRVB7zQhqwPsxSO3VcH1b3YO8JY5Pw0RFnA1Tg=w976-h690-rw-no', '606', 1632691557476, 'en_us', '香港聯合報,丹拿,利蘭,報紙,傳媒,報章,珍寶巴士,長陣,中巴,42,united,daily,news,newspaper,mass,media,lf213,br5378,leyland,daimler,fleetline,cmb');
INSERT INTO public.search_record VALUES (50517, 'united-daily-news', '香港聯合報', 'mass-media', '傳媒', 'cmb', 'leyland-fleetline-long', '長陣珍寶 (亞歷山大車身)', 'nwfb-42', '42', 'BR5378', 'LF', '213', 'https://lh3.googleusercontent.com/0GwBn8dsoNPnkHEIE6HpQy4aZdbGwRk2Zr4UtxoBwbd1cRVB7zQhqwPsxSO3VcH1b3YO8JY5Pw0RFnA1Tg=w976-h690-rw-no', '606', 1632691557476, 'zh_hk', '香港聯合報,丹拿,利蘭,報紙,傳媒,報章,珍寶巴士,長陣,中巴,42,united,daily,news,newspaper,mass,media,lf213,br5378,leyland,daimler,fleetline,cmb');
INSERT INTO public.search_record VALUES (38859, 'king-to-nin-jiom', 'King To Nin Jiom', 'medicine', 'Medicine', 'cmb', 'dennis-dragon-11m-ac', 'Dragon/Condor 11m Air-Conditioned', 'cross-harbour-105', '105', 'HA9736', 'DA', '73', 'https://lh3.googleusercontent.com/_OVFvvcvqXLWvxSqXRhfRNF7upj_YglLKno8De74nVZYNkKGYhj09e2ozFFfapZMne4toYIzkrCPRaov_g=w982-h679-rw-no', '606', 1632691557476, 'en_us', 'medicine,king,to,nin,jiom,藥物,京都念慈菴,川貝枇杷膏,丹尼士,巨龍,ha9736,da73,105,dennis,dragon,11m,cmb,中巴');
INSERT INTO public.search_record VALUES (38859, 'king-to-nin-jiom', '京都念慈菴', 'medicine', '藥物', 'cmb', 'dennis-dragon-11m-ac', '巨龍/禿鷹十一米空調', 'cross-harbour-105', '105', 'HA9736', 'DA', '73', 'https://lh3.googleusercontent.com/_OVFvvcvqXLWvxSqXRhfRNF7upj_YglLKno8De74nVZYNkKGYhj09e2ozFFfapZMne4toYIzkrCPRaov_g=w982-h679-rw-no', '606', 1632691557476, 'zh_hk', 'medicine,king,to,nin,jiom,藥物,京都念慈菴,川貝枇杷膏,丹尼士,巨龍,ha9736,da73,105,dennis,dragon,11m,cmb,中巴');
INSERT INTO public.search_record VALUES (30088, 'china-insurance', 'China Insurance', 'finance', 'Finance/Insurance', 'ctb', 'leyland-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'ctb-5b', '5B', 'FR5220', '3', '68', 'https://lh3.googleusercontent.com/EO6r25d1p7VHvIOhB0RsdyBPO8JfwRwq7ZLkMchfvfbtvQicwzlsf23yPiEB778qkziQfKDBXJbhHiS_Cg=w982-h674-rw-no', '606', 1632691557476, 'en_us', 'china,insurance,finance,leyland,olympian,12m,368,fr5220,ctb,5b,中國保險,保險公司,金融,利蘭,奧林比安,十二米,空調,城巴');
INSERT INTO public.search_record VALUES (30088, 'china-insurance', '中國保險', 'finance', '金融/保險', 'ctb', 'leyland-olympian-12m-ac', '奧林比安十二米空調', 'ctb-5b', '5B', 'FR5220', '3', '68', 'https://lh3.googleusercontent.com/EO6r25d1p7VHvIOhB0RsdyBPO8JfwRwq7ZLkMchfvfbtvQicwzlsf23yPiEB778qkziQfKDBXJbhHiS_Cg=w982-h674-rw-no', '606', 1632691557476, 'zh_hk', 'china,insurance,finance,leyland,olympian,12m,368,fr5220,ctb,5b,中國保險,保險公司,金融,利蘭,奧林比安,十二米,空調,城巴');
INSERT INTO public.search_record VALUES (66914, 'shell-motor-oil', 'Shell Motor Oil', 'petroleum', 'Petroleum', 'ctb', 'dennis-dragon-12m-ac', 'Dragon 12m Air-Conditioned', 'ctb-8x', '8X', 'GL4186', '8', '24', 'https://lh3.googleusercontent.com/FktZBoZBf0_FKUiyeVetDewJMOmszfVvtcJfh4FbirhK-zrKh2HWwgGOxnVXgmLToTfCKeuDbMgLOZFDVg=w982-h685-rw-no', '606', 1632691557476, 'en_us', 'shell,motor,oil,petroleum,gas,gl4186,8x,824,機油,蜆殼,丹尼士,巨龍,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (66914, 'shell-motor-oil', '蜆殼機油', 'petroleum', '石油', 'ctb', 'dennis-dragon-12m-ac', '巨龍十二米空調', 'ctb-8x', '8X', 'GL4186', '8', '24', 'https://lh3.googleusercontent.com/FktZBoZBf0_FKUiyeVetDewJMOmszfVvtcJfh4FbirhK-zrKh2HWwgGOxnVXgmLToTfCKeuDbMgLOZFDVg=w982-h685-rw-no', '606', 1632691557477, 'zh_hk', 'shell,motor,oil,petroleum,gas,gl4186,8x,824,機油,蜆殼,丹尼士,巨龍,dennis,dragon,12m');
INSERT INTO public.search_record VALUES (97312, 'wonderful-arts-wedding', 'Wonderful Arts Wedding Services', 'photography', 'Photography', 'kmb', 'dennis-trident-12m-alexander', 'Trident 12m (Alexendar Body)', 'kmb-1a', '1A', 'HJ2127', 'ATR', '1', 'https://lh3.googleusercontent.com/DWxAyyz-hLLfii3raxDmVwkCPxXmHfLQkTxkLakTqPnE-YOyXVYF6yIF-8QLhixEEfbThPLUYMMuJYhEMg=w982-h679-rw-no', '606', 1632691557477, 'en_us', '新天地,婚紗攝影,結婚,丹尼士,三叉戟,樣板車,hj2127,atr1,1a,kmb,亞歷山大車身,十二米,dennis,trident');
INSERT INTO public.search_record VALUES (97312, 'wonderful-arts-wedding', '新天地婚紗攝影', 'photography', '攝影', 'kmb', 'dennis-trident-12m-alexander', '三叉戟十二米 (亞歷山大車身)', 'kmb-1a', '1A', 'HJ2127', 'ATR', '1', 'https://lh3.googleusercontent.com/DWxAyyz-hLLfii3raxDmVwkCPxXmHfLQkTxkLakTqPnE-YOyXVYF6yIF-8QLhixEEfbThPLUYMMuJYhEMg=w982-h679-rw-no', '606', 1632691557477, 'zh_hk', '新天地,婚紗攝影,結婚,丹尼士,三叉戟,樣板車,hj2127,atr1,1a,kmb,亞歷山大車身,十二米,dennis,trident');
INSERT INTO public.search_record VALUES (28302, 'goldstar-cassette', 'Goldstar Cassette', 'computer', 'Computer', 'kmb', 'volvo-olympian-12m-ac', 'Olympian 12m Air-Conditioned', 'cross-harbour-112', '112', 'GC7236', '3AV', '42', 'https://lh3.googleusercontent.com/VLW_xTBV9XKqI26uLnjp_dzb0I6Pkc8VWjE9_3ZCOvsBzvzpfZ9Lts20VlMypirrTYzJZKAQdyMYNWJaYQ=w982-h674-rw-no', '606', 1632691557477, 'en_us', '金星,錄影帶,磁碟,電腦,富豪,奧林比安,十二米,空調,九巴,goldstar,vhs,floppy,cassette,disk,computer,kmb,112,volvo,olympian,gc7236,3av42,12m');
INSERT INTO public.search_record VALUES (28302, 'goldstar-cassette', '金星卡式磁帶', 'computer', '電腦', 'kmb', 'volvo-olympian-12m-ac', '奧林比安十二米空調', 'cross-harbour-112', '112', 'GC7236', '3AV', '42', 'https://lh3.googleusercontent.com/VLW_xTBV9XKqI26uLnjp_dzb0I6Pkc8VWjE9_3ZCOvsBzvzpfZ9Lts20VlMypirrTYzJZKAQdyMYNWJaYQ=w982-h674-rw-no', '606', 1632691557477, 'zh_hk', '金星,錄影帶,磁碟,電腦,富豪,奧林比安,十二米,空調,九巴,goldstar,vhs,floppy,cassette,disk,computer,kmb,112,volvo,olympian,gc7236,3av42,12m');
INSERT INTO public.search_record VALUES (18627, 'fuji-vhs-tape', 'Fujifilm VHS Tape', 'computer', 'Computer', 'cmb', 'metrobus-12m', 'Metrobus 12m', 'cross-harbour-101', '101', 'DT9375', 'ML', '49', 'https://lh3.googleusercontent.com/B1oL30QNkKnHzhJyvLU23pSfSUr6vpdySqq-pejXRAsQTq4YrzYnP9qS_QPGD4F5zz0FiOrYjNohvk9KdA=w987-h679-rw-no', '606', 1632691557477, 'en_us', '富士,錄影帶,電腦,卡式帶,都城嘉慕,都城型,十二米,中巴,101,cmb,fuji,vhs,tape,metrobus,mcw,12m,ml49,dt9375');
INSERT INTO public.search_record VALUES (18627, 'fuji-vhs-tape', '富士錄影帶', 'computer', '電腦', 'cmb', 'metrobus-12m', '都城巴士十二米', 'cross-harbour-101', '101', 'DT9375', 'ML', '49', 'https://lh3.googleusercontent.com/B1oL30QNkKnHzhJyvLU23pSfSUr6vpdySqq-pejXRAsQTq4YrzYnP9qS_QPGD4F5zz0FiOrYjNohvk9KdA=w987-h679-rw-no', '606', 1632691557477, 'zh_hk', '富士,錄影帶,電腦,卡式帶,都城嘉慕,都城型,十二米,中巴,101,cmb,fuji,vhs,tape,metrobus,mcw,12m,ml49,dt9375');
INSERT INTO public.search_record VALUES (9839, 'san-shui-wah-seafood-restaurant', 'San Shui Wah Seafood Restaurant', 'restaurants', 'Restaurants', 'kmb', 'dennis-dragon-11m', 'Dragon 11.3m', 'kmb-70r', '70R', 'FZ7935', 'S3N', '338', 'https://lh3.googleusercontent.com/RQk4Gc1kkk54XbgB1W6FMNHvPOVCWHdr3iOYTmJsb-_WwBpht0vvAmudskdQIBBHQBZZmQxxIhb1_BqUMw=w971-h685-rw-no', '606', 1632691557477, 'en_us', '新瑞華,海鮮酒家,燒味,丹尼士,巨龍,十一米,閉龍,九巴,70r,s3n338,fz7935,dennis,dragon,11m,san,shui,wah,seafood,restaurant,siu,mei');
INSERT INTO public.search_record VALUES (9839, 'san-shui-wah-seafood-restaurant', '新瑞華海鮮酒家', 'restaurants', '餐廳', 'kmb', 'dennis-dragon-11m', '巨龍十一點三米', 'kmb-70r', '70R', 'FZ7935', 'S3N', '338', 'https://lh3.googleusercontent.com/RQk4Gc1kkk54XbgB1W6FMNHvPOVCWHdr3iOYTmJsb-_WwBpht0vvAmudskdQIBBHQBZZmQxxIhb1_BqUMw=w971-h685-rw-no', '606', 1632691557477, 'zh_hk', '新瑞華,海鮮酒家,燒味,丹尼士,巨龍,十一米,閉龍,九巴,70r,s3n338,fz7935,dennis,dragon,11m,san,shui,wah,seafood,restaurant,siu,mei');


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES ('6914e44f-a64f-481a-b57c-e37367b733ae', 'admin', 'test_hash', 'admin', NULL, NULL);
INSERT INTO public."user" VALUES ('6f3a2920-ab89-4cb9-94e3-42720116e2b7', 'KR7295', 'placeholder-hash', 'user', '2021-09-26', '2021-09-26');
INSERT INTO public."user" VALUES ('625807f1-2994-4845-862f-8e9c6b4d17cb', '606', 'placeholder-hash', 'user', '2021-09-26', '2021-09-26');


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
-- Name: search_record search_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.search_record
    ADD CONSTRAINT search_record_pkey PRIMARY KEY (photo_short_id, language);


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
-- Name: TABLE search_record; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.search_record TO hkadbus2;


--
-- Name: TABLE "user"; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public."user" TO hkadbus2;

--
-- PostgreSQL database dump complete
--

