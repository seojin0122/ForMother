package com.seojin.equipment.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.seojin.equipment.dto.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/main")
public class SessionFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	private Set<String> protectedActions = new HashSet<>();

	@Override
	public void init() throws ServletException {
		protectedActions.add("equipment-list");
		protectedActions.add("equipment-detail");
		protectedActions.add("equipment-regist-form");
		protectedActions.add("equipment-regist");
		protectedActions.add("equipment-update-form");
		protectedActions.add("equipment-update");
		protectedActions.add("equipment-delete");
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String action = request.getParameter("action");

		if (action != null && protectedActions.contains(action)) {
			HttpSession session = request.getSession(false);
			User loginUser = null;

			if (session != null) {
				loginUser = (User) session.getAttribute("loginUser");
			}

			if (loginUser == null) {
				response.sendRedirect(request.getContextPath() + "/main?action=login-form");
				return;
			}
		}

		chain.doFilter(request, response);
	}
}