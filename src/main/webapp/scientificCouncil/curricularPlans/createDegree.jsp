<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<fp:select actionClass="net.sourceforge.fenixedu.presentationTier.Action.scientificCouncil.ScientificCouncilApplication$ScientificCurricularPlansManagement" />

<f:view>
	<f:loadBundle basename="resources/HtmlaltResources" var="htmlAltBundle"/>
	<f:loadBundle basename="resources/ScientificCouncilResources" var="scouncilBundle"/>
	
	<h:outputFormat value="<h2>#{scouncilBundle['create.param']}</h2>" escape="false">
		<f:param value="#{scouncilBundle['degree']}"/>
	</h:outputFormat>
	<h:form>

		<h:outputText styleClass="error0" rendered="#{!empty DegreeManagement.errorMessage}"
			value="#{DegreeManagement.errorMessage}<br/>" escape="false"/>
		<h:messages infoClass="success0" errorClass="error0" layout="table" globalOnly="true"/>			

		
		<h:outputText value="<table class='tstyle5 thlight thright'>" escape="false"/>

		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th><span class='required'>*</span> #{scouncilBundle['name']} (pt):</th><td>" escape="false"/>
		<h:panelGroup>
			<h:inputText alt="#{htmlAltBundle['inputText.name']}" id="name" value="#{DegreeManagement.name}" maxlength="100" size="60"/>
			<h:message for="name" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>	

		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th><span class='required'>*</span> #{scouncilBundle['name']} (en):</th><td>" escape="false"/>
		<h:panelGroup>
			<h:inputText alt="#{htmlAltBundle['inputText.nameEn']}" id="nameEn" value="#{DegreeManagement.nameEn}" maxlength="100" size="60"/>
			<h:message for="nameEn" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>

		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th><span class='required'>*</span> #{scouncilBundle['acronym']}:</th><td>" escape="false"/>
		<h:panelGroup>
			<h:inputText alt="#{htmlAltBundle['inputText.acronym']}" id="acronym" value="#{DegreeManagement.acronym}" maxlength="9" size="9"/>
			<h:message for="acronym" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>
		
		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th><span class='required'>*</span> #{scouncilBundle['degreeType']}:</th><td>" escape="false"/>
		<h:panelGroup>
			<h:selectOneMenu id="bolonhaDegreeType" value="#{DegreeManagement.bolonhaDegreeType}" onchange="this.form.submit();">
				<f:selectItems value="#{DegreeManagement.bolonhaDegreeTypes}" />
			</h:selectOneMenu>
			<h:message for="bolonhaDegreeType" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>
		
		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th><span class='required'>*</span> #{scouncilBundle['academicAdminOffice']}:</th><td>" escape="false"/>
		<h:panelGroup>
			<h:selectOneMenu id="academicAdminOffice" value="#{DegreeManagement.academicAdminOfficeId}">
				<f:selectItems value="#{DegreeManagement.academicAdminOffices}" />
			</h:selectOneMenu>			
			<h:message for="academicAdminOffice" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>

		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th>#{scouncilBundle['ectsCredits']}:</th><td>" escape="false"/>
		<h:panelGroup>
			<h:inputText alt="#{htmlAltBundle['inputText.ectsCredits']}" id="ectsCredits" value="#{DegreeManagement.ectsCredits}" maxlength="5" size="4"/>
			<h:message for="ectsCredits" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>

		<h:outputText value="<tr>" escape="false"/>
		<h:outputText value="<th>#{scouncilBundle['prevailingScientificArea']}:</th><td>" escape="false"/>
		<h:panelGroup>
			<h:inputText alt="#{htmlAltBundle['inputText.prevailingScientificArea']}" id="prevailingScientificArea" value="#{DegreeManagement.prevailingScientificArea}" maxlength="100" size="60"/>
			<h:message for="prevailingScientificArea" errorClass="error0" rendered="#{empty DegreeManagement.errorMessage}"/>
		</h:panelGroup>
		<h:outputText value="</td>" escape="false"/>
		<h:outputText value="</tr>" escape="false"/>

		<h:outputText value="<tr><th></th><td><span class='smalltxt'>#{scouncilBundle['mandatory.fields']}</td></tr>" escape="false"/>

		<h:outputText value="</table>" escape="false"/>






		<h:outputText value="<p>" escape="false"/>
		<h:commandButton alt="#{htmlAltBundle['commandButton.create']}" styleClass="inputbutton" value="#{scouncilBundle['create']}"
			action="#{DegreeManagement.createDegree}"/>
		<h:commandButton alt="#{htmlAltBundle['commandButton.cancel']}" immediate="true" styleClass="inputbutton" value="#{scouncilBundle['cancel']}"
			action="curricularPlansManagement"/>
		<h:outputText value="</p>" escape="false"/>
	</h:form>

</f:view>
