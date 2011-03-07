<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr" %>

<h2><bean:message key="label.categories" bundle="CONTRACTS_RESOURCES"/></h2>

<logic:present name="professionalCategories">
	<fr:edit name="professionalCategories">
		<fr:schema bundle="CONTRACTS_RESOURCES" type="net.sourceforge.fenixedu.domain.personnelSection.contracts.ProfessionalCategory">
			<fr:slot readOnly="true" name="giafId" key="label.giafId" layout="null-as-label"/>
			<fr:slot readOnly="true" name="categoryType" key="label.type" layout="null-as-label"/>
			<fr:slot readOnly="true" name="name" key="label.category" layout="null-as-label"/>
			<fr:slot name="weight" key="label.weight" layout="null-as-label">
				<fr:property name="size" value="2"/>
			</fr:slot>
		</fr:schema>

		<fr:layout name="tabular-editable">
			<fr:property name="classes" value="tstyle2 thlight mtop15" />
			<fr:property name="sortBy" value="categoryType, weight, giafId"/>
		</fr:layout>
	</fr:edit>
</logic:present>
