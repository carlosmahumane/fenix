package net.sourceforge.fenixedu.domain.phd.debts;

import net.sourceforge.fenixedu.domain.accounting.Account;
import net.sourceforge.fenixedu.domain.accounting.AccountType;
import net.sourceforge.fenixedu.domain.accounting.Exemption;
import net.sourceforge.fenixedu.domain.accounting.PostingRule;
import net.sourceforge.fenixedu.domain.phd.PhdProgram;
import net.sourceforge.fenixedu.domain.phd.PhdProgramUnit;

abstract public class PhdEvent extends PhdEvent_Base {

    protected PhdEvent() {
	super();
    }

    @Override
    protected Account getFromAccount() {
	return getPerson().getAccountBy(AccountType.EXTERNAL);
    }

    @Override
    public Account getToAccount() {
	return getUnit().getAccountBy(AccountType.INTERNAL);
    }

    protected PhdProgramUnit getUnit() {
	return getPhdProgram().getPhdProgramUnit();
    }

    abstract protected PhdProgram getPhdProgram();

    @Override
    public PostingRule getPostingRule() {
	return getPhdProgram().getServiceAgreementTemplate().findPostingRuleByEventTypeAndDate(getEventType(), getWhenOccured());
    }

    @Override
    public boolean isExemptionAppliable() {
	return true;
    }

    public boolean hasPhdEventExemption() {
	return getPhdEventExemption() != null;
    }

    public PhdEventExemption getPhdEventExemption() {
	for (final Exemption exemption : getExemptionsSet()) {
	    if (exemption instanceof PhdEventExemption) {
		return (PhdEventExemption) exemption;
	    }
	}
	return null;
    }

}