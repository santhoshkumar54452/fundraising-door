package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionManager.ConnectionManager;
import model.Fundraiser;

public class FundraiserDAO {
	public void addFundraiser(Fundraiser f) throws ClassNotFoundException, SQLException
	{
		String RaiserID=f.getRaiserID();
		String RaiserName=f.getRaiserName();
		int age=f.getage();
		String Problem=f.getProblem();
		int amount=f.getamount();
		int Count=f.getCount();
		
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		String query = "insert into Fundraiser values (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,RaiserID);
		ps.setString(2, RaiserName);
		ps.setInt(3,age);
		ps.setString(4,Problem);
		ps.setInt(5,amount);
		ps.setInt(6,Count);
		
		ps.executeUpdate();
		
		conm.closeConnection();
	}
	
	public void display() throws ClassNotFoundException, SQLException
	{
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Fundraiser");
		while(rs.next())
		{
			System.out.println(rs.getString("RaiserID")+" | "+rs.getString("RaiserName")+" | "+rs.getInt("age")+" | "+rs.getString("Problem")+" | "+rs.getInt("amount")+rs.getInt("Count"));
		}
	}
	
	public boolean updateFundraiser(Fundraiser f) throws ClassNotFoundException, SQLException
	{
		String RaiserID = f.getRaiserID();
		int Count = f.getCount();
		
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		PreparedStatement st = con.prepareStatement("select amount from Fundraiser where RaiserID = ?");
		st.setString(1,RaiserID);
		
		ResultSet rs = st.executeQuery();
		System.out.println(rs);
		rs.next();
		int inStock = rs.getInt(1);
		if(inStock>=Count)
		{
			st = con.prepareStatement("update Fundraiser set Count=? where RaiserID=?");
			st.setInt(1,(inStock-Count));
			st.setString(2, RaiserID);
			st.executeUpdate();
			conm.closeConnection();
			return true;
		}
		else
			conm.closeConnection();
			return false;
	}


}
