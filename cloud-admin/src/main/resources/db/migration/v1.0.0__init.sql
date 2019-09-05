CREATE DATABASE IF NOT EXISTS tjs_cloud CHARSET utf8 COLLATE utf8_bin;
USE tjs_cloud;

DROP TABLE IF EXISTS `rms_file`;
CREATE TABLE `rms_file` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `type` int(11) DEFAULT NULL COMMENT '文件类型',
                            `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
                            `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文件上传';

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
                            `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
                            `order_num` int(11) DEFAULT NULL COMMENT '排序',
                            `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
                            PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='部门管理';

INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');

DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
                            `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
                            `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
                            `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
                            `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
                            `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
                            `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
                            `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
                            `create_by` int(64) DEFAULT NULL COMMENT '创建者',
                            `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
                            `update_date` datetime DEFAULT NULL COMMENT '更新时间',
                            `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
                            `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
                            PRIMARY KEY (`id`),
                            KEY `sys_dict_value` (`value`),
                            KEY `sys_dict_label` (`name`),
                            KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('121', '编码', 'code', 'hobby', '爱好', null, null, null, null, null, null, '', '');

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
                           `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                           `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
                           `time` int(11) DEFAULT NULL COMMENT '响应时间',
                           `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
                           `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
                           `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
                           `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='系统日志';

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
                            `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                            `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
                            `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
                            `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
                            `order_num` int(11) DEFAULT NULL COMMENT '排序',
                            `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
                            `component` varchar(20) DEFAULT NULL,
                            `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
                            `redirect` varchar(20) DEFAULT NULL,
                            PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', '/admin/menu', 'admin:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null, null, null);
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', null, '2017-08-14 14:13:43', null);
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', '/admin/user', 'admin:user:user', '1', 'fa fa-user', '0', '2017-08-10 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', '/admin/role', 'admin:role:role', '1', 'fa fa-paw', '1', '2017-08-10 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '/api-admin/user', 'admin:user:add', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '/api-admin/user', 'admin:user:edit', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', '/api-admin/user', 'admin:user:remove', '2', null, '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '/api-admin/role', 'admin:role:add', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '/api-admin/menu', 'admin:menu:add', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '/api-admin/menu', 'admin:menu:edit', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '/api-admin/menu', 'admin:menu:remove', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '/api-admin/user', 'admin:user:batchRemove', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', '/api-admin/user/disable', 'admin:user:disable', '2', null, '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '/api-admin/user/resetPwd', 'admin:user:resetPwd', '2', '', '0', '2017-08-14 00:00:00', null, null, null);
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '/api-admin/role', 'admin:role:edit', '2', '', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '/api-admin/role', 'admin:role:remove', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '/api-admin/menu/batchRemove', 'admin:menu:batchRemove', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '/api-admin/role/batchRemove', 'admin:role:batchRemove', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '清除缓存', '/admin/api/menu/clearCache', 'system:sysDept:add', '1', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '/admin/api/user/currentUser', 'system:sysDept:edit', '2', null, '3', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('77', '2', '列表', '/api-admin/menu', 'admin:menu:list', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('78', '0', '内容管理', null, null, '0', 'fa fa-th-list', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('80', '79', '数据字典', '/a', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('81', '78', '文件管理', '/rms/file', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('82', '81', '列表', '/api-rms/file', 'cms:file', '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('83', '78', '博客管理', '/rms/content', 'rms:content', '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('84', '0', '基础信息', null, null, '0', 'fa fa-bars', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('85', '84', '系统日志', '/base/log', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('86', '85', '列表', '/api-base/log', null, '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('87', '81', '上传', '/file/upload', null, '2', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('88', '0', '系统监控', null, null, '0', 'fa fa-video-camera', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('89', '88', '服务注册', 'http://localhost:8001', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('90', '0', '系统工具', null, null, '0', 'fa fa-wrench', null, null, null, null, null);

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
                            `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
                            `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                            `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
                            `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
                            `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='角色';

INSERT INTO `sys_role` VALUES ('1', '管理员角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:00:00', '2017-08-12 00:00:00');

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3225 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

INSERT INTO `sys_role_menu` VALUES ('3195', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('3196', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('3197', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('3198', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('3199', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('3200', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('3201', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('3202', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('3203', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('3204', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('3205', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('3206', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('3207', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('3208', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('3209', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('3210', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('3211', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('3212', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('3213', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('3214', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('3215', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('3216', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('3217', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('3218', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('3219', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3220', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('3221', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('3222', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('3223', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('3224', '1', '20');

DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
                            `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
                            `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
                            `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
                            `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
                            `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
                            `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                            `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
                            `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
                            `update_date` datetime DEFAULT NULL COMMENT '更新时间',
                            `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
                            `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
                            `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
                            PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `sys_task` VALUES ('2', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                            `name` varchar(100) DEFAULT NULL,
                            `password` varchar(50) DEFAULT NULL COMMENT '密码',
                            `dept_id` bigint(20) DEFAULT NULL,
                            `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                            `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
                            `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
                            `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
                            `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
                            `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
                            `sex` bigint(32) DEFAULT NULL COMMENT '性别',
                            `birth` datetime DEFAULT NULL COMMENT '出身日期',
                            `pic_id` bigint(32) DEFAULT NULL,
                            `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
                            `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
                            `province` varchar(255) DEFAULT NULL COMMENT '省份',
                            `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
                            `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
                            PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '122198e3a7b5e602ec4be45831b7209e', '6', 'admin@example.com', '15899221100', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '138', 'ccc', '', '北京市', '北京市市辖区', '东城区');
DROP TABLE IF EXISTS `sys_user_plus`;
CREATE TABLE `sys_user_plus` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) NOT NULL,
                                 `payment` double DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');
INSERT INTO `sys_user_role` VALUES ('127', '123', '48');
INSERT INTO `sys_user_role` VALUES ('132', '36', '48');
INSERT INTO `sys_user_role` VALUES ('150', '156', '1');
INSERT INTO `sys_user_role` VALUES ('151', '136', '1');
INSERT INTO `sys_user_role` VALUES ('152', '135', '1');
INSERT INTO `sys_user_role` VALUES ('153', '134', '1');
INSERT INTO `sys_user_role` VALUES ('154', '134', '48');
INSERT INTO `sys_user_role` VALUES ('155', '134', '56');
INSERT INTO `sys_user_role` VALUES ('158', '131', '48');
INSERT INTO `sys_user_role` VALUES ('159', '131', '1');
INSERT INTO `sys_user_role` VALUES ('160', '130', '1');
INSERT INTO `sys_user_role` VALUES ('164', '124', '56');
INSERT INTO `sys_user_role` VALUES ('165', '124', '48');

DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
                                  `user_id` bigint(20) NOT NULL,
                                  `token` varchar(100) NOT NULL COMMENT 'token',
                                  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
                                  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`user_id`),
                                  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

