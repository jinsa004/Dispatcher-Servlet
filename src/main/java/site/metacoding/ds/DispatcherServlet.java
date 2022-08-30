package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod();// 요청된 메소드가 어떤 것인지 출력해줌
//		System.out.println(httpMethod);
		String identifier = req.getRequestURI();// 로컬호스트 기본주소 이후의 주소를 출력해줌
//		System.out.println(identifier);

		UserController c = new UserController();
		
		Method[] methodList = c.getClass().getDeclaredMethods();
		for (Method method : methodList) { //for each 배열과 함께 쓰는 for문 배열의 크기에 맞춰서 for 횟수가 정해짐.
//			System.out.println(method.getName());
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			
			try {
//				System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c);
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없어용.");
			}
		}
	}
}
