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

public class registration extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		session.setAttribute("name", name);
		session.setAttribute("password", password);
		
		res.sendRedirect("Rverify");
		
	}
}
