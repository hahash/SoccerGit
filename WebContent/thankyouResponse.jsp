<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!--Directive Element :: 이부분에 있는 내용을 컨테이네에게 알려줄때 사용  -->
<%@ page import="sl314.domain.League, sl314.domain.Player"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<TABLE BORDER='0' WIDTH='600'>
<TR>
<TD COLSPAN='2' BGCOLOR='#CCCCFF' ALIGN='center'>
<H3>Registration: Thank You</H3>
</TD></TR></TABLE><BR>
<%
League league = (League) session.getAttribute("league");
Player player = (Player) session.getAttribute("player");

session.invalidate();
%>

Thank you, <%= player.getName()  %>, for registering  
 in the <B><%= league.getTitle() %> </B> league.<br>
</body>
</html>




