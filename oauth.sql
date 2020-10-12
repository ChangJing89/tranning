CREATE DATABASE IF NOT EXISTS s_base DEFAULT CHARSET utf8;

USE s_base;

-- ----------------------------
-- Table structure for oauth_client_details 将请求的路径存在数据表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY comment '用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的',
  resource_ids VARCHAR(256) comment '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,当注册客户端时,根据实际需要可选择资源id,也可根据不同的注册流程,赋予对应的资源id',
  client_secret VARCHAR(256) comment '用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的.',
  scope VARCHAR(256) comment '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔',
  authorized_grant_types VARCHAR(256) comment '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔',
  web_server_redirect_uri VARCHAR(256) comment '客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.',
  authorities VARCHAR(256) comment '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔',
  access_token_validity INTEGER comment '设定客户端的access_token的有效时间值(单位:秒),可选',
  refresh_token_validity INTEGER comment '设定客户端的refresh_token的有效时间值(单位:秒),可选',
  additional_information VARCHAR(4096) comment '预留的字段,在Oauth的流程中没有实际的使用,可选',
  autoapprove VARCHAR(256) comment '设置用户是否自动Approval操作, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''. 该字段只适用于grant_type="authorization_code"的情况'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_client_token`;
create table oauth_client_token (
  token_id VARCHAR(256) comment '从服务器端获取到的access_token的值',
  token BLOB NULL DEFAULT NULL comment '这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据',
  authentication_id VARCHAR(256) PRIMARY KEY comment '该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的.',
  user_name VARCHAR(256) comment '登录时的用户名',
  client_id VARCHAR(256)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_access_token`;
create table oauth_access_token (
  token_id VARCHAR(256) NULL DEFAULT NULL comment '该字段的值是将access_token的值通过MD5加密后存储的',
  token BLOB NULL DEFAULT NULL comment '存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值',
  authentication_id VARCHAR(256) PRIMARY KEY NOT NULL comment '该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的',
  user_name VARCHAR(256) NULL DEFAULT NULL comment '登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id',
  client_id VARCHAR(256) NULL DEFAULT NULL,
  authentication BLOB NULL DEFAULT NULL comment '存储将OAuth2Authentication.java对象序列化后的二进制数据',
  refresh_token VARCHAR(256) comment '该字段的值是将refresh_token的值通过MD5加密后存储的'
);

DROP TABLE IF EXISTS `oauth_refresh_token`;
create table oauth_refresh_token (
  token_id VARCHAR(256) comment '该字段的值是将refresh_token的值通过MD5加密后存储的',
  token BLOB NULL DEFAULT NULL comment '存储将OAuth2RefreshToken.java对象序列化后的二进制数据',
  authentication BLOB NULL DEFAULT NULL comment '存储将OAuth2Authentication.java对象序列化后的二进制数据'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_code`;
create table oauth_code (
  code VARCHAR(256) comment '存储服务端系统生成的code的值(未加密)',
  authentication VARBINARY(255) comment '存储将AuthorizationRequestHolder.java对象序列化后的二进制数据'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_approvals`;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt DATETIME NULL DEFAULT NULL,
	lastModifiedAt DATETIME NULL DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- customized oauth_client_details table
DROP TABLE IF EXISTS `client_details`;
create table client_details (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
