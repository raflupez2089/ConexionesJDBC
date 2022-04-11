package com.ralp.moon.transacciones;

import java.sql.Connection;
import java.sql.SQLException;

import com.ralp.moon.datos.UsuarioJDBC;
import com.ralp.moon.jdbc.JdbcDriverManager;

public class ManejoUsuarios {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// Creamos la conexión
			conn = JdbcDriverManager.getConnection();
			//Validamos que la conexion no tenga activo el autocomit, y si la tiene la inactivamos
			if(conn.getAutoCommit()==true)
				conn.setAutoCommit(false);
			//Creamos una instancia de la clase UsuarioJDBC con la conexion para manejar las transacciones
			UsuarioJDBC usuarioJdbc = new UsuarioJDBC(conn);
			//Una transaccion es el conjunto de sentencias sql que se ejecutan para completar lo esperado en la DB
			usuarioJdbc.update(22, "Sixta", "12id2d*");
			usuarioJdbc.insertar("Beaty",
					//"2dg3kasdjfñakjsd23jasdfljkhasd13lhljkahsdlfkjh1ljkh3ql2jhlajksehlkj3hsf3lkjhasdf323fafasdf*");
					"2dg3*");
			conn.commit();
			System.out.println("Se he hecho commit de los cambios en base de datos");
		} catch (SQLException e) {
			System.out.println("Entrando al rollback");
			e.printStackTrace();
			try{
				conn.rollback();
				System.out.print("Se hizo rollback");
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		

	}

}
