package net.sourceforge.fenixedu.presentationTier.Action.commons;

import net.sourceforge.fenixedu.presentationTier.Action.masterDegree.administrativeOffice.MasterDegreeOfficeApplication.MasterDegreeCandidatesApp;

import org.fenixedu.bennu.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@StrutsFunctionality(app = MasterDegreeCandidatesApp.class, path = "select-candidates",
        titleKey = "link.masterDegree.administrativeOffice.selectCandidates")
@Mapping(path = "/chooseExecutionYearToSelectCandidates", module = "masterDegreeAdministrativeOffice",
        input = "/chooseExecutionYear_bd.jsp", formBean = "chooseExecutionYearForm")
@Forwards({
        @Forward(name = "DisplayMasterDegreeList",
                path = "/masterDegreeAdministrativeOffice/candidate/displayMasterDegreesToEditCandidates_bd.jsp"),
        @Forward(
                name = "MasterDegreeReady",
                path = "/masterDegreeAdministrativeOffice/candidate/displayCurricularPlanByChosenMasterDegreeToEditCandidates_bd.jsp"),
        @Forward(name = "PrepareSuccess", path = "/masterDegreeAdministrativeOffice/chooseExecutionYear_bd.jsp"),
        @Forward(name = "ChooseSuccess",
                path = "/masterDegreeAdministrativeOffice/displayListToSelectCandidates.do?method=prepareSelectCandidates&page=0") })
public class ChooseExecutionYearToSelectCandidatesDA extends ChooseExecutionYearDispatchAction {

}
