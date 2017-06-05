drop schema if exists `ippie-db`;
  create schema if not exists `ippie-db-test`;
  use `ippie-db-test`;

  drop table if exists type;
  drop table if exists model;
  drop table if exists state;
  drop table if exists component;

  create table type (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_type primary key (id))
  ;

  create table model (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    type_id                   bigint default NULL,
    constraint pk_model primary key (id))
  ;

  create table state (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_state primary key (id))
  ;

  create table component (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                date NULL,
    discontinued              date NULL,
    state_id                  bigint default NULL,
    state_details             varchar(255),
    model_id                  bigint default NULL,
    details                   text,
    constraint pk_component primary key (id))
  ;

  alter table model add constraint fk_model_type_1 foreign key (type_id) references type (id) on delete restrict on update restrict;

  alter table component add constraint fk_component_state_1 foreign key (state_id) references state (id) on delete restrict on update restrict;
  alter table component add constraint fk_component_model_1 foreign key (model_id) references model (id) on delete restrict on update restrict;
	
/*  CREATE INDEX computer_name_asc ON computer (name ASC);
  CREATE INDEX computer_name_desc ON computer (name DESC);

  CREATE INDEX computer_company_asc ON computer (company_id ASC);
  CREATE INDEX computer_company_desc ON computer (company_id DESC);

  CREATE INDEX company_name_asc ON company (name ASC);
  CREATE INDEX company_name_desc ON company (name DESC);

  CREATE INDEX user_name_asc ON users (username ASC);
  CREATE INDEX user_name_desc ON users (username DESC);
*/ 

