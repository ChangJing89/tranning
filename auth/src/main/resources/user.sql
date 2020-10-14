create table if not exists t_base_user (
id varchar(255) primary key not null,
username varchar(30) not null,
password varchar(100) not null ,
is_account_non_expired bit(1) not null,
is_account_non_locked bit(1) not null ,
is_credentials_non_expired bit(1) not null ,
is_enabled bit(1) not null ,
version bigint(20) default null
);

insert into t_base_user (id, username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired,is_enabled,version)
values ("ff8081816fa6e534016fa6e6adbd0000","admin","$2a$10$boK9yc1Q8Pjjh5nLs9iybuloc6tBXfAnslnxsvtPllALeFhwy80Le",true,true,true,true ,0);