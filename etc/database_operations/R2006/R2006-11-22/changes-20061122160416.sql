--  SQL file representing changes to the functionalities model
--  Generated at Wed Nov 22 16:04:23 WET 2006
--  DO NOT EDIT THIS FILE, run the generating script instead

--  Preamble
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- 
--  Inserting new functionalities
-- 

--  ID: 18769 UUID: '40bcc4e0-1d8e-4a02-8fca-5e6ef6e923f7'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT '40bcc4e0-1d8e-4a02-8fca-5e6ef6e923f7', 'net.sourceforge.fenixedu.domain.functionalities.ConcreteFunctionality', 1, NULL, `ID_INTERNAL`, NULL, 'pt20:Criar Pessoa Externa', 'pt20:Criar Pessoa Externa', NULL, 'createInvitedPerson.do?method=prepareSearchExistentPersonBeforeCreateNewInvitedPerson', NULL, 1, 1, NULL, 11, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = 'cced3f10-5a09-470a-bfed-aed2454805bf';

COMMIT;
