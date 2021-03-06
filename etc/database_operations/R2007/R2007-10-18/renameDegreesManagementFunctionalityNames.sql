--  SQL file representing changes to the functionalities model
--  Generated at Thu Oct 18 16:12:16 WEST 2007
--  DO NOT EDIT THIS FILE, run the generating script instead

--  Preamble
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- 
--  Deleting functionalities
-- 

--  ID: 63 UUID: '18fd2e9b-ea1f-4297-bf3a-e49fbb98c77f'
DELETE FROM ap USING `AVAILABILITY_POLICY` AS ap, `ACCESSIBLE_ITEM` AS f WHERE ap.`KEY_ACCESSIBLE_ITEM` = f.`ID_INTERNAL` AND f.`UUID` = '18fd2e9b-ea1f-4297-bf3a-e49fbb98c77f';
DELETE FROM `ACCESSIBLE_ITEM` WHERE `UUID` = '18fd2e9b-ea1f-4297-bf3a-e49fbb98c77f';

-- 
--  Updating existing functionalities
-- 

--  ID: 25 UUID: 'f832317c-db1e-4c36-b67c-3c4070ccee59'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt26:Estrutura de Cursos Antiga', own.`ORDER_IN_MODULE` = 0 WHERE own.`UUID` = 'f832317c-db1e-4c36-b67c-3c4070ccee59';

--  ID: 26 UUID: '5b51f27d-2f26-4f11-83a7-8b22ccfc7016'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt26:Estrutura de Cursos Antiga', own.`ORDER_IN_MODULE` = 1 WHERE own.`UUID` = '5b51f27d-2f26-4f11-83a7-8b22ccfc7016';

--  ID: 59 UUID: '0cac3f6f-6be4-4476-8681-7b980edbccee'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt23:Competência Pré-Bolonha', own.`ORDER_IN_MODULE` = 4 WHERE own.`UUID` = '0cac3f6f-6be4-4476-8681-7b980edbccee';

--  ID: 60 UUID: '10e32908-a524-47df-8a36-f6ab0d48462c'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt23:Competência Pré-Bolonha', own.`ORDER_IN_MODULE` = 5 WHERE own.`UUID` = '10e32908-a524-47df-8a36-f6ab0d48462c';

--  ID: 64 UUID: 'fe00b1c9-b931-489c-9398-21ddf806a638'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt19:Estrutura de Cursos', own.`ORDER_IN_MODULE` = 2 WHERE own.`UUID` = 'fe00b1c9-b931-489c-9398-21ddf806a638';

--  ID: 65 UUID: '59e0a4d6-3e45-4ca0-8313-0bf62b898b15'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt19:Estrutura de Cursos', own.`ORDER_IN_MODULE` = 3 WHERE own.`UUID` = '59e0a4d6-3e45-4ca0-8313-0bf62b898b15';

--  ID: 79 UUID: '50ab74d4-5050-4975-b88c-91b0c39ed493'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt20:Competências Bolonha', own.`ORDER_IN_MODULE` = 6 WHERE own.`UUID` = '50ab74d4-5050-4975-b88c-91b0c39ed493';

--  ID: 80 UUID: '0b58f724-6282-48fb-9de7-b84c852a940c'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt20:Competências Bolonha', own.`ORDER_IN_MODULE` = 7 WHERE own.`UUID` = '0b58f724-6282-48fb-9de7-b84c852a940c';

--  ID: 252 UUID: '9cf56518-f706-4854-b28e-2108ba374c43'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'en9:Messagingpt11:Comunicação' WHERE own.`UUID` = '9cf56518-f706-4854-b28e-2108ba374c43';

--  ID: 17209 UUID: '6c6c707b-3a07-4f8c-b76e-7bfdce694324'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`ORDER_IN_MODULE` = 8 WHERE own.`UUID` = '6c6c707b-3a07-4f8c-b76e-7bfdce694324';

--  ID: 189877 UUID: '4cbd4716-40e1-4052-ac47-b24db983efd4'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'en7:Librarypt10:Biblioteca', own.`TITLE` = 'en14:Library Portalpt20:Portal da Biblioteca' WHERE own.`UUID` = '4cbd4716-40e1-4052-ac47-b24db983efd4';

COMMIT;
