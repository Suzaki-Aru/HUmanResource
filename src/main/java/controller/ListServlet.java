package controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/humanresource";
        String user = "root";
        String password ="root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e){
            e.printStackTrace();
        }
        
        HttpSession session = request.getSession();
        String sql;
        String position_param = (String) session.getAttribute("position");
        String iid = (String) session.getAttribute("id");
        session.setAttribute("position_param", position_param);
        
        if (Objects.equals(position_param, "Leader") || Objects.equals(position_param, "Assistant Manager") || Objects.equals(position_param, "Manager")) {
			sql = "SELECT * FROM humans where del_flag = 0";
		} else {
			sql = "SELECT * FROM humans where del_flag = 0 and id = ?";
		}
        try (Connection connection = DriverManager.getConnection(url, user, password);
        		PreparedStatement statement = connection.prepareStatement(sql)){
        		if (Objects.equals(sql, "SELECT * FROM humans where del_flag = 0 and id = ?")) {
        			statement.setString(1, iid);
        		}
        		ResultSet results = statement.executeQuery();
		                ArrayList<HashMap<String,String>> rows =new
		                ArrayList<HashMap<String,String>>();
		                while(results.next()){
		                    HashMap<String,String> columns = new
		                    HashMap<String,String>();
		                    String id = results.getString("id");
		                    columns.put("id", id);
		                    String department = results.getString("department");
		                    columns.put("department", department);
		                    String emproyee = results.getString("emproyee");
		                    columns.put("emproyee", emproyee);
		                    String position = results.getString("position");
		                    columns.put("position", position);
		                    String joined = results.getString("joined");
		                    columns.put("joined", joined);
		                   
		                    rows.add(columns);
		                }
		                
//		                HttpSession session = request.getSession();
		                session.setAttribute("rows",rows);
		                
        }   catch(SQLException e){
            e.printStackTrace();
        }
            catch (Exception e){
            request.setAttribute("message", "Exception" + e.getMessage());
        }
        if (iid != null) {
            String view = "/WEB-INF/view/list.jsp";
            request.getRequestDispatcher(view).forward(request, response);
        } else {
            response.sendRedirect("LoginServlet");
        }
	}
} 
	