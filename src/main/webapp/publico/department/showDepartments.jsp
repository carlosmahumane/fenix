<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/taglib/jsf-fenix" prefix="fc"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:view>" definition="definition.public.departments" attributeName="body-inline">
	<f:loadBundle basename="resources/ScientificCouncilResources" var="scouncilBundle"/>
	<f:loadBundle basename="resources/EnumerationResources" var="enumerationBundle"/>
	<f:loadBundle basename="resources/PublicDepartmentResources" var="publicDepartmentBundle"/>
	<f:loadBundle basename="resources/GlobalResources" var="globalBundle"/>
	
	<h:outputText value="<div class='breadcumbs mvert0'>" escape="false"/>
		<h:outputLink value="#{DepartmentManagement.applicationUrl}" >
			<h:outputText value="#{DepartmentManagement.institutionAcronym}"/>
		</h:outputLink>
		&nbsp;&gt;&nbsp;
		
		<h:outputLink target="_blank" value="#{CurricularCourseManagement.institutionUrl}#{globalBundle['link.institution.structure']}" >
			<h:outputText value="#{publicDepartmentBundle['structure']}"/>
		</h:outputLink>
		&nbsp;&gt;&nbsp;
		
		<h:outputText value="#{publicDepartmentBundle['academic.units']}"/>
	<h:outputText value="</div>" escape="false"/>


	<h:outputText value="<h1>#{publicDepartmentBundle['structure']}</h1>" escape="false"/>
	<h:outputText value="<h2>#{publicDepartmentBundle['academic.units']}</h2>" escape="false"/>
	
	<h:dataTable value="#{DepartmentManagement.departmentUnits}" var="departmentUnit" style="padding-left: 3em; width: 70em;" columnClasses=",aright">
		<h:column>					
			<fc:contentLink content="#{departmentUnit.site}" label="#{departmentUnit.department.nameI18n.content}" />								
		</h:column>
	</h:dataTable>
	
</f:view>
