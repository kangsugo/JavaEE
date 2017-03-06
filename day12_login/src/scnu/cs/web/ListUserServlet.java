package scnu.cs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scnu.cs.entity.User;

public class ListUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//1.��ѯ�������û�
			List<User> list = new ArrayList<User>();
			for(int i = 1; i <= 10; i++)
			{
				list.add(new User(i,"people"+i,"123"));
			}
			//2.�����ݷ��������
			request.setAttribute("userList",list);
			//3.������ת����jspҳ��
			request.getRequestDispatcher("/userList.jsp").forward(request, response);
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
