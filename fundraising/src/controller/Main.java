package controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.LoginDAO;
import dao.FundraiserDAO;
import model.Login;
import model.Fundraiser;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		int choice,funds;
		
		Login l = new Login();
		LoginDAO ldao = new LoginDAO();
		Fundraiser f = new Fundraiser();
		FundraiserDAO fdao = new FundraiserDAO();
		
		do
		{
			System.out.println("1. Admin\n2. Fundraiser \n3. Exit");
			System.out.println("*****************************************************");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Admin Login!!!");
				System.out.println("Enter the username");
				String username = sc.next();
				System.out.println("Enter the password");
				String password = sc.next();
				l.setUsername(username);
				l.setPassword(password);
				if(ldao.loginValidation(l))
				{
					System.out.println("Logged in as Admin!!!");
					do
					{
						System.out.println("*****************************************************");
						System.out.println("1. Add Fundraiser\n2. Display Fundrasier\n3. Logout");
						System.out.println("*****************************************************");
						System.out.println("Enter your choice");
						funds = sc.nextInt();
						switch(funds)
						{
						case 1:
							System.out.println("Add Fundraiser");
							System.out.println("Enter RaiserID");
							String RaiserID = sc.next();
							System.out.println("Enter RaiserName");
							String RaiserName = sc.next();
							System.out.println("Enter Raiser's age");
							int age = sc.nextInt();
							System.out.println("Enter Problem of Raiser");
							String Problem = sc.next();
							System.out.println("Enter the Donation Amount");
							int amount = sc.nextInt();
							f.setRaiserID(RaiserID);
							f.setRaiserName(RaiserName);
							f.setage(age);
							f.setProblem(Problem);
							f.setamount(amount);
							
							fdao.addFundraiser(f);
							System.out.println("Fundraiser added Successfully!!!");
							break;
						case 2:
							System.out.println("Fundraiser details are below:");
							fdao.display();
							break;
						case 3:
							System.out.println("Logged out successfully!!!");
							break;
						}
					}while(funds!=3);
				}
				else
				{
					System.out.println("Admin failed to login!!!---Check Credentials---");
				}
				break;
			case 2:
				System.out.println("Fundraiser Login!!!");
				System.out.println("Enter the username");
				String username1 = sc.next();
				System.out.println("Enter the password");
				String password1 = sc.next();
				l.setUsername(username1);
				l.setPassword(password1);
				if(ldao.loginValidation(l))
				{
					System.out.println("Fundraiser successfully logged in!!!");
					do
					{
						System.out.println("*****************************************************");
						System.out.println("1. Display Fundraiser\n2. Successfull Donations\n3. Logout");
						System.out.println("*****************************************************");
						System.out.println("Enter your choice");
						funds = sc.nextInt();
						switch(funds)
						{
						case 1:
							System.out.println("Fundraiser details are below:");
							fdao.display();
							break;
						case 2:
							System.out.println("Successfull Donations Details:");
							System.out.println("Enter the RaiserID");
							String RaiserID = sc.next();
							System.out.println("Enter the  Donation Amount");
							int Count = sc.nextInt();
							f.setRaiserID(RaiserID);
							f.setCount(Count);
							if(fdao.updateFundraiser(f))
								System.out.println("Updated Successfully!!!");
							else
								System.out.println("Out of STOCK!!!");
						case 3:
							System.out.println("Logged out successfully!!!");
							break;
						}
					}while(funds!=3);
				}
				else
				{
					System.out.println("Fundraiser failed to login!!!\nCheck Credentials");
				}
				break;
			case 3:
				System.out.println("Exit");
				break;
			}
		}while(choice!=3);
		sc.close();
	}

}
