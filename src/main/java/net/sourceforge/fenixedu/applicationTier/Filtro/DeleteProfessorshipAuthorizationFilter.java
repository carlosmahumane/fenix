package net.sourceforge.fenixedu.applicationTier.Filtro;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.NotAuthorizedException;
import net.sourceforge.fenixedu.domain.ExecutionCourse;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Professorship;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.person.RoleType;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.security.Authenticate;

import pt.ist.fenixframework.FenixFramework;

public class DeleteProfessorshipAuthorizationFilter extends AuthorizationByRoleFilter {

    public static final DeleteProfessorshipAuthorizationFilter instance = new DeleteProfessorshipAuthorizationFilter();

    @Override
    protected RoleType getRoleType() {
        return RoleType.TEACHER;
    }

    public void execute(String executionCourseID, String selectedTeacherID) throws NotAuthorizedException {
        User id = Authenticate.getUser();

        try {
            final Person loggedPerson = id.getPerson();

            Teacher teacher = FenixFramework.getDomainObject(selectedTeacherID);
            ExecutionCourse executionCourse = FenixFramework.getDomainObject(executionCourseID);

            Professorship selectedProfessorship = null;
            if (teacher != null) {
                selectedProfessorship = teacher.getProfessorshipByExecutionCourse(executionCourse);
            }

            if ((id == null) || (selectedProfessorship == null) || (id.getPerson().getPersonRolesSet() == null)
                    || !id.getPerson().hasRole(getRoleType())
                    || isSamePersonAsBeingRemoved(loggedPerson, selectedProfessorship.getPerson())
                    || selectedProfessorship.getResponsibleFor()) {
                throw new NotAuthorizedException();
            }
        } catch (RuntimeException e) {
            throw new NotAuthorizedException();
        }
    }

    private boolean isSamePersonAsBeingRemoved(Person loggedPerson, Person selectedPerson) {
        return loggedPerson == selectedPerson;
    }

}
