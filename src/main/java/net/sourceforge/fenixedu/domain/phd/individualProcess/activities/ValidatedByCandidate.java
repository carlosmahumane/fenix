package net.sourceforge.fenixedu.domain.phd.individualProcess.activities;

import net.sourceforge.fenixedu.domain.phd.PhdIndividualProgramProcess;

import org.fenixedu.bennu.core.domain.User;

public class ValidatedByCandidate extends PhdIndividualProgramProcessActivity {

    @Override
    protected void activityPreConditions(PhdIndividualProgramProcess process, User userView) {
    }

    @Override
    protected PhdIndividualProgramProcess executeActivity(PhdIndividualProgramProcess process, User userView, Object object) {
        process.getCandidacyProcess().executeActivity(userView,
                net.sourceforge.fenixedu.domain.phd.candidacy.activities.ValidatedByCandidate.class.getSimpleName(), null);
        return process;
    }
}