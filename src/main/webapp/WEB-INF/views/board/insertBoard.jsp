<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>새글등록</title>
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<center>
					<h1>글 등록</h1>
					<hr>
					<form action="write.do" method="post" enctype="multipart/form-data">
						<table class="tbl-ex" >
							<tr>
								<td bgcolor="#999">제목</td>
								<td class="left"><input type="text" name="title" size="50" /></td>
							</tr>
							<tr>
								<td bgcolor="#999">작성자</td>
								<td class="left"><input type="text" name="writer" size="20" /></td>
							</tr>
							<tr>
								<td bgcolor="#999">내용</td>
								<td class="left"><textarea name="content" cols="100"
										rows="10"></textarea></td>
							</tr>
							<tr>
								<td bgcolor="#999" width=70>업로드</td>
								<td class="left"><input type="file" name="uploadFile" /> </textarea></td>
							</tr>

							<tr>
								<td colspan="2" align="center"><input type="submit"
									value=" 새글 등록 " /></td>
							</tr>
						</table>
					</form>
					<hr>
				<div class="bottom">	
					<a href="getBoardList.do" id="new-book">글목록</a>
				</div>					
				</center>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="boardList" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>