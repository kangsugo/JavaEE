package gz.itcast.servlet;

import gz.itcast.dao.ProductDao;
import gz.itcast.entity.Product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//1)��ȡ��Ʒ�б���Ϣ
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		
		
		//2)�ѻ�ȡ��������ʾ�������
		String html = "";
		html += "<html>";
		html += "<head><title>�鿴��Ʒ�б�</title></head>";
		html +="<body>";
		html +="<table border='1' align='center' width='600px'>";
		html +="<tr><th>���</th><th>��Ʒ����</th><th>��Ʒ�ͺ�</th><th>�۸�</th><th>����</th></tr>";
		
		//2.1�������е���Ʒ���м�����Ʒ���м�������
		if(list != null)
		for(Product product : list){
			html +="<tr>";
			html +="<td>"+product.getId()+"</td>";
			html +="<td>"+product.getName()+"</td>";
			html +="<td>"+product.getType()+"</td>";
			html +="<td>"+product.getPrice()+"</td>";
			html +="<td><a href='"+request.getContextPath()+"/DetailProdServlet?id="+product.getId()+"'>�鿴</a></td>";
			html +="</tr>";
		}
		
		
		
		html += "</table>";
		
		//3)��ʾ���������Ʒ
		html +="<hr/>";
		html +="<br/>";
		
		
		//3.1)��cookieȡ��prodHist
				Cookie[] cookies = request.getCookies();
				if(cookies!=null){
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("prodHist")){
							String prodHist = cookie.getValue(); // ������Ʒ��� �� 3,2,1
							String[] ids = prodHist.split(",");
							for (String id : ids) {//ȡ��ÿ����Ʒ���
								//ʹ����Ʒ��Ų�ѯ��Ӧ����Ʒ
								Product p = dao.findById(id);
								//��ʾ��Ʒ��Ϣ
								html += ""+p.getId()+"&nbsp;"+p.getName()+"&nbsp;"+p.getPrice()+"<br/>";
							}
							break;
						}
					}
				}

				html += "</body>";
				html += "</html>";
				
				response.getWriter().write(html);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
		
	}

}
