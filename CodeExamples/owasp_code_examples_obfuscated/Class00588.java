
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/SilentGamma375-00/Class00588")
public class Class00588 extends HttpServlet {

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

        String var_eca07335 = "";
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class00588")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = var_eca07335;
        if (var_eca07335 != null && var_eca07335.length() > 1) {
            var_37b51d19 = var_eca07335.substring(0, var_eca07335.length() - 1);
        }

        // javax.servlet.http.HttpSession.setAttribute(java.lang.String^,java.lang.Object)
        param_10573b87.getSession().setAttribute(var_37b51d19, "10340");

        param_d1fc8eaf.getWriter()
                .println(
                        "Item: '"
                                + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.encodeForHTML(var_37b51d19)
                                + "' with var_2063c160: '10340' saved in session.");
    }
}
