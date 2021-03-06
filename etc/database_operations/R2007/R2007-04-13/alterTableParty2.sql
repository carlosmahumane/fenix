delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'CENTRAL_ORG' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'DIRECTIVE_COUNCIL' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'EXECUTIVE_DIRECTION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'FINANCIER_DIRECTION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'TECHNIQUE_DIRECTION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'ACADEMIC_SERVICES_SUPERVISION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'OUT_RELATION_SERVICES_SUPERVISION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'SCIENTIFIC_PEDAGOGICAL_MANAGEMENT_RESOURCES_SERVICES_SUPERVISION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'DEGREE_COORDINATION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'SCIENCE_INFRASTRUCTURE' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'INFORMATION_TECHNOLOGY_LABORATORY' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'ASSOCIATED_LABORATORY' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'EXTRA_CURRICULAR_ACTIVITIES' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'PARTICIPATED_IST_INSTITUTION' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'DIVERSE_CONSTRUCTION_BUILDING' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'RESEARCH_UNIT' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'OUT' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'DIRECTIVE_COUNCIL_TAGUSPARK' ;
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'EXECUTIVE_DIRECTION_TAGUSPARK';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'TAGUSPARK';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'AUTONOMOUS_SECTION';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'ACADEMIC_UNIT';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'SERVICES';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'EXTERNAL_INSTITUTION';
delete from PARTY_TYPE where PARTY_TYPE.TYPE = 'UNKNOWN';

alter table PARTY drop column TYPE;

update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.DepartmentUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'DEPARTMENT');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.DegreeUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'DEGREE_UNIT');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.AdministrativeOfficeUnit' where PARTY.KEY_ADMINISTRATIVE_OFFICE is not null;
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.AggregateUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'AGGREGATE_UNIT');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.PlanetUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'PLANET');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.CountryUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'COUNTRY');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.UniversityUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'UNIVERSITY');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.SchoolUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'SCHOOL');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.ScientificAreaUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'SCIENTIFIC_AREA');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.CompetenceCourseGroupUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'COMPETENCE_COURSE_GROUP');
update PARTY set OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.SectionUnit' where PARTY.KEY_PARTY_TYPE is not null and PARTY.KEY_PARTY_TYPE in (select PARTY_TYPE.ID_INTERNAL from PARTY_TYPE where PARTY_TYPE.TYPE = 'SECTION');

alter table PARTY add column CAN_BE_RESPONSIBLE_OF_SPACES tinyint(1) NOT NULL default '0'; 