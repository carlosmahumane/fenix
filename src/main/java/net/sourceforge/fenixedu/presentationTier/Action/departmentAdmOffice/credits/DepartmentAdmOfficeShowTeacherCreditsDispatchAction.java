package net.sourceforge.fenixedu.presentationTier.Action.departmentAdmOffice.credits;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Department;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.organizationalStructure.DepartmentUnit;
import net.sourceforge.fenixedu.domain.organizationalStructure.Unit;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.presentationTier.Action.credits.ShowTeacherCreditsDispatchAction;
import net.sourceforge.fenixedu.presentationTier.Action.credits.departmentAdmOffice.DepartmentAdmOfficeViewTeacherCreditsDA;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.security.Authenticate;

import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixframework.FenixFramework;

@Mapping(module = "departmentAdmOffice", path = "/showFullTeacherCreditsSheet", formBean = "teacherSearchForm",
        functionality = DepartmentAdmOfficeViewTeacherCreditsDA.class)
@Forwards(value = {
        @Forward(name = "show-teacher-credits", path = "/departmentAdmOffice/credits/showTeacherCredits.jsp"),
        @Forward(name = "teacher-not-found",
                path = "/departmentAdmOffice/showAllTeacherCreditsResume.do?method=showTeacherCreditsResume&page=0") })
public class DepartmentAdmOfficeShowTeacherCreditsDispatchAction extends ShowTeacherCreditsDispatchAction {

    public ActionForward showTeacherCredits(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, FenixServiceException, ParseException {

        DynaActionForm teacherCreditsForm = (DynaActionForm) form;
        ExecutionSemester executionSemester =
                FenixFramework.getDomainObject((String) teacherCreditsForm.get("executionPeriodId"));
        Teacher teacher = FenixFramework.getDomainObject((String) teacherCreditsForm.get("teacherId"));

        if (teacher == null || !isTeacherOfManageableDepartments(teacher, executionSemester, request)) {
            request.setAttribute("teacherNotFound", "teacherNotFound");
            return mapping.findForward("teacher-not-found");
        }

        showLinks(request, executionSemester, RoleType.DEPARTMENT_ADMINISTRATIVE_OFFICE);
        getAllTeacherCredits(request, executionSemester, teacher);
        return mapping.findForward("show-teacher-credits");
    }

    private boolean isTeacherOfManageableDepartments(Teacher teacher, ExecutionSemester executionSemester,
            HttpServletRequest request) {
        User userView = Authenticate.getUser();
        Collection<Department> manageableDepartments = userView.getPerson().getManageableDepartmentCredits();
        List<Unit> workingPlacesByPeriod =
                teacher.getWorkingPlacesByPeriod(executionSemester.getBeginDateYearMonthDay(),
                        executionSemester.getEndDateYearMonthDay());
        for (Unit unit : workingPlacesByPeriod) {
            DepartmentUnit departmentUnit = unit.getDepartmentUnit();
            Department teacherDepartment = departmentUnit != null ? departmentUnit.getDepartment() : null;
            if (manageableDepartments.contains(teacherDepartment)) {
                return true;
            }
        }
        return false;
    }
}
