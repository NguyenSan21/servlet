package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection connectDB() {	

		// Tạo String chứa thông tin port + tên database + encrypt + user + pass
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" 
								+ "databaseName=java;" 
								+ "encrypt=false;"
								+ "username=sa;" 
								+ "password=123456;";
		// Tạo String chứa driver
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		// Bât connecttion có tên connect
		Connection connect = null;

		// Tạo try-catch để throw exception
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException e1) {
			System.out.println("Kết nối thất bại, hãy kiểm tra lại Driver");
		} catch (SQLException e) {
			System.out.println("Kết nối thất bại, hãy kiểm tra cấu hình kết nối.");
		}

		return connect;
	}
	
}
