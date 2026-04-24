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
String keyword = (String) request.getAttribute("keyword");
String status = (String) request.getAttribute("status");

if (keyword == null)
	keyword = "";
if (status == null)
	status = "";
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
		<div
			style="margin-bottom: 20px; padding: 15px; background: #f7f7f7; border: 1px solid #ddd;">
			<form action="<%=root%>/main" method="get"
				style="margin-bottom: 10px;">
				<input type="hidden" name="action" value="equipment-search">
				<label><strong>스티커번호 검색</strong></label> <input type="text"
					name="keyword" value="<%=keyword%>" placeholder="예: SC-001"
					style="padding: 8px; width: 220px; margin-left: 10px;">
				<button type="submit" style="padding: 8px 12px;">검색</button>
			</form>

			<form action="<%=root%>/main" method="get">
				<input type="hidden" name="action" value="equipment-search">
				<label><strong>상태별 조회</strong></label> <select name="status"
					style="padding: 8px; margin-left: 20px;">
					<option value="">전체</option>
					<option value="회사보유" <%="회사보유".equals(status) ? "selected" : ""%>>회사보유</option>
					<option value="출고중" <%="출고중".equals(status) ? "selected" : ""%>>출고중</option>
					<option value="A/S중" <%="A/S중".equals(status) ? "selected" : ""%>>A/S중</option>
					<option value="회수예정" <%="회수예정".equals(status) ? "selected" : ""%>>회수예정</option>
				</select>
				<button type="submit" style="padding: 8px 12px;">조회</button>
				<a class="button" href="<%=root%>/main?action=equipment-list">초기화</a>
			</form>
		</div>
		<div class="top-actions">
			<a class="button" href="<%=root%>/main?action=equipment-regist-form">장비
				등록</a> <a class="button" href="<%=root%>/main?action=history-list">이력
				목록</a>
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