package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import db.DB;

public class App {
	
	public static void main(String[] args) {
		
		//Teste simples para confirmar a conexão com o banco de dados.
		
		Connection conn = null;
		PreparedStatement st = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			conn = DB.getConnection();
			//Colocando autoCommit falso como boa prática.
			conn.setAutoCommit(false);
			
			System.out.print("Enter the name you wish to insert: ");
			String name = sc.nextLine();
			
			st = conn.prepareStatement("INSERT INTO teste (name) VALUES (?)");
			
			st.setString(1, name);
			
			int rowsAffected = st.executeUpdate();
			conn.commit();
			System.out.println("Done! Rows affected: " + rowsAffected);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
			sc.close();
		}
		
	}

}
