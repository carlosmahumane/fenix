<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/taglib/jsf-fenix" prefix="fc"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<fp:select actionClass="net.sourceforge.fenixedu.presentationTier.Action.manager.ManagerApplications$OrganizationalStructurePage" />

<f:view>
	<f:loadBundle basename="resources/HtmlaltResources" var="htmlAltBundle"/>
	<f:loadBundle basename="resources/ManagerResources" var="bundle"/>
	<h:form>	

		<h:inputHidden binding="#{organizationalStructureBackingBean.unitIDHidden}"/>
		
		<h:outputText styleClass="error" rendered="#{!empty organizationalStructureBackingBean.errorMessage}"
				value="#{bundle[organizationalStructureBackingBean.errorMessage]}<br/>" escape="false"/>
										
		<h:outputText value="<h2>#{bundle['title.chooseUnit']}</h2><br/>" escape="false" />		
		
		<span class="alert alert-info">
			<h:outputText value="<b>#{bundle['message.name']}</b>" escape="false"/>		
			<h:outputText value="#{organizationalStructureBackingBean.unit.name}" escape="false"/>												
		</span>
		
		<h:outputText value="<br/><br/><br/>" escape="false" />	
		
		<h:dataTable value="#{organizationalStructureBackingBean.parentAccountabilities}" styleClass="table" var="accountability"
			 headerClass="listClasses-header" columnClasses="listClasses" rendered="#{!empty organizationalStructureBackingBean.parentAccountabilities}">
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{bundle['message.unitName']}" />
				</f:facet>				
				<h:outputText value="<strong>#{accountability.parentParty.name}</strong>" escape="false"/>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{bundle['message.unitCostCenter']}" />
				</f:facet>				
				<h:outputText value="#{accountability.parentParty.costCenterCode}" escape="false"/>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{bundle['message.unitType']}" />
				</f:facet>				
				<h:outputText value="#{bundleEnum[accountability.parentParty.type.name]}" escape="false"/>
			</h:column>
			<h:column>	
				<f:facet name="header">
					<h:outputText value="#{bundle['message.unitBeginDate']}" />
				</f:facet>				
				<h:outputFormat value="{0, date, dd/MM/yyyy}">
					<f:param value="#{accountability.parentParty.beginDate}"/>
				</h:outputFormat>
			</h:column>
			<h:column>	
				<f:facet name="header">
					<h:outputText value="#{bundle['message.unitEndDate']}" />
				</f:facet>								
				<h:outputFormat value="{0, date, dd/MM/yyyy}" rendered="#{!empty accountability.parentParty.endDate}">
					<f:param value="#{accountability.parentParty.endDate}"/>
				</h:outputFormat>					
			</h:column>
			<h:column>	
				<f:facet name="header">
					<h:outputText value="#{bundle['title.relation.type']}" />
				</f:facet>								
				<h:outputText value="#{organizationalStructureBackingBean.unitRelationsAccountabilityTypes[accountability.externalId]}" escape="false"/>									
			</h:column>	
			<h:column>
				<f:facet name="header">
					<h:outputText value="#{bundle['message.action']}" />
				</f:facet>
				<h:commandLink action="#{organizationalStructureBackingBean.disassociateParentUnit}" value="#{bundle['button.choose']}">
					<f:param id="accountabilityID" name="accountabilityID" value="#{accountability.externalId}"/>
				</h:commandLink> 				
			</h:column>						
		</h:dataTable>				
		
		<h:outputText value="<br/>" escape="false" />		
		<h:commandButton alt="#{htmlAltBundle['commandButton.return']}" action="backToUnitDetails" immediate="true" value="#{bundle['label.return']}" styleClass="inputbutton"/>						
				
	</h:form>
</f:view>