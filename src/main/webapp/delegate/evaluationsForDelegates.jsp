<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/taglib/jsf-fenix" prefix="fc"%>

<fp:select actionClass="net.sourceforge.fenixedu.presentationTier.Action.delegate.DelegateApplication$EvaluationsForDelegatesAction" />

<f:view>
	<f:loadBundle basename="resources/HtmlaltResources" var="htmlAltBundle"/>
	<f:loadBundle basename="resources/DelegateResources" var="delegatesBundle"/>
	<f:loadBundle basename="resources/ApplicationResources" var="bundleApplication"/>
	<f:loadBundle basename="resources/EnumerationResources" var="bundleEnum"/>

	<h:form>
		<h:outputText escape="false" value="<input alt='input.degreeID' id='degreeID' name='degreeID' type='hidden' value='#{evaluationsForDelegates.degreeID}'/>"/>
		<%-- 
		<h:outputText value="<em><p class='mtop1 mbottom1'>" escape="false"/>
		<h:outputText value="#{evaluationsForDelegates.degreeName}"/>
		<h:outputText value="</p></em>" escape="false"/>
		--%>
		
		<h:outputText value="<h2>" escape="false"/>
		<h:outputText value="#{delegatesBundle['label.delegates.degreeEvaluations']}"/>
		<h:outputText value="</h2>" escape="false"/>
				
		<h:outputText rendered="#{empty evaluationsForDelegates.degree.mostRecentDegreeCurricularPlan}" value="<p><em>#{bundleApplication['error.curricularPlanHasNoExecutionDegreesInNotClosedYears']}</em></p>" escape="false"/>
		
		<h:panelGroup rendered="#{!empty evaluationsForDelegates.degree.mostRecentDegreeCurricularPlan}">
			<h:outputText value="<p>#{delegatesBundle['label.delegates.evaluations.curricularPlan']}: " escape="false"/>
				<h:selectOneMenu id="degreeCurricularPlanID" value="#{evaluationsForDelegates.degreeCurricularPlanID}"
						onchange="this.form.submit();">
					<f:selectItems value="#{evaluationsForDelegates.degreeCurricularPlanSelectItems}"/>
				</h:selectOneMenu>
			<h:outputText value="</p>" escape="false"/>

			<h:outputText value="<p>#{delegatesBundle['label.delegates.evaluations.execution.period']}: " escape="false"/>
				<h:selectOneMenu id="executionPeriodID" value="#{evaluationsForDelegates.executionPeriodID}"
						onchange="this.form.submit();">
					<f:selectItems value="#{evaluationsForDelegates.executionPeriodSelectItems}"/>
				</h:selectOneMenu>
			<h:outputText value="</p>" escape="false"/>
	
			<h:outputText value="<p>#{delegatesBundle['label.delegates.evaluations.curricular.year']}: " escape="false"/>
				<h:selectOneMenu id="curricularYearID" value="#{evaluationsForDelegates.curricularYearID}"
						onchange="this.form.submit();">
					<f:selectItem itemLabel="#{delegatesBundle['label.delegates.evaluations.allCurricularYears']}" itemValue=""/>
					<f:selectItems value="#{evaluationsForDelegates.curricularYearSelectItems}"/>
				</h:selectOneMenu>
			<h:outputText value="</p>" escape="false"/>
	
			<h:outputText value="<br/>" escape="false"/>

		 	<fc:fenixCalendar 
			 		begin="#{evaluationsForDelegates.beginDate}" 
			 		end="#{evaluationsForDelegates.endDate}"
			 		editLinkPage="#{evaluationsForDelegates.applicationContext}/publico/executionCourse.do"
			 		editLinkParameters="#{evaluationsForDelegates.calendarLinks}"/>
		</h:panelGroup> 
	</h:form>
</f:view>
