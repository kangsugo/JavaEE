package gz.itcast.dao;

import gz.itcast.entity.Product;
import gz.itcast.util.XMLUtil;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;


public class ProductDao {

	/**
	 * �鿴������Ʒ
	 * @return
	 */
	public List<Product> findAll(){
		//1.��ȡxml�ļ�
		Document doc = XMLUtil.getDocument();
		//2.��ȡ����product��ǩ
		List<Element> proList = doc.getRootElement().elements("product");
		List<Product> list = new ArrayList<Product>();
		
		//3.������ǩ�б��е�ÿһ����ǩ�ԣ�����ת��Ϊproduct����Ȼ�����product�����б���
		for(Element proElem : proList){
			//3.1����Product����
			Product p = new Product();
			
			//3.2��product��ǩ���ݷ�װ��Product������
			p.setId(proElem.attributeValue("id"));
			p.setName(proElem.elementText("name"));
			p.setType(proElem.elementText("type"));
			p.setPrice(proElem.elementText("price"));
			
			//3.3��װ�õĶ������List
			list.add(p);
		}
		
		
		return list;
	}
	
	/**
	 * ���ݱ�Ų�ѯ��Ӧ��Ʒ
	 */
	public Product findById(String id){
		//1��ȡxml
		Document doc = XMLUtil.getDocument();
		
		//2.��ѯ�ƶ�ID��prouduct��ǩ
		Element proElem = (Element) doc.selectSingleNode("//product[@id='"+id+"']");
		
		
		//ʹ���ҵ��ı�ǩ��װproduct����
		 Product p = null;
		 if(proElem != null){
			 p = new Product();
			 p.setId(proElem.attributeValue("id"));//��ȡ����ֵ
			 p.setName(proElem.elementText("name"));//��ȡ�ı�ֵ
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
