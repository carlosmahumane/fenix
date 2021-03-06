package net.sourceforge.fenixedu.presentationTier.Action.credits.scientificCouncil;

import net.sourceforge.fenixedu.presentationTier.Action.credits.ManageCreditsReductionsDispatchAction;
import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@Mapping(module = "scientificCouncil", path = "/creditsReductions", functionality = ScientificCouncilViewTeacherCreditsDA.class)
@Forwards(value = { @Forward(name = "editReductionService", path = "/credits/degreeTeachingService/editCreditsReduction.jsp"),
        @Forward(name = "viewAnnualTeachingCredits", path = "/scientificCouncil/credits.do?method=viewAnnualTeachingCredits") })
public class ScientificCouncilManageCreditsReductionsDA extends ManageCreditsReductionsDispatchAction {
}
