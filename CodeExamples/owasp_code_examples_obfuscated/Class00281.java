
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00281")
public class Class00281 extends HttpServlet {

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
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Referer");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            var_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        String var_37b51d19;

        // Simple ? condition that assigns constant to var_37b51d19 on true condition
        int var_0fc3cfbc = 106;

        var_37b51d19 = (7 * 18) + var_0fc3cfbc > 200 ? "This_should_always_happen" : var_eca07335;

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", "b"};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, var_37b51d19, var_be8f8018);
    }
}
