<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

<title>글 목록</title>
</head>
<body>


	<div id="container">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
		<div id="board">
			<center>
				<h1>글 목록</h1>
				<h3>

				</h3>
				<!-- 검색 시작 -->
				<form action="getBoardList.do" method="post">
					<table class="tbl-ex" width=700>
						<tr>
							<td align="right"><select name="searchCondition">
									<!-- option value="TITLE">제목
							<option value="CONTENT">내용  -->


									<c:forEach items="${conditionMap}" var="option">
										<option value="${option.value }">${option.key}</option>
									</c:forEach>
							</select> <input name="searchKeyword" type="text" /> <input type="submit"
								value="검색" /></td>
						</tr>
					</table>
				</form>



				<!-- 검색 종료 -->
				<table class="tbl-ex" width=700>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
						<th>조회수</th>
					</tr>
			<c:forEach items="${map.list }" var="board">
				<tr>
					<td>${board.seq }</td>
					<td align="left"><a href="getBoard.do?seq=${board.seq }">
							${board.title }</a></td>
					<td>${board.writer }</td>
					<td>${board.regDate }</td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
				</table>
			
					<div class="pager">
					<ul>
						<c:if test="${map.prevPage > 0 }" >
							<a href="getBoardList.do?p=${map.prevPage }&searchKeyword=${map.keyword }&searchCondition=${map.searchCondition }">◀</a>
						
						</c:if>
						
						<c:forEach begin="${map.beginPage }" end="${map.beginPage + map.listSize - 1 }" var="page">
							<c:choose>
							 
								<c:when test="${map.endPage < page }">
								
								</c:when> 
 
								<c:when test="${map.currentPage == page }">
								    <li class="selected"><a href="#">${page } </a></li> 
								</c:when>
								<c:otherwise> 
								    <li><a href="getBoardList.do?p=${page }&searchKeyword=${map.keyword }&searchCondition=${map.searchCondition }">${page }</a></li>
								</c:otherwise>


							</c:choose>
						</c:forEach>
						
						<c:if test="${map.nextPage > 0 }" >
							<a href="getBoardList.do?p=${map.nextPage }&searchKeyword=${map.keyword }&searchCondition=${map.searchCondition }">▶</a>
						</c:if>	

					</ul>
				</div>
				
				
				<br>
					<div class="bottom">
					<a href="write.do" id="new-book">글쓰기</a>
				</div>
			</center>
			</div>
		</div>
				<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="boardList"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>