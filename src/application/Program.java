package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = DB.connect();
		DB.closeConnection();
		
		System.out.println("Terminado");
	}

}
