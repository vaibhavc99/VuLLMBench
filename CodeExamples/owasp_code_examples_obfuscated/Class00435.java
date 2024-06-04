
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00435")
public class Class00435 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00435");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19 = "safe!";
        java.util.HashMap<String, Object> var_f014ed64 = new java.util.HashMap<String, Object>();
        var_f014ed64.put("keyA-86691", "a-Value"); // put some stuff in the collection
        var_f014ed64.put("keyB-86691", var_eca07335); // put it in a collection
        var_f014ed64.put("keyC", "another-Value"); // put some stuff in the collection
        var_37b51d19 = (String) var_f014ed64.get("keyB-86691"); // get it back out

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            var_4245bf56.addBatch(var_ac5c74b6);
            int[] var_0700cd3e = var_4245bf56.executeBatch();
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_ac5c74b6, var_0700cd3e, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    }
}
