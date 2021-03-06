<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<h2><bean:message key="label.enrollment.optional.course" bundle="STUDENT_RESOURCES" /></h2>

<fr:form action="/bolonhaStudentEnrollment.do">

	<input type="hidden" name="method" value="" />

	<fr:edit id="optionalEnrolment" name="optionalEnrolmentBean"
		schema="BolonhaStudentOptionalEnrollmentBean.chooseCriteria">
		<fr:destination name="updateComboBoxes"
			path="/bolonhaStudentEnrollment.do?method=updateParametersToSearchOptionalCurricularCourses" />
		<fr:layout name="tabular">
			<fr:property name="classes" value="tstyle4 thlight thright" />
			<fr:property name="columnClasses" value=",,tdclear tderror1" />
		</fr:layout>
	</fr:edit>


	<logic:messagesPresent message="true" property="error">
		<div class="mtop1 mbottom15 error0" style="padding: 0.5em;">
		<p class="mvert0"><strong><bean:message bundle="STUDENT_RESOURCES" key="label.enrollment.errors.in.enrolment" />:</strong></p>
		<ul class="mvert05">
			<html:messages id="messages" message="true" bundle="APPLICATION_RESOURCES" property="error">
				<li><span class="error0"><bean:write name="messages" /></span></li>
			</html:messages>
		</ul>
		</div>
	</logic:messagesPresent>

	<logic:present name="curricularRuleLabels">
		<logic:notEmpty name="curricularRuleLabels">
			<div class="smalltxt noborder">
				<ul class="mvert05">
					<logic:iterate id="curricularRuleLabel" name="curricularRuleLabels">
						<li><span style="color: #888"><bean:write name="curricularRuleLabel" /></span></li>
					</logic:iterate>
				</ul>
			</div>
		</logic:notEmpty>
	</logic:present>

	<logic:present name="optionalEnrolmentBean" property="degreeCurricularPlan">
		<fr:edit id="degreeCurricularPlan" name="optionalEnrolmentBean">
			<fr:layout name="bolonha-student-optional-enrolments">
				<fr:property name="classes" value="mtop15" />
				<fr:property name="groupRowClasses" value="se_groups" />
			</fr:layout>
		</fr:edit>
	</logic:present>

</fr:form>

<fr:form action="/bolonhaStudentEnrollment.do?method=cancelChooseOptionalCurricularCourseToEnrol">
<fr:edit id="optionalEnrolment" name="optionalEnrolmentBean" visible="false"/>
<p class="mtop15">
	<html:submit bundle="HTMLALT_RESOURCES" altKey="submit.submit">
		<bean:message bundle="APPLICATION_RESOURCES" key="label.cancel"/>
	</html:submit>
</p>
</fr:form>

