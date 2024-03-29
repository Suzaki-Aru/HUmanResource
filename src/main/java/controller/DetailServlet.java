package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
        String department = request.getParameter("department");
        String emproyee = request.getParameter("emproyee");
        String position = request.getParameter("position");
        
        request.setAttribute("id", id);
		request.setAttribute("department", department);
	    request.setAttribute("emproyee", emproyee);
		request.setAttribute("position", position);	
	    
	    String view="WEB-INF/view/edit.jsp";
    	RequestDispatcher dispatcher= request .getRequestDispatcher(view);
    	dispatcher.forward(request, response);
		         
	}

}
