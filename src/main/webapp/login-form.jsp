<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f5f5f5;
	}
	.container {
		width: 400px;
		margin: 100px auto;
		padding: 30px;
		background: white;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0,0,0,0.1);
	}
	h2 {
		text-align: center;
		margin-bottom: 20px;
	}
	.form-group {
		margin-bottom: 15px;
	}
	label {
		display: block;
		margin-bottom: 5px;
		font-weight: bold;
	}
	input[type="text"], input[type="password"] {
		width: 100%;
		padding: 10px;
		box-sizing: border-box;
	}
	button {
		width: 100%;
		padding: 10px;
		background-color: #333;
		color: white;
		border: none;
		cursor: pointer;
	}
	button:hover {
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
		<h2>장비 관리 시스템 로그인</h2>

		<% if (error != null) { %>
			<div class="error"><%= error %></div>
		<% } %>

		<form action="<%= root %>/main" method="post">
			<input type="hidden" name="action" value="login">

			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" required>
			</div>

			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" required>
			</div>

			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>