package net.sourceforge.fenixedu.domain.cardGeneration;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.RootDomainObject;

import org.joda.time.LocalDate;

public class CardGenerationRegister extends CardGenerationRegister_Base {

    public CardGenerationRegister() {
        super();
        setRootDomainObject(RootDomainObject.getInstance());
    }

    public CardGenerationRegister(final Person person, final String linePrefix, final LocalDate emission,
            final Boolean withAccountInformation) {
        this();
        setPerson(person);
        setEmission(emission);
        setWithAccountInformation(withAccountInformation);
        setLinePrefix(linePrefix);
    }

    public void delete() {
        setPerson(null);
        setRootDomainObject(null);
        deleteDomainObject();
    }

    @Deprecated
    public boolean hasRootDomainObject() {
        return getRootDomainObject() != null;
    }

    @Deprecated
    public boolean hasWithAccountInformation() {
        return getWithAccountInformation() != null;
    }

    @Deprecated
    public boolean hasLinePrefix() {
        return getLinePrefix() != null;
    }

    @Deprecated
    public boolean hasEmission() {
        return getEmission() != null;
    }

    @Deprecated
    public boolean hasPerson() {
        return getPerson() != null;
    }

}
