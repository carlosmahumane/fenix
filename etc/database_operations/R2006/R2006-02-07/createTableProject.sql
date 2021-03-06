--NOTE THIS SCRIPT SHOULD ONLY BE EXECUTED IN DATA BASES WHERE TABLE PROJECT IS EMPTY!

DROP TABLE IF EXISTS PROJECT;
CREATE TABLE PROJECT (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_PROJECT_TITLE int(11) NOT NULL,
  KEY_PROJECT_ABSTRACT int(11) NOT NULL,
  START_DATE varchar(10),
  END_DATE varchar(10),
  STATUS varchar(50),
  TYPE varchar(50),
  PRIMARY KEY (ID_INTERNAL)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_RESEARCHER;
CREATE TABLE PROJECT_RESEARCHER (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_RESEARCHER int(11) NOT NULL,
  KEY_PROJECT int(11) NOT NULL,
  PRIMARY KEY (ID_INTERNAL),
  UNIQUE KEY (KEY_RESEARCHER, KEY_PROJECT)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_UNIT;
CREATE TABLE PROJECT_UNIT (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_UNIT int(11) NOT NULL,
  KEY_PROJECT int(11) NOT NULL,
  PRIMARY KEY (ID_INTERNAL),
  UNIQUE KEY (KEY_UNIT, KEY_PROJECT)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_TITLE;
CREATE TABLE PROJECT_TITLE (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_PROJECT int(11) NOT NULL,
  TITLE varchar(100),
  TRANSLATION varchar(100),  
  PRIMARY KEY (ID_INTERNAL)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_ABSTRACT;
CREATE TABLE PROJECT_ABSTRACT (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_PROJECT int(11) NOT NULL,
  ABSTRACT varchar(100),
  TRANSLATION varchar(100),  
  PRIMARY KEY (ID_INTERNAL)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_KEYWORD;
CREATE TABLE PROJECT_KEYWORD (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEYWORD varchar(50),
  TRANSLATION varchar(50),  
  PRIMARY KEY (ID_INTERNAL)
) Type=InnoDB;

DROP TABLE IF EXISTS PROJECT_PROJECT_KEYWORD;
CREATE TABLE PROJECT_PROJECT_KEYWORD (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_PROJECT int(11) NOT NULL,
  KEY_PROJECT_KEYWORD int(11) NOT NULL,
  PRIMARY KEY (ID_INTERNAL),
  UNIQUE KEY (KEY_PROJECT, KEY_PROJECT_KEYWORD)
) Type=InnoDB;