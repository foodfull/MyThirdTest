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
		// 1. 接收用户的请求数据
		// --> 获取用户名(username)和密码(password)
		// 2. 对用户的数据进行处理
		// --> 判断username和password的值在数据库中的
		// 检索结果
		// a) 注册mysql的jar包，加载驱动
		// b) 获取mysql的连接
		// c) 使用Statement/PreparedStatement
		// 执行查询的sql语句
		// d) 使用ResultSet获取查询结果，
		// 并判断ReslutSet，
		// 得出是否能登录的结论
		// 3. 返回结果（响应）
		// --> 跳转到登录界面（登录失败）或者其他页面

		// 1. 接收用户的请求数据
		String username = req.getParameter("user");
		String password = req.getParameter("passwd");
		System.out.println(username + "  " + password);
		// 2. 对用户的数据进行处理
		// try {
		// // a) 注册mysql的jar包，加载驱动
		// Class.forName("com.mysql.jdbc.Driver");
		// // b) 获取mysql的连接
		// // url的格式：jdbc:mysql://服务器地址:服务器端口/数据库库名称
		// String url =
		// "jdbc:mysql://localhost:3306/tarena_empms";
		// String user = "root";
		// String pwd = "";
		// Connection conn = DriverManager.getConnection(url, user, pwd);
		// // c) 使用Statement/PreparedStatement
		// // 执行查询的sql语句
		// String sql = "select username, password from admin where username=?
		// and password=?";
		// PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setString(1, username);
		// pstmt.setString(2, password);
		// // d) 使用ResultSet获取查询结果，
		// // 并判断ReslutSet，
		// // 得出是否能登录的结论
		// ResultSet rs = pstmt.executeQuery();
		// boolean allowLogin = rs.next();
		// // 3. 返回结果（响应）
		// PrintWriter out = resp.getWriter();
		// if(allowLogin) {
		// out.println("登录成功");
		// System.out.println("登录成功！");
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (username.equals("1") && password.equals("1")) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">");
			out.println("<title>查看员工列表</title>");
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
			out.println("<li><a href=\"login.html\">登录</a></li>");
			out.println("<li><a href=\"addnewEmployee.html\">增加员工信息</a></li>");
			out.println("<li><a href=\"employeeList.do\" class=\"selected\">查看员工列表</a></li>");
			out.println("<li><a href=\"logout.do\">退出</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("<div class=\"body_wrap\">");
			out.println("<div class=\"body\">");
			out.println("<table class=\"list_table\">");
			out.println("<tr>");
			out.println("<th>姓名</td>");
			out.println("<th>性别</td>");
			out.println("<th>部门</td>");
			out.println("<th>月薪</td>");
			out.println("<th colspan=\"2\">操作</td>");
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
