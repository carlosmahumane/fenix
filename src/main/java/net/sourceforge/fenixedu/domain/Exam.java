package net.sourceforge.fenixedu.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.space.AllocatableSpace;
import net.sourceforge.fenixedu.domain.space.WrittenEvaluationSpaceOccupation;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.domain.util.icalendar.EvaluationEventBean;
import net.sourceforge.fenixedu.util.BundleUtil;
import net.sourceforge.fenixedu.util.Season;

import org.fenixedu.bennu.core.domain.Bennu;

import pt.utl.ist.fenix.tools.util.DateFormatUtil;

public class Exam extends Exam_Base {

    public Exam(Date examDay, Date examStartTime, Date examEndTime, List<ExecutionCourse> executionCoursesToAssociate,
            List<DegreeModuleScope> curricularCourseScopesToAssociate, List<AllocatableSpace> rooms, GradeScale gradeScale,
            Season season) {

        super();
        checkScopeAndSeasonConstrains(executionCoursesToAssociate, curricularCourseScopesToAssociate, season);

        setAttributesAndAssociateRooms(examDay, examStartTime, examEndTime, executionCoursesToAssociate,
                curricularCourseScopesToAssociate, rooms);

        this.setSeason(season);
        if (gradeScale == null) {
            this.setGradeScale(GradeScale.TYPE20);
        } else {
            this.setGradeScale(gradeScale);
        }
        checkIntervalBetweenEvaluations();
        logCreate();
    }

    public void edit(Date examDay, Date examStartTime, Date examEndTime, List<ExecutionCourse> executionCoursesToAssociate,
            List<DegreeModuleScope> curricularCourseScopesToAssociate, List<AllocatableSpace> rooms, GradeScale gradeScale,
            Season season) {

        // It's necessary to remove this associations before check some
        // constrains
        this.getAssociatedExecutionCourses().clear();
        this.getAssociatedCurricularCourseScope().clear();
        this.getAssociatedContexts().clear();

        checkScopeAndSeasonConstrains(executionCoursesToAssociate, curricularCourseScopesToAssociate, season);

        super.edit(examDay, examStartTime, examEndTime, executionCoursesToAssociate, curricularCourseScopesToAssociate, rooms,
                gradeScale);
        this.setSeason(season);
        checkIntervalBetweenEvaluations();
        logEdit();
    }

    private boolean checkScopeAndSeasonConstrains(List<ExecutionCourse> executionCoursesToAssociate,
            List<DegreeModuleScope> curricularCourseScopesToAssociate, Season season) {

        // for each execution course, there must not exist an exam for the same
        // season and scope

        for (ExecutionCourse executionCourse : executionCoursesToAssociate) {
            for (Evaluation evaluation : executionCourse.getAssociatedEvaluations()) {
                if (evaluation instanceof Exam) {
                    Exam existingExam = (Exam) evaluation;
                    if (existingExam.getSeason().equals(season)) {
                        // is necessary to confirm if is for the same scope
                        for (DegreeModuleScope scope : existingExam.getDegreeModuleScopes()) {
                            if (curricularCourseScopesToAssociate.contains(scope)) {
                                throw new DomainException("error.existingExam");
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isExamsMapPublished() {
        for (final ExecutionCourse executionCourse : getAssociatedExecutionCourses()) {
            for (final CurricularCourse curricularCourse : executionCourse.getAssociatedCurricularCourses()) {
                final DegreeCurricularPlan degreeCurricularPlan = curricularCourse.getDegreeCurricularPlan();
                for (final ExecutionDegree executionDegree : degreeCurricularPlan.getExecutionDegrees()) {
                    if (executionCourse.getExecutionPeriod().getExecutionYear() == executionDegree.getExecutionYear()
                            && (executionDegree.isPublishedExam(executionCourse.getExecutionPeriod()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean getIsExamsMapPublished() {
        return isExamsMapPublished();
    }

    public static List<Exam> getAllByRoomAndExecutionPeriod(String room, String executionPeriod, String year) {
        List<Exam> result = new ArrayList<Exam>();

        outter: for (Exam exam : Exam.readExams()) {
            for (WrittenEvaluationSpaceOccupation occupation : exam.getWrittenEvaluationSpaceOccupations()) {
                if (!(occupation.getRoom()).getNome().equals(room)) {
                    continue outter;
                }
            }

            for (ExecutionCourse course : exam.getAssociatedExecutionCourses()) {
                if (!course.getExecutionPeriod().getName().equals(executionPeriod)) {
                    continue outter;
                }

                if (!course.getExecutionPeriod().getExecutionYear().getYear().equals(year)) {
                    continue outter;
                }
            }

            result.add(exam);
        }

        return result;
    }

    public static List<Exam> getAllByDate(Calendar examDay, Calendar examStartTime) {
        return getAllByDate(examDay, examStartTime, null);
    }

    public static List<Exam> getAllByDate(Calendar examDay, Calendar examStartTime, Calendar examEndTime) {
        List<Exam> result = new ArrayList<Exam>();

        outter: for (Exam exam : Exam.readExams()) {
            if (!DateFormatUtil.equalDates("dd/MM/yyyy", examDay.getTime(), exam.getDayDate())) {
                continue;
            }

            if (examStartTime != null && !DateFormatUtil.equalDates("HH:mm", examStartTime.getTime(), exam.getBeginningDate())) {
                continue;
            }

            if (examEndTime != null && !DateFormatUtil.equalDates("HH:mm", examEndTime.getTime(), exam.getEndDate())) {
                continue;
            }

            result.add(exam);
        }

        return result;
    }

    public static List<Exam> readExams() {
        List<Exam> result = new ArrayList<Exam>();

        for (Evaluation evaluation : Bennu.getInstance().getEvaluationsSet()) {
            if (evaluation instanceof Exam) {
                result.add((Exam) evaluation);
            }
        }
        return result;
    }

    @Override
    public boolean isExam() {
        return true;
    }

    public boolean isForSeason(final Season season) {
        return this.getSeason().equals(season);
    }

    public boolean isSpecialSeason() {
        return isForSeason(Season.SPECIAL_SEASON_OBJ);
    }

    @Override
    public boolean canBeAssociatedToRoom(AllocatableSpace room) {
        return room.isFree(getBeginningDateTime().toYearMonthDay(), getEndDateTime().toYearMonthDay(),
                getBeginningDateHourMinuteSecond(), getEndDateHourMinuteSecond(), getDayOfWeek(), null, null, null);
    }

    @Override
    public List<EvaluationEventBean> getAllEvents(Registration registration) {
        return getAllEvents("Exame (" + this.getSeason() + ")", registration);
    }

    @Override
    public String getPresentationName() {
        return BundleUtil.getStringFromResourceBundle("resources.ApplicationResources", "label.exam") + " "
                + BundleUtil.getStringFromResourceBundle("resources.ApplicationResources", getSeason().getKey());
    }

    @Deprecated
    public java.util.Set<net.sourceforge.fenixedu.domain.serviceRequests.documentRequests.ExamDateCertificateRequest> getExamDateCertificateRequests() {
        return getExamDateCertificateRequestsSet();
    }

    @Deprecated
    public boolean hasAnyExamDateCertificateRequests() {
        return !getExamDateCertificateRequestsSet().isEmpty();
    }

    @Deprecated
    public boolean hasSeason() {
        return getSeason() != null;
    }

}
