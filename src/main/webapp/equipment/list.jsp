<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.seojin.equipment.dto.Equipment"%>
<%@ page import="com.seojin.equipment.dto.User"%>
<%
	String root = request.getContextPath();
	List<Equipment> equipments = (List<Equipment>) request.getAttribute("equipments");
	User loginUser = (User) session.getAttribute("loginUser");
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장비 목록</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
}

.header {
	background-color: #222;
	color: white;
	padding: 15px 30px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.container {
	width: 1000px;
	margin: 30px auto;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

h2 {
	margin-top: 0;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #f0f0f0;
}

.top-actions {
	margin-bottom: 15px;
}

a.button {
	display: inline-block;
	padding: 8px 12px;
	background-color: #333;
	color: white;
	text-decoration: none;
	border-radius: 4px;
}

a.button:hover {
	background-color: #555;
}

.user-info {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="header">
		<div>
			<strong>주류 유통 장비 관리 시스템</strong>
		</div>
		<div class="user-info">
			<%
			if (loginUser != null) {
			%>
			<%=loginUser.getName()%>님 로그인 중 <a class="button"
				href="<%=root%>/main?action=logout">로그아웃</a>
			<%
			} else {
			%>
			<a class="button" href="<%=root%>/main?action=login-form">로그인</a>
			<%
			}
			%>
		</div>
	</div>

	<div class="container">
		<h2>장비 목록</h2>

		<div class="top-actions">
			<a class="button"
				href="<%=root%>/main?action=equipment-regist-form">장비 등록</a>
		</div>

		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>스티커번호</th>
					<th>품명</th>
					<th>연식</th>
					<th>담당자</th>
					<th>현재위치</th>
					<th>상태</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (equipments != null && !equipments.isEmpty()) {
					for (Equipment equipment : equipments) {
				%>
				<tr>
					<td><%=equipment.getEno()%></td>
					<td><a
						href="<%=root%>/main?action=equipment-detail&stickerNo=<%=equipment.getStickerNo()%>">
							<%=equipment.getStickerNo()%>
					</a></td>
					<td><%=equipment.getProductName()%></td>
					<td><%=equipment.getModelYear()%></td>
					<td><%=equipment.getManager()%></td>
					<td><%=equipment.getCurrentLocation()%></td>
					<td><%=equipment.getStatus()%></td>
					<td><%=equipment.getNote()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="8">등록된 장비가 없습니다.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>