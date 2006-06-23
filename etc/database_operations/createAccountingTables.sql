create table ACCOUNT (
	ID_INTERNAL int(11) NOT NULL auto_increment,
	KEY_ROOT_DOMAIN_OBJECT int(11) NOT NULL default '1',
	KEY_PARTY int(11) NOT NULL,
	ACCOUNT_TYPE varchar(100) default null,
	PRIMARY KEY (ID_INTERNAL),
	UNIQUE KEY PARTY_ACCOUNT_TYPE (KEY_PARTY, ACCOUNT_TYPE)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table ACCOUNTING_TRANSACTION (
	ID_INTERNAL int(11) NOT NULL auto_increment,
	KEY_ROOT_DOMAIN_OBJECT int(11) NOT NULL default '1',
	WHEN_REGISTED datetime NOT NULL default '0000-00-00 00:00:00',
	PRIMARY KEY (ID_INTERNAL)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table ACCOUNTING_ENTRY (
	ID_INTERNAL int(11) NOT NULL auto_increment,
	KEY_ROOT_DOMAIN_OBJECT int(11) NOT NULL default '1',
	KEY_ACCOUNT int(11) default NULL,
	KEY_EVENT int(11) default NULL,
	KEY_ACCOUNTING_TRANSACTION int(11) default NULL,
	WHEN_BOOKED datetime NOT NULL default '0000-00-00 00:00:00',
	AMOUNT varchar(250) NOT NULL,
	PRIMARY KEY (ID_INTERNAL)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table ACCOUNTING_EVENT (
	ID_INTERNAL int(11) NOT NULL auto_increment,
	KEY_ROOT_DOMAIN_OBJECT int(11) NOT NULL default '1',
	OJB_CONCRETE_CLASS varchar(250) NOT NULL,
	EVENT_TYPE varchar(100) NOT NULL,
	WHEN_OCCURED datetime NOT NULL default '0000-00-00 00:00:00',
	WHEN_NOTICED datetime default null,
	PRIMARY KEY (ID_INTERNAL)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#For candidacy.DFACandidacyEvent
alter table ACCOUNTING_EVENT add column KEY_CANDIDACY int(11) default NULL;
