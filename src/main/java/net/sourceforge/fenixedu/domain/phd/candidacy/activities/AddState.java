package net.sourceforge.fenixedu.domain.phd.candidacy.activities;

import net.sourceforge.fenixedu.domain.caseHandling.PreConditionNotValidException;
import net.sourceforge.fenixedu.domain.phd.PhdCandidacyProcessState;
import net.sourceforge.fenixedu.domain.phd.candidacy.PhdProgramCandidacyProcess;
import net.sourceforge.fenixedu.domain.phd.candidacy.PhdProgramCandidacyProcessBean;

import org.fenixedu.bennu.core.domain.User;

public class AddState extends PhdProgramCandidacyProcessActivity {

    @Override
    protected void activityPreConditions(PhdProgramCandidacyProcess process, User userView) {
        if (!process.isAllowedToManageProcess(userView)) {
            throw new PreConditionNotValidException();
        }
    }

    @Override
    protected PhdProgramCandidacyProcess executeActivity(PhdProgramCandidacyProcess process, User userView, Object object) {
        PhdProgramCandidacyProcessBean bean = (PhdProgramCandidacyProcessBean) object;

        PhdCandidacyProcessState.createStateWithGivenStateDate(process, bean.getState(), userView.getPerson(), "", bean
                .getStateDate().toDateTimeAtStartOfDay());

        return process;
    }

}
