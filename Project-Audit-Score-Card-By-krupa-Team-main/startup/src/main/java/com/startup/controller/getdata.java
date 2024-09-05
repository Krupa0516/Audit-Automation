package com.startup.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.startup.model.*;
import com.startup.service.*;

public class getdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String tablename = request.getParameter("tableName");
			String startupname = request.getParameter("startupname");
			HttpSession session = request.getSession();
		    session.removeAttribute("tablename");
			RequestDispatcher rs = null;
			session.removeAttribute("startup_data");
			session.removeAttribute("saatracker_data");
			session.removeAttribute("samanual_data");
			session.removeAttribute("sscard_data");
			if ("startup".equals(tablename)) {
				session.setAttribute("tablename", tablename);
				session.setAttribute("startup_data", (List<startup>) service.getdata(startupname));
			} else if ("saatracker".equals(tablename)) {
				session.setAttribute("tablename", tablename);
				session.setAttribute("saatracker_data", (List<saatracker>) service.getdata1(startupname));
			} else if ("samanual".equals(tablename)) {
				List<samanual> obj=(List<samanual>) service.getdata2(startupname);
				session.setAttribute("tablename", tablename);
				session.setAttribute("samanual_data", obj);
			} else if ("sscard".equals(tablename)) {
				session.setAttribute("tablename", tablename);
				session.setAttribute("sscard_data", (List<sscard>) service.getdata3(startupname));
			} else {
				rs = request.getRequestDispatcher("Home.jsp");
				rs.forward(request, response);
			}

			rs = request.getRequestDispatcher("view.jsp");
			rs.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			RequestDispatcher rs = request.getRequestDispatcher("Home.jsp");
			rs.forward(request, response);
		}
	}
}
