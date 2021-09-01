/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/6/18 15:53:46                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists company;

drop table if exists empl_control;

drop table if exists employee;

drop table if exists evaluate;

drop table if exists resume;

drop table if exists user_control;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   ad_id                varchar(50) not null,
   ad_pwd               varchar(50),
   primary key (ad_id)
);

/*==============================================================*/
/* Table: company                                               */
/*==============================================================*/
create table company
(
   company_id           varchar(50) not null,
   company_name         varchar(50),
   company_pwd          varchar(50),
   company_email        varchar(50),
   company_tel          varchar(50),
   primary key (company_id)
);

/*==============================================================*/
/* Table: empl_control                                          */
/*==============================================================*/
create table empl_control
(
   ad_id                varchar(50) not null,
   empl_id              int not null,
   primary key (ad_id, empl_id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   empl_id              int not null auto_increment,
   resu_id              int,
   company_id           varchar(50),
   empl_name            varchar(60),
   empl_sex             varchar(4),
   empl_email           varchar(50),
   empl_tel             varchar(50),
   age                  int,
   salary               numeric(10,2),
   position             varchar(60),
   primary key (empl_id)
);

/*==============================================================*/
/* Table: evaluate                                              */
/*==============================================================*/
create table evaluate
(
   eval_id              int not null auto_increment,
   company_id           varchar(50),
   resu_id              int,
   eval_content         varchar(255),
   primary key (eval_id)
);

/*==============================================================*/
/* Table: resume                                                */
/*==============================================================*/
create table resume
(
   resu_id              int not null auto_increment,
   empl_id              int,
   content              varchar(255),
   primary key (resu_id)
);

/*==============================================================*/
/* Table: user_control                                          */
/*==============================================================*/
create table user_control
(
   ad_id                varchar(50) not null,
   company_id           varchar(50) not null,
   primary key (ad_id, company_id)
);

alter table empl_control add constraint FK_empl_control foreign key (ad_id)
      references admin (ad_id) on delete restrict on update restrict;

alter table empl_control add constraint FK_empl_control2 foreign key (empl_id)
      references employee (empl_id) on delete restrict on update restrict;

alter table employee add constraint FK_own foreign key (resu_id)
      references resume (resu_id) on delete restrict on update restrict;

alter table employee add constraint FK_work foreign key (company_id)
      references company (company_id) on delete restrict on update restrict;

alter table evaluate add constraint FK_resume_evaluate foreign key (resu_id)
      references resume (resu_id) on delete restrict on update restrict;

alter table evaluate add constraint FK_user_evaluate foreign key (company_id)
      references company (company_id) on delete restrict on update restrict;

alter table resume add constraint FK_own2 foreign key (empl_id)
      references employee (empl_id) on delete restrict on update restrict;

alter table user_control add constraint FK_user_control foreign key (ad_id)
      references admin (ad_id) on delete restrict on update restrict;

alter table user_control add constraint FK_user_control2 foreign key (company_id)
      references company (company_id) on delete restrict on update restrict;

