package net.sourceforge.fenixedu.presentationTier.Action.operator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.applicationTier.Servico.operator.ValidateAlumniIdentity;
import net.sourceforge.fenixedu.domain.AlumniIdentityCheckRequest;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.presentationTier.Action.base.FenixDispatchAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.bennu.portal.EntryPoint;
import org.fenixedu.bennu.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@StrutsFunctionality(app = OperatorApplication.class, path = "alumni-address-validation", titleKey = "alumni.identity.requests",
        bundle = "ManagerResources")
@Mapping(module = "operator", path = "/alumni")
@Forwards(value = { @Forward(name = "alumni.closed.identity.requests", path = "/operator/alumni/viewClosedIdentityRequests.jsp"),
        @Forward(name = "alumni.view.identity.requests.list", path = "/operator/alumni/viewIdentityRequestsList.jsp"),
        @Forward(name = "alumni.validate.request.result", path = "/operator/alumni/validateIdentityRequestResult.jsp"),
        @Forward(name = "alumni.validate.request", path = "/operator/alumni/validateIdentityRequest.jsp") })
public class AlumniIdentityValidationDA extends FenixDispatchAction {

    @EntryPoint
    public ActionForward prepareIdentityRequestsList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        request.setAttribute("identityRequestsList", AlumniIdentityCheckRequest.readPendingRequests());
        return mapping.findForward("alumni.view.identity.requests.list");
    }

    public ActionForward prepareIdentityValidation(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return innerPrepareValidation(mapping, request, (AlumniIdentityCheckRequest) getDomainObject(request, "requestId"),
                (Person) getDomainObject(request, "personId"));
    }

    private ActionForward innerPrepareValidation(ActionMapping mapping, HttpServletRequest request,
            AlumniIdentityCheckRequest checkRequest, Person alumniPerson) {
        request.setAttribute("requestBody", checkRequest);
        request.setAttribute("personBody", alumniPerson);
        request.setAttribute("operation", "validate");
        return mapping.findForward("alumni.validate.request");
    }

    public ActionForward showIdentityValidation(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        request.setAttribute("requestBody", getDomainObject(request, "requestId"));
        request.setAttribute("personBody", getDomainObject(request, "personId"));
        return mapping.findForward("alumni.validate.request");
    }

    public ActionForward confirmIdentity(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        AlumniIdentityCheckRequest identityRequest = (AlumniIdentityCheckRequest) getObjectFromViewState("requestBody");
        ValidateAlumniIdentity.runValidateAlumniIdentity(identityRequest, Boolean.TRUE, getLoggedPerson(request));

        request.setAttribute("identityRequestResult", "identity.validation.ok");
        return mapping.findForward("alumni.validate.request.result");
    }

    public ActionForward refuseIdentity(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        AlumniIdentityCheckRequest identityRequest = (AlumniIdentityCheckRequest) getObjectFromViewState("requestBody");
        ValidateAlumniIdentity.runValidateAlumniIdentity(identityRequest, Boolean.FALSE, getLoggedPerson(request));

        request.setAttribute("identityRequestResult", "identity.validation.nok");
        return mapping.findForward("alumni.validate.request.result");
    }

    public ActionForward viewClosedRequests(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        request.setAttribute("identityRequestsList", AlumniIdentityCheckRequest.readClosedRequests());
        return mapping.findForward("alumni.closed.identity.requests");
    }

    public ActionForward updateSocialSecurityNumber(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        AlumniIdentityCheckRequest checkRequest = getDomainObject(request, "requestId");
        Person alumniPerson = getDomainObject(request, "personId");
        ValidateAlumniIdentity.runValidateAlumniIdentity(checkRequest, alumniPerson);
        return innerPrepareValidation(mapping, request, checkRequest, alumniPerson);
    }

}
