alter table COMPETENCE_COURSE_INFORMATION drop column GENERAL_OBJECTIVES;
alter table COMPETENCE_COURSE_INFORMATION drop column OPERACIONAL_OBJECTIVES;
alter table COMPETENCE_COURSE_INFORMATION drop column PROGRAM;
alter table COMPETENCE_COURSE_INFORMATION drop column GENERAL_OBJECTIVES_EN;
alter table COMPETENCE_COURSE_INFORMATION drop column OPERACIONAL_OBJECTIVES_EN;
alter table COMPETENCE_COURSE_INFORMATION drop column PROGRAM_EN;

alter table COMPETENCE_COURSE_INFORMATION add column PROGRAM text;
alter table COMPETENCE_COURSE_INFORMATION add column GENERAL_OBJECTIVES text;
alter table COMPETENCE_COURSE_INFORMATION add column OPERATIONAL_OBJECTIVES text;
alter table COMPETENCE_COURSE_INFORMATION add column EVALUATION_METHOD text;
alter table COMPETENCE_COURSE_INFORMATION add column PREREQUISITES text;
alter table COMPETENCE_COURSE_INFORMATION add column NAME_EN text;
alter table COMPETENCE_COURSE_INFORMATION add column PROGRAM_EN text;
alter table COMPETENCE_COURSE_INFORMATION add column GENERAL_OBJECTIVES_EN text;
alter table COMPETENCE_COURSE_INFORMATION add column OPERATIONAL_OBJECTIVES_EN text;
alter table COMPETENCE_COURSE_INFORMATION add column EVALUATION_METHOD_EN text;
alter table COMPETENCE_COURSE_INFORMATION add column PREREQUISITES_EN text;
