package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionManager.ConnectionManager;
import model.Login;

public class LoginDAO {
	public boolean loginValidation(Login l) throws ClassNotFoundException, SQLException
	{
		String username = l.getUsername();
		String password = l.getPassword();
		
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Login");
		while(rs.next())
		{
			if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
			{
				conm.closeConnection();
				return true;
			}
		}
		conm.closeConnection();
		return false;
	}

}
