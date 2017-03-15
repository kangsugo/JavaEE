package cn.itcast.b_constant;

import java.util.Arrays;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class Demo1Action {
	public String execute(){
		//获得参数，并在控制台输出
		
		//String name = ServletActionContext.getRequest().getParameter("name");
		String[] names = (String[]) ActionContext .getContext().getParameters().get("name");
		System.out.println(Arrays.toString(names));
		return "success";
	}
}
