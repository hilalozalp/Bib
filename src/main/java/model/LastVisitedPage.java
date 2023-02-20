package model;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LastVisitedPage", value = "/LastVisitedPage")
public class LastVisitedPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Cookie wird aus request geholt und in einem Array gespeichert
        //url String wird auf default seite gesetzt
        Cookie[] cookies = request.getCookies();
        String url = request.getContextPath() + "/assignment5/index.html";


        //Falls das Array Daten (Cookie vom Browser) enthält,
        // soll das richtige (last_page also) ausgewertet werden
        //Default Wert im url String wird überschireben
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("last_page")){
                    url = c.getValue();
                    System.out.println("Cookie eingelesen " + c.getValue());
                }
            }
        }


        //Zeigt die richitge Seite an
        response.sendRedirect(url);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        //out.println("<meta http-equiv = 'Refresh' content'0; url=" + url + "'");
        out.println("</html>");
        System.out.println("Cookie wurde erfolgreich gesetzt; URL ist: " + url + ".");

    }



    /*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

     */
}
