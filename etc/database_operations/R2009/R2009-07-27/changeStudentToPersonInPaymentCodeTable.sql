ALTER TABLE PAYMENT_CODE ADD COLUMN OID_PERSON bigint(20), ADD COLUMN KEY_PERSON int(11);
UPDATE PAYMENT_CODE PC SET OID_PERSON = (SELECT S.OID_PERSON FROM STUDENT S WHERE S.OID = PC.OID_STUDENT);
UPDATE PAYMENT_CODE PC SET KEY_PERSON = (SELECT S.KEY_PERSON FROM STUDENT S WHERE S.ID_INTERNAL = PC.KEY_STUDENT);