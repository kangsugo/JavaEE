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
		//1)读取商品列表信息
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		
		
		//2)把获取的内容显示到浏览器
		String html = "";
		html += "<html>";
		html += "<head><title>查看商品列表</title></head>";
		html +="<body>";
		html +="<table border='1' align='center' width='600px'>";
		html +="<tr><th>编号</th><th>商品名称</th><th>商品型号</th><th>价格</th><th>详情</th></tr>";
		
		//2.1遍历所有的商品，有几个商品就有几行数据
		if(list != null)
		for(Product product : list){
			html +="<tr>";
			html +="<td>"+product.getId()+"</td>";
			html +="<td>"+product.getName()+"</td>";
			html +="<td>"+product.getType()+"</td>";
			html +="<td>"+product.getPrice()+"</td>";
			html +="<td><a href='"+request.getContextPath()+"/DetailProdServlet?id="+product.getId()+"'>查看</a></td>";
			html +="</tr>";
		}
		
		
		
		html += "</table>";
		
		//3)显示浏览过的商品
		html +="<hr/>";
		html +="<br/>";
		
		
		//3.1)从cookie取出prodHist
				Cookie[] cookies = request.getCookies();
				if(cookies!=null){
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("prodHist")){
							String prodHist = cookie.getValue(); // 包含商品编号 。 3,2,1
							String[] ids = prodHist.split(",");
							for (String id : ids) {//取出每个商品编号
								//使用商品编号查询对应的商品
								Product p = dao.findById(id);
								//显示商品信息
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
