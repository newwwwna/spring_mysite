<%@page import="com.javalec.mysite.vo.UserVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	UserVO vo = (UserVO)session.getAttribute("user") ;

	if(vo != null) {
		response.sendRedirect("main/login.jsp");
	}
%>

		<div id="header">
			<h1>MySite</h1>
			<ul>
			<li><a href="${pageContext.request.contextPath }/user/logout.do"> Log-out</a>  </li>

			<li>    ${userName} 님 환영합니다.^^;</li>
			
								
			</ul>
		</div>
