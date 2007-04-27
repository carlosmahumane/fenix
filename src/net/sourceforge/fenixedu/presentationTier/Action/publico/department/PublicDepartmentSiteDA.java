package net.sourceforge.fenixedu.presentationTier.Action.publico.department;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.domain.Degree;
import net.sourceforge.fenixedu.domain.Department;
import net.sourceforge.fenixedu.domain.DepartmentSite;
import net.sourceforge.fenixedu.domain.Employee;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.UnitSite;
import net.sourceforge.fenixedu.domain.degree.DegreeType;
import net.sourceforge.fenixedu.domain.messaging.Announcement;
import net.sourceforge.fenixedu.domain.messaging.AnnouncementBoard;
import net.sourceforge.fenixedu.domain.organizationalStructure.Unit;
import net.sourceforge.fenixedu.presentationTier.Action.manager.SiteVisualizationDA;
import net.sourceforge.fenixedu.presentationTier.servlets.filters.pathProcessors.DepartmentProcessor;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;

public class PublicDepartmentSiteDA extends SiteVisualizationDA {

    public static final int ANNOUNCEMENTS_NUMBER = 3;
    public static final String ANNOUNCEMENTS_NAME = "An�ncios";
    public static final String EVENTS_NAME = "Eventos";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = getIntegerFromRequest(request, "selectedDepartmentUnitID");
        Unit unit = (Unit) RootDomainObject.getInstance().readPartyByOID(id);
        
        if (unit != null) {
            request.setAttribute("unit", unit);
            request.setAttribute("department", unit.getDepartment());
            
            DepartmentSite site = (DepartmentSite) unit.getSite();
            request.setAttribute("site", site);
        }
        
        request.setAttribute("announcementActionVariable", "/department/announcements.do");
        request.setAttribute("eventActionVariable", "/department/events.do");
        request.setAttribute("siteContextParam", "selectedDepartmentUnitID");
        request.setAttribute("siteContextParamValue", id);
        
        return super.execute(mapping, actionForm, request, response);
    }

    @Override
    protected String getDirectLinkContext(HttpServletRequest request) {
        Department department = getDepartment(request);
        try {
            return RequestUtils.absoluteURL(request, DepartmentProcessor.getDepartmentPath(department)).toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }
    
    @Override
    protected ActionForward getSiteDefaultView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return presentation(mapping, form, request, response);
    }

    public ActionForward presentation(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        Unit unit = getUnit(request);
        UnitSite site = unit.getSite();
        
        AnnouncementBoard announcementsBoard = null;
        AnnouncementBoard eventsBoard = null;
        
        for (AnnouncementBoard unitBoard : unit.getBoards()) {
            if (unitBoard.isPublicToRead() && unitBoard.getName().equals(ANNOUNCEMENTS_NAME)) {
                announcementsBoard = unitBoard;
            }

            if (unitBoard.isPublicToRead() && unitBoard.getName().equals(EVENTS_NAME)) {
                eventsBoard = unitBoard;
            }
        }
        
        if (announcementsBoard != null) {
            List<Announcement> announcements = announcementsBoard.getActiveAnnouncements();
            announcements = announcements.subList(0, Math.min(announcements.size(), ANNOUNCEMENTS_NUMBER));
            request.setAttribute("announcements", announcements);
        }
        
        if (eventsBoard != null) {
            List<Announcement> announcements = eventsBoard.getActiveAnnouncements();
            announcements = announcements.subList(0, Math.min(announcements.size(), ANNOUNCEMENTS_NUMBER));
            request.setAttribute("eventAnnouncements", announcements);
        }

        return mapping.findForward("frontPage-" + site.getLayout());
    }
    
    private Department getDepartment(HttpServletRequest request) {
        Unit unit = getUnit(request);
        if (unit == null) {
            return null;
        }
        else {
            return unit.getDepartment();
        }
    }

    private Unit getUnit(HttpServletRequest request) {
        return (Unit) request.getAttribute("unit");
    }
    
    public ActionForward employees(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        Unit unit = getUnit(request);
        
        List<Employee> employees = unit.getAllCurrentNonTeacherEmployees();
        Collections.sort(employees, new Comparator<Employee>() {

            public int compare(Employee o1, Employee o2) {
                return o1.getPerson().getNickname().compareTo(o2.getPerson().getNickname());
            }
            
        });
        
        request.setAttribute("employees", employees);
        return mapping.findForward("department-employees");
    }

    public ActionForward degrees(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        Unit unit = getUnit(request);
        
        Department department = unit.getDepartment();
        if (department == null) {
            return presentation(mapping, actionForm, request, response);
        }
        
        Map<DegreeType, SortedSet<Degree>> degreeAndTypes = new HashMap<DegreeType, SortedSet<Degree>>();
        
        for (Degree degree : department.getDegrees()) {
            DegreeType type = degree.getDegreeType();
            
            SortedSet<Degree> current = degreeAndTypes.get(type);
            if (current == null) {
                current = new TreeSet<Degree>(Degree.COMPARATOR_BY_NAME_AND_ID);
                degreeAndTypes.put(type, current);
            }
            
            current.add(degree);
        }
        
        SortedSet<DegreeType> types = new TreeSet<DegreeType>(degreeAndTypes.keySet());
        request.setAttribute("types", types);
        
        for (DegreeType type : types) {
            request.setAttribute(type.getName(), degreeAndTypes.get(type));
        }

        return mapping.findForward("department-degrees");
    }
    
}
