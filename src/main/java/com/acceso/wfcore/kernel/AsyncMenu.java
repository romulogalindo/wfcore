package com.acceso.wfcore.kernel;

import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RequestManager;
import com.acceso.wfweb.web.Menu;
import com.acceso.wfweb.web.MenuItem;
import com.acceso.wfweb.web.Root;
import com.acceso.wfweb.web.Sistema;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class AsyncMenu extends AsyncProcessor {

    public AsyncMenu(AsyncContext asyncCtx, int secs, int type) {
        super(asyncCtx, secs, type);
    }

    @Override
    public void run() {
        JsonResponse jsonResponse = JsonResponse.defultJsonResponseOK("");
        PrintWriter out = null;
        Integer co_subsis = null;
        Integer co_sistem = null;

        try {
            out = this.asyncContext.getResponse().getWriter();
            co_subsis = Util.toInt(asyncContext.getRequest().getParameter("co_subsis"), -1);
            co_sistem = Util.toInt(asyncContext.getRequest().getParameter("co_sistem"), -1);
            Usuario usuario = ((Usuario) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("US"));

            String no_temdef = "";
            for (Sistema sistema : ((Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas()) {
                if (sistema.getCo_sistem() == co_sistem) {
                    no_temdef = sistema.getNo_temdef();
                }
            }

            usuario.setMainMenu(usuario.getMainMenus().get(co_sistem + "" + co_subsis));
            usuario.setCo_sistem(co_sistem);
            usuario.setCo_subsis(co_subsis);
            usuario.setNo_temdef(no_temdef);

            String htmlresponse = "";
            htmlresponse += "<ul class=\"collapsible collapsible-accordion\">";
            for (Menu menu : usuario.getMainMenu().getMenu()) {
                htmlresponse += "                            <li>";
                if (menu.getUrl() != null && menu.getUrl().length() > 0) {
                    htmlresponse += "                            <a id=\"menu" + menu.getCo_mensis() + "\" class=\"collapsible-header waves-effect arrow-r\">";
                    htmlresponse += "                                <i class=\"fas fa-chevron-right\"></i>";
                    htmlresponse += menu.getName();
                    htmlresponse += "                                        <i class=\"fas fa-angle-down rotate-icon\"></i>";
                    htmlresponse += "                                    </a>";
                    htmlresponse += "                                    <div class=\"collapsible-body\"> ";
                    htmlresponse += "                                        <ul>";

                    for (MenuItem menuItem : menu.getSub()) {
                        htmlresponse += "                                               <li class=\"\">";
                        if (menuItem.getUrl() == null & menuItem.getSub().size() > 0) {
                            htmlresponse += "                                                                <a class=\"collapsible-header waves-effect arrow-r\">";
                            htmlresponse += "                                                                     <i class=\"fas fa-chevron-right\"></i>";
                            htmlresponse += menuItem.getName();
                            htmlresponse += "                                                            <i class=\"fas fa-angle-down rotate-icon\"></i>";
                            htmlresponse += "                                                        </a>";
                            htmlresponse += "                                                        <div class=\"collapsible-body\">";
                            htmlresponse += "                                                            <ul>";
                            for (MenuItem menuItem2 : menuItem.getSub()) {
                                if (menuItem2.getUrl() != null & menuItem2.getSub().size() == 0) {
                                    htmlresponse += "                                                                       <li class=\"\">";
                                    htmlresponse += "                                                                            <a href=\"" + menuItem2.getUrl() + "\" class=\"waves-effect a\">" + menuItem2.getName() + "</a>";
                                    htmlresponse += "                                                                        </li>";
                                }
                            }
                            htmlresponse += "                                                            </ul>";
                            htmlresponse += "                                                        </div>";
                        }
//                                                                    <c:if test="${empty menuitem.url and fn:length(menuitem.sub) > 0}">
//
//                                                                    </c:if>
//                                                                    <c:if test="${not empty menuitem.url and fn:length(menuitem.sub) eq 0}">
                        if (menuItem.getUrl() != null & menuItem.getSub().size() == 0) {
                            htmlresponse += "                                                        <a href=\"" + menuItem.getUrl() + "\" class=\"waves-effect\">" + menuItem.getName() + "</a>";
                        }

//                                                                    </c:if>

                        htmlresponse += "                                               </li>";
                    }
                    htmlresponse += "                                        </ul>";
                    htmlresponse += "                                    </div>";
                }
//                                                <c:if test="${empty menu.url and fn:length(menu.sub) > 0}">
//
//                                                </c:if>

                if (menu.getUrl() == null) {
                    htmlresponse += "                                    <a href=\"" + menu.getUrl() + "\" class=\"waves-effect\">" + menu.getName() + "</a>";
                }

                htmlresponse += "                            </li>";
            }
            htmlresponse += "                    </ul>";


            /*EXE VALPAGJS*/
            ValpagJson valpagJson = null;
            Object valpagx = null;
            jsonResponse.setResult(htmlresponse);

            out.write(Util.toJSON2(jsonResponse));
        } catch (Exception ep) {
            ErrorMessage em = Util.getError(ep, 90);
            jsonResponse.setError(em);
            jsonResponse.setStatus(JsonResponse.ERROR);
            out.write(Util.toJSON2(jsonResponse));

            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        out.flush();
        out.close();
        asyncContext.complete();
    }

}
