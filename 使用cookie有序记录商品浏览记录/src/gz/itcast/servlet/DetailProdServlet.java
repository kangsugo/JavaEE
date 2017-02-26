package gz.itcast.servlet;

import gz.itcast.dao.ProductDao;
import gz.itcast.entity.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailProdServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)接收id的参数
				String id = request.getParameter("id");
				//2)查询指定id的商品对象
				ProductDao dao = new ProductDao();
				Product product = dao.findById(id);
				
				//3)显示到浏览器上
				response.setContentType("text/html;charset=utf-8");
				String html = "";
				
				html += "<html>";
				html += "<head><title>查看商品详情</title></head>";
				html += "<body>";
				html += "<table border='1' align='center' width='250px'>";
				html += "<tr><th>编号</th><td>"+product.getId()+"</td></tr>";
				html += "<tr><th>商品名称</th><td>"+product.getName()+"</td></tr>";
				html += "<tr><th>商品型号</th><td>"+product.getType()+"</td></tr>";
				html += "<tr><th>价格</th><td>"+product.getPrice()+"</td></tr>";
				html += "</table>";
				html += "<center><a href='"+request.getContextPath()+"/ListProdServlet'>[返回商品列表]</a></center>";
				html += "</body>";
				html += "</html>";
				
				response.getWriter().write(html);

				//创建cookie对象
				Cookie cookie = new Cookie("prodHist",getCookieValue(request,product.getId()));
				response.addCookie(cookie);
	}

	private String getCookieValue(HttpServletRequest request,String id) {
		Cookie[] cookies = request.getCookies();
		String prodHist = null;
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prodHist")){
					prodHist = cookie.getValue();
					break;
				}
			}
		}
		if(cookies==null || prodHist==null){
			// 1)直接返回传入的id 
			return id;
		}
		
		//3,10   1
		// 满足两个条件：１）方便判断元素是否重复  2）方便增删元素内容        使用集合： Collection   coll.contains(id): 判断指定元素是否存在   
														//     List: ArrayList LinkedList(链表)
		// String-> String[]
		String[] prodHists = prodHist.split(",");
		// String->Collection
		Collection colls = Arrays.asList(prodHists);
		// Collectoin->LinkedList
		LinkedList list = new LinkedList(colls);
															
		if(list.size()<3){
			//有重复的
			if(list.contains(id)){
				// 删除重复的id，把传入的id放前面
				list.remove(id);
				list.addFirst(id);
			}else{
				//直接把传入的id放前面
				list.addFirst(id);
			}
		}else{
			//有重复的
			if(list.contains(id)){
				// 删除重复的id，把传入的id放前面
				list.remove(id);
				list.addFirst(id);
			}else{
				//  删除结尾的id，把传入的id放前面                     
				list.removeLast();
				list.addFirst(id);
			}
		}				
		
		//List->String
		String str = "";
		for (Object obj : list) {
			str += obj+",";
		}
		//去掉最后的逗号
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
