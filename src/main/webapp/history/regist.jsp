<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.seojin.equipment.dto.EquipmentHistory" %>
<%
	String root = request.getContextPath();
	String error = (String) request.getAttribute("error");
	String stickerNoParam = (String) request.getAttribute("stickerNo");
	EquipmentHistory history = (EquipmentHistory) request.getAttribute("history");

	String historyDate = history != null ? history.getHistoryDate() : "";
	String companyName = history != null ? history.getCompanyName() : "";
	String stickerNo = history != null ? history.getStickerNo() : (stickerNoParam != null ? stickerNoParam : "");
	String productName = history != null ? history.getProductName() : "";
	String manager = history != null ? history.getManager() : "";
	String actionType = history != null ? history.getActionType() : "";
	String partnerName = history != null ? history.getPartnerName() : "";
	String detail = history != null ? history.getDetail() : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장비 이력 등록</title>
<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f8f8f8;
		margin: 0;
		padding: 0;
	}
	.container {
		width: 650px;
		margin: 40px auto;
		background: white;
		padding: 30px;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0,0,0,0.08);
	}
	h2 {
		text-align: center;
		margin-top: 0;
	}
	.form-group {
		margin-bottom: 15px;
	}
	label {
		display: block;
		margin-bottom: 6px;
		font-weight: bold;
	}
	input[type="text"], input[type="date"], textarea, select {
		width: 100%;
		padding: 10px;
		box-sizing: border-box;
	}
	textarea {
		height: 100px;
		resize: vertical;
	}
	.button-group {
		margin-top: 20px;
		text-align: center;
	}
	button, a.button {
		display: inline-block;
		padding: 10px 16px;
		border: none;
		background-color: #333;
		color: white;
		text-decoration: none;
		cursor: pointer;
		border-radius: 4px;
		margin: 0 5px;
	}
	button:hover, a.button:hover {
		background-color: #555;
	}
	.error {
		color: red;
		text-align: center;
		margin-bottom: 15px;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>장비 이력 등록</h2>

		<% if (error != null) { %>
			<div class="error"><%= error %></div>
		<% } %>

		<form action="<%= root %>/main" method="post">
			<input type="hidden" name="action" value="history-regist">

			<div class="form-group">
				<label for="historyDate">날짜</label>
				<input type="date" id="historyDate" name="historyDate" value="<%= historyDate %>" required>
			</div>

			<div class="form-group">
				<label for="companyName">거래처명</label>
				<input type="text" id="companyName" name="companyName" value="<%= companyName %>">
			</div>

			<div class="form-group">
				<label for="stickerNo">스티커번호</label>
				<input type="text" id="stickerNo" name="stickerNo" value="<%= stickerNo %>" required>
			</div>

			<div class="form-group">
				<label for="productName">품명</label>
				<input type="text" id="productName" name="productName" value="<%= productName %>">
			</div>

			<div class="form-group">
				<label for="manager">담당자</label>
				<input type="text" id="manager" name="manager" value="<%= manager %>">
			</div>

			<div class="form-group">
				<label for="actionType">구분</label>
				<select id="actionType" name="actionType">
					<option value="출고" <%= "출고".equals(actionType) ? "selected" : "" %>>출고</option>
					<option value="입고" <%= "입고".equals(actionType) ? "selected" : "" %>>입고</option>
					<option value="A/S" <%= "A/S".equals(actionType) ? "selected" : "" %>>A/S</option>
					<option value="기타" <%= "기타".equals(actionType) ? "selected" : "" %>>기타</option>
				</select>
			</div>

			<div class="form-group">
				<label for="partnerName">입출처 / 처리업체</label>
				<input type="text" id="partnerName" name="partnerName" value="<%= partnerName %>">
			</div>

			<div class="form-group">
				<label for="detail">상세내용</label>
				<textarea id="detail" name="detail"><%= detail %></textarea>
			</div>

			<div class="button-group">
				<button type="submit">등록</button>
				<a class="button" href="<%= root %>/main?action=equipment-detail&stickerNo=<%= stickerNo %>">취소</a>
			</div>
		</form>
	</div>
</body>
</html>