package net.sourceforge.fenixedu.applicationTier.Servico.commons.externalPerson;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.ExistingServiceException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Country;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.organizationalStructure.ExternalContract;
import net.sourceforge.fenixedu.domain.organizationalStructure.Unit;
import net.sourceforge.fenixedu.domain.person.Gender;
import net.sourceforge.fenixedu.domain.person.IDDocumentType;

import org.joda.time.YearMonthDay;

import pt.ist.fenixframework.Atomic;

public class InsertExternalPerson {

    public static class ServiceArguments {
        private final Unit unit;
        private final String personName;

        public ServiceArguments(String personName, Unit unit) {
            this.unit = unit;
            this.personName = personName;
        }

        public String getPersonName() {
            return personName;
        }

        public Unit getUnit() {
            return unit;
        }

    }

    @Atomic
    public static ExternalContract run(String personName, String organizationName) {
        final Unit organization = Unit.createNewNoOfficialExternalInstitution(organizationName);
        Person externalPerson =
                Person.createExternalPerson(personName, Gender.MALE, null, null, null, null, null,
                        String.valueOf(System.currentTimeMillis()), IDDocumentType.EXTERNAL);

        return new ExternalContract(externalPerson, organization, new YearMonthDay(), null);
    }

    @Atomic
    public static ExternalContract run(String personName, String organizationName, Country country) {
        final Unit organization = Unit.createNewNoOfficialExternalInstitution(organizationName, country);
        Person externalPerson =
                Person.createExternalPerson(personName, Gender.MALE, null, null, null, null, null,
                        String.valueOf(System.currentTimeMillis()), IDDocumentType.EXTERNAL);

        return new ExternalContract(externalPerson, organization, new YearMonthDay(), null);
    }

    @Atomic
    public static ExternalContract run(ServiceArguments arguments) throws FenixServiceException {

        ExternalContract storedExternalContract = null;
        String personName = arguments.getPersonName();
        Unit organization = arguments.getUnit();
        storedExternalContract =
                ExternalContract.readByPersonNameAddressAndInstitutionID(personName, null, organization.getExternalId());
        if (storedExternalContract != null) {
            throw new ExistingServiceException("error.exception.commons.ExternalContract.existingExternalContract");
        }

        Person externalPerson =
                Person.createExternalPerson(personName, Gender.MALE, null, null, null, null, null,
                        String.valueOf(System.currentTimeMillis()), IDDocumentType.EXTERNAL);
        return new ExternalContract(externalPerson, organization, new YearMonthDay(), null);
    }

    @Atomic
    public static ExternalContract runWithOrganizationName(String name, String organizationName, String phone, String mobile,
            String email) throws FenixServiceException {

        final Unit organization = Unit.createNewNoOfficialExternalInstitution(organizationName);
        Person externalPerson =
                Person.createExternalPerson(name, Gender.MALE, null, phone, mobile, null, email,
                        String.valueOf(System.currentTimeMillis()), IDDocumentType.EXTERNAL);

        return new ExternalContract(externalPerson, organization, new YearMonthDay(), null);
    }

    @Atomic
    public static ExternalContract run(String name, Unit institutionLocation, String phone, String mobile, String email)
            throws FenixServiceException {

        Person externalPerson =
                Person.createExternalPerson(name, Gender.MALE, null, phone, mobile, null, email,
                        String.valueOf(System.currentTimeMillis()), IDDocumentType.EXTERNAL);

        return new ExternalContract(externalPerson, institutionLocation, new YearMonthDay(), null);
    }
}