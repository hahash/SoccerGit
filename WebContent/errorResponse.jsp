<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="sl314.util.Status"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<TABLE BORDER='0' WIDTH='600'>
<TR>
<TD COLSPAN='2' BGCOLOR='#CCCCFF' ALIGN='center'>
 <H3>Registration: Form Errors</H3>
 </TD></TR></TABLE><BR>
 There were problems processing your request:
 <FONT COLOR='red'>
 <UL>
 <%
 Status status = (Status) request.getAttribute("status");
 Iterator errors = status.getExceptions();
 while ( errors.hasNext() ) {
   Exception ex = (Exception) errors.next();
   out.println("  <LI> " + ex.getMessage());
 }
 %>
 </UL>
 </FONT>
Please, back up and try again.

</body>
</html>






