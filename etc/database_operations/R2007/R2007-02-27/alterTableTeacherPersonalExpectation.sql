alter table TEACHER_PERSONAL_EXPECTATION change column IST_ORGANS INSTITUTION_ORGANS text;
alter table TEACHER_PERSONAL_EXPECTATION change column UTL_ORGANS UNIVERSITY_ORGANS text;

CREATE TABLE `TEACHER_EXPECTATIONS_VISUALIZATION_PERIOD` (
  `ID_INTERNAL` int(11) NOT NULL auto_increment,
  `ACK_OPT_LOCK` int(11) default NULL,
  `START_DATE_YEAR_MONTH_DAY` date NOT NULL default '0000-00-00',
  `END_DATE_YEAR_MONTH_DAY` date NOT NULL default '0000-00-00',
  `KEY_EXECUTION_YEAR` int(11) NOT NULL default '0',
  `KEY_DEPARTMENT` int(11) NOT NULL default '0',
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`),
  UNIQUE KEY `U1` (`KEY_DEPARTMENT`,`KEY_EXECUTION_YEAR`),
  KEY `KEY_DEPARTMENT` (`KEY_DEPARTMENT`),
  KEY `KEY_EXECUTION_YEAR` (`KEY_EXECUTION_YEAR`),
  KEY `KEY_ROOT_DOMAIN_OBJECT` (`KEY_ROOT_DOMAIN_OBJECT`)
) ENGINE=InnoDB;
