package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Verify extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String password = (String) session.getAttribute("password");
		
		String url = "jdbc:mysql://localhost:3306/students";
		String userName = "root";
		String passwordDb= "mahi";
		String query = "Select * from login where name = ? and password = ?;";
		
		try {
			Connection con = DriverManager.getConnection(url, userName, passwordDb);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				out.println("Login successful");
			}else {
				out.println("Invalid credential");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
