package cn.itcast.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bean.ActionConfig;
import cn.itcast.util.ConfigManager;

/**
 * Servlet Filter implementation class strutsPrepareAndExecuteFilter
 */
public class strutsPrepareAndExecuteFilter implements Filter {
	private Map<String, ActionConfig>  actionConfigs;
	
	public void init(FilterConfig fConfig) throws ServletException {
		//��Ŀ���� ����һ�������ļ�
		actionConfigs = ConfigManager.getActionConfig();
		System.out.println("wewew"+actionConfigs);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//1.�������·��������
		String path = req.getServletPath();  //path = /hello.action
			//�ж��Ƿ���.action��β
		if(!path.endsWith(".action")){
			//������.action��β ������ ֱ�ӷ���
			chain.doFilter(request, response);
			return ;
		}
		
		//��������action����
		int start = 1;
		int end = path.lastIndexOf(".");
		path = path.substring(start,end);
		System.out.println("111"+path);
		
		
		
		//2.��������·�����ҵ���Ӧ��Actionconfig����
		ActionConfig ac = actionConfigs.get(path);
		System.out.println(ac);
		if(ac == null){
			throw new RuntimeException("û�еõ�action������Ϣ");
		}
		
		//3.���class����ʵ��������
		String clazz = ac.getClazz();
		try {
			Class actionClass = Class.forName(clazz);
			//4.���Method����ʹ�÷�����÷����������ַ���
			Method m = actionClass.getMethod(ac.getMethod());
			Object actionInstance =  actionClass.newInstance();
			String result = (String) m.invoke(actionInstance);
			
			
			//5.���ݷ��ص��ַ����ҵ�ResultConfig���ҵ�·��Target
			String targetPath = ac.getResults().get(result).getTarget();
			
			//6.ת����Target
			request.getRequestDispatcher(targetPath).forward(request, response);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		
	}

	


}
