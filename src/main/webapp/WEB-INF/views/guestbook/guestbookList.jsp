<%@page import="com.javalec.mysite.vo.GuestbookVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<title>방명록</title>
</head>
<body>


	<%
		List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
	%>

	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<!-- 
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		 -->
		<div id="content">
			<div id="guestbook">


				<form action="insert.do" method="post">
							<input type="text" name="name" placeholder="이름">
							 비밀번호 <input type="password" name="password">
							<!--  name 을 pass로 하고 controller 호출시 오류 바생 ..... vo 이름과 동일하게 세팅해준다..!! -->
<textarea name="content" cols=60 rows=5></textarea>
<input type="submit" VALUE=" 확인 ">
				</form>
<!-- 

				<form action="searchkeywordlist.do" method="post">
					<table class="tbl-ex" width=700>
						<tr>
							<td>검색</td>
							<td><input type="text" name="searchKeyword"
								value='${ searchKeyword}'></td>
						</tr>
						<tr>
							<td colspan=2 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
 -->
				<%
				System.out.println("list :" + list.size());
				
				
					int index = 0;
					for (GuestbookVo vo : list) {
						System.out.println("vo :" + vo.toString());
				%>
				<br>
				<li>
					<strong><%=vo.getName()%></strong>
					<p>
						<%=vo.getContent().replaceAll("\n", "<br/>")%>
					</p>	
					<strong><%=vo.getRegDate()%></strong>	
					<a href="deleteform.do?no=<%=vo.getNo()%>">삭제</a>
				</li>
				<%
					}
				%>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>

	</div>
</body>
</html>