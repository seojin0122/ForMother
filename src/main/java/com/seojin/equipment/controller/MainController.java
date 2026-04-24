package com.seojin.equipment.controller;

import java.io.IOException;
import java.util.List;

import com.seojin.equipment.dto.Equipment;
import com.seojin.equipment.dto.User;
import com.seojin.equipment.service.EquipmentService;
import com.seojin.equipment.service.UserService;
import com.seojin.equipment.dto.EquipmentHistory;
import com.seojin.equipment.service.EquipmentHistoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();
	private EquipmentService equipmentService = EquipmentService.getInstance();
	private EquipmentHistoryService historyService = EquipmentHistoryService.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if (action == null || action.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		switch (action) {
		case "login-form":
			loginForm(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "equipment-list":
			equipmentList(request, response);
			break;
		case "equipment-regist-form":
			equipmentRegistForm(request, response);
			break;
		case "equipment-regist":
			equipmentRegist(request, response);
			break;
		case "equipment-detail":
			equipmentDetail(request, response);
			break;
		case "equipment-update-form":
			equipmentUpdateForm(request, response);
			break;
		case "equipment-update":
			equipmentUpdate(request, response);
			break;
		case "equipment-delete":
			equipmentDelete(request, response);
			break;
		case "history-list":
			historyList(request, response);
			break;
		case "history-regist-form":
			historyRegistForm(request, response);
			break;
		case "history-regist":
			historyRegist(request, response);
			break;
		case "equipment-search":
			equipmentSearch(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login-form.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		User loginUser = userService.login(id, password);

		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath() + "/main?action=equipment-list");
		} else {
			request.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			request.getRequestDispatcher("/login-form.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/main?action=login-form");
	}

	private void equipmentList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Equipment> equipments = equipmentService.getEquipmentList();
		request.setAttribute("equipments", equipments);
		request.getRequestDispatcher("/equipment/list.jsp").forward(request, response);
	}
	
	private void equipmentRegistForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/equipment/regist.jsp").forward(request, response);
	}

	private void equipmentRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");
		String productName = request.getParameter("productName");
		String modelYear = request.getParameter("modelYear");
		String manager = request.getParameter("manager");
		String currentLocation = request.getParameter("currentLocation");
		String status = request.getParameter("status");
		String note = request.getParameter("note");

		Equipment equipment = new Equipment();
		equipment.setStickerNo(stickerNo);
		equipment.setProductName(productName);
		equipment.setModelYear(modelYear);
		equipment.setManager(manager);
		equipment.setCurrentLocation(currentLocation);
		equipment.setStatus(status);
		equipment.setNote(note);

		try {
			equipmentService.registEquipment(equipment);
			response.sendRedirect(request.getContextPath() + "/main?action=equipment-list");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("equipment", equipment);
			request.getRequestDispatcher("/equipment/regist.jsp").forward(request, response);
		}
	}
	
	private void equipmentDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");
		Equipment equipment = equipmentService.getEquipmentDetail(stickerNo);

		if (equipment == null) {
			request.setAttribute("error", "해당 장비를 찾을 수 없습니다.");
			equipmentList(request, response);
			return;
		}

		List<EquipmentHistory> histories = historyService.getHistoryByStickerNo(stickerNo);

		request.setAttribute("equipment", equipment);
		request.setAttribute("histories", histories);
		request.getRequestDispatcher("/equipment/detail.jsp").forward(request, response);
	}
	
	private void equipmentUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");
		Equipment equipment = equipmentService.getEquipmentDetail(stickerNo);

		if (equipment == null) {
			request.setAttribute("error", "수정할 장비를 찾을 수 없습니다.");
			request.getRequestDispatcher("/equipment/list.jsp").forward(request, response);
			return;
		}

		request.setAttribute("equipment", equipment);
		request.getRequestDispatcher("/equipment/update.jsp").forward(request, response);
	}

	private void equipmentUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");
		String productName = request.getParameter("productName");
		String modelYear = request.getParameter("modelYear");
		String manager = request.getParameter("manager");
		String currentLocation = request.getParameter("currentLocation");
		String status = request.getParameter("status");
		String note = request.getParameter("note");

		Equipment equipment = new Equipment();
		equipment.setStickerNo(stickerNo);
		equipment.setProductName(productName);
		equipment.setModelYear(modelYear);
		equipment.setManager(manager);
		equipment.setCurrentLocation(currentLocation);
		equipment.setStatus(status);
		equipment.setNote(note);

		try {
			equipmentService.modifyEquipment(equipment);
			response.sendRedirect(request.getContextPath() + "/main?action=equipment-detail&stickerNo=" + stickerNo);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("equipment", equipment);
			request.getRequestDispatcher("/equipment/update.jsp").forward(request, response);
		}
	}
	
	private void equipmentDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");

		try {
			equipmentService.removeEquipment(stickerNo);
			response.sendRedirect(request.getContextPath() + "/main?action=equipment-list");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			equipmentList(request, response);
		}
	}
	
	private void historyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<EquipmentHistory> histories = historyService.getHistoryList();
		request.setAttribute("histories", histories);
		request.getRequestDispatcher("/history/list.jsp").forward(request, response);
	}

	private void historyRegistForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stickerNo = request.getParameter("stickerNo");
		request.setAttribute("stickerNo", stickerNo);
		request.getRequestDispatcher("/history/regist.jsp").forward(request, response);
	}

	private void historyRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String historyDate = request.getParameter("historyDate");
		String companyName = request.getParameter("companyName");
		String stickerNo = request.getParameter("stickerNo");
		String productName = request.getParameter("productName");
		String manager = request.getParameter("manager");
		String actionType = request.getParameter("actionType");
		String partnerName = request.getParameter("partnerName");
		String detail = request.getParameter("detail");

		EquipmentHistory history = new EquipmentHistory();
		history.setHistoryDate(historyDate);
		history.setCompanyName(companyName);
		history.setStickerNo(stickerNo);
		history.setProductName(productName);
		history.setManager(manager);
		history.setActionType(actionType);
		history.setPartnerName(partnerName);
		history.setDetail(detail);

		try {
			historyService.registHistory(history);
			response.sendRedirect(request.getContextPath() + "/main?action=equipment-detail&stickerNo=" + stickerNo);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("history", history);
			request.getRequestDispatcher("/history/regist.jsp").forward(request, response);
		}
	}
	
	private void equipmentSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		String status = request.getParameter("status");

		List<Equipment> equipments;

		if (keyword != null && !keyword.trim().isEmpty()) {
			equipments = equipmentService.searchByStickerNo(keyword);
			request.setAttribute("keyword", keyword);
		} else if (status != null && !status.trim().isEmpty()) {
			equipments = equipmentService.searchByStatus(status);
			request.setAttribute("status", status);
		} else {
			equipments = equipmentService.getEquipmentList();
		}

		request.setAttribute("equipments", equipments);
		request.getRequestDispatcher("/equipment/list.jsp").forward(request, response);
	}
}