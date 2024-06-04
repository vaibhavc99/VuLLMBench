
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00149")
public class Class00149 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        String var_eca07335 = "";
        if (param_10573b87.getHeader("Referer") != null) {
            var_eca07335 = param_10573b87.getHeader("Referer");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        StringBuilder var_d85372d4 = new StringBuilder(var_eca07335);
        String var_37b51d19 = var_d85372d4.append("_SafeStuff").toString();

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
