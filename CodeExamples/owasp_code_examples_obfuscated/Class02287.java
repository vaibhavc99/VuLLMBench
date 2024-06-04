
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-05/Class02287")
public class Class02287 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class02287");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "INSERT INTO users (username, password) VALUES ('foo','" + var_37b51d19 + "')";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            int var_e2942a04 = var_4245bf56.executeUpdate(var_ac5c74b6, new String[] {"USERNAME", "PASSWORD"});
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.outputUpdateComplete(var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;

        // Simple ? condition that assigns param_eca07335 to var_37b51d19 on false condition
        int var_0fc3cfbc = 106;

        var_37b51d19 = (7 * 42) - var_0fc3cfbc > 200 ? "This should never happen" : param_eca07335;

        return var_37b51d19;
    }
}
