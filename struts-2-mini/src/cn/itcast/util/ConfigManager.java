package cn.itcast.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.itcast.bean.ActionConfig;
import cn.itcast.bean.ResultConfig;

public class ConfigManager {
	public static Map<String,ActionConfig> getActionConfig(){
		Map<String, ActionConfig> map = new HashMap<String, ActionConfig>();
		//1.创建解析器
		SAXReader reader = new SAXReader();
			
		//2.加载xml文件
		InputStream is = ConfigManager.class.getResourceAsStream("/struts.xml");
		
		try {
			Document doc;
			doc = reader.read(is);
			//3.定义xpath表达式
			String xpath = "//action";
			
			//4.根据xpath表达式查找元素action
			List<Element> actions = doc.selectNodes(xpath);
			
			//5.遍历action元素并封装成ActionConfig对象
			//<struts>
			//<action name="HelloAction"  class="" method=" ">
			//	<result name=""></result>
			//	<result name=""></result>
			//	<result name=""></result>
			//</action>
			//</struts>
			for(Element action : actions){
				ActionConfig ac = new ActionConfig();
				//1封装name
				ac.setName(action.attributeValue("name"));
				//2封装class
				ac.setClazz(action.attributeValue("class"));
				//3封装method
				ac.setMethod(action.attributeValue("method"));
				
				
				//------------封装result子元素--------------
				Map<String, ResultConfig> results = new HashMap<String, ResultConfig>();
				
				//获得action元素下所有result子元素
				List<Element> rs = action.elements("result");
				//遍历 创建resultconfig对象 封装到map
				for(Element e : rs){
					ResultConfig rc = new ResultConfig();
					//1.封装name
					rc.setName(e.attributeValue("name"));
					//2.封装class
					rc.setTarget(e.getText());
					
					
					results.put(rc.getName(),rc);
				}
				ac.setResults(results);
				
				//将封装好的actionconfig放入map
				map.put(ac.getName(),ac);
			}
			return map;
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("加载配置文件失败！");
		}

	}
}
