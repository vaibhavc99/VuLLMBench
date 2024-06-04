
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00008")
public class Class00008 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        // some code
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        String var_eca07335 = "";
        if (param_10573b87.getHeader("Class00008") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00008");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        String var_ac5c74b6 = "{call " + var_eca07335 + "}";

        try {
            java.var_ac5c74b6.Connection var_4717d53e =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlConnection();
            java.var_ac5c74b6.CallableStatement var_4245bf56 = var_4717d53e.prepareCall(var_ac5c74b6);
            java.var_ac5c74b6.ResultSet var_3a2d7564 = var_4245bf56.executeQuery();
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_3a2d7564, var_ac5c74b6, param_d1fc8eaf);

        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    }
}
