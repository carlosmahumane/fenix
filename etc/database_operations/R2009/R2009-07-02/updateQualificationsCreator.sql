
UPDATE QUALIFICATION SET QUALIFICATION.KEY_CREATOR = QUALIFICATION.KEY_PERSON WHERE QUALIFICATION.KEY_CREATOR IS NULL AND QUALIFICATION.KEY_PERSON IS NOT NULL;
UPDATE QUALIFICATION SET QUALIFICATION.OID_CREATOR = QUALIFICATION.OID_PERSON WHERE QUALIFICATION.OID_CREATOR IS NULL AND QUALIFICATION.OID_PERSON IS NOT NULL;

