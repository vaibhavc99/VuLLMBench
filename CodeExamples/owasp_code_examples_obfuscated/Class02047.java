
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class02047")
public class Class02047 extends HttpServlet {

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

        String param_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Referer");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            param_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19.toCharArray());
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;

        // Simple if statement that assigns param_eca07335 to var_37b51d19 on true condition
        int var_0fc3cfbc = 196;
        if ((500 / 42) + var_0fc3cfbc > 200) var_37b51d19 = param_eca07335;
        else var_37b51d19 = "This should never happen";

        return var_37b51d19;
    }
}
