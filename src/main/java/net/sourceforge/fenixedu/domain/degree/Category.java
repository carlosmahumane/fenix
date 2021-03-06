package net.sourceforge.fenixedu.domain.degree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public enum Category {

    CODE_71(71, LineLayout.EMPLOYEE, newDegreeTypeSet()),

    CODE_72(72, LineLayout.EMPLOYEE, newDegreeTypeSet()),

    CODE_73(73, LineLayout.MULTIPE, newDegreeTypeSet()),

    CODE_81(81, LineLayout.EMPLOYEE, newDegreeTypeSet()),

    CODE_82(82, LineLayout.MULTIPE, newDegreeTypeSet()),

    CODE_83(83, LineLayout.EMPLOYEE, newDegreeTypeSet()),

    CODE_92(92, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.DEGREE, DegreeType.BOLONHA_DEGREE)),

    CODE_94(94, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.MASTER_DEGREE, DegreeType.BOLONHA_MASTER_DEGREE,
            DegreeType.BOLONHA_INTEGRATED_MASTER_DEGREE)),

    CODE_95(95, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.BOLONHA_ADVANCED_SPECIALIZATION_DIPLOMA)),

    CODE_96(96, LineLayout.MULTIPE, newDegreeTypeSet()),

    CODE_97(97, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.BOLONHA_ADVANCED_FORMATION_DIPLOMA)),

    CODE_98(98, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.BOLONHA_SPECIALIZATION_DEGREE)),

    CODE_99(99, LineLayout.STUDENT, newDegreeTypeSet(DegreeType.EMPTY));

    private static final Set<DegreeType> newDegreeTypeSet(final DegreeType... degreeTypes) {
        final Set<DegreeType> result = new HashSet<DegreeType>();
        for (final DegreeType degreeType : degreeTypes) {
            result.add(degreeType);
        }
        return result;
    }

    private final int code;
    private final LineLayout lineLayout;
    private final Set<DegreeType> degreeTypes;

    private Category(final int code, final LineLayout lineLayout, final Set<DegreeType> degreeTypes) {
        this.code = code;
        this.lineLayout = lineLayout;
        this.degreeTypes = Collections.unmodifiableSortedSet(new TreeSet<DegreeType>(degreeTypes));
    }

    public String getName() {
        return name();
    }

    public int getCode() {
        return code;
    }

    public LineLayout getLineLayout() {
        return lineLayout;
    }

    public Set<DegreeType> getDegreeTypes() {
        return degreeTypes;
    }

    public static Category valueOf(final int code) {
        for (Category category : values()) {
            if (category.code == code) {
                return category;
            }
        }
        return null;
    }

}
