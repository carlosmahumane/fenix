package net.sourceforge.fenixedu.presentationTier.Action.degreeAdministrativeOffice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sourceforge.fenixedu.applicationTier.IUserView;
import net.sourceforge.fenixedu.applicationTier.Filtro.exception.NotAuthorizedFilterException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.InfoCountry;
import net.sourceforge.fenixedu.dataTransferObject.InfoPerson;
import net.sourceforge.fenixedu.domain.person.Gender;
import net.sourceforge.fenixedu.domain.person.IDDocumentType;
import net.sourceforge.fenixedu.domain.person.MaritalStatus;
import net.sourceforge.fenixedu.framework.factory.ServiceManagerServiceFactory;
import net.sourceforge.fenixedu.presentationTier.Action.exceptions.FenixActionException;
import net.sourceforge.fenixedu.presentationTier.Action.sop.utils.SessionConstants;
import net.sourceforge.fenixedu.util.Data;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

public class EditStudentPersonInfoDispatchAction extends DispatchAction {
    

    public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        //      Clear the Session
        session.removeAttribute(SessionConstants.NATIONALITY_LIST_KEY);
        session.removeAttribute(SessionConstants.MARITAL_STATUS_LIST_KEY);
        session.removeAttribute(SessionConstants.IDENTIFICATION_DOCUMENT_TYPE_LIST_KEY);
        session.removeAttribute(SessionConstants.SEX_LIST_KEY);
        session.removeAttribute(SessionConstants.MONTH_DAYS_KEY);
        session.removeAttribute(SessionConstants.MONTH_LIST_KEY);
        session.removeAttribute(SessionConstants.YEARS_KEY);
        session.removeAttribute(SessionConstants.EXPIRATION_YEARS_KEY);
        session.removeAttribute(SessionConstants.CANDIDATE_SITUATION_LIST);

        if (session != null) {
            IUserView userView = (IUserView) session.getAttribute(SessionConstants.U_VIEW);
            Integer personId = 0;
            try {
                personId = new Integer(getFromRequest("personId", request));
            } catch (NumberFormatException numberFormatException) {
                ActionErrors errors = new ActionErrors();
                errors.add("nonExisting", new ActionError("error.exception.noStudents"));
                saveErrors(request, errors);
                return mapping.findForward("Insuccess");
            }
            
            Integer studentNumber = new Integer(getFromRequest("studentNumber", request));
            request.setAttribute("studentNumber", studentNumber);

            InfoPerson infoPerson = null;
            Object args[] = { personId };
            try {
                infoPerson = (InfoPerson) ServiceManagerServiceFactory.executeService(userView,
                        "ReadPersonByID", args);
            } catch (FenixServiceException e) {
                throw new FenixActionException(e);
            }

            if (infoPerson == null) {
                ActionErrors errors = new ActionErrors();
                errors.add("nonExisting", new ActionError("error.exception.noStudents"));
                saveErrors(request, errors);
                return mapping.findForward("NoStudent");
            }
            request.setAttribute("infoPerson", infoPerson);
            
            DynaActionForm changeApplicationInfoForm = (DynaActionForm) form;
            populateForm(changeApplicationInfoForm, infoPerson);

            //          Get List of available Countries
            Object result = null;
            result = ServiceManagerServiceFactory.executeService(userView, "ReadAllCountries", null);
            List country = (ArrayList) result;

            //          Build List of Countries for the Form
            Iterator iterador = country.iterator();

            List nationalityList = new ArrayList();
            while (iterador.hasNext()) {
                InfoCountry countryTemp = (InfoCountry) iterador.next();
                nationalityList.add(new LabelValueBean(countryTemp.getNationality(), countryTemp
                        .getNationality()));
            }

            session.setAttribute(SessionConstants.NATIONALITY_LIST_KEY, nationalityList);
            session.setAttribute(SessionConstants.SEX_LIST_KEY, Gender.getSexLabelValues((Locale) request.getAttribute(Globals.LOCALE_KEY)));
            session.setAttribute(SessionConstants.MONTH_DAYS_KEY, Data.getMonthDays());
            session.setAttribute(SessionConstants.MONTH_LIST_KEY, Data.getMonths());
            session.setAttribute(SessionConstants.YEARS_KEY, Data.getYears());
            session.setAttribute(SessionConstants.EXPIRATION_YEARS_KEY, Data.getExpirationYears());

            return mapping.findForward("Success");
        }
        throw new Exception();
    }

    
    public ActionForward change(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        if (session != null) {
            DynaActionForm changeApplicationInfoForm = (DynaActionForm) form;
            IUserView userView = (IUserView) session.getAttribute(SessionConstants.U_VIEW);

            Integer personCode = Integer.valueOf(getFromRequest("idInternal", request));
            
            Integer studentNumber = new Integer(getFromRequest("studentNumber", request));
            request.setAttribute("studentNumber", studentNumber);
            
            // Create Dates

            Calendar birthDate = Calendar.getInstance();
            Calendar idDocumentIssueDate = Calendar.getInstance();
            Calendar idDocumentExpirationDate = Calendar.getInstance();

            InfoPerson infoPerson = new InfoPerson();
            infoPerson.setIdInternal(personCode);
            String dayString = (String) changeApplicationInfoForm.get("birthDay");
            String monthString = (String) changeApplicationInfoForm.get("birthMonth");
            String yearString = (String) changeApplicationInfoForm.get("birthYear");

            Integer day = null;
            Integer month = null;
            Integer year = null;

            if ((dayString == null) || (monthString == null) || (yearString == null)
                    || (dayString.length() == 0) || (monthString.length() == 0)
                    || (yearString.length() == 0)) {
                infoPerson.setNascimento(null);
            } else {
                day = new Integer((String) changeApplicationInfoForm.get("birthDay"));
                month = new Integer((String) changeApplicationInfoForm.get("birthMonth"));
                year = new Integer((String) changeApplicationInfoForm.get("birthYear"));

                birthDate.set(year.intValue(), month.intValue(), day.intValue());
                infoPerson.setNascimento(birthDate.getTime());
            }

            dayString = (String) changeApplicationInfoForm.get("idIssueDateDay");
            monthString = (String) changeApplicationInfoForm.get("idIssueDateMonth");
            yearString = (String) changeApplicationInfoForm.get("idIssueDateYear");

            if ((dayString == null) || (monthString == null) || (yearString == null)
                    || (dayString.length() == 0) || (monthString.length() == 0)
                    || (yearString.length() == 0)) {
                infoPerson.setDataEmissaoDocumentoIdentificacao(null);
            } else {
                day = new Integer((String) changeApplicationInfoForm.get("idIssueDateDay"));
                month = new Integer((String) changeApplicationInfoForm.get("idIssueDateMonth"));
                year = new Integer((String) changeApplicationInfoForm.get("idIssueDateYear"));

                idDocumentIssueDate.set(year.intValue(), month.intValue(), day.intValue());
                infoPerson.setDataEmissaoDocumentoIdentificacao(idDocumentIssueDate.getTime());
            }

            dayString = (String) changeApplicationInfoForm.get("idExpirationDateDay");
            monthString = (String) changeApplicationInfoForm.get("idExpirationDateMonth");
            yearString = (String) changeApplicationInfoForm.get("idExpirationDateYear");

            if ((dayString == null) || (monthString == null) || (yearString == null)
                    || (dayString.length() == 0) || (monthString.length() == 0)
                    || (yearString.length() == 0)) {
                infoPerson.setDataValidadeDocumentoIdentificacao(null);
            } else {
                day = new Integer((String) changeApplicationInfoForm.get("idExpirationDateDay"));
                month = new Integer((String) changeApplicationInfoForm.get("idExpirationDateMonth"));
                year = new Integer((String) changeApplicationInfoForm.get("idExpirationDateYear"));

                idDocumentExpirationDate.set(year.intValue(), month.intValue(), day.intValue());
                infoPerson.setDataValidadeDocumentoIdentificacao(idDocumentExpirationDate.getTime());
            }

            InfoCountry nationality = new InfoCountry();
            nationality.setNationality((String) changeApplicationInfoForm.get("nationality"));

            infoPerson.setTipoDocumentoIdentificacao(IDDocumentType.valueOf((String) changeApplicationInfoForm.get("identificationDocumentType")));
            infoPerson.setNumeroDocumentoIdentificacao((String) changeApplicationInfoForm
                    .get("identificationDocumentNumber"));
            infoPerson.setLocalEmissaoDocumentoIdentificacao((String) changeApplicationInfoForm
                    .get("identificationDocumentIssuePlace"));
            infoPerson.setNome((String) changeApplicationInfoForm.get("name"));

            String aux = (String) changeApplicationInfoForm.get("sex");
            if ((aux == null) || (aux.length() == 0))
                infoPerson.setSexo(null);
            else
                infoPerson.setSexo(Gender.valueOf(aux));

            aux = (String) changeApplicationInfoForm.get("maritalStatus");
            if ((aux == null) || (aux.length() == 0))
                infoPerson.setMaritalStatus(null);
            else
                infoPerson.setMaritalStatus(MaritalStatus.valueOf(aux));
            infoPerson.setInfoPais(nationality);
            infoPerson.setNomePai((String) changeApplicationInfoForm.get("fatherName"));
            infoPerson.setNomeMae((String) changeApplicationInfoForm.get("motherName"));
            infoPerson.setFreguesiaNaturalidade((String) changeApplicationInfoForm
                    .get("birthPlaceParish"));
            infoPerson.setConcelhoNaturalidade((String) changeApplicationInfoForm
                    .get("birthPlaceMunicipality"));
            infoPerson.setDistritoNaturalidade((String) changeApplicationInfoForm
                    .get("birthPlaceDistrict"));
            infoPerson.setMorada((String) changeApplicationInfoForm.get("address"));
            infoPerson.setLocalidade((String) changeApplicationInfoForm.get("place"));
            infoPerson.setCodigoPostal((String) changeApplicationInfoForm.get("postCode"));
            infoPerson.setFreguesiaMorada((String) changeApplicationInfoForm.get("addressParish"));
            infoPerson.setConcelhoMorada((String) changeApplicationInfoForm.get("addressMunicipality"));
            infoPerson.setDistritoMorada((String) changeApplicationInfoForm.get("addressDistrict"));
            infoPerson.setTelefone((String) changeApplicationInfoForm.get("telephone"));
            infoPerson.setTelemovel((String) changeApplicationInfoForm.get("mobilePhone"));
            infoPerson.setEmail((String) changeApplicationInfoForm.get("email"));
            infoPerson.setEnderecoWeb((String) changeApplicationInfoForm.get("webSite"));
            infoPerson.setNumContribuinte((String) changeApplicationInfoForm.get("contributorNumber"));
            infoPerson.setProfissao((String) changeApplicationInfoForm.get("occupation"));
            infoPerson.setUsername((String) changeApplicationInfoForm.get("username"));
            infoPerson.setLocalidadeCodigoPostal((String) changeApplicationInfoForm
                    .get("areaOfAreaCode"));

            Object args[] = { infoPerson };
            InfoPerson newInfoPerson = new InfoPerson();
            try {
                newInfoPerson = (InfoPerson) ServiceManagerServiceFactory.executeService(userView,
                        "EditPersonalStudentInfo", args);
            } catch (FenixServiceException e) {
                throw new FenixActionException(e);
            } catch (NotAuthorizedFilterException e) {
                ActionErrors errors = new ActionErrors();
                errors.add("errors", new ActionError("error.degreeAdministration.notMereStudent"));
                saveErrors(request, errors);
                return mapping.findForward("done");
            }

            request.removeAttribute("idInternal");
            request.setAttribute("infoPerson", newInfoPerson);
            populateForm(changeApplicationInfoForm, newInfoPerson);

            return mapping.findForward("done");
        }

        throw new Exception();
    }

    private void populateForm(DynaActionForm changeApplicationInfoForm, InfoPerson infoPerson) {
        changeApplicationInfoForm.set("identificationDocumentNumber", infoPerson
                .getNumeroDocumentoIdentificacao());
        changeApplicationInfoForm.set("identificationDocumentType", infoPerson
                .getTipoDocumentoIdentificacao().toString());
        changeApplicationInfoForm.set("identificationDocumentIssuePlace", infoPerson
                .getLocalEmissaoDocumentoIdentificacao());
        changeApplicationInfoForm.set("name", infoPerson.getNome());

        Calendar birthDate = Calendar.getInstance();
        if (infoPerson.getNascimento() == null) {
            changeApplicationInfoForm.set("birthDay", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("birthMonth", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("birthYear", Data.OPTION_DEFAULT);
        } else {
            birthDate.setTime(infoPerson.getNascimento());
            changeApplicationInfoForm.set("birthDay", new Integer(birthDate.get(Calendar.DAY_OF_MONTH))
                    .toString());
            changeApplicationInfoForm.set("birthMonth", new Integer(birthDate.get(Calendar.MONTH))
                    .toString());
            changeApplicationInfoForm.set("birthYear", new Integer(birthDate.get(Calendar.YEAR))
                    .toString());
        }

        Calendar identificationDocumentIssueDate = Calendar.getInstance();
        if (infoPerson.getDataEmissaoDocumentoIdentificacao() == null) {
            changeApplicationInfoForm.set("idIssueDateDay", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("idIssueDateMonth", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("idIssueDateYear", Data.OPTION_DEFAULT);
        } else {
            identificationDocumentIssueDate.setTime(infoPerson.getDataEmissaoDocumentoIdentificacao());
            changeApplicationInfoForm.set("idIssueDateDay", new Integer(identificationDocumentIssueDate
                    .get(Calendar.DAY_OF_MONTH)).toString());
            changeApplicationInfoForm.set("idIssueDateMonth", new Integer(
                    identificationDocumentIssueDate.get(Calendar.MONTH)).toString());
            changeApplicationInfoForm.set("idIssueDateYear", new Integer(identificationDocumentIssueDate
                    .get(Calendar.YEAR)).toString());
        }

        Calendar identificationDocumentExpirationDate = Calendar.getInstance();
        if (infoPerson.getDataValidadeDocumentoIdentificacao() == null) {
            changeApplicationInfoForm.set("idExpirationDateDay", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("idExpirationDateMonth", Data.OPTION_DEFAULT);
            changeApplicationInfoForm.set("idExpirationDateYear", Data.OPTION_DEFAULT);
        } else {
            identificationDocumentExpirationDate.setTime(infoPerson
                    .getDataValidadeDocumentoIdentificacao());
            changeApplicationInfoForm.set("idExpirationDateDay", new Integer(
                    identificationDocumentExpirationDate.get(Calendar.DAY_OF_MONTH)).toString());
            changeApplicationInfoForm.set("idExpirationDateMonth", new Integer(
                    identificationDocumentExpirationDate.get(Calendar.MONTH)).toString());
            changeApplicationInfoForm.set("idExpirationDateYear", new Integer(
                    identificationDocumentExpirationDate.get(Calendar.YEAR)).toString());
        }

        changeApplicationInfoForm.set("fatherName", infoPerson.getNomePai());
        changeApplicationInfoForm.set("motherName", infoPerson.getNomeMae());
        changeApplicationInfoForm.set("nationality", infoPerson.getInfoPais().getNationality());
        changeApplicationInfoForm.set("birthPlaceParish", infoPerson.getFreguesiaNaturalidade());
        changeApplicationInfoForm.set("birthPlaceMunicipality", infoPerson.getConcelhoNaturalidade());
        changeApplicationInfoForm.set("birthPlaceDistrict", infoPerson.getDistritoNaturalidade());
        changeApplicationInfoForm.set("address", infoPerson.getMorada());
        changeApplicationInfoForm.set("place", infoPerson.getLocalidade());
        changeApplicationInfoForm.set("postCode", infoPerson.getCodigoPostal());
        changeApplicationInfoForm.set("addressParish", infoPerson.getFreguesiaMorada());
        changeApplicationInfoForm.set("addressMunicipality", infoPerson.getConcelhoMorada());
        changeApplicationInfoForm.set("addressDistrict", infoPerson.getDistritoMorada());
        changeApplicationInfoForm.set("telephone", infoPerson.getTelefone());
        changeApplicationInfoForm.set("mobilePhone", infoPerson.getTelemovel());
        changeApplicationInfoForm.set("email", infoPerson.getEmail());
        changeApplicationInfoForm.set("webSite", infoPerson.getEnderecoWeb());
        changeApplicationInfoForm.set("contributorNumber", infoPerson.getNumContribuinte());
        changeApplicationInfoForm.set("occupation", infoPerson.getProfissao());
        changeApplicationInfoForm.set("username", infoPerson.getUsername());
        changeApplicationInfoForm.set("areaOfAreaCode", infoPerson.getLocalidadeCodigoPostal());

        if (infoPerson.getSexo() != null)
            changeApplicationInfoForm.set("sex", infoPerson.getSexo().toString());
        if (infoPerson.getMaritalStatus() != null)
            changeApplicationInfoForm.set("maritalStatus", infoPerson.getMaritalStatus().toString());
    }

    private String getFromRequest(String parameter, HttpServletRequest request) {
        String parameterString = request.getParameter(parameter);
        if (parameterString == null) {
            parameterString = (String) request.getAttribute(parameter);
        }
        return parameterString;
    }

}