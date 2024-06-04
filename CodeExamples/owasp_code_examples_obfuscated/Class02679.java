
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-05/Class02679")
public class Class02679 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String param_eca07335 = var_456727ac.getTheValue("Class02679");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        java.io.PrintWriter var_c68271a6 = param_d1fc8eaf.getWriter();
        var_c68271a6.write("<!DOCTYPE html>\n<html>\n<body>\n<p>");
        var_c68271a6.format(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
        var_c68271a6.write("\n</p>\n</body>\n</html>");
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
