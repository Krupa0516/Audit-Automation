package com.startup.controller;

import java.io.IOException;
import java.util.*;

import com.startup.repository.repositroy;
import com.startup.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
@MultipartConfig
public class uploadexcelfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("excel");
		String tablename= request.getParameter("tableName");
		List<String> startupnames= new ArrayList<String>();
		HttpSession session = request.getSession();
		session.removeAttribute("tablename");
		session.removeAttribute("startup_data");
		session.removeAttribute("saatracker_data");
		session.removeAttribute("samanual_data");
		session.removeAttribute("sscard_data");
		try {
			session.removeAttribute("startupnames");
			
		startupnames= service.uploadfiletoservice(filePart,tablename,request,response);
		session.setAttribute("startupnames", startupnames);
		RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
	    rs.forward(request, response);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			session.setAttribute("startupnames", repositroy.getnames());
			RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
		    rs.forward(request, response);
		}
		
		
	}

}
