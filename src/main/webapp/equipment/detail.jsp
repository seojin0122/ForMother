<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.seojin.equipment.dto.Equipment"%>
<%@ page import="java.util.List"%>
<%@ page import="com.seojin.equipment.dto.EquipmentHistory"%>
<%
String root = request.getContextPath();
Equipment equipment = (Equipment) request.getAttribute("equipment");
List<EquipmentHistory> histories = (List<EquipmentHistory>) request.getAttribute("histories");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장비 상세</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
}

.container {
	width: 800px;
	margin: 40px auto;
	background: white;
	padding: 30px;
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
	padding: 12px;
}

th {
	background-color: #f0f0f0;
	width: 180px;
	text-align: left;
}

.button-group {
	margin-top: 20px;
}

a.button {
	display: inline-block;
	padding: 10px 16px;
	background-color: #333;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	margin-right: 8px;
}

a.button:hover {
	background-color: #555;
}

.section-title {
	margin-top: 35px;
	margin-bottom: 10px;
	font-size: 20px;
	font-weight: bold;
}

.empty-box {
	padding: 20px;
	border: 1px solid #ddd;
	background-color: #fafafa;
	color: #666;
}


</style>
</head>
<body>
	<div class="container">
		<h2>장비 상세정보</h2>

		<table>
			<tr>
				<th>장비번호</th>
				<td><%=equipment.getEno()%></td>
			</tr>
			<tr>
				<th>스티커번호</th>
				<td><%=equipment.getStickerNo()%></td>
			</tr>
			<tr>
				<th>품명</th>
				<td><%=equipment.getProductName()%></td>
			</tr>
			<tr>
				<th>연식</th>
				<td><%=equipment.getModelYear()%></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td><%=equipment.getManager()%></td>
			</tr>
			<tr>
				<th>현재위치</th>
				<td><%=equipment.getCurrentLocation()%></td>
			</tr>
			<tr>
				<th>상태</th>
				<td><%=equipment.getStatus()%></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><%=equipment.getNote()%></td>
			</tr>
		</table>

		<div class="button-group">
			<a class="button" href="<%=root%>/main?action=equipment-list">목록</a>
			<a class="button"
				href="<%=root%>/main?action=equipment-update-form&stickerNo=<%=equipment.getStickerNo()%>">수정</a>
			<a class="button"
				href="<%=root%>/main?action=equipment-delete&stickerNo=<%=equipment.getStickerNo()%>"
				onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a> <a class="button"
				href="<%=root%>/main?action=history-regist-form&stickerNo=<%=equipment.getStickerNo()%>">이력
				등록</a>
		</div>

		<div class="section-title">장비 이력</div>

		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>거래처</th>
					<th>구분</th>
					<th>처리업체</th>
					<th>담당자</th>
					<th>상세내용</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (histories != null && !histories.isEmpty()) {
					for (EquipmentHistory history : histories) {
				%>
				<tr>
					<td><%=history.getHno()%></td>
					<td><%=history.getHistoryDate()%></td>
					<td><%=history.getCompanyName()%></td>
					<td><%=history.getActionType()%></td>
					<td><%=history.getPartnerName()%></td>
					<td><%=history.getManager()%></td>
					<td><%=history.getDetail()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="7">등록된 이력이 없습니다.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>