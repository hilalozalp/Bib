package model;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    LinkedList<User> users;


    public void init() {
        users = new LinkedList<>();
        users.add(new User("User", "User", "user", "pass"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext application = getServletContext();
        HttpSession session = request.getSession();
        boolean loggedIn = false;




        //aus session rausholen, ob man eingelogged ist oder nicht
        if (session.getAttribute("loggedIn") != null) {
            loggedIn = (boolean) session.getAttribute("loggedIn");
        }

        String dispatchto = "";
        if (request.getParameter("dispatchto") != null) {
            dispatchto = request.getParameter("dispatchto");
        }



        //wenn man eingelogged ist und dispatchto nicht leer, dann soll man
        //auf die jeweilige Seite redirected werden
        if (loggedIn) {


            if (dispatchto.equals("Buecher")) {
                response.sendRedirect(request.getContextPath() + "/buecher.html");

            } else if (dispatchto.equals("CDDVD")) {
                response.sendRedirect(request.getContextPath() + "/cddvd.html");

            } else if (dispatchto.equals("Gaestebuch")) {
                response.sendRedirect(request.getContextPath() + "/gaestebuch.html");

            } else if (dispatchto.equals("Team")) {
                response.sendRedirect(request.getContextPath() + "/team.html");

            } else if (dispatchto.equals("Registrieren")) {
                response.sendRedirect(request.getContextPath() + "/registrieren.html");


            } else {
                response.sendRedirect(request.getContextPath() + "/index.html");
            }

            //falls nich eingelooged --> login page


        } else {
            if (dispatchto.equals("Registrieren")) {
                response.sendRedirect(request.getContextPath() + "/registrieren.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/anmelden.html");
            }
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //zuerst muss man den validationType herausfinden
        String validationType = request.getParameter("validationType");
        System.out.println(validationType);


        switch (validationType) {
            case "register":
                //inputs aus form herausholen
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                //wenn ich "post" mache, wird ein neuer User mit diesen Parametern erstellt
                users.add(new User(firstname, lastname, username, password));
                break;

            case "login":
                String userID = request.getParameter("username");
                String pass = request.getParameter("password");

                for (User user : users) {
                    if (user.getUserID().equals(userID) && user.getPassword().equals(pass)) {
                        HttpSession session = request.getSession();
                        boolean loggedIn = true;
                        session.setAttribute("loggedIn", loggedIn);

                       // session.setAttribute("history", history);
                    }
                }
                break;

        }
        //ansonsten zu index.html weiterleiten
        response.sendRedirect(request.getContextPath() + "/index.html");

    }

}
