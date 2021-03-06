INSERT INTO `ACCESSIBLE_ITEM` (`OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `VISIBLE`, `KEY_SITE`, `SECTION_ORDER`, `KEY_FUNCTIONALITY`) 
	SELECT 'net.sourceforge.fenixedu.domain.FunctionalitySection', 1, 1, -1, 0, AI.ID_INTERNAL
	FROM `ACCESSIBLE_ITEM` AS AI WHERE AI.UUID = '2a487bf2-9986-40cf-923d-c571c211491f';
	
UPDATE `ACCESSIBLE_ITEM` AS AI, `SITE` AS S
	SET AI.`KEY_SITE` = S.`ID_INTERNAL`
	WHERE AI.`OJB_CONCRETE_CLASS` LIKE 'net.sourceforge.fenixedu.domain.FunctionalitySection'
		AND AI.`KEY_SITE` = -1
		AND S.`OJB_CONCRETE_CLASS` LIKE 'net.sourceforge.fenixedu.domain.SiteTemplate'
		AND S.`SITE_TYPE` LIKE 'net.sourceforge.fenixedu.domain.ResearchUnitSite';