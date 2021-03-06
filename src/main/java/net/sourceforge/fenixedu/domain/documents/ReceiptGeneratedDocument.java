package net.sourceforge.fenixedu.domain.documents;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.accessControl.RoleGroup;
import net.sourceforge.fenixedu.domain.accounting.Receipt;
import net.sourceforge.fenixedu.domain.organizationalStructure.Party;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.injectionCode.AccessControl;

import org.fenixedu.bennu.core.groups.Group;

import pt.ist.fenixframework.Atomic;

/**
 * @author Pedro Santos (pmrsa)
 */
public class ReceiptGeneratedDocument extends ReceiptGeneratedDocument_Base {
    protected ReceiptGeneratedDocument(Receipt source, Party addressee, Person operator, String filename, byte[] content) {
        super();
        setSource(source);
        init(GeneratedDocumentType.RECEIPT, addressee, operator, filename, content);
    }

    @Override
    protected Group computePermittedGroup() {
        return RoleGroup.get(RoleType.MANAGER);
    }

    @Override
    public void delete() {
        setSource(null);
        super.delete();
    }

    @Atomic
    public static void store(Receipt source, String filename, byte[] content) {
        new ReceiptGeneratedDocument(source, source.getPerson(), AccessControl.getPerson(), filename, content);
    }

    @Deprecated
    public boolean hasSource() {
        return getSource() != null;
    }

}
