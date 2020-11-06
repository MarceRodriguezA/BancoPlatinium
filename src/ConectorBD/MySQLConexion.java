package ConectorBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost/login";
			String usuario = "iplacex_ci";
			String contraseña = "doremi87";
			
			conn = DriverManager.getConnection(url, usuario, contraseña);
			
		} catch (SQLException e) {
			System.out.println("Error al cargar la Base de Datos");
			e.printStackTrace();
		}	
		
		return null;
	}
}
