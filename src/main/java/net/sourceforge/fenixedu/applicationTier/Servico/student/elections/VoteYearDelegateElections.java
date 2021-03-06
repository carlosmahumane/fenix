package net.sourceforge.fenixedu.applicationTier.Servico.student.elections;

import static net.sourceforge.fenixedu.injectionCode.AccessControl.check;

import java.util.ResourceBundle;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.elections.DelegateElectionBlankVote;
import net.sourceforge.fenixedu.domain.elections.DelegateElectionVote;
import net.sourceforge.fenixedu.domain.elections.DelegateElectionVotingPeriod;
import net.sourceforge.fenixedu.domain.elections.YearDelegateElection;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.student.Student;
import net.sourceforge.fenixedu.domain.util.email.ConcreteReplyTo;
import net.sourceforge.fenixedu.domain.util.email.Message;
import net.sourceforge.fenixedu.domain.util.email.Recipient;
import net.sourceforge.fenixedu.predicates.RolePredicates;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.groups.UserGroup;
import org.fenixedu.commons.i18n.I18N;

import pt.ist.fenixframework.Atomic;

public class VoteYearDelegateElections {

    @Atomic
    public static void run(YearDelegateElection yearDelegateElection, Student student, Student votedStudent)
            throws FenixServiceException {
        check(RolePredicates.STUDENT_PREDICATE);

        final ResourceBundle bundle = ResourceBundle.getBundle("resources.DelegateResources", I18N.getLocale());
        DelegateElectionVotingPeriod votingPeriod = yearDelegateElection.getCurrentVotingPeriod();

        try {
            if (!votingPeriod.getVotingStudents().contains(student)) {
                final String fromName = bundle.getString("VoteYearDelegateElections.email.fromName");
                final String fromAddress = bundle.getString("VoteYearDelegateElections.email.fromAddress");
                final String subject = fromName + "-" + bundle.getString("VoteYearDelegateElections.email.subject");
                final String msg = bundle.getString("VoteYearDelegateElections.email.message");
                final Person person = student.getPerson();
                DelegateElectionVote vote = createDelegateElectionVote(votingPeriod, votedStudent);
                votingPeriod.addVotingStudents(student);
                votingPeriod.addVotes(vote);
                new Message(Bennu.getInstance().getSystemSender(), new ConcreteReplyTo(fromAddress).asCollection(),
                        new Recipient(UserGroup.of(person.getUser())).asCollection(), subject, msg, "");
            } else {
                throw new FenixServiceException("error.student.elections.voting.studentAlreadyVoted");
            }
        } catch (DomainException ex) {
            throw new FenixServiceException(ex.getMessage(), ex.getArgs());
        }
    }

    private static DelegateElectionVote createDelegateElectionVote(DelegateElectionVotingPeriod votingPeriod, Student votedStudent) {
        if (DelegateElectionBlankVote.isBlankVote(votedStudent)) {
            return new DelegateElectionBlankVote(votingPeriod);
        }
        return new DelegateElectionVote(votingPeriod, votedStudent);
    }

}