package net.sourceforge.fenixedu.applicationTier.Servico.thesis;

import java.io.IOException;
import java.util.Locale;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.accessControl.RoleGroup;
import net.sourceforge.fenixedu.domain.accessControl.ScientificCommissionGroup;
import net.sourceforge.fenixedu.domain.accessControl.ThesisReadersGroup;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.domain.thesis.Thesis;
import net.sourceforge.fenixedu.domain.thesis.ThesisFile;

import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.bennu.core.security.Authenticate;

public abstract class CreateThesisFile {

    public ThesisFile run(Thesis thesis, byte[] bytes, String fileName, String title, String subTitle, Locale language)
            throws FenixServiceException, IOException {

        if (!thesis.isWaitingConfirmation() && !Authenticate.getUser().getPerson().hasRole(RoleType.SCIENTIFIC_COUNCIL)) {
            throw new DomainException("thesis.files.submit.unavailable");
        }

        if (!thesis.isDeclarationAccepted() && !Authenticate.getUser().getPerson().hasRole(RoleType.SCIENTIFIC_COUNCIL)) {
            throw new DomainException("thesis.files.submit.unavailable");
        }

        removePreviousFile(thesis);

        if (bytes == null || fileName == null) {
            return null;
        }
        RoleType roleType = RoleType.SCIENTIFIC_COUNCIL;

        Group scientificCouncil = RoleGroup.get(roleType);
        Group commissionMembers = ScientificCommissionGroup.get(thesis.getDegree());
        Group student = thesis.getStudent().getPerson().getPersonGroup();
        Group thesisGroup = ThesisReadersGroup.get(thesis);
        final Group permittedGroup = scientificCouncil.or(commissionMembers).or(student).or(thesisGroup);

        ThesisFile file = new ThesisFile(fileName, fileName, bytes, permittedGroup);

        updateThesis(thesis, file, title, subTitle, language, fileName, bytes);

        return file;
    }

    protected abstract void removePreviousFile(Thesis thesis);

    protected abstract void updateThesis(Thesis thesis, ThesisFile file, String title, String subTitle, Locale language,
            String fileName, byte[] bytes) throws FenixServiceException, IOException;

}
