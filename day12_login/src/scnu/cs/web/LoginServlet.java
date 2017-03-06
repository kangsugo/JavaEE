package scnu.cs.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String  name = request.getParameter("name");
		String  password = request.getParameter("password");
		
		//��½�ɹ������û����ݱ��浽session�������
		if(name.equals("people4") && password.equals("123456")){
			HttpSession session = request.getSession(true);
			session.setAttribute("user",name);
			System.out.println("��¼��ȷ");
			response.sendRedirect(request.getContextPath()+"/ListUserServlet");
		}else{
			//��½ʧ�ܡ����û����ݱ��浽request�������
			System.out.println("��¼ʧ��");
			request.setAttribute("msg","�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}

}
