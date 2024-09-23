package aptech;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDBC;

@WebServlet(urlPatterns = "/Update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameter của BookId
		String BookId = (String) request.getParameter("BookId");
		//Chuyển đổi từ String sang int
		int ID = Integer.parseInt(BookId);
    	
		// Tạo thuộc tính list
		ArrayList<String> Booklist = new ArrayList<>();
        
        try {
            // Câu lệnh sql
            String sql = "SELECT * FROM dbo.TBL_Book WHERE BookId = ?";
			PreparedStatement stmt = JDBC.connectDB().prepareStatement(sql);
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
				// Tạo 1 list trong vòng lặp
				
				// Add tất cả thông tin vào
            	Booklist.add(rs.getString("BookId"));
            	Booklist.add(rs.getString("BookTitle"));
            	Booklist.add(rs.getString("BookAuthor"));
            	Booklist.add(rs.getString("BookPrice"));
			} else {
				//Quay về lại display
                response.sendRedirect("Display");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
		//request thuộc tính cho List và gán tên là "Booklist"
		request.setAttribute("UpdateBooklist", Booklist);
        // Đưa sang Update.jsp
        request.getRequestDispatcher("Update.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
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
    	        
        try {
            // Câu lệnh sql
            String sql = "UPDATE dbo.TBL_Book SET BookTitle = ?, BookAuthor = ?, BookPrice = ? WHERE BookId = ?";
			//Đưa vào PreparedStatement rồi gán các giá trị vào
            PreparedStatement stmt = JDBC.connectDB().prepareStatement(sql);
            stmt.setString(1, BookTilte);
            stmt.setString(2, BookAuthor);
            stmt.setInt(3, Price);
            stmt.setInt(4, ID);
            //Thực hiện query và gán vào biến int có tên rowAffect
            int rowAffect = stmt.executeUpdate();
            //Tạo if-else check điều kiện
            if (rowAffect > 0) {
				//Đưa thông tin sang DisplayServlet (theo url)
				request.getRequestDispatcher("Display").forward(request, response);
			} else {
                //Forward về lại trang Update
		        request.getRequestDispatcher("Update.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
   	
    	
    	
    }
}
