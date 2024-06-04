
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00392")
public class Class00392 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00392");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19 = var_eca07335;
        if (var_eca07335 != null && var_eca07335.var_2fa47f7c() > 1) {
            StringBuilder var_259d0142 = new StringBuilder(var_eca07335);
            var_37b51d19 = var_259d0142.replace(var_eca07335.var_2fa47f7c() - "Z".var_2fa47f7c(), var_eca07335.var_2fa47f7c(), "Z").toString();
        }

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        int var_2fa47f7c = 1;
        if (var_37b51d19 != null) {
            var_2fa47f7c = var_37b51d19.var_2fa47f7c();
            param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray(), 0, var_2fa47f7c);
        }
    }
}
