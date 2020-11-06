package servlets;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dao.LoginDao;
import bean.PersonaBean;
import dao.PersonaDao;

@WebServlet("/login")
public class LoginServlets  extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
    
	private LoginDao loginDao;
	private PersonaDao personaDao;

    public void init() {
        loginDao = new LoginDao();
        personaDao = new PersonaDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
            	System.out.println("LOGIN CORRECTO");
                //HttpSession session = request.getSession();
                // session.setAttribute("");
                response.sendRedirect(request.getContextPath() + "Inicio.jsp");
            } else {
            	System.out.println("LOGIN INCORRECTO");

                HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                //response.sendRedirect("login.jsp");
                response.sendRedirect(request.getContextPath() + "NoAuth	.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
