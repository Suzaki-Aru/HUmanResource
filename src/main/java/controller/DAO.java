package controller;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.HashGenerator;

public class DAO {

	String url = "jdbc:mysql://localhost:3306/humanresource";
	String user = "root";
	String password = "root";
	Connection connection = null;

	public void connect() throws Exception{
		// おまじない
		Class.forName("com.mysql.cj.jdbc.Driver");
		// ①DBに接続
		connection = DriverManager.getConnection(url, user, password);
//		throw new Exception();
	}
	
	public ArrayList<HashMap<String, String>> select(HttpServletRequest request) throws Exception {
		connect();
        
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
		                
		                session.setAttribute("rows",rows);
		                return (rows);
        }   catch(SQLException e){
            e.printStackTrace();
        }
            catch (Exception e){
            request.setAttribute("message", "Exception" + e.getMessage());
            
        }
        return (null);
	}
	
	public void insert (String department, String emproyee, String position, String password_param, HttpServletRequest request) throws Exception {
        connect();
        try {
            String hashedPassword = HashGenerator.generateHash(password_param);
            String sql ="INSERT INTO humans (department, emproyee, position, password, joined, updated) VALUES (?, ?, ?, ?, current_timestamp, current_timestamp)";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
            		
            	    statement.setString(1, department);
            	    statement.setString(2, emproyee);
            	    statement.setString(3, position);
            	    statement.setString(4, hashedPassword);
            	   
                    statement.executeUpdate();
                    
            }   catch(SQLException e){
                e.printStackTrace();
            }
                catch (Exception e){
                request.setAttribute("message", "Exception" + e.getMessage());
            }
            
        } catch (NoSuchAlgorithmException e1) {
    		// TODO 自動生成された catch ブロック
    		e1.printStackTrace();
    	}

    	}
	
	public void delete (String id) throws Exception {
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
//            catch (Exception e){
//            request.setAttribute("message", "Exception" + e.getMessage());
//        }
	}
}