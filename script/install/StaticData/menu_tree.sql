USE `feugodb`;


INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (1,'assertsManage','首页','','icon icon-home','IndexInit.do',0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (2,'assertsManage','事务管理','submenu','icon icon-shopping-cart',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (21,'assertsManage','事务查看','','icon icon-file','IndexInit.do',2);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (22,'assertsManage','事务统计','','icon icon-file','GatherTransInit.do',2);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (10000,'assertsManage','实物资产管理','submenu','icon icon-briefcase',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (11000,'assertsManage','实物资产采购','','icon icon-shopping-cart','PurchasePlanInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (12000,'assertsManage','实物资产验收','','icon icon-hdd','ImportAssetsInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (13000,'assertsManage','实物资产调拨','','icon icon-random','AssignInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (14000,'assertsManage','实物资产回收','','icon icon-repeat','AssetsRecaptureInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (15000,'assertsManage','实物资产处置','','icon icon-trash','DiscardSearchInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (16000,'assertsManage','加油站资产报备','','icon icon-calendar','GasAssetsApplyInit.do',10000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (20000,'assertsManage','资产信息查询','submenu','icon icon-search',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (21000,'assertsManage','实物资产台账查询','','icon icon-file','AssetsStatusSearchInit.do?showModifyBtn=false',20000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (22000,'assertsManage','台账操作记录查询	','','icon icon-edit','AssetsOperateLog.do',20000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (30000,'assertsManage','盘点管理','submenu','icon icon-check',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (31000,'assertsManage','下发实物资产盘点','','icon icon-list-alt','GasStationCheckInit.do',30000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (32000,'assertsManage','盘点情况统计','','icon icon-tasks','GasStationCheckStatusInit.do',30000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (40000,'assertsManage','基础数据维护','submenu','icon icon-wrench',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (41000,'assertsManage','基础数据导入','','icon icon-download-alt','BasicDataInit.do',40000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (46000,'assertsManage','维修台账','','icon icon-download-alt','AssetsFixInit.do',40000);

INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (45000,'assertsManage','加油站配置表','','icon icon-download-alt','QuotaDataInit.do',40000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (44000,'assertsManage','价格参考表维护','','icon icon-download-alt','PriceDataInit.do',40000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (42000,'assertsManage','台账信息修改','','icon icon-info-sign','AssetsStatusSearchInit.do?showModifyBtn=true',40000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (43000,'assertsManage','系统参数修改','','icon icon-user','SystemParaSetupInit.do',40000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (50000,'assertsManage','用户账户管理','submenu','icon icon-cog',NULL,0);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (51000,'assertsManage','修改用户密码','','icon icon-repeat','UserPasswordSetupInit.do',50000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (52000,'assertsManage','密码重置','','icon icon-home','PasswordResetupInit.do',50000);
INSERT INTO `menu_tree`(MENU_ID,NAME,VALUE,MENU_CSS,ICON_CSS,URL,PARENT_ID) VALUES (53000,'assertsManage','电子签名管理','','icon icon-home','NickNameSetupInit.do',50000);
