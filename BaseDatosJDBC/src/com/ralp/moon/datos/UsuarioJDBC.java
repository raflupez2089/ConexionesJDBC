package com.ralp.moon.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ralp.moon.dominio.Usuario;
import com.ralp.moon.jdbc.ConexionJDBC;
import com.ralp.moon.jdbc.JdbcDriverManager;

public class UsuarioJDBC {
	private Connection userCon = null;
	protected final String JDBC_INSERT = "Insert into Usuario(usuario, password) values(?, ?)";
	protected final String JDBC_UPDATE = "Update Usuario set usuario = ?, password = ? where id_usuario = ?";
	protected final String JDBC_DELETE = "Delete from Usuario where idUsuario = ?";
	protected final String JDBC_SELECT = "Select * from Usuario";
	
	public UsuarioJDBC(Connection conn){
		this.userCon = conn;
	}
	
	public int insertar(String usuario, String pass) throws SQLException{
		Connection conn = null;
		PreparedStatement pstm = null;
		int rowLines = 0;
		try {
			int index = 1;
			/*Para aplicar el concepto de transacciones, es decir, hacer commits y rollbacks se debe usar
			la misma conexion activa de principio a fin*/
			conn = (this.userCon != null)? this.userCon : JdbcDriverManager.getConnection();
			pstm = conn.prepareStatement(JDBC_INSERT);
			pstm.setString(index++, usuario);
			pstm.setString(index++, pass);
			System.out.println("Ejecutando el query: "+JDBC_INSERT);
			rowLines = pstm.executeUpdate();
			System.out.println("Registro insertado exitosamente");
		/*Se omite el bloque catch para que en caso de exception, se propage la exception hacia el metodo
			desde donde se invoca este metodo, para eso en vez del catch se define el metodo como throws*/
		}finally{
			JdbcDriverManager.closeStatement(pstm);	
			/* si la conexion global es null se va a grabar la conexión del metodo, de lo contrario vamos
			 * a mantenerla activa hasta que la instancia creada de la clase UsuarioJDBC exista
			 * */
			if(this.userCon==null)
				JdbcDriverManager.closeConnection(conn);
		}
		return rowLines;
	}
	
	public int update(int idUsuario, String usuario, String pass) throws SQLException{
		Connection conn = null;
		PreparedStatement pstm = null;
		int rowLines = 0;
		try{
			int index = 1;
			conn = (this.userCon != null) ? this.userCon : JdbcDriverManager.getConnection();
			pstm = conn.prepareStatement(JDBC_UPDATE);
			pstm.setString(index++, usuario);
			pstm.setString(index++, pass);
			pstm.setInt(index, idUsuario);
			System.out.println("Ejecutando el query: "+JDBC_UPDATE);
			rowLines = pstm.executeUpdate();
			System.out.println("Ejecutado exitosamente");
		}finally{
			JdbcDriverManager.closePreparedStatement(pstm);
			//Solo se cierra la conexión si es null, de lo contrario vamos a dejarla activa para el manejo de transacciones
			if(conn==null)
				JdbcDriverManager.closeConnection(conn);
		}
		return rowLines;
	}
	
	public int delete(int idUsuario) throws SQLException{
		Connection conn = null;
		PreparedStatement pstm = null;
		int rowLines = 0;
		try{
			conn = (this.userCon != null) ? this.userCon : JdbcDriverManager.getConnection();
			pstm = conn.prepareStatement(JDBC_DELETE);
			pstm.setInt(1, idUsuario);
			System.out.println("Ejecutando el delete");
			rowLines = pstm.executeUpdate();
			System.out.println("Ejecutado exitosamente");
		}finally{
			JdbcDriverManager.closePreparedStatement(pstm);
			if(this.userCon == null)
				JdbcDriverManager.closeConnection(conn);
		}
		return rowLines;
	}
	
	public List<Usuario> select() throws SQLException{
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		try{
			conn = (this.userCon != null) ? this.userCon : JdbcDriverManager.getConnection();
			pstm = conn.prepareStatement(JDBC_SELECT);
			rs = pstm.executeQuery();
			while(rs.next()){
				usuario = new Usuario();
				usuario.setIdUsario(rs.getInt("idUsuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setUsuario(rs.getString("password"));
				listaUsuarios.add(usuario);
			}
		}finally{
			JdbcDriverManager.closePreparedStatement(pstm);
			if(this.userCon == null)
				JdbcDriverManager.closeConnection(conn);
		}
		return listaUsuarios;
	}
	
}
