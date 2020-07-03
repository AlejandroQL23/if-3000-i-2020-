package edu.ucr.rp.lab08.Jetty.Servlet;

import edu.ucr.rp.lab08.Jetty.FileUtil;
import edu.ucr.rp.lab08.Jetty.domain.User;
import edu.ucr.rp.lab08.Jetty.repository.UserRepository;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(FileUtil.readFromFile("welcome.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");

        UserRepository.getInstance().add(new User(name, lastName, email));

        resp.sendRedirect("room?email=" + email);//room?name=jcartero@vecindad.com

    }
}
