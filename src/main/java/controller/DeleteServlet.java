package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			
			String url = "jdbc:mysql://localhost:3306/humanresource";
	        String user = "root";
	        String password ="root";
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	        String sql ="UPDATE humans SET del_flag  = 1 WHERE id = ?";
	        try (Connection connection = DriverManager.getConnection
	        (url, user, password);
	        PreparedStatement statement = connection.prepareStatement
	        (sql)){
	        	    
	        	     statement.setString(1, id);
	                 statement.executeUpdate();
	                
                    
	        }   catch(SQLException e){
	            e.printStackTrace();
	        }
	            catch (Exception e){
	            request.setAttribute("message", "Exception" + e.getMessage());
	        }
	        String forward="/ListServlet";
	        RequestDispatcher dispatcher= request .getRequestDispatcher(forward);
	        dispatcher.forward(request, response);
	    }
	}


