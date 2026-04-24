<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.seojin.equipment.dto.Equipment" %>
<%
	String root = request.getContextPath();
	String error = (String) request.getAttribute("error");
	Equipment equipment = (Equipment) request.getAttribute("equipment");

	String stickerNo = equipment != null ? equipment.getStickerNo() : "";
	String productName = equipment != null ? equipment.getProductName() : "";
	String modelYear = equipment != null ? equipment.getModelYear() : "";
	String manager = equipment != null ? equipment.getManager() : "";
	String currentLocation = equipment != null ? equipment.getCurrentLocation() : "";
	String status = equipment != null ? equipment.getStatus() : "";
	String note = equipment != null ? equipment.getNote() : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장비 수정</title>
<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f8f8f8;
		margin: 0;
		padding: 0;
	}
	.container {
		width: 600px;
		margin: 40px auto;
		background: white;
		padding: 30px;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0,0,0,0.08);
	}
	h2 {
		margin-top: 0;
		text-align: center;
	}
	.form-group {
		margin-bottom: 15px;
	}
	label {
		display: block;
		margin-bottom: 6px;
		font-weight: bold;
	}
	input[type="text"], textarea, select {
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
	.readonly-box {
		width: 100%;
		padding: 10px;
		box-sizing: border-box;
		background-color: #f0f0f0;
		border: 1px solid #ccc;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>장비 수정</h2>

		<% if (error != null) { %>
			<div class="error"><%= error %></div>
		<% } %>

		<form action="<%= root %>/main" method="post">
			<input type="hidden" name="action" value="equipment-update">
			<input type="hidden" name="stickerNo" value="<%= stickerNo %>">

			<div class="form-group">
				<label>스티커번호</label>
				<div class="readonly-box"><%= stickerNo %></div>
			</div>

			<div class="form-group">
				<label for="productName">품명</label>
				<input type="text" id="productName" name="productName" value="<%= productName %>" required>
			</div>

			<div class="form-group">
				<label for="modelYear">연식</label>
				<input type="text" id="modelYear" name="modelYear" value="<%= modelYear %>">
			</div>

			<div class="form-group">
				<label for="manager">담당자</label>
				<input type="text" id="manager" name="manager" value="<%= manager %>">
			</div>

			<div class="form-group">
				<label for="currentLocation">현재위치</label>
				<input type="text" id="currentLocation" name="currentLocation" value="<%= currentLocation %>">
			</div>

			<div class="form-group">
				<label for="status">상태</label>
				<select id="status" name="status">
					<option value="회사보유" <%= "회사보유".equals(status) ? "selected" : "" %>>회사보유</option>
					<option value="출고중" <%= "출고중".equals(status) ? "selected" : "" %>>출고중</option>
					<option value="A/S중" <%= "A/S중".equals(status) ? "selected" : "" %>>A/S중</option>
					<option value="회수예정" <%= "회수예정".equals(status) ? "selected" : "" %>>회수예정</option>
				</select>
			</div>

			<div class="form-group">
				<label for="note">비고</label>
				<textarea id="note" name="note"><%= note %></textarea>
			</div>

			<div class="button-group">
				<button type="submit">수정완료</button>
				<a class="button" href="<%= root %>/main?action=equipment-detail&stickerNo=<%= stickerNo %>">취소</a>
			</div>
		</form>
	</div>
</body>
</html>