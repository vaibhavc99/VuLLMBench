
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/SilentEpsilon118-00/Class00077")
public class Class00077 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class00077", "ECHOOO");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/SilentEpsilon118-00/Class00077.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String var_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class00077")) {
                    var_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19;
        String var_41420474 = "ABC";
        char var_654e45cf = var_41420474.charAt(2);

        // Simple case statement that assigns var_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
        switch (var_654e45cf) {
            case 'A':
                var_37b51d19 = var_eca07335;
                break;
            case 'B':
                var_37b51d19 = "bobs_your_uncle";
                break;
            case 'C':
            case 'D':
                var_37b51d19 = var_eca07335;
                break;
            default:
                var_37b51d19 = "bobs_your_uncle";
                break;
        }

        java.util.List<String> var_b3de6633 = new java.util.ArrayList<String>();

        String var_1ed86085 = System.getProperty("os.name");
        if (var_1ed86085.indexOf("Windows") != -1) {
            var_b3de6633.add("cmd.exe");
            var_b3de6633.add("/c");
        } else {
            var_b3de6633.add("sh");
            var_b3de6633.add("-c");
        }
        var_b3de6633.add("echo " + var_37b51d19);

        ProcessBuilder var_f09883b5 = new ProcessBuilder(var_b3de6633);

        try {
            Process var_83878c91 = var_f09883b5.start();
            org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.printOSCommandResults(var_83878c91, param_d1fc8eaf);
        } catch (IOException e) {
            System.out.println(
                    "Problem executing SilentEpsilon118 - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }
}
