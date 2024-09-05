package com.startup.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.startup.repository.repositroy;

/**
 * Servlet implementation class moveservlet
 */
public class moveservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
	    if(request != null) {
	        session = request.getSession();
	    }
		session.removeAttribute("tablename");
		session.removeAttribute("startup_data");
		session.removeAttribute("saatracker_data");
		session.removeAttribute("samanual_data");
		session.removeAttribute("sscard_data");
	    session.setAttribute("startupnames", repositroy.getnames());
	    RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
	    rs.forward(request, response);
	}

}
