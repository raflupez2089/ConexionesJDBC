package com.ralp.moon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexionJDBC {
	//registrar el driver jdbc, por defecto se debe usar el protocolo ssl
	
	public static void main(String args[]){
		
		try{
			/* Cargar en memoria la clase del driver jdbc
			* Class.forName(String de conexion);  
			* Driver de conexion depende del motor de base de datos */
			String mysql = "com.mysql.jdbc.Driver";
			String oracle = "oracle.jdbc.driver.OracleDriver";
			String postgresql = "org.postgresql.Driver";
			String mariadb = "com.mysql.jdbc.Driver";
			Class.forName(mysql);
			String urlMysql = "jdbc:mysql://localhost:3306/moonsys?useSSL=false";
			String urlOracle = "jdbc:oracle:thin:@localhost:1521:XE";
			String urlPostgresql = "jdbc:postgresql://localhost:5432/dbName";
			String urlMariadb = "jdbc:mysql://localhost:3406/dbName";
			//Crear la conexión a la bd
			Connection conexion = (Connection) DriverManager.getConnection(urlMysql,"root","root");
			//creando objeto de 
			Statement stmt = conexion.createStatement();
			String query = "Select * from moonsys.usuario";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.print(rs.getInt("id_usuario")+" ");
				System.out.print(rs.getString("usuario")+" ");
				System.out.print(rs.getString("password")+"\n");
			}
			rs.close();
			stmt.close();
			conexion.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
