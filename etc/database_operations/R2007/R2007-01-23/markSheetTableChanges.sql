ALTER TABLE MARKSHEET DROP KEY KEY_EMPLOYEE;
ALTER TABLE MARKSHEET CHANGE COLUMN KEY_EMPLOYEE `KEY_CONFIRMATION_EMPLOYEE` int(11) default NULL;
ALTER TABLE MARKSHEET ADD COLUMN `KEY_CREATION_EMPLOYEE` int(11) default NULL;
ALTER TABLE MARKSHEET ADD KEY KEY_CONFIRMATION_EMPLOYEE (KEY_CONFIRMATION_EMPLOYEE);
ALTER TABLE MARKSHEET ADD KEY KEY_CREATION_EMPLOYEE (KEY_CREATION_EMPLOYEE);