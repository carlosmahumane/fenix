<%@ page isELIgnored="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<h2><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="label.payments.paymentCodes" /></h2>

<p class="mtop15 mbottom05"><strong><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="label.payments.person" /></strong></p>
<fr:view name="person" schema="person.view-with-name-and-idDocumentType-and-documentIdNumber">
	<fr:layout name="tabular">
		<fr:property name="classes" value="tstyle2 thlight thright mtop05" />
		<fr:property name="rowClasses" value="tdhl1,," />
	</fr:layout>
</fr:view>

<bean:define id="personId" name="person" property="externalId" />

<logic:notEmpty name="eventsWithPaymentCodes">
	<fr:view name="eventsWithPaymentCodes" schema="AccountingEvent.view-with-amountToPay">
		<fr:layout name="tabular-sortable">
			<fr:property name="classes" value="tstyle4 tdleftm mtop05" />
			<fr:property name="columnClasses" value=",acenter,aright,aright" />
			
			<fr:property name="linkFormat(detail)" value="/payments.do?method=showPaymentCodesForEvent&eventId=${externalId}&personId=${person.externalId}"/>
			<fr:property name="key(detail)" value="label.details"/>
			<fr:property name="bundle(detail)" value="APPLICATION_RESOURCES"/>

			<fr:property name="sortParameter" value="sortBy"/>
	        <fr:property name="sortUrl" value="<%= "/payments.do?method=showEventsWithPayments&personId=" + personId %>" />
    	    <fr:property name="sortBy" value="<%= request.getParameter("sortBy") == null ? "whenOccured=asc" : request.getParameter("sortBy") %>"/>
			<fr:property name="sortableSlots" value="lastPaymentDate, payedAmount, reimbursableAmount" />
		</fr:layout>
	</fr:view>	
</logic:notEmpty>

<logic:empty name="eventsWithPaymentCodes">
	<em><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="label.payments.events.noEvents" />.</em>
</logic:empty>
	
<fr:form action='<%= "/payments.do?personId=" + personId %>'>
	<input type="hidden" name="method" value=""/>
	<html:cancel bundle="HTMLALT_RESOURCES" altKey="submit.submit" onclick="this.form.method.value='backToShowOperations';"><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="button.payments.back"/></html:cancel>
</fr:form>
