<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<html:xhtml/>
<bean:define id="unitID" name="unit" property="externalId"/>
<bean:define id="unitExternalId" name="unit" property="externalId"/>

<h2><fr:view name="unit" property="name"/> (<fr:view name="unit" property="acronym"/>)</h2>

<ul>
	<li>
		<html:link page="<%= "/sendEmailToResearchUnitGroups.do?method=prepare&unitExternalId=" + unitExternalId %>">
			<bean:message key="label.sendEmailToGroups" bundle="RESEARCHER_RESOURCES"/>
		</html:link>
		<br/>
		<span class="color888">
			<bean:message key="label.sendEmailToGroups.explanation" bundle="RESEARCHER_RESOURCES"/>
		</span>
	</li>
    <logic:equal name="unit" property="currentUserAbleToInsertOthersPublications" value="true">
	<li>
			<html:link page="<%= "/researchUnitFunctionalities.do?method=preparePublications&unitId=" + unitID %>">
						<bean:message key="link.Publications" bundle="RESEARCHER_RESOURCES"/>
			</html:link>
			<br/>
			<span class="color888">
				<bean:message key="label.Publications.explanation" bundle="RESEARCHER_RESOURCES"/>
			</span>
	</li>
	</logic:equal>
	<logic:equal name="unit" property="currentUserAbleToDefineGroups" value="true">
		<li>
			<html:link page="<%= "/researchUnitFunctionalities.do?method=configureGroups&unitId=" + unitID %>">
				<bean:message key="label.configurePersistentGroups" bundle="RESEARCHER_RESOURCES"/>
			</html:link>
			<br/>
			<span class="color888">
				<bean:message key="label.configurePersistentGroups.explanation" bundle="RESEARCHER_RESOURCES"/>
			</span>
		</li>
	</logic:equal>
	<li>
		<html:link page="<%= "/researchUnitFunctionalities.do?method=manageFiles&unitId=" + unitID %>">
			<bean:message key="label.manageFiles" bundle="RESEARCHER_RESOURCES"/>
		</html:link>
		<br/>
		<span class="color888">
			<bean:message key="label.manageFiles.explanation" bundle="RESEARCHER_RESOURCES"/>
		</span>
	</li>

</ul>
