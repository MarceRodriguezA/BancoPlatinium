package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;
import bean.PersonaBean;

public class PersonaDao {
	
	public boolean validate(PersonaBean personaBean) throws ClassNotFoundException {
        
		boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/Login?useSSL=false", "iplacex_ci", "doremi87");
            
            PreparedStatement preparedStatement = conn
            .prepareStatement("select * from login.Persona where username = ? and password = ? ")) {
            preparedStatement.setInt(1, personaBean.getRut());
            preparedStatement.setString(2, personaBean.getNombre());
            preparedStatement.setString(3, personaBean.getApellido());
            preparedStatement.setInt(4, personaBean.getTelefono());
            preparedStatement.setDate(5, personaBean.getFecha_ingreso());
            
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
           
            printSQLException(e);
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
