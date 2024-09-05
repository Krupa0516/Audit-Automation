package com.startup.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.startup.model.startup;
import com.startup.repository.repositroy;
import com.startup.service.*;
import java.util.*;
public class updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String startupcode=request.getParameter("startupcode");
			String startupcategory=request.getParameter("startupcategory");
			String website=request.getParameter("website");
			HttpSession session=request.getSession();
			@SuppressWarnings("unchecked")
			List<startup> updateclass=(List<startup>)session.getAttribute("startup_data");
			String startupname="";
			int srno = 0;
			for(startup obj : updateclass)
			{
				startupname=obj.getStartupname();
				srno=obj.getSrno();
			}
			session.removeAttribute("startup_data");
			startup Startup=new startup(startupcode,startupcategory,srno,startupname,website);
			service.update(Startup);
			session.setAttribute("startup_data", repositroy.getdata(startupname));
			RequestDispatcher rs=request.getRequestDispatcher("update.jsp");
		    rs.forward(request, response);
		}catch(Exception ex) {
			ex.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
		    rs.forward(request, response);
		}
	}

}
