<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.seojin.equipment.dto.EquipmentHistory" %>
<%
	String root = request.getContextPath();
	List<EquipmentHistory> histories = (List<EquipmentHistory>) request.getAttribute("histories");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장비 이력 목록</title>
<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f8f8f8;
		margin: 0;
		padding: 0;
	}
	.container {
		width: 1100px;
		margin: 40px auto;
		background: white;
		padding: 30px;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0,0,0,0.08);
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
</style>
</head>
<body>
	<div class="container">
		<h2>전체 장비 이력 목록</h2>

		<div style="margin-bottom:15px;">
			<a class="button" href="<%= root %>/main?action=equipment-list">장비 목록</a>
		</div>

		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>거래처</th>
					<th>스티커번호</th>
					<th>품명</th>
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
					<td><%= history.getHno() %></td>
					<td><%= history.getHistoryDate() %></td>
					<td><%= history.getCompanyName() %></td>
					<td><%= history.getStickerNo() %></td>
					<td><%= history.getProductName() %></td>
					<td><%= history.getActionType() %></td>
					<td><%= history.getPartnerName() %></td>
					<td><%= history.getManager() %></td>
					<td><%= history.getDetail() %></td>
				</tr>
				<%
						}
					} else {
				%>
				<tr>
					<td colspan="9">등록된 이력이 없습니다.</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>