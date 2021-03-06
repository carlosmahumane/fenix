package net.sourceforge.fenixedu.domain.accessControl;

import java.util.HashSet;
import java.util.Set;

import org.fenixedu.bennu.core.annotation.GroupOperator;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.groups.PersistentGroup;
import org.joda.time.DateTime;

import com.google.common.base.Objects;

@GroupOperator("researchAuthor")
public class ResearchAuthorGroup extends FenixGroup {
    private static final long serialVersionUID = 5155805498915779696L;

    private static final ResearchAuthorGroup INSTANCE = new ResearchAuthorGroup();

    private ResearchAuthorGroup() {
        super();
    }

    public static ResearchAuthorGroup get() {
        return INSTANCE;
    }

    @Override
    public Set<User> getMembers() {
        Set<User> users = new HashSet<>();
        for (User user : Bennu.getInstance().getUserSet()) {
            if (!user.getPerson().getResultParticipationsSet().isEmpty()) {
                users.add(user);
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
        return user != null && !user.getPerson().getResultParticipationsSet().isEmpty();
    }

    @Override
    public boolean isMember(User user, DateTime when) {
        return isMember(user);
    }

    @Override
    public PersistentGroup toPersistentGroup() {
        return PersistentResearchAuthorGroup.getInstance();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof ResearchAuthorGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ResearchAuthorGroup.class);
    }
}
