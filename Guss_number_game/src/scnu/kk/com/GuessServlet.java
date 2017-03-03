package scnu.kk.com;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuessServlet extends HttpServlet {

	private int answer;
		
	//Servletֻ���ʼ��һ�Σ����ɵ����������һֱʹ��
	public GuessServlet() {
		super();
		genAnswer();
		System.out.println("���캯����ʼ");
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
					message = "��ϧС�˵㣡";
				}else if(lucy>answer){
					message = "��ϧ���˵㣡";
				}
				else{
					message = "�¶��ˣ�";
					genAnswer();
					times = -1;
				}
			}else{
				message = "������5�Σ�������������";
				genAnswer();
				times = -1;
			}
			
			
			request.setAttribute("timesStr", ",�㻹��"+(4-times)+"�λ���");
			request.setAttribute("times", times);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/guess.jsp").forward(request, response);
		}
		
			
		
	}


}
