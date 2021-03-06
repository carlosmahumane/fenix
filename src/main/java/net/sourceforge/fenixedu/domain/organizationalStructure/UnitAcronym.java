package net.sourceforge.fenixedu.domain.organizationalStructure;

import net.sourceforge.fenixedu.domain.exceptions.DomainException;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.commons.StringNormalizer;

import pt.ist.fenixframework.dml.runtime.RelationAdapter;

public class UnitAcronym extends UnitAcronym_Base {

    static {
        getRelationUnitUnitAcronym().addListener(new RelationAdapter<Unit, UnitAcronym>() {

            @Override
            public void afterRemove(Unit unit, UnitAcronym unitAcronym) {
                if (unitAcronym != null) {
                    if (!unitAcronym.hasAnyUnits()) {
                        unitAcronym.delete();
                    }
                }
            }

        });
    }

    public UnitAcronym(final String acronym) {
        super();
        setRootDomainObject(Bennu.getInstance());
        setAcronym(acronym);
    }

    @Override
    public void setAcronym(String acronym) {
        super.setAcronym(acronym == null ? null : acronym.toLowerCase());
    }

    public void delete() {
        if (!canBeDeleted()) {
            throw new DomainException("error.unitAcronym.cannot.be.deleted");
        }
        setRootDomainObject(null);
        deleteDomainObject();
    }

    private boolean canBeDeleted() {
        return !hasAnyUnits();
    }

    public static UnitAcronym readUnitAcronymByAcronym(final String acronym) {
        return readUnitAcronymByAcronym(acronym, false);
    }

    public static UnitAcronym readUnitAcronymByAcronym(String acronym, boolean shouldNormalize) {
        if (acronym == null) {
            return null;
        }
        final String acronymLowerCase = shouldNormalize ? normalize(acronym.toLowerCase()) : acronym.toLowerCase();

        for (UnitAcronym unitAcronym : Bennu.getInstance().getUnitAcronymsSet()) {

            if ((shouldNormalize && normalize(unitAcronym.getAcronym()).equals(acronymLowerCase))
                    || unitAcronym.getAcronym().equals(acronymLowerCase)) {
                return unitAcronym;
            }
        }
        return null;
    }

    public static String normalize(final String string) {
        return string == null ? null : StringNormalizer.normalize(string).replace(' ', '-');
    }

    @Deprecated
    public java.util.Set<net.sourceforge.fenixedu.domain.organizationalStructure.Unit> getUnits() {
        return getUnitsSet();
    }

    @Deprecated
    public boolean hasAnyUnits() {
        return !getUnitsSet().isEmpty();
    }

    @Deprecated
    public boolean hasBennu() {
        return getRootDomainObject() != null;
    }

    @Deprecated
    public boolean hasAcronym() {
        return getAcronym() != null;
    }

}
