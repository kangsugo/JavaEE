package gz.itcast.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 把xml文件的通用的操作方法抽取出来
 * @author APPle
 *
 */
public class XMLUtil {

	/**
	 * 读取xml文件，返回document对象
	 */
	public static Document getDocument(){
		try {
			Document doc = new SAXReader().read(new File("e:/product.xml"));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			//把转换为运行时异常抛出即可！
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 传如docuemnt对象，写出到xml文件中
	 */
	public static void write2xml(Document doc){
		try {
			OutputStream out = new FileOutputStream("e:/product.xml");
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out,format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			//把转换为运行时异常抛出即可！
			throw new RuntimeException(e);
		}
	}
}
