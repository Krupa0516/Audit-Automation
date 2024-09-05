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
import com.startup.model.samanualkey;
import com.startup.service.service;

/**
 * Servlet implementation class update_data
 */
public class update_data extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Double actualscore = Double.parseDouble(request.getParameter("actualscore"));
			String remark = request.getParameter("remark");
			Long id = (long) Integer.parseInt(request.getParameter("id"));
			System.out.println(actualscore + " : " + remark + " : " + id);
			String tablename = request.getParameter("tableName");
			String startupname = request.getParameter("startupname");
			HttpSession session = request.getSession();
			RequestDispatcher rs = null;
			samanual one = new samanual();
			one.setId(id);
			one.setStartupname(startupname);
			one.setActualscore(actualscore);
			one.setRemark(remark);
			
			try {
				service.updatemanualandcard(one);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			
			// service.updatemanual()
			session.removeAttribute("samanual_data");
			List<samanual> obj = (List<samanual>) service.getdata2(startupname);
			session.setAttribute("tablename", tablename);
			session.setAttribute("samanual_data", obj);
			rs = request.getRequestDispatcher("view.jsp");
			rs.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			RequestDispatcher rs = request.getRequestDispatcher("Home.jsp");
			rs.forward(request, response);
		}
	}

}
