package net.sourceforge.fenixedu.domain.degreeStructure;

import net.sourceforge.fenixedu.domain.CompetenceCourse;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Role;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.organizationalStructure.CompetenceCourseGroupUnit;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.domain.time.calendarStructure.AcademicPeriod;
import net.sourceforge.fenixedu.injectionCode.AccessControl;
import pt.ist.fenixWebFramework.security.accessControl.Checked;
import pt.ist.fenixWebFramework.services.Service;

public class CompetenceCourseInformationChangeRequest extends CompetenceCourseInformationChangeRequest_Base {

    public CompetenceCourseInformationChangeRequest() {
	setRootDomainObject(RootDomainObject.getInstance());
    }

    public CompetenceCourseInformationChangeRequest(CompetenceCourseInformation information, String justification,
	    Person requester) {
	this(information.getName(), information.getNameEn(), justification, information.getRegime(), information.getObjectives(),
		information.getObjectivesEn(), information.getProgram(), information.getProgramEn(), information
			.getEvaluationMethod(), information.getEvaluationMethodEn(), information.getCompetenceCourse(),
		information.getCompetenceCourseLevel(), information.getExecutionPeriod(), requester, information
			.getTheoreticalHours(1), information.getProblemsHours(1), information.getLaboratorialHours(1),
		information.getSeminaryHours(1), information.getFieldWorkHours(1), information.getTrainingPeriodHours(1),
		information.getTutorialOrientationHours(1), information.getAutonomousWorkHours(1), information.getEctsCredits(1),
		information.getTheoreticalHours(2), information.getProblemsHours(2), information.getLaboratorialHours(2),
		information.getSeminaryHours(2), information.getFieldWorkHours(2), information.getTrainingPeriodHours(2),
		information.getTutorialOrientationHours(2), information.getAutonomousWorkHours(2), information.getEctsCredits(2),
		information.getBibliographicReferences(), information.getCompetenceCourseGroupUnit());
    }

    public CompetenceCourseInformationChangeRequest(String name, String nameEn, String justification, RegimeType regime,
	    String objectives, String objectivesEn, String program, String programEn, String evaluationMethod,
	    String evaluationMethodEn, CompetenceCourse course, CompetenceCourseLevel level, ExecutionSemester period,
	    Person requester, Double theoreticalHours, Double problemsHours, Double laboratorialHours, Double seminaryHours,
	    Double fieldWorkHours, Double trainingPeriodHours, Double tutorialOrientationHours, Double autonomousWorkHours,
	    Double ectsCredits, Double secondTheoreticalHours, Double secondProblemsHours, Double secondLaboratorialHours,
	    Double secondSeminaryHours, Double secondFieldWorkHours, Double secondTrainingPeriodHours,
	    Double secondTutorialOrientationHours, Double secondAutonomousWorkHours, Double secondEctsCredits,
	    BibliographicReferences references, CompetenceCourseGroupUnit group) {
	this();
	if (course.isRequestDraftAvailable(period)) {
	    throw new DomainException("error.can.only.exist.one.request.draft.per.execution.period");
	}
	if (course == null || period == null) {
	    throw new DomainException("error.fields.are.required");
	}
	setCompetenceCourse(course);
	setExecutionPeriod(period);
	edit(name, nameEn, justification, regime, objectives, objectivesEn, program, programEn, evaluationMethod,
		evaluationMethodEn, level, requester, theoreticalHours, problemsHours, laboratorialHours, seminaryHours,
		fieldWorkHours, trainingPeriodHours, tutorialOrientationHours, autonomousWorkHours, ectsCredits,
		secondTheoreticalHours, secondProblemsHours, secondLaboratorialHours, secondSeminaryHours, secondFieldWorkHours,
		secondTrainingPeriodHours, secondTutorialOrientationHours, secondAutonomousWorkHours, secondEctsCredits,
		references, group);
    }

    public void edit(String name, String nameEn, String justification, RegimeType regime, String objectives, String objectivesEn,
	    String program, String programEn, String evaluationMethod, String evaluationMethodEn, CompetenceCourseLevel level,
	    Person requester, Double theoreticalHours, Double problemsHours, Double laboratorialHours, Double seminaryHours,
	    Double fieldWorkHours, Double trainingPeriodHours, Double tutorialOrientationHours, Double autonomousWorkHours,
	    Double ectsCredits, Double secondTheoreticalHours, Double secondProblemsHours, Double secondLaboratorialHours,
	    Double secondSeminaryHours, Double secondFieldWorkHours, Double secondTrainingPeriodHours,
	    Double secondTutorialOrientationHours, Double secondAutonomousWorkHours, Double secondEctsCredits,
	    BibliographicReferences references, CompetenceCourseGroupUnit group) {

	if (name == null || nameEn == null || justification == null || regime == null || objectives == null
		|| objectivesEn == null || program == null || programEn == null || evaluationMethod == null
		|| evaluationMethodEn == null || requester == null || theoreticalHours == null || problemsHours == null
		|| laboratorialHours == null || seminaryHours == null || fieldWorkHours == null || trainingPeriodHours == null
		|| tutorialOrientationHours == null || autonomousWorkHours == null || ectsCredits == null) {

	    throw new DomainException("error.fields.are.required");
	}

	if (level == null) {
	    level = CompetenceCourseLevel.UNKNOWN;
	}

	setName(name);
	setNameEn(nameEn);
	setJustification(justification);
	setRegime(regime);
	setObjectives(objectives);
	setObjectivesEn(objectivesEn);
	setProgram(program);
	setProgramEn(programEn);
	setEvaluationMethod(evaluationMethod);
	setEvaluationMethodEn(evaluationMethodEn);
	setRequester(requester);
	setApproved(null);
	setCompetenceCourseLevel(level);

	setTheoreticalHours(theoreticalHours);
	setProblemsHours(problemsHours);
	setLaboratorialHours(laboratorialHours);
	setSeminaryHours(seminaryHours);
	setFieldWorkHours(fieldWorkHours);
	setTrainingPeriodHours(trainingPeriodHours);
	setTutorialOrientationHours(tutorialOrientationHours);
	setAutonomousWorkHours(autonomousWorkHours);
	setEctsCredits(ectsCredits);

	setSecondTheoreticalHours(secondTheoreticalHours);
	setSecondProblemsHours(secondProblemsHours);
	setSecondLaboratorialHours(secondLaboratorialHours);
	setSecondSeminaryHours(secondSeminaryHours);
	setSecondFieldWorkHours(secondFieldWorkHours);
	setSecondTrainingPeriodHours(secondTrainingPeriodHours);
	setSecondTutorialOrientationHours(secondTutorialOrientationHours);
	setSecondAutonomousWorkHours(secondAutonomousWorkHours);
	setSecondEctsCredits(secondEctsCredits);

	setBibliographicReferences(references);

	setCompetenceCourseGroupUnit(group == null ? getCompetenceCourse().getCompetenceCourseGroupUnit() : group);
    }

    public RequestStatus getStatus() {
	if (getApproved() == null) {
	    return RequestStatus.ON_HOLD;
	} else {
	    return (getApproved()) ? RequestStatus.APPROVED : RequestStatus.REJECTED;
	}
    }

    public void delete() {
	removeCompetenceCourse();
	removeAnalizedBy();
	removeRequester();
	removeExecutionPeriod();
	removeRootDomainObject();
	removeCompetenceCourseGroupUnit();
	super.deleteDomainObject();
    }

    public enum RequestStatus {
	APPROVED, REJECTED, ON_HOLD;
    }

    @Checked("RolePredicates.SCIENTIFIC_COUNCIL_PREDICATE")
    @Service
    public void reject(Person analisedBy) {
	if (getApproved() != null) {
	    throw new DomainException("error.request.already.processed");
	}
	setApproved(false);
	setAnalizedBy(analisedBy);
    }

    @Checked("RolePredicates.SCIENTIFIC_COUNCIL_PREDICATE")
    @Service
    public void approve(Person analisedBy) {
	if (getApproved() != null) {
	    throw new DomainException("error.request.already.processed");
	}
	setApproved(true);
	setAnalizedBy(analisedBy);

	CompetenceCourse course = getCompetenceCourse();

	ExecutionSemester executionSemester = getExecutionPeriod();
	CompetenceCourseInformation information = null;
	if (course.isCompetenceCourseInformationDefinedAtExecutionPeriod(executionSemester)) {
	    information = course.findCompetenceCourseInformationForExecutionPeriod(executionSemester);
	    information.edit(getName(), getNameEn(), information.getBasic(), getCompetenceCourseLevel(),
		    getCompetenceCourseGroupUnit());
	    information.setRegime(getRegime());
	    information.edit(getObjectives(), getProgram(), getEvaluationMethod(), getObjectivesEn(), getProgramEn(),
		    getEvaluationMethodEn());

	    information.setBibliographicReferences(getBibliographicReferences());

	    for (; !information.getCompetenceCourseLoads().isEmpty(); information.getCompetenceCourseLoads().get(0).delete())
		;
	    createLoads(information);

	} else {
	    information = new CompetenceCourseInformation(getName(), getNameEn(), course.isBasic(), getRegime(),
		    getCompetenceCourseLevel(), getExecutionPeriod(), getCompetenceCourseGroupUnit());
	    information.edit(getObjectives(), getProgram(), getEvaluationMethod(), getObjectivesEn(), getProgramEn(),
		    getEvaluationMethodEn());
	    information.setAcronym(course.getAcronym());
	    information.setBibliographicReferences(getBibliographicReferences());
	    course.addCompetenceCourseInformations(information);

	    createLoads(information);
	}
    }

    private void createLoads(CompetenceCourseInformation information) {
	CompetenceCourseLoad courseLoad = new CompetenceCourseLoad(getTheoreticalHours(), getProblemsHours(),
		getLaboratorialHours(), getSeminaryHours(), getFieldWorkHours(), getTrainingPeriodHours(),
		getTutorialOrientationHours(), getAutonomousWorkHours(), getEctsCredits(), Integer.valueOf(1),
		(getRegime() == RegimeType.SEMESTRIAL) ? AcademicPeriod.SEMESTER : AcademicPeriod.YEAR);

	information.addCompetenceCourseLoads(courseLoad);

	if (getRegime() == RegimeType.ANUAL) {
	    CompetenceCourseLoad secondCourseLoad = new CompetenceCourseLoad(getSecondTheoreticalHours(),
		    getSecondProblemsHours(), getSecondLaboratorialHours(), getSecondSeminaryHours(), getSecondFieldWorkHours(),
		    getSecondTrainingPeriodHours(), getSecondTutorialOrientationHours(), getSecondAutonomousWorkHours(),
		    getSecondEctsCredits(), Integer.valueOf(2), AcademicPeriod.YEAR);

	    information.addCompetenceCourseLoads(secondCourseLoad);
	}
    }

    public boolean isLoggedPersonAllowedToEdit() {
	Person person = AccessControl.getPerson();
	if (person.hasPersonRoles(Role.getRoleByRoleType(RoleType.SCIENTIFIC_COUNCIL))) {
	    return true;
	}
	if (!person.hasPersonRoles(Role.getRoleByRoleType(RoleType.BOLONHA_MANAGER))) {
	    return false;
	}
	return getCompetenceCourse().getDepartmentUnit(getExecutionPeriod()).getDepartment()
		.isUserMemberOfCompetenceCourseMembersGroup(person);
    }
}
