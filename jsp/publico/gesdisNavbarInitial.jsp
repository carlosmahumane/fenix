<%@ page language="java" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div id="nav">
   <h3>Navega&ccedil;&atilde;o Local</h3>	
<ul>	
<li><html:link  page="/accessAnnouncements.do">
	<bean:message key="link.announcements"/>
</html:link></li>
<li><html:link page="/accessTeachers.do">
	<bean:message key="link.teachers"/>
</html:link></li>
<li><html:link page="/viewTimeTable.do">
		<bean:message key="link.executionCourse.timeTable"/>
</html:link></li>
<li><html:link page="/viewExecutionCourseShifts.do">
		<bean:message key="link.executionCourse.shifts"/>
</html:link></li>
<li> <a href="/" onclick="houdini('seccao');return false;">Informa&ccedil;&atilde;o Curricular</a></li>
</ul>
 <dl id="seccao" style="display: none;">
            <dd><html:link page="/accessObjectives.do?method=acessObjectives">
				<bean:message key="link.objectives"/>
				</html:link></dd>
            <dd><html:link page="/accessProgram.do?method=acessProgram">
				<bean:message key="link.program"/>
				</html:link></dd>
            <dd><html:link page="/accessBibliographicReferences.do?method=viewBibliographicReference">
				<bean:message key="link.bibliography"/>
				</html:link></dd>
			<dd><html:link page="/curricularCourses.do">
				<bean:message key="link.associatedCurricularCourses"/>
				</html:link></dd>	
  </dl>
<logic:present name="<%= SessionConstants.SECTIONS %>" >
	<ul>
<logic:iterate id="infoSection" name="<%= SessionConstants.SECTIONS %>" type="DataBeans.gesdis.InfoSection" indexId="index">



<ul><li><html:link page="/viewSection.do" indexed="true" >
	 <bean:write name="infoSection" property="name"/> 	
</html:link></li></ul>	

</logic:iterate>
</ul>	
</logic:present>	
	
</div>