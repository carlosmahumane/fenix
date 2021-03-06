--  SQL file representing changes to the functionalities model
--  Generated at Mon Nov 20 14:53:00 WET 2006
--  DO NOT EDIT THIS FILE, run the generating script instead

--  Preamble
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- 
--  Inserting new functionalities
-- 

--  ID: 18269 UUID: 'eed02d28-7445-4263-ade3-523af17545b3'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT 'eed02d28-7445-4263-ade3-523af17545b3', 'net.sourceforge.fenixedu.domain.functionalities.Module', 1, `ID_INTERNAL`, `ID_INTERNAL`, NULL, 'pt10:Pagamentos', NULL, NULL, '', '/', 1, 1, NULL, 15, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = '4d6686aa-9e47-4a47-a08a-a37d69f94eb5';

--  ID: 18270 UUID: '1247fbf5-a2df-44d6-a591-b240611544fa'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT '1247fbf5-a2df-44d6-a591-b240611544fa', 'net.sourceforge.fenixedu.domain.functionalities.ConcreteFunctionality', 1, NULL, `ID_INTERNAL`, NULL, 'pt18:Upload de Ficheiro', NULL, NULL, '/degreePaymentsManagement.do?method=prepareUploadSibsPaymentsFile', NULL, 1, 1, NULL, 0, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = 'eed02d28-7445-4263-ade3-523af17545b3';

COMMIT;
