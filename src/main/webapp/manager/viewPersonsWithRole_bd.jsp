<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>
<h2 style="width: 100%"><bean:message bundle="MANAGER_RESOURCES" key="title.manage.roles"/>
	<div class="small">${role.roleType.localizedName}</div>
</h2>
<br />
<span class="error"><!-- Error messages go here --><html:errors /></span>
<html:messages id="message" message="true" bundle="MANAGER_RESOURCES">
	<span class="error"><!-- Error messages go here -->
		<bean:write name="message"/>
	</span>
</html:messages>

<logic:present name="roles" scope="request">
	<div class="col-lg-4">
	<table class="table">
		<tr>
			<th class="listClasses-header">
				<bean:message bundle="MANAGER_RESOURCES" key="label.roleType"/>
			</th>
		</tr>
		<logic:iterate id="role" name="roles" scope="request">
			<tr>
				<td class="listClasses">
					<bean:define id="roleName" name="role" property="roleType"/>
					<bean:define id="roleID" name="role" property="externalId"/>
					<html:link action="<%="/viewPersonsWithRole.do?method=searchWithRole&amp;roleID=" + roleID %>">
						<bean:message bundle="MANAGER_RESOURCES" key="<%= pageContext.findAttribute("roleName").toString() %>"/>
					</html:link>
				</td>
			</tr>
		</logic:iterate>
	</table>
	</div>
</logic:present>


<logic:present name="role" scope="request">
	<html:link action="/viewPersonsWithRole.do?method=showRoleOperationLogs&roleID=${role.externalId}"><bean:message bundle="MANAGER_RESOURCES" key="link.show.roleoperationlog"/></html:link>
	<fr:view name="role" property="associatedPersons" schema="PersonNameAndUsername">
		<fr:layout name="tabular">
			<fr:property name="classes" value="tstyle4" />
			<fr:property name="sortBy" value="name"/>
			<fr:property name="linkFormat(remove)" value='<%= "/viewPersonsWithRole.do?method=removePersonFromRole&amp;personUsername=${username}&amp;roleID=" + request.getAttribute("roleID")%>' />
			<fr:property name="key(remove)" value="label.remove"/>
			<fr:property name="bundle(remove)" value="APPLICATION_RESOURCES" />
			<fr:property name="contextRelative(remove)" value="true"/>
			<fr:property name="confirmationKey(remove)" value="label.manager.delete.role.confirmation.message"/>
			<fr:property name="confirmationBundle(remove)" value="APPLICATION_RESOURCES"/>
		</fr:layout>
	</fr:view>
</logic:present>
