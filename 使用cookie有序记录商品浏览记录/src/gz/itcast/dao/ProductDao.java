package gz.itcast.dao;

import gz.itcast.entity.Product;
import gz.itcast.util.XMLUtil;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;


public class ProductDao {

	/**
	 * 查看所有商品
	 * @return
	 */
	public List<Product> findAll(){
		//1.读取xml文件
		Document doc = XMLUtil.getDocument();
		//2.读取所有product标签
		List<Element> proList = doc.getRootElement().elements("product");
		List<Product> list = new ArrayList<Product>();
		
		//3.遍历标签列表中的每一个标签对，将其转换为product对象，然后放入product对象列表中
		for(Element proElem : proList){
			//3.1创建Product对象
			Product p = new Product();
			
			//3.2把product标签内容封装到Product对象中
			p.setId(proElem.attributeValue("id"));
			p.setName(proElem.elementText("name"));
			p.setType(proElem.elementText("type"));
			p.setPrice(proElem.elementText("price"));
			
			//3.3封装好的对象放入List
			list.add(p);
		}
		
		
		return list;
	}
	
	/**
	 * 根据编号查询对应商品
	 */
	public Product findById(String id){
		//1读取xml
		Document doc = XMLUtil.getDocument();
		
		//2.查询制定ID的prouduct标签
		Element proElem = (Element) doc.selectSingleNode("//product[@id='"+id+"']");
		
		
		//使用找到的便签封装product对象
		 Product p = null;
		 if(proElem != null){
			 p = new Product();
			 p.setId(proElem.attributeValue("id"));//获取属性值
			 p.setName(proElem.elementText("name"));//获取文本值
			 p.setType(proElem.elementText("type"));
			 p.setPrice(proElem.elementText("price"));
		 }

		 return p;
	}
	
	public static void main(String[] args){
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		for(Product product : list){
			System.out.println(product);
		}
	}
	
}
