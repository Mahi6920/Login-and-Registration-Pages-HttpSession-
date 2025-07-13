package Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class verify extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String password = (String) session.getAttribute("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		String url = "jdbc:mysql://localhost:3306/students";
		String username = "root";
		String passwordDb = "mahi";
		String query = "insert into login(name, password) values (?, ?);";
		
		try {
			Connection con = DriverManager.getConnection(url, username, passwordDb);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
			
			int r = ps.executeUpdate();
			
			if(r>0) {
				out.println("Registration successfull");
			}else {
				out.println("Registration failed");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
}
