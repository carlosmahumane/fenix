package net.sourceforge.fenixedu.domain.candidacy.workflow.form;

import java.util.Collections;
import java.util.List;

import net.sourceforge.fenixedu.domain.util.workflow.Form;
import net.sourceforge.fenixedu.util.StudentPersonalDataAuthorizationChoice;
import pt.utl.ist.fenix.tools.resources.LabelFormatter;

public class InquiryAboutYieldingPersonalDataForm extends Form {

    private StudentPersonalDataAuthorizationChoice personalDataAuthorizationChoice;
    private boolean personalDataAuthorizationForStudentsAssociation = true;

    public InquiryAboutYieldingPersonalDataForm() {
        super();
    }

    public InquiryAboutYieldingPersonalDataForm(final StudentPersonalDataAuthorizationChoice personalDataAuthorizationChoice) {
        this();
        this.personalDataAuthorizationChoice = personalDataAuthorizationChoice;
    }

    public StudentPersonalDataAuthorizationChoice getPersonalDataAuthorizationChoice() {
        return personalDataAuthorizationChoice;
    }

    public void setPersonalDataAuthorizationChoice(StudentPersonalDataAuthorizationChoice personalDataAuthorizationChoice) {
        this.personalDataAuthorizationChoice = personalDataAuthorizationChoice;
    }

    public void setPersonalDataAuthorizationForStudentsAssociation(boolean personalDataAuthorizationForStudentsAssociation) {
        this.personalDataAuthorizationForStudentsAssociation = personalDataAuthorizationForStudentsAssociation;
    }

    public boolean isPersonalDataAuthorizationForStudentsAssociation() {
        return personalDataAuthorizationForStudentsAssociation;
    }

    public StudentPersonalDataAuthorizationChoice getPersonalDataAuthorizationForStudentsAssociation() {
        return StudentPersonalDataAuthorizationChoice
                .getPersonalDataAuthorizationForStudentsAssociationType(isPersonalDataAuthorizationForStudentsAssociation());
    }

    @Override
    public List<LabelFormatter> validate() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getFormName() {
        return "label.candidacy.workflow.inquiryAboutYieldingPersonalDataForm";
    }
}