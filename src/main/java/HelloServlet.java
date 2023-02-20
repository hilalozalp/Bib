import java.io.*;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Comment;


@WebServlet(urlPatterns = {"/servlet/HelloServlet"})
public class HelloServlet extends HttpServlet {
     LinkedList<Comment> comments;


    public void init() { //init macht einmal
       comments = new LinkedList<>();
       comments.add(new Comment("jonny_boy", "jonny123@gmail.com", "Tolle Bücherauswahl!"));
       comments.add(new Comment("lisa1234", "lisa@hotmail.com", "Super Bibliothek!"));
       comments.add(new Comment("günther_maier", "gunther@gmail.com", "Sehr freundliche Mitarbeiter"));
    }


/*
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

     */


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        comments.add(new Comment(request.getParameter("username"), request.getParameter("email"), request.getParameter("comment")));




        out.println("<div class=\"navbar\">\n" +
                "    <h1 class= \"head-line\">\n" +
                "        ÖFFENTLICHE BIBLIOTHEK <br> <span class=\"head-line2\">KLAUS-WEILER-FRAXERN</span>\n" +
                "    </h1>\n" +
                "</div>");


        out.println("<nav>\n" +
                "    <ul>\n" +
                "        <li><a href=\"mailto: klaus@bibliothek.at\"> <img class=\"mail-link\" src=\"mail.png\">&nbsp;&nbsp;E-Mail</a></li>\n" +
                "        <li><a href=\"index.html\"> <img class=\"bibicon\" src=\"buch.png\">Startseite</a></li>\n" +
                "\n" +
                "        <li><a href=\"#\"> <img class=\"media\" src=\"media.png\">&nbsp;&nbsp;Unsere Medien</a>\n" +
                "            <ul>\n" +
                "                <li><a href=\"buecher.html\"> Bücher & Zeitschriften</a></li>\n" +
                "                <li><a href=\"cddvd.html\"> CDs & DVDs </a></li>\n" +
                "                <li><a href=\"#\"> Hörbücher</a></li>\n" +
                "                <li><a href=\"#\"> Spiele</a></li>\n" +
                "            </ul>\n" +
                "        </li>\n" +
                "        <li><a href=\"team.html\"> <img class=\"team\" src=\"webapp/assignment05/team.png\">&nbsp;&nbsp;Team</a></li>\n" +
                "        <li><a href=\"#\"> <img class=\"media\" src=\"konto.png\">&nbsp;&nbsp;Konto</a>\n" +
                "            <ul>\n" +
                "                <li><a href=\"registrieren.html\">Registrieren</a></li>\n" +
                "                <li><a href=\"#\">Anmelden</a></li>\n" +
                "            </ul>\n" +
                "        </li>\n" +
                "        <li><a href=\"gaestebuch.html\"> <img class =\"guestbook\"  src=\"comment.png\">Gästebuch</a></li>\n" +
                "    </ul>\n" +
                "</nav>");

        out.println("<div class =\"name-site\">\n" +
                "    <span class=\"name-item\">Unser Gästebuch</span>\n" +
                "</div>");



        for(Comment entries : comments){
            out.println("Kommentar von: ");
            out.println(entries.getUsername());
            out.println(" | ");
            out.println(entries.getEmail());
            out.println("<br></br>");
            out.println(entries.getComment());

            out.println("<br></br>");
            out.println("<br></br>");

        }

        out.println("<br></br>");

    }

}