package net.sourceforge.fenixedu.applicationTier.Servico.commons.searchers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import net.sourceforge.fenixedu.applicationTier.Servico.commons.AutoCompleteSearchService;
import net.sourceforge.fenixedu.domain.Person;

public class SearchPeopleByNameOrISTID extends SearchPeople{

    protected Collection search(final String value, final int size) {
	if (value.length() > 3 && value.substring(0, 3).equals("ist")) {
	    ArrayList<Person> result = new ArrayList<Person>();
	    result.add(Person.readPersonByIstUsername(value));
	    return result;
	} else {
	    return super.search(value, size);

	}
    }

}