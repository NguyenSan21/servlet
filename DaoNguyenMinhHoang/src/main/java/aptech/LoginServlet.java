package aptech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AccountAllocate;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/Login", asyncSupported = true)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountAllocate userService = new AccountAllocate();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Tạo string rồi getParameter
		String username = request.getParameter("user");
		String password = request.getParameter("password");	
		String remember = request.getParameter("remember");
		
		//Lấy đối tượng ServletContext để tương tác với môi trường ứng dụng
		//ServletContext SrvletContext = getServletContext();
		
		//Dùng RequestDispatcher để chuyển tiếp yêu cầu đến tài nguyên khác 
		//RequestDispatcher dispatcher = SrvletContext.getRequestDispatcher("/Home.jsp");
		//forward đến HttpServletRequest và HttpServletResponse
		//dispatcher.forward(request, response);

		//Tạo if-else để check user và mật khẩu
		try {
			if (userService.getUser(username, password) > 0) {
				//Tạo session
				HttpSession session = request.getSession(false);
				//Đặt thuộc tính  session cho user
				session.setAttribute("user", username);
				//Set thời gian
				session.setMaxInactiveInterval(3600);
				
				// Tạo cookie
				if (remember!=null) {
					// Tạo cookie chứa thông tin  user
					Cookie UserCookie = new Cookie("user", username);
					// Tạo cookie chứa thông tin password
					Cookie PasswordCookie = new Cookie("password", password);
					// Set thời hạn lưu cookie
					UserCookie.setMaxAge(600);
					PasswordCookie.setMaxAge(600);
					// Add cookie của user
					response.addCookie(UserCookie);	
					response.addCookie(PasswordCookie);			

				}
				//đưa response vào index.jsp
				response.sendRedirect("Index.jsp");
				
				
			}else {
				//Tạo thông báo nếu bị sai
				request.setAttribute("msg", "Đăng nhập sai");
				//forward request, response đến Login.jsp
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
