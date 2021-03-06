package net.sourceforge.fenixedu.domain.student.registrationStates;

import java.util.Collections;
import java.util.Set;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.student.Registration;

import org.joda.time.DateTime;

/**
 * 
 * @author - Shezad Anavarali (shezad@ist.utl.pt)
 * 
 */
public class ExternalAbandonState extends ExternalAbandonState_Base {

    protected ExternalAbandonState(Registration registration, Person person, DateTime dateTime) {
        super();
        init(registration, person, dateTime);
    }

    @Override
    public Set<String> getValidNextStates() {
        return Collections.singleton(RegistrationStateType.REGISTERED.name());
    }

    @Override
    public RegistrationStateType getStateType() {
        return RegistrationStateType.EXTERNAL_ABANDON;
    }

}
