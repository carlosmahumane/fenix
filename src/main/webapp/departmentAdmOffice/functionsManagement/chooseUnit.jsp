<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<f:view>
	<f:loadBundle basename="resources/HtmlaltResources" var="htmlAltBundle"/>

	<script language="JavaScript">
	function check(e,v)
	{	
		var contextPath = '<%= request.getContextPath() %>';	
		if (e.style.display == "none")
		  {
		  e.style.display = "";
		  v.src = contextPath + '/images/toggle_minus10.gif';
		  }
		else
		  {
		  e.style.display = "none";
		  v.src = contextPath + '/images/toggle_plus10.gif';
		  }
	}
	</script>

	<f:loadBundle basename="resources/DepartmentAdmOfficeResources" var="bundle"/>
	
	<h:form>
	
		<h:inputHidden binding="#{functionsManagementBackingBean.personIDHidden}"/>
				
		<h:outputText value="<H2>#{bundle['label.chooseUnit']}</H2>" escape="false"/>		

		<h:outputText value="<br/>" escape="false" />
		
		<h:panelGroup styleClass="infoop">
			<h:outputText value="<b>#{bundle['label.name']}</b>: " escape="false"/>		
			<h:outputText value="#{functionsManagementBackingBean.person.name}" escape="false"/>		
		</h:panelGroup>
		<h:outputText value="<br/><br/><br/>" escape="false" />
						
		<h:outputText value="<p><strong>#{bundle['label.units']}</strong>:</p>" escape="false"/>		
					
		<h:outputText value="#{functionsManagementBackingBean.units}<br/>" escape="false"/>
		
		<h:commandButton alt="#{htmlAltBundle['commandButton.person']}" styleClass="inputbutton" value="#{bundle['button.choose.new.person']}" action="backToList"/>
						    				
	</h:form>

</f:view>