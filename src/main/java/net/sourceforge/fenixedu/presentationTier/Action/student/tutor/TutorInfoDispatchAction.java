package net.sourceforge.fenixedu.presentationTier.Action.student.tutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.dataTransferObject.student.ExecutionPeriodStatisticsBean;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.Tutorship;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.presentationTier.Action.base.FenixDispatchAction;
import net.sourceforge.fenixedu.presentationTier.Action.student.StudentApplication.StudentViewApp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.bennu.portal.EntryPoint;
import org.fenixedu.bennu.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@StrutsFunctionality(app = StudentViewApp.class, path = "tutor-info", titleKey = "link.student.tutorInfo")
@Mapping(module = "student", path = "/viewTutorInfo")
@Forwards(@Forward(name = "ShowStudentTutorInfo", path = "/student/tutor/showStudentTutorInfo.jsp"))
public class TutorInfoDispatchAction extends FenixDispatchAction {

    @EntryPoint
    public ActionForward prepare(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {

        final Person person = getLoggedPerson(request);
        List<Tutorship> pastTutors = new ArrayList<Tutorship>();

        Collection<Registration> registrations = person.getStudent().getRegistrationsSet();

        for (Registration registration : registrations) {
            Collection<StudentCurricularPlan> studentCurricularPlans = registration.getStudentCurricularPlansSet();
            for (StudentCurricularPlan studentCurricularPlan : studentCurricularPlans) {
                for (Tutorship tutorship : studentCurricularPlan.getTutorshipsSet()) {
                    if (tutorship.isActive()) {
                        request.setAttribute("actualTutor", tutorship);
                        request.setAttribute("personID", tutorship.getTeacher().getPerson().getExternalId());
                    } else {
                        pastTutors.add(tutorship);
                    }
                }
            }
        }
        request.setAttribute("pastTutors", pastTutors);
        return prepareStudentStatistics(mapping, actionForm, request, response);
    }

    public ActionForward prepareStudentStatistics(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {

        final Person person = getLoggedPerson(request);
        final Collection<Registration> registrations = person.getStudent().getRegistrationsSet();

        List<ExecutionPeriodStatisticsBean> studentStatistics = getStudentStatistics(registrations);

        request.setAttribute("studentStatistics", studentStatistics);
        return mapping.findForward("ShowStudentTutorInfo");
    }

    /*
     * AUXIALIRY METHODS
     */

    private List<ExecutionPeriodStatisticsBean> getStudentStatistics(Collection<Registration> registrations) {
        List<ExecutionPeriodStatisticsBean> studentStatistics = new ArrayList<ExecutionPeriodStatisticsBean>();

        Map<ExecutionSemester, ExecutionPeriodStatisticsBean> enrolmentsByExecutionPeriod =
                new HashMap<ExecutionSemester, ExecutionPeriodStatisticsBean>();

        for (Registration registration : registrations) {
            for (StudentCurricularPlan studentCurricularPlan : registration.getStudentCurricularPlansSet()) {
                for (ExecutionSemester executionSemester : studentCurricularPlan.getEnrolmentsExecutionPeriods()) {
                    if (enrolmentsByExecutionPeriod.containsKey(executionSemester)) {
                        ExecutionPeriodStatisticsBean executionPeriodStatisticsBean =
                                enrolmentsByExecutionPeriod.get(executionSemester);
                        executionPeriodStatisticsBean.addEnrolmentsWithinExecutionPeriod(studentCurricularPlan
                                .getEnrolmentsByExecutionPeriod(executionSemester));
                        enrolmentsByExecutionPeriod.put(executionSemester, executionPeriodStatisticsBean);
                    } else {
                        ExecutionPeriodStatisticsBean executionPeriodStatisticsBean =
                                new ExecutionPeriodStatisticsBean(executionSemester);
                        executionPeriodStatisticsBean.addEnrolmentsWithinExecutionPeriod(studentCurricularPlan
                                .getEnrolmentsByExecutionPeriod(executionSemester));
                        enrolmentsByExecutionPeriod.put(executionSemester, executionPeriodStatisticsBean);
                    }
                }
            }
        }

        studentStatistics.addAll(enrolmentsByExecutionPeriod.values());

        return studentStatistics;
    }
}
