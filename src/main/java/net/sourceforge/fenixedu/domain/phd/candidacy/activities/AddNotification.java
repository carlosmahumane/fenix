package net.sourceforge.fenixedu.domain.phd.candidacy.activities;

import net.sourceforge.fenixedu.domain.caseHandling.PreConditionNotValidException;
import net.sourceforge.fenixedu.domain.phd.candidacy.PhdProgramCandidacyProcess;
import net.sourceforge.fenixedu.domain.phd.notification.PhdNotification;
import net.sourceforge.fenixedu.domain.phd.notification.PhdNotificationBean;

import org.fenixedu.bennu.core.domain.User;

public class AddNotification extends PhdProgramCandidacyProcessActivity {
    @Override
    protected void activityPreConditions(PhdProgramCandidacyProcess process, User userView) {
        if (!process.isAllowedToManageProcess(userView)) {
            throw new PreConditionNotValidException();
        }
    }

    @Override
    protected PhdProgramCandidacyProcess executeActivity(PhdProgramCandidacyProcess process, User userView, Object object) {
        new PhdNotification((PhdNotificationBean) object);

        return process;
    }

}
