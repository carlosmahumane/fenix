package net.sourceforge.fenixedu.domain.accessControl;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.fenixedu.domain.Degree;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.student.Registration;

import org.fenixedu.bennu.core.annotation.GroupArgument;
import org.fenixedu.bennu.core.annotation.GroupOperator;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.groups.PersistentGroup;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.YearMonthDay;

import com.google.common.base.Objects;

@GroupOperator("studentsConcluded")
public class StudentsConcludedInExecutionYearGroup extends FenixGroup {
    private static final long serialVersionUID = 5303530923037645419L;

    @GroupArgument
    private Degree degree;

    @GroupArgument
    private ExecutionYear conclusionYear;

    private StudentsConcludedInExecutionYearGroup() {
        super();
    }

    private StudentsConcludedInExecutionYearGroup(Degree degree, ExecutionYear conclusionYear) {
        this();
        this.degree = degree;
        this.conclusionYear = conclusionYear;
    }

    public static StudentsConcludedInExecutionYearGroup get(Degree degree, ExecutionYear conclusionYear) {
        return new StudentsConcludedInExecutionYearGroup(degree, conclusionYear);
    }

    @Override
    public String[] getPresentationNameKeyArgs() {
        return new String[] { degree.getPresentationName(), conclusionYear.getName() };
    }

    @Override
    public Set<User> getMembers() {
        Set<User> users = new HashSet<User>();
        for (Registration registration : degree.getRegistrationsSet()) {
            if (registration.hasConcluded()) {
                LocalDate conclusionDate = getConclusionDate(degree, registration);
                if (conclusionDate != null
                        && (conclusionDate.getYear() == conclusionYear.getEndCivilYear() || conclusionDate.getYear() == conclusionYear
                                .getBeginCivilYear())) {
                    User user = registration.getPerson().getUser();
                    if (user != null) {
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }

    @Override
    public Set<User> getMembers(DateTime when) {
        return getMembers();
    }

    @Override
    public boolean isMember(User user) {
        if (user == null || user.getPerson().getStudent() == null) {
            return false;
        }
        for (final Registration registration : user.getPerson().getStudent().getRegistrationsSet()) {
            if (registration.isConcluded() && registration.getDegree().equals(degree)) {
                LocalDate conclusionDate = getConclusionDate(registration.getDegree(), registration);
                if (conclusionDate != null
                        && (conclusionDate.getYear() == conclusionYear.getEndCivilYear() || conclusionDate.getYear() == conclusionYear
                                .getBeginCivilYear())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isMember(User user, DateTime when) {
        return isMember(user);
    }

    private LocalDate getConclusionDate(Degree degree, Registration registration) {
        for (StudentCurricularPlan scp : registration.getStudentCurricularPlansByDegree(degree)) {
            if (registration.isBolonha()) {
                if (scp.getLastConcludedCycleCurriculumGroup() != null) {
                    YearMonthDay conclusionDate =
                            registration.getConclusionDate(scp.getLastConcludedCycleCurriculumGroup().getCycleType());
                    if (conclusionDate != null) {
                        return conclusionDate.toLocalDate();
                    }
                }
                return null;
            } else {
                return registration.getConclusionDate() != null ? registration.getConclusionDate().toLocalDate() : null;
            }
        }
        return null;
    }

    @Override
    public PersistentGroup toPersistentGroup() {
        return PersistentStudentsConcludedInExecutionYearGroup.getInstance(degree, conclusionYear);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StudentsConcludedInExecutionYearGroup) {
            StudentsConcludedInExecutionYearGroup other = (StudentsConcludedInExecutionYearGroup) object;
            return Objects.equal(degree, other.degree) && Objects.equal(conclusionYear, other.conclusionYear);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(degree, conclusionYear);
    }
}
