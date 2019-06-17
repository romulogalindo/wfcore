package com.acceso.wfweb.servlets;

import com.acceso.security.DoLogin;
import com.acceso.wfweb.utils.RequestManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo En teoria esto debe ser reconocido como un webservice
 */
public class LoginServlet extends HttpServlet {

    public static String LOGINSERVLET_LOGIN64 = "/login64";
    public static String LOGINSERVLET_LOGOUT64 = "/logout64";
    public static String LOGINSERVLET_GETPACKAGE = "/package64";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);

        if (requestManager.getPath().contentEquals(LOGINSERVLET_LOGIN64)) {
            //Validar parametros
        } else if (requestManager.getPath().contentEquals(LOGINSERVLET_LOGOUT64)) {
            request.getSession().invalidate();
            try {
                requestManager.redirect("/");
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        } else {

            /*
            <div class="row">
                                    <ul style="width: 100%;">
                                        <!--Search Form-->
                                        <li>
                                            <form class="search-form" role="search">
                                                <div class="form-group md-form mt-0 pt-1 waves-light">
                                                    <input type="text" class="form-control" placeholder="Search">
                                                </div>
                                            </form>
                                        </li>
                                        <!--/.Search Form-->

                                        <!-- Side navigation links -->
                                        <li>
                                            <ul class="collapsible collapsible-accordion">
                                                <c:forEach var="menu" items="${US.mainMenu.menu}">
                                                    <li>
                                                        <c:if test="${empty menu.url and fn:length(menu.sub) > 0}">
                                                            <a class="collapsible-header waves-effect arrow-r">
                                                                <i class="fas fa-chevron-right"></i>
                                                                ${menu.name}
                                                                <i class="fas fa-angle-down rotate-icon"></i>
                                                            </a>
                                                            <div class="collapsible-body">
                                                                <ul>
                                                                    <c:forEach var="menuitem" items="${menu.sub}">
                                                                        <li>
                                                                            <c:if test="${empty menuitem.url and fn:length(menuitem.sub) > 0}">
                                                                                <a class="collapsible-header waves-effect arrow-r">
                                                                                    <i class="fas fa-chevron-right"></i>
                                                                                    ${menuitem.name}
                                                                                    <i class="fas fa-angle-down rotate-icon"></i>
                                                                                </a>
                                                                                <div class="collapsible-body">
                                                                                    <ul>
                                                                                        <c:forEach var="menuitem2"
                                                                                                   items="${menuitem.sub}">
                                                                                            <c:if test="${not empty menuitem2.url and fn:length(menuitem2.sub) eq 0}">
                                                                                                <li>
                                                                                                    <a href="${menuitem2.url}"
                                                                                                       class="waves-effect">${menuitem2.name}</a>
                                                                                                </li>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </ul>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:if test="${not empty menuitem.url and fn:length(menuitem.sub) eq 0}">
                                                                                <a href="${menuitem.url}"
                                                                                   class="waves-effect">${menuitem.name}</a>
                                                                            </c:if>
                                                                        </li>
                                                                    </c:forEach>
                                                                </ul>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${not empty menu.url }">
                                                            <a href="${menu.url}" class="waves-effect">${menu.name}</a>
                                                        </c:if>

                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <!--/. Side navigation links -->
                                    </ul>
                                </div>
             */
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);
        DoLogin doLogin = new DoLogin();
        String goToUrl = "/";

        if (requestManager.getPath().contains(LOGINSERVLET_LOGIN64) || requestManager.getPath().contentEquals("/")) {
            try {
                System.out.println("1* => " + 1);
                if (doLogin.SecurityLogin(requestManager)) {
                    System.out.println("(*)goToUrl = " + goToUrl);
                    requestManager.save_over_request("goto", "go!");
                    requestManager.save_over_session("US", doLogin.getUsuario());
                    //deberia darme una linea por default>>>>ejeurl-->444
                    goToUrl = "/wf?co_conten=444";
                    System.out.println("goToUrl = " + goToUrl);
                    System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());
                } else {
                    System.out.println("1** => " + 1);
                    throw new Exception(doLogin.getMessage());
                }

            } catch (Exception ep) {
                requestManager.save_over_request("goto", "go!");
                requestManager.save_over_session("login_error", ep.getMessage());
                goToUrl = "/";
            }
        } else if (requestManager.getPath().contains(LOGINSERVLET_LOGOUT64)) {
            request.getSession().invalidate();
            goToUrl = "/";
        }

        try {
            requestManager.redirect(goToUrl);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

}
