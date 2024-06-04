
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class01047")
public class Class01047 extends HttpServlet {

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

        String param_eca07335 = "";
        if (param_10573b87.getHeader("Referer") != null) {
            param_eca07335 = param_10573b87.getHeader("Referer");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        java.io.PrintWriter var_c68271a6 = param_d1fc8eaf.getWriter();
        var_c68271a6.write("<!DOCTYPE html>\n<html>\n<body>\n<p>");
        var_c68271a6.format(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
        var_c68271a6.write("\n</p>\n</body>\n</html>");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = param_eca07335;
            if (param_eca07335 != null && param_eca07335.length() > 1) {
                StringBuilder var_1979ccdc = new StringBuilder(param_eca07335);
                var_37b51d19 =
                        var_1979ccdc
                                .replace(param_eca07335.length() - "Z".length(), param_eca07335.length(), "Z")
                                .toString();
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
