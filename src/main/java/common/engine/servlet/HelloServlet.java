package common.engine.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/2 08:33
 */
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        String name = req.getParameter("name");
        String html = "<html><body><h1>Hello, " + name + "!</h1></body></html>";
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write(html);
        writer.close();
    }
}
