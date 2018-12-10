<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
<title>글 상세</title>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<center>
					<h1>글 상세</h1>
					<hr>
					<form action="updateBoard.do" method="post">
						<input name="seq" type="hidden" value="${board.seq}" />
						<table class="tbl-ex" width=700>
							<tr>
								<td bgcolor="#999">제목</td>
								<td class="left"><input name="title" type="text"
									value="${board.title }" /></td>
							</tr>
							<tr>
								<td bgcolor="#999">작성자</td>
								<td class="left">${board.writer }</td>
							</tr>
							<tr>
								<td bgcolor="#999">내용</td>
								<td class="left">
								<textarea name="content" cols="100" rows="10">${board.content }</textarea>
								</td>
							</tr>
							<tr>
								<td bgcolor="#999">등록일</td>
								<td class="left">${board.regDate }</td>
							</tr>
							<tr>
								<td bgcolor="#999">조회수</td>
								<td class="left">${board.cnt }</td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="글 수정" /></td>
							</tr>
						</table>
					</form>
					<hr>
				</center>
				
				<div class="bottom">	
					<a href="write.do" id="new-book">글등록</a>&nbsp;&nbsp;&nbsp; 
					<a href="deleteBoard.do?seq=${board.seq }" id="new-book">글삭제</a>&nbsp;&nbsp;&nbsp;
					<a href="getBoardList.do" id="new-book">글목록</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="boardList" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
