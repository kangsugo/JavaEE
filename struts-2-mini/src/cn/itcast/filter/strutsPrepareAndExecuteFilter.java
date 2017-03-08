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
		//项目启动 加载一次配置文件
		actionConfigs = ConfigManager.getActionConfig();
		System.out.println("wewew"+actionConfigs);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//1.获得请求路径并处理
		String path = req.getServletPath();  //path = /hello.action
			//判断是否以.action结尾
		if(!path.endsWith(".action")){
			//不是以.action结尾 不处理 直接放行
			chain.doFilter(request, response);
			return ;
		}
		
		//否则分离出action名称
		int start = 1;
		int end = path.lastIndexOf(".");
		path = path.substring(start,end);
		System.out.println("111"+path);
		
		
		
		//2.根据请求路径，找到对应的Actionconfig配置
		ActionConfig ac = actionConfigs.get(path);
		System.out.println(ac);
		if(ac == null){
			throw new RuntimeException("没有得到action配置信息");
		}
		
		//3.获得class，并实例化对象
		String clazz = ac.getClazz();
		try {
			Class actionClass = Class.forName(clazz);
			//4.获得Method，并使用反射调用方法，返回字符串
			Method m = actionClass.getMethod(ac.getMethod());
			Object actionInstance =  actionClass.newInstance();
			String result = (String) m.invoke(actionInstance);
			
			
			//5.根据返回的字符串找到ResultConfig，找到路径Target
			String targetPath = ac.getResults().get(result).getTarget();
			
			//6.转发到Target
			request.getRequestDispatcher(targetPath).forward(request, response);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		
	}

	


}
