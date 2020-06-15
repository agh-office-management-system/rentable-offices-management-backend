DROP TABLE IF EXISTS public.message;
DROP TABLE IF EXISTS public.notification;

CREATE TABLE public.message
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    content character varying(255) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    sender character varying(255) COLLATE pg_catalog."default",
    read boolean NOT NULL,
    receiver character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT message_pkey PRIMARY KEY (id)
);

CREATE TABLE public.notification
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_at timestamp without time zone,
    sender character varying(255) COLLATE pg_catalog."default",
    params character varying(255) COLLATE pg_catalog."default",
    receiver character varying(255) COLLATE pg_catalog."default",
    notification_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT notification_pkey PRIMARY KEY (id)
);