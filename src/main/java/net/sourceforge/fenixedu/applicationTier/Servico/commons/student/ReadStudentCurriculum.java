package net.sourceforge.fenixedu.applicationTier.Servico.commons.student;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.applicationTier.Filtro.CoordinatorAuthorizationFilter;
import net.sourceforge.fenixedu.applicationTier.Filtro.MasterDegreeAdministrativeOfficeAuthorizationFilter;
import net.sourceforge.fenixedu.applicationTier.Filtro.StudentCurriculumViewAuthorizationFilter;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.NonExistingServiceException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.NotAuthorizedException;
import net.sourceforge.fenixedu.dataTransferObject.InfoEnrolment;
import net.sourceforge.fenixedu.domain.Enrolment;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;

public class ReadStudentCurriculum {

    protected List run(String executionDegreeCode, String studentCurricularPlanID) throws FenixServiceException {

        final StudentCurricularPlan studentCurricularPlan = FenixFramework.getDomainObject(studentCurricularPlanID);
        if (studentCurricularPlan == null) {
            throw new NonExistingServiceException("error.readStudentCurriculum.noStudentCurricularPlan");
        }

        final List<InfoEnrolment> result = new ArrayList<InfoEnrolment>(studentCurricularPlan.getEnrolmentsCount());
        for (final Enrolment enrolment : studentCurricularPlan.getEnrolmentsSet()) {
            result.add(InfoEnrolment.newInfoFromDomain(enrolment));
        }
        return result;
    }

    // Service Invokers migrated from Berserk

    private static final ReadStudentCurriculum serviceInstance = new ReadStudentCurriculum();

    @Atomic
    public static List runReadStudentCurriculum(String executionDegreeCode, String studentCurricularPlanID)
            throws FenixServiceException, NotAuthorizedException {
        try {
            StudentCurriculumViewAuthorizationFilter.instance.execute();
            return serviceInstance.run(executionDegreeCode, studentCurricularPlanID);
        } catch (NotAuthorizedException ex2) {
            try {
                CoordinatorAuthorizationFilter.instance.execute();
                return serviceInstance.run(executionDegreeCode, studentCurricularPlanID);
            } catch (NotAuthorizedException ex3) {
                try {
                    MasterDegreeAdministrativeOfficeAuthorizationFilter.instance.execute();
                    return serviceInstance.run(executionDegreeCode, studentCurricularPlanID);
                } catch (NotAuthorizedException ex4) {
                    throw ex4;
                }
            }
        }
    }

}