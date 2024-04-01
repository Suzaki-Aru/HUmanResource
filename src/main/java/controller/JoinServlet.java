package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String department = request.getParameter("department");
        String emproyee = request.getParameter("emproyee");
        String position = request.getParameter("position");
        String password_param = request.getParameter("password");
        
//        
//        String url = "jdbc:mysql://localhost:3306/humanresource";
//        String user = "root";
//        String ppassword ="root";
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        try (Connection connection = DriverManager.getConnection (url, user, ppassword)){
//        String hashedPassword = HashGenerator.generateHash(password);
//        String sql ="INSERT INTO humans (department, emproyee, position, password, joined, updated) VALUES (?, ?, ?, ?, current_timestamp, current_timestamp)";
//        try(PreparedStatement statement = connection.prepareStatement(sql)) {
//        		
//        	    statement.setString(1, department);
//        	    statement.setString(2, emproyee);
//        	    statement.setString(3, position);
//        	    statement.setString(4, hashedPassword);
//        	   
//                statement.executeUpdate();
//                
//        }   catch(SQLException e){
//            e.printStackTrace();
//        }
//            catch (Exception e){
//            request.setAttribute("message", "Exception" + e.getMessage());
//        }
//        String forward="/ListServlet";
//        RequestDispatcher dispatcher= request .getRequestDispatcher(forward);
//        dispatcher.forward(request, response);
//    } catch (SQLException | NoSuchAlgorithmException e1) {
//		// TODO 自動生成された catch ブロック
//		e1.printStackTrace();
//	}
    	DAO dao = new DAO();
    	try {
			dao.insert(department, emproyee, position, password_param);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        String forward="/ListServlet";
        RequestDispatcher dispatcher= request .getRequestDispatcher(forward);
        dispatcher.forward(request, response);
	}
}
		