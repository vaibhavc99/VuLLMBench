
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentGamma375-00/Class00670")
public class Class00670 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00670");
        if (var_eca07335 == null) var_eca07335 = "";

        String var_37b51d19 = var_eca07335;
        if (var_eca07335 != null && var_eca07335.length() > 1) {
            StringBuilder var_9ce94792 = new StringBuilder(var_eca07335);
            var_37b51d19 = var_9ce94792.replace(var_eca07335.length() - "Z".length(), var_eca07335.length(), "Z").toString();
        }

        // javax.servlet.http.HttpSession.putValue(java.lang.String^,java.lang.Object)
        param_10573b87.getSession().putValue(var_37b51d19, "10340");

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' with value: 10340 saved in session.");
    }
}
