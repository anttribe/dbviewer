/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/31 ������ 20:56:22                      */
/*==============================================================*/


drop table if exists t_datasource_info;

/*==============================================================*/
/* Table: t_datasource_info                                     */
/*==============================================================*/
create table t_datasource_info
(
   id                   varchar(32) not null comment 'id���',
   alias                varchar(64) comment '����',
   dialect              varchar(32) comment '���ݿⷽ��',
   conn_url             varchar(256) comment '���ݿ�����url',
   username             varchar(64) comment '�û���',
   password             varchar(64) comment '����',
   primary key (id)
);

alter table t_datasource_info comment '����Դ��Ϣ��';

