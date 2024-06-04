
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-01/Class00760")
public class Class00760 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00760");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_c7edb1aa = new java.util.HashMap<String, Object>();
        var_c7edb1aa.put("keyA-18915", "a-Value"); // put some stuff in the collection
        var_c7edb1aa.put("keyB-18915", var_eca07335); // put it in a collection
        var_c7edb1aa.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_c7edb1aa.get("keyB-18915"); // get it back out

        String var_ac5c74b6 = "{call " + var_37b51d19 + "}";

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
