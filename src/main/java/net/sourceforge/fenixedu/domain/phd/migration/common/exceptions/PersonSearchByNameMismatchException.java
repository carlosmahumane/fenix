package net.sourceforge.fenixedu.domain.phd.migration.common.exceptions;

import java.util.Set;

import net.sourceforge.fenixedu.domain.Person;

public class PersonSearchByNameMismatchException extends PhdMigrationException {

    public PersonSearchByNameMismatchException(Set<Person> possibleCandidates) {
        super(getPersonNames(possibleCandidates));
    }

    static private String getPersonNames(Set<Person> possibleCandidates) {
        StringBuilder builder = new StringBuilder();

        for (Person person : possibleCandidates) {
            builder.append(person.getName() + " ;");
        }

        return builder.toString();
    }

}
