DROP TABLE IF EXISTS `UNIT_SITE_MANAGERS`;
CREATE TABLE `UNIT_SITE_MANAGERS` (
  `ID_INTERNAL` INTEGER  NOT NULL AUTO_INCREMENT,
  `KEY_UNIT_SITE` INTEGER NOT NULL,
  `KEY_PERSON` INTEGER NOT NULL,
  PRIMARY KEY(`ID_INTERNAL`)
) type=InnoDB;
