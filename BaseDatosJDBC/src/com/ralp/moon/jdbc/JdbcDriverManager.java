package com.ralp.moon.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Usando la forma de conexión DriverManager, se usa para aplicaciones Java SE
 * */

public class JdbcDriverManager {
	protected static String JDBC_CLASS = "com.mysql.jdbc.Driver";
	protected static String JDBC_URL = "jdbc:mysql://localhost:3306/moonsys?useSSL=false";
	protected static String JDBC_USER = "root";
	protected static String JDBC_PASS = "root";
	protected static Driver driver = null;
	
	//Se utiliza el synchronized para que la conexion la pueda usar solo un hilo a la vez
	public static synchronized Connection getMySqlConnection() throws SQLException{	
		if(driver == null){
			try{
				//Se crea una instancia de la clase Class
				Class jdbcDriverClass = Class.forName(JDBC_CLASS);
				//Se crea una instancia de la clase driver
				driver = (Driver) jdbcDriverClass.newInstance();
				//Se carga en memoria
				DriverManager.registerDriver(driver);
			}catch(Exception e){
				System.out.println("Error cargando el driver en memoria");
				e.printStackTrace();
			}
		}
		/* Crear la conexion usando el DriverManager */
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
	
	public static void closeResulSet(ResultSet rs){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void closeStatement(Statement stmt){
		if(stmt!=null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void closePreparedStatement(PreparedStatement pstm){
		if(pstm!=null)
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void closeConnection(Connection conn){
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*
	 * En el concepto de transacciones, para JDBC hay una propiedad que se llama autocomit. 
	 * Dicha propiedad se recomienda poner en false ya que por defecto viene en true
	 * Si se cierra la conexion, hace commit automatico aun cuando el autocomit esté en false
	 * controlarlo en un try - catch para que en caso de errores se haga rollback
	 * */
}
