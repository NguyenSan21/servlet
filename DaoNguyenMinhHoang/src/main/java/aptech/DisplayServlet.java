package aptech;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDBC;

@WebServlet(urlPatterns = "/Display")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Tạo 1 list
		List<BookAttribute> BookList = new ArrayList<>();
		try{
			// Tạo câu lệnh sql
			String sql = "SELECT * FROM dbo.TBL_Book";
			// Đưa vào preparestatement có kết nối JDBC
			PreparedStatement stmt = JDBC.connectDB().prepareStatement(sql);
			// Thực thi câu lệnh và gán vào ResultSet
			ResultSet rs = stmt.executeQuery(); 
			// Tạo vòng lặp để hiển thị ra các thông tin
			while (rs.next()) {
				// Gọi đối tượng từ class BookAttribute
				BookAttribute obj = new BookAttribute();
				// Lấy các thông tin từ DB rồi set vào các thuộc tính của đối tượng
				obj.setBookId(rs.getString("BookId"));
				obj.setBookTitle(rs.getString("BookTitle"));
				obj.setBookAuthor(rs.getString("BookAuthor"));
				obj.setBookPrice(rs.getString("BookPrice"));
				// Add vào trong list
				BookList.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//request thuộc tính cho List <BookList> và gán tên là "BookList"
		request.setAttribute("BookList", BookList);
		//Forward đến trang display
		request.getRequestDispatcher("Display.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
