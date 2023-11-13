package com.bosa.eshop.web.servlet;

import com.bosa.eshop.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns="/servlet/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService;
    private final SpringTemplateEngine templateEngine;

    public CategoryServlet(CategoryService c, SpringTemplateEngine templateEngine ){
        this.categoryService= c;
        this.templateEngine=templateEngine;
    }
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        categoryService.create(name,desc);
        resp.sendRedirect("/servlet/categories");

        /*String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        addCat(name,desc);
        resp.sendRedirect("/hello");*/
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        IWebExchange webExchange =JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("ipAddress",req.getRemoteAddr());
        context.setVariable("clientAgent",req.getHeader("User-Agent"));
        context.setVariable("categories", categoryService.findAll());

        templateEngine.process("categories.html",context,resp.getWriter());

        /*PrintWriter pw = resp.getWriter();
        String ip = req.getRemoteAddr();
        String agent = req.getHeader("User-Agent");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>CatList</h3>");
        pw.println("<h3>User info</h3>");
        pw.format("IP: %s\n",ip);
        pw.format("Browser: %s\n",agent);
        pw.println("<ul>");
        this.catService.listCategories().forEach(r->pw.printf("<li>%s (%s)</li>\n",r.getName(),r.getDesc()));
        pw.println("</ul>");
        pw.println("<h3>New Cat</h3>");
        pw.println("<form method='POST' action='/hello'>");
        pw.println("<label for='name'>Name:</label>");
        pw.println("<input id='name' type='text' name='name'/>");
        pw.println("<label for='desc'>Name:</label>");
        pw.println("<input id='desc' type='text' name='desc'/>");
        pw.println("<input type='submit' value='Submit'/>");
        pw.println("</form>");
        pw.println("</body>");
        pw.println("</html>");
        pw.flush();*/
    }

    public void addCat(String name,String desc){
        categoryService.create(name,desc);
    }
}
