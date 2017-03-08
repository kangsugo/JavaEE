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
		//1.����������
		SAXReader reader = new SAXReader();
			
		//2.����xml�ļ�
		InputStream is = ConfigManager.class.getResourceAsStream("/struts.xml");
		
		try {
			Document doc;
			doc = reader.read(is);
			//3.����xpath���ʽ
			String xpath = "//action";
			
			//4.����xpath���ʽ����Ԫ��action
			List<Element> actions = doc.selectNodes(xpath);
			
			//5.����actionԪ�ز���װ��ActionConfig����
			//<struts>
			//<action name="HelloAction"  class="" method=" ">
			//	<result name=""></result>
			//	<result name=""></result>
			//	<result name=""></result>
			//</action>
			//</struts>
			for(Element action : actions){
				ActionConfig ac = new ActionConfig();
				//1��װname
				ac.setName(action.attributeValue("name"));
				//2��װclass
				ac.setClazz(action.attributeValue("class"));
				//3��װmethod
				ac.setMethod(action.attributeValue("method"));
				
				
				//------------��װresult��Ԫ��--------------
				Map<String, ResultConfig> results = new HashMap<String, ResultConfig>();
				
				//���actionԪ��������result��Ԫ��
				List<Element> rs = action.elements("result");
				//���� ����resultconfig���� ��װ��map
				for(Element e : rs){
					ResultConfig rc = new ResultConfig();
					//1.��װname
					rc.setName(e.attributeValue("name"));
					//2.��װclass
					rc.setTarget(e.getText());
					
					
					results.put(rc.getName(),rc);
				}
				ac.setResults(results);
				
				//����װ�õ�actionconfig����map
				map.put(ac.getName(),ac);
			}
			return map;
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("���������ļ�ʧ�ܣ�");
		}

	}
}
