/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/31 星期四 20:56:22                      */
/*==============================================================*/


drop table if exists t_datasource_info;

/*==============================================================*/
/* Table: t_datasource_info                                     */
/*==============================================================*/
create table t_datasource_info
(
   id                   varchar(32) not null comment 'id编号',
   alias                varchar(64) comment '别名',
   dialect              varchar(32) comment '数据库方言',
   conn_url             varchar(256) comment '数据库连接url',
   username             varchar(64) comment '用户名',
   password             varchar(64) comment '密码',
   primary key (id)
);

alter table t_datasource_info comment '数据源信息表';

