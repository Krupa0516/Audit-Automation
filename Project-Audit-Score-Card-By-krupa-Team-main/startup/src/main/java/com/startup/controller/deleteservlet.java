package com.startup.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.startup.model.startup;
import com.startup.repository.repositroy;
import com.startup.service.*;
public class deleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=null;
		try {
			session=request.getSession();
			@SuppressWarnings("unchecked")
			List<startup> obj = (List<startup>)session.getAttribute("startup_data");
			String name="";
			for(startup list:obj)
				name=list.getStartupname();
			
			int result=service.deletedata(name);
			if(result==1) {
				
				session.removeAttribute("startup_data");
				session.removeAttribute("startupnames");
				session.removeAttribute("tablename");
				session.removeAttribute("startup_data");
				session.removeAttribute("saatracker_data");
				session.removeAttribute("samanual_data");
				session.removeAttribute("sscard_data");
				session.setAttribute("startupnames", repositroy.getnames());
				RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
			    rs.forward(request, response);
			}
			else {
				
				RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
			    rs.forward(request, response);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			session.setAttribute("startupnames", repositroy.getnames());
			RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
		    rs.forward(request, response);
		}
	}

}
