ALTER TABLE PROFESSORSHIP DISABLE KEY `U1`;
ALTER TABLE PROFESSORSHIP ADD UNIQUE KEY `U1` (`KEY_PERSON`,`KEY_EXECUTION_COURSE`);
ALTER TABLE PROFESSORSHIP DROP COLUMN KEY_TEACHER;




