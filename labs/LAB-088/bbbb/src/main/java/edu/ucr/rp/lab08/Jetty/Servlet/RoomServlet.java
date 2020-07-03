/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.lab08.Jetty.Servlet;

import edu.ucr.rp.lab08.Jetty.domain.Message;
import edu.ucr.rp.lab08.Jetty.domain.User;
import edu.ucr.rp.lab08.Jetty.repository.MessageRepository;
import edu.ucr.rp.lab08.Jetty.repository.UserRepository;
import edu.ucr.rp.lab08.Jetty.util.ThymeleafLoader;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomServlet extends HttpServlet {

    private ThymeleafLoader templates;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        templates = new ThymeleafLoader();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("users", UserRepository.getInstance().all());
        ctx.setVariable("messages", MessageRepository.getInstance().all());

        TemplateEngine engine = templates.getTemplateEngine();
        engine.process("room", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String email = req.getParameter("email");
        User user = UserRepository.getInstance().findByEmail(email);
        MessageRepository.getInstance().add(new Message(message, user));
        resp.sendRedirect("room?email=" + email);
    }

}
