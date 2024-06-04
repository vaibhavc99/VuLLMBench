
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00863")
public class Class00863 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheValue("Class00863");

        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_eca07335);

        java.io.File var_b30d6992 = new java.io.File(var_37b51d19, "/Test.txt");
        param_d1fc8eaf.getWriter()
                .println(
                        "Access to file: '"
                                + org.BrightEpsilon304
                                        .esapi
                                        .ESAPI
                                        .encoder()
                                        .encodeForHTML(var_b30d6992.toString())
                                + "' created.");
        if (var_b30d6992.exists()) {
            param_d1fc8eaf.getWriter().println(" And file already exists.");
        } else {
            param_d1fc8eaf.getWriter().println(" But file doesn't exist yet.");
        }
    }
}
