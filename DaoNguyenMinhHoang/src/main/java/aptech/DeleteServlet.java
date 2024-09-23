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
 * Servlet implementation class DeleteServlet
 */
@WebServlet(urlPatterns = "/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
        try {
            // Câu lệnh SQL để xóa
            String sql = "DELETE FROM dbo.TBL_Book WHERE BookId = ?";
            PreparedStatement stmt = JDBC.connectDB().prepareStatement(sql);
            stmt.setInt(1, ID);

            // Thực thi câu lệnh xóa
            stmt.executeUpdate();

            // Chuyển hướng về trang Display sau khi xóa
            response.sendRedirect("Display");
        } catch (SQLException e) {
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
