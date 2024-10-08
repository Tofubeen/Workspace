<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
			<link rel="stylesheet" type="text/css" href="/css/todoboard.css">
		</head>

		<body>
			<form method="post">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>완료?</th>
							<th>TODO Subject</th>
							<th>기한</th>
							<th>완료하기</th>
							<th>삭제하기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${toDoBoardListVO.boardList}" var="board">
							<c:choose>
								<c:when test="${board.status == 'DONE'}">
									<tr>
										<td>${board.id}</td>
										<td>${board.status}</td>
										<td>${board.subject}</td>
										<td>${board.endDt}</td>
										<td>
											<input class="success" type="hidden">
										</td>
										<td>
											<a href="/board/remove/${board.id}">
												<input class="remove" type="button" value="삭제">
											</a>
										</td>
									</tr>

								</c:when>
								<c:otherwise>
									<tr>
										<td>${board.id}</td>
										<td>${board.status}</td>
										<td>${board.subject}</td>
										<td>${board.endDt}</td>
										<td>
											<a href="/board/success/${board.id}" style="color: red;">완료</a>
										</td>
										<td>
											<a href="/board/remove/${board.id}">
												<input class="remove" type="button" value="삭제">
											</a>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
				<div class="right-align">
					<a href="/board/write">
						<input type="button" style="color: white; background-color: black;" value="새 아이템추가">
					</a>
				</div>
			</form>
		</body>

		</html>