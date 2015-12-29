# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alumno (
  expediente                    bigint not null,
  nombre                        varchar(255),
  apellido1                     varchar(255),
  apellido2                     varchar(255),
  constraint pk_alumno primary key (expediente)
);
create sequence alumno_seq;

create table alumno_asignatura (
  alumno_expediente             bigint not null,
  asignatura_id                 bigint not null,
  constraint pk_alumno_asignatura primary key (alumno_expediente,asignatura_id)
);

create table asignatura (
  id                            bigint not null,
  nombre                        varchar(255),
  curso                         integer,
  profesor_id                   bigint,
  constraint pk_asignatura primary key (id)
);
create sequence asignatura_seq;

create table profesor (
  id                            bigint not null,
  nombre                        varchar(255),
  apellido1                     varchar(255),
  apellido2                     varchar(255),
  constraint pk_profesor primary key (id)
);
create sequence profesor_seq;

alter table alumno_asignatura add constraint fk_alumno_asignatura_alumno foreign key (alumno_expediente) references alumno (expediente) on delete restrict on update restrict;
create index ix_alumno_asignatura_alumno on alumno_asignatura (alumno_expediente);

alter table alumno_asignatura add constraint fk_alumno_asignatura_asignatura foreign key (asignatura_id) references asignatura (id) on delete restrict on update restrict;
create index ix_alumno_asignatura_asignatura on alumno_asignatura (asignatura_id);

alter table asignatura add constraint fk_asignatura_profesor_id foreign key (profesor_id) references profesor (id) on delete restrict on update restrict;
create index ix_asignatura_profesor_id on asignatura (profesor_id);


# --- !Downs

alter table alumno_asignatura drop constraint if exists fk_alumno_asignatura_alumno;
drop index if exists ix_alumno_asignatura_alumno;

alter table alumno_asignatura drop constraint if exists fk_alumno_asignatura_asignatura;
drop index if exists ix_alumno_asignatura_asignatura;

alter table asignatura drop constraint if exists fk_asignatura_profesor_id;
drop index if exists ix_asignatura_profesor_id;

drop table if exists alumno;
drop sequence if exists alumno_seq;

drop table if exists alumno_asignatura;

drop table if exists asignatura;
drop sequence if exists asignatura_seq;

drop table if exists profesor;
drop sequence if exists profesor_seq;

