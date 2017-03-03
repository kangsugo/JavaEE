package scnu.kk.com;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuessServlet extends HttpServlet {

	private int answer;
		
	//Servlet只会初始化一次，生成的随机数可以一直使用
	public GuessServlet() {
		super();
		genAnswer();
		System.out.println("构造函数开始");
	}


	public void genAnswer(){
		Random random = new Random();
		answer = random.nextInt(30);
		System.out.println(answer+"  Answer");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String lucyNo = request.getParameter("lucyNo");
		System.out.println(lucyNo+"  Input");
		System.out.println(answer+"  Answer");
		String message = "";
		int times= 0;
		
		
		String timeStr = request.getParameter("times");
		if(timeStr!=null && !timeStr.equals("")){
			times = Integer.parseInt(timeStr);
			times++;
		}
		
		if(lucyNo != null && lucyNo != ""){
			if(times < 4){
				int lucy = Integer.parseInt(lucyNo);
				System.out.println(lucy+"  lucy");
				if(lucy<answer){
					message = "可惜小了点！";
				}else if(lucy>answer){
					message = "可惜大了点！";
				}
				else{
					message = "猜对了！";
					genAnswer();
					times = -1;
				}
			}else{
				message = "超过了5次，请重新再来！";
				genAnswer();
				times = -1;
			}
			
			
			request.setAttribute("timesStr", ",你还有"+(4-times)+"次机会");
			request.setAttribute("times", times);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/guess.jsp").forward(request, response);
		}
		
			
		
	}


}
