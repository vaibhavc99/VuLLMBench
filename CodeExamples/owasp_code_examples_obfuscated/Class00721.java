
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00721")
public class Class00721 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00721");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        String var_37b51d19 = "";
        if (var_eca07335 != null) {
            java.util.List<String> var_c95d91f5 = new java.util.ArrayList<String>();
            var_c95d91f5.add("safe");
            var_c95d91f5.add(var_eca07335);
            var_c95d91f5.add("moresafe");

            var_c95d91f5.remove(0); // remove the 1st safe value

            var_37b51d19 = var_c95d91f5.get(0); // get the var_eca07335 value
        }

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
