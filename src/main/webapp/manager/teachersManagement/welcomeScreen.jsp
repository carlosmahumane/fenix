<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<h2><bean:message bundle="MANAGER_RESOURCES" key="label.manager.teachersManagement"/></h2>

<table>
	<tr>
		<td class="well">
			<bean:message bundle="MANAGER_RESOURCES" key="label.manager.teachersManagement.welcome"/>		
		</td>
	</tr>
</table>

<br />

<html:link styleClass="btn btn-primary" page="/dissociateProfShipsAndRespFor.do?method=prepareDissociateEC">
	<bean:message bundle="MANAGER_RESOURCES" key="link.manager.teachersManagement.removeECAssociation" />
</html:link>