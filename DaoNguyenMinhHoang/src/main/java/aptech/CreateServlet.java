package aptech;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDBC;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet(urlPatterns = "/Create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameter của BookId
		String BookId = (String) request.getParameter("BookId");
		//Chuyển đổi từ String sang int
		int ID = Integer.parseInt(BookId);
		//Get parameter của BookTitle
		String BookTilte = (String) request.getParameter("BookTitle");
		//Get parameter của BookAuthor
		String BookAuthor = (String) request.getParameter("BookAuthor");
		//Get parameter của BookPrice
		String BookPrice = (String) request.getParameter("BookPrice");
		//Chuyển đổi từ String sang int
		int Price = Integer.parseInt(BookPrice);
		
		//Tạo câu lệnh SQL
		String sql = "INSERT INTO dbo.TBL_Book (BookId, BookTitle, BookAuthor, BookPrice) VALUES (?,?,?, ?)";
		try {
			// Đưa vào prepareStatement có kết nối API
			PreparedStatement pre = JDBC.connectDB().prepareStatement(sql);
			// Gán các thông tin từ các biến đã thực hiện getParameter
			pre.setInt(1, ID);
			pre.setString(2, BookTilte);
			pre.setString(3, BookAuthor);
			pre.setInt(4, Price);
			
			// Thực thi câu lệnh và gán biến vào câu lệnh
			int rowAffect = pre.executeUpdate();
			
			//Tạo if-else để check xem đã thực thi thành công chưa
			if (rowAffect > 0) {
				//Đưa thông tin sang DisplayServlet (theo url)
				request.getRequestDispatcher("Display").forward(request, response);
			}else {
				//Set thuộc tính có tên "msg" và gán 1 message
                request.setAttribute("msg", "Đăng nhập sai thông tin");
                //Forward về lại trang Create
                request.getRequestDispatcher("Create.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			System.out.println("ID and Price must be number, not string.");
			//Set thuộc tính có tên "msg" và gán 1 message
            request.setAttribute("msg", "Đăng nhập sai thông tin");

			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
