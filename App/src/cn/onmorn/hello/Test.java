package cn.onmorn.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Test extends HttpServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// 1. �����û�����������
		// --> ��ȡ�û���(username)������(password)
		// 2. ���û������ݽ��д���
		// --> �ж�username��password��ֵ�����ݿ��е�
		// �������
		// a) ע��mysql��jar������������
		// b) ��ȡmysql������
		// c) ʹ��Statement/PreparedStatement
		// ִ�в�ѯ��sql���
		// d) ʹ��ResultSet��ȡ��ѯ�����
		// ���ж�ReslutSet��
		// �ó��Ƿ��ܵ�¼�Ľ���
		// 3. ���ؽ������Ӧ��
		// --> ��ת����¼���棨��¼ʧ�ܣ���������ҳ��

		// 1. �����û�����������
		String username = req.getParameter("user");
		String password = req.getParameter("passwd");
		System.out.println(username + "  " + password);
		// 2. ���û������ݽ��д���
		// try {
		// // a) ע��mysql��jar������������
		// Class.forName("com.mysql.jdbc.Driver");
		// // b) ��ȡmysql������
		// // url�ĸ�ʽ��jdbc:mysql://��������ַ:�������˿�/���ݿ������
		// String url =
		// "jdbc:mysql://localhost:3306/tarena_empms";
		// String user = "root";
		// String pwd = "";
		// Connection conn = DriverManager.getConnection(url, user, pwd);
		// // c) ʹ��Statement/PreparedStatement
		// // ִ�в�ѯ��sql���
		// String sql = "select username, password from admin where username=?
		// and password=?";
		// PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setString(1, username);
		// pstmt.setString(2, password);
		// // d) ʹ��ResultSet��ȡ��ѯ�����
		// // ���ж�ReslutSet��
		// // �ó��Ƿ��ܵ�¼�Ľ���
		// ResultSet rs = pstmt.executeQuery();
		// boolean allowLogin = rs.next();
		// // 3. ���ؽ������Ӧ��
		// PrintWriter out = resp.getWriter();
		// if(allowLogin) {
		// out.println("��¼�ɹ�");
		// System.out.println("��¼�ɹ���");
		// } else {
		// out.println("<a href=\"login.html\">username or password err! please
		// try again!</a>");
		// }
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		resp.setContentType("text/html;charset=GB18030");
		PrintWriter out = resp.getWriter();
		try { 
		Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		if (username.equals("1") && password.equals("1")) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">");
			out.println("<title>�鿴Ա���б�</title>");
			out.println("<style type=\"text/css\">");
			out.println("* { font-size: 18px; color: #333; font-family: \"Microsoft YaHei UI\"; }");
			out.println("body, ul, ol, li, form { margin: 0; padding: 0; }");
			out.println("li { list-style: none; }");
			out.println("a { text-decoration: none; }");
			out.println("a:hover { text-decoration: underline; }");
			out.println(
					".nav_wrap { text-align: center; width: 100%; padding: 20px 0; border-bottom: 2px solid #333; }");
			out.println(".nav_wrap .nav { width: 1008px; margin: 0 auto; overflow: auto; }");
			out.println(".nav_wrap .nav li { float: left; }");
			out.println(
					".nav_wrap .nav li a { padding:0 30px; line-height: 36px; font-size: 24px; font-weight: bold; color: #888; }");
			out.println(".nav_wrap .nav li a.selected { color: #000; }");
			out.println(".nav_wrap .nav li a:hover { color: #000; text-decoration: none; }");
			out.println(".body_wrap { text-align: center; width: 100%; margin: 20px 0; padding: 20px 0; }");
			out.println(".body_wrap .body { width: 1008px; margin: 0 auto; text-align: left; }");
			out.println(".list_table { width: 100%; border-collapse: collapse; border: 1px solid #333; }");
			out.println(
					".list_table th { padding: 10px; border-collapse: collapse; border: 1px solid #333; text-align: center; }");
			out.println(
					".list_table td { padding: 10px; border-collapse: collapse; border: 1px solid #333; text-align: center; font-size: 16px; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("");
			out.println("</body>");
			out.println("<div class=\"nav_wrap\">");
			out.println("<ul class=\"nav\">");
			out.println("<li><a href=\"login.html\">��¼</a></li>");
			out.println("<li><a href=\"addnewEmployee.html\">����Ա����Ϣ</a></li>");
			out.println("<li><a href=\"employeeList.do\" class=\"selected\">�鿴Ա���б�</a></li>");
			out.println("<li><a href=\"logout.do\">�˳�</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("<div class=\"body_wrap\">");
			out.println("<div class=\"body\">");
			out.println("<table class=\"list_table\">");
			out.println("<tr>");
			out.println("<th>����</td>");
			out.println("<th>�Ա�</td>");
			out.println("<th>����</td>");
			out.println("<th>��н</td>");
			out.println("<th colspan=\"2\">����</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");
			out.println("</html>");
		} else {

			out.println("error");
		}
		// out.println("<a href=\"login.html\">username or password err! please
		// try again!</a>");
	}

}
