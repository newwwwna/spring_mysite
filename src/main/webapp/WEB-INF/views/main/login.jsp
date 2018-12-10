<%@page import="com.javalec.mysite.vo.UserVO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<script>
a:text-decoration:none;

</script>

</head>
<body>

<%
	UserVO vo = (UserVO)session.getAttribute("user") ;
	if(vo == null) {
%>
	<div id="container">
		<div id="content">
		<div id="board">
	<center>
		<h1>로그인</h1>
		<hr>
		<form action="${pageContext.request.contextPath }/user/login.do" method="post">
			<table class="tbl-ex" align="center"   width=400>
				<tr>
					<td bgcolor="#999">아이디</td>
					<!-- td><input type="text" name="id"  value="${userVO.id }"/></td -->
					<td><input type="text" name="id"  value="${user.id }"/></td>
					
				</tr>
				<tr>
					<td bgcolor="#999">비밀번호</td>
					<!-- td><input type="password" name="password" value="${userVO.password }"/></td-->
					<td><input type="password" name="password" value="${user.password }"/></td>
				</tr>
				<tr>
					<td colspan=2 align=center>
					<input type="submit" value="로그인" />
				</td>
				</tr>
			</table>
		</form>
		
		<hr>
		<a href="${pageContext.request.contextPath }/user/join.do">회원가입</a>
		<a href="${pageContext.request.contextPath }/user/findpw.do">비밀번호 찾기</a>
		
		
	</center>
	</div>
	</div>
	</div>
	<%
	}else{
		String temp = request.getContextPath() ;		
		response.sendRedirect(temp +"/main/main.do");
	}
	%>
</body>
</html>