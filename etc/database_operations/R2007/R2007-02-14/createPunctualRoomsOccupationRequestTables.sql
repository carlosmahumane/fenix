CREATE TABLE `PUNCTUAL_ROOMS_OCCUPATION_REQUEST` (
  `ID_INTERNAL` int(11) unsigned NOT NULL auto_increment,
  `IDENTIFICATION` int(11) unsigned NOT NULL,
  `INSTANT` timestamp NOT NULL, 
  `EMPLOYEE_READ_COMMENTS` int(11) unsigned default 0,
  `TEACHER_READ_COMMENTS` int(11) unsigned default 0,
  `KEY_REQUESTOR` int(11) NOT NULL default '0',    
  `KEY_OWNER` int(11) default NULL, 
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`),
  KEY `KEY_OWNER` (`KEY_OWNER`),
  KEY `KEY_REQUESTOR` (`KEY_REQUESTOR`),
  KEY `KEY_ROOT_DOMAIN_OBJECT` (`KEY_ROOT_DOMAIN_OBJECT`)
) ENGINE=InnoDB;

CREATE TABLE `PUNCTUAL_ROOMS_OCCUPATION_COMMENT` (
  `ID_INTERNAL` int(11) unsigned NOT NULL auto_increment,
  `SUBJECT` longtext,
  `DESCRIPTION` longtext,  
  `INSTANT` timestamp NOT NULL, 
  `KEY_REQUEST` int(11) NOT NULL default '0',  
  `KEY_OWNER` int(11) NOT NULL default '0',  
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`),
  KEY `KEY_OWNER` (`KEY_OWNER`),
  KEY `KEY_REQUEST` (`KEY_REQUEST`),
  KEY `KEY_ROOT_DOMAIN_OBJECT` (`KEY_ROOT_DOMAIN_OBJECT`)
) ENGINE=InnoDB;

CREATE TABLE `PUNCTUAL_ROOMS_OCCUPATION_STATE_INSTANT` (
  `ID_INTERNAL` int(11) unsigned NOT NULL auto_increment,
  `REQUEST_STATE` varchar(100) default '',
  `INSTANT` timestamp NOT NULL,  
  `KEY_REQUEST` int(11) NOT NULL default '0', 
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`),
  KEY `KEY_REQUEST` (`KEY_REQUEST`),
  KEY `KEY_ROOT_DOMAIN_OBJECT` (`KEY_ROOT_DOMAIN_OBJECT`)
) ENGINE=InnoDB;

alter table `GENERIC_EVENT` add column `KEY_PUNCTUAL_ROOMS_OCCUPATION_REQUEST` int(11) default NULL;
alter table `GENERIC_EVENT` add key `KEY_PUNCTUAL_ROOMS_OCCUPATION_REQUEST` (`KEY_PUNCTUAL_ROOMS_OCCUPATION_REQUEST`);