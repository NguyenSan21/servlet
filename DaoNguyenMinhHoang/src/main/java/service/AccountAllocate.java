package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountAllocate {
	public int getUser(String name, String pass) throws ClassNotFoundException {
		
		int result = 0;
		// Tạo try-cach để throw exception
		try {
			String connectionUrl = "jdbc:sqlserver://localhost:1433;" 
					+ "databaseName=java;" 
					+ "encrypt=false;"
					+ "username=sa;" 
					+ "password=123456;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Kết nối thành công!");
        
            //Tạo string chứa câu lệnh query từ sql
			String SQL = "SELECT COUNT(*) AS CNT FROM dbo.LOGIN WHERE UserID = ? AND UserPassword = ?";
			//Đưa vào PreparedStatement có kết nối connection với SQL
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setString(1, name);
			stmt.setString(2, pass);
			//Thực thi câu lệnh
			ResultSet rs = stmt.executeQuery();
			// Hiển thị câu lệnh nếu có trong database
			while (rs.next()) {
				result = rs.getInt("CNT");
			}

            // Đóng kết nối khi không còn sử dụng
            connection.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
