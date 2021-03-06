alter table CANDIDACY_PRECEDENT_DEGREE_INFORMATION add column CONCLUSION_YEAR int(11);
alter table CANDIDACY_PRECEDENT_DEGREE_INFORMATION add column KEY_COUNTRY int(11);
alter table CANDIDACY_PRECEDENT_DEGREE_INFORMATION add column OTHER_SCHOOL_LEVEL text;
alter table CANDIDACY_PRECEDENT_DEGREE_INFORMATION add column SCHOOL_LEVEL text;
alter table CANDIDACY_PRECEDENT_DEGREE_INFORMATION add index (KEY_COUNTRY);

alter table INDIVIDUAL_CANDIDACY add column DISLOCATED_FROM_PERMANENT_RESIDENCE tinyint(1);
alter table INDIVIDUAL_CANDIDACY add column FATHER_PROFESSIONAL_CONDITION text;
alter table INDIVIDUAL_CANDIDACY add column FATHER_PROFESSION_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column FATHER_SCHOOL_LEVEL text;
alter table INDIVIDUAL_CANDIDACY add column GRANT_OWNER_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column HIGH_SCHOOL_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column KEY_COUNTRY_OF_RESIDENCE int(11);
alter table INDIVIDUAL_CANDIDACY add column KEY_DISTRICT_SUBDIVISION_OF_RESIDENCE int(11);
alter table INDIVIDUAL_CANDIDACY add column KEY_SCHOOL_TIME_DISTRICT_SUB_DIVISION_OF_RESIDENCE int(11);
alter table INDIVIDUAL_CANDIDACY add column MARITAL_STATUS text;
alter table INDIVIDUAL_CANDIDACY add column MOTHER_PROFESSIONAL_CONDITION text;
alter table INDIVIDUAL_CANDIDACY add column MOTHER_PROFESSION_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column MOTHER_SCHOOL_LEVEL text;
alter table INDIVIDUAL_CANDIDACY add column NUMBER_OF_CANDIDACIES_TO_HIGHER_SCHOOL int(11);
alter table INDIVIDUAL_CANDIDACY add column NUMBER_OF_FLUNKS_ON_HIGH_SCHOOL int(11);
alter table INDIVIDUAL_CANDIDACY add column PROFESSIONAL_CONDITION text;
alter table INDIVIDUAL_CANDIDACY add column PROFESSION_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column SPOUSE_PROFESSIONAL_CONDITION text;
alter table INDIVIDUAL_CANDIDACY add column SPOUSE_PROFESSION_TYPE text;
alter table INDIVIDUAL_CANDIDACY add column SPOUSE_SCHOOL_LEVEL text;
alter table INDIVIDUAL_CANDIDACY add index (KEY_COUNTRY_OF_RESIDENCE);
alter table INDIVIDUAL_CANDIDACY add index (KEY_DISTRICT_SUBDIVISION_OF_RESIDENCE);
alter table INDIVIDUAL_CANDIDACY add index (KEY_SCHOOL_TIME_DISTRICT_SUB_DIVISION_OF_RESIDENCE);
