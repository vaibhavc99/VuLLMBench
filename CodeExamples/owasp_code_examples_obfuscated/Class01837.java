
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-02/Class01837")
public class Class01837 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie var_72a0aafa =
                new javax.servlet.http.Cookie("Class01837", "FileName");
        var_72a0aafa.setMaxAge(60 * 3); // Store cookie for 3 minutes
        var_72a0aafa.setSecure(true);
        var_72a0aafa.setPath(param_10573b87.getRequestURI());
        var_72a0aafa.setDomain(new java.net.URL(param_10573b87.getRequestURL().toString()).getHost());
        param_d1fc8eaf.addCookie(var_72a0aafa);
        javax.servlet.RequestDispatcher var_eeec033a =
                param_10573b87.getRequestDispatcher("/CleverBeta661-02/Class01837.html");
        var_eeec033a.include(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        javax.servlet.http.Cookie[] var_428cd0f5 = param_10573b87.getCookies();

        String param_eca07335 = "noCookieValueSupplied";
        if (var_428cd0f5 != null) {
            for (javax.servlet.http.Cookie var_1f87f66c : var_428cd0f5) {
                if (var_1f87f66c.getName().equals("Class01837")) {
                    param_eca07335 = java.net.URLDecoder.decode(var_1f87f66c.getValue(), "UTF-8");
                    break;
                }
            }
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        // FILE URIs are tricky because they are different between Mac and Windows because of lack
        // of standardization.
        // Mac requires an extra slash for some reason.
        String var_80adda85 = "";
        if (System.getProperty("os.name").indexOf("Windows") != -1)
            if (System.getProperty("os.name").indexOf("Windows") != -1) var_80adda85 = "/";
            else var_80adda85 = "//";

        try {
            java.net.URI var_7aa060d5 =
                    new java.net.URI(
                            "file",
                            null,
                            var_80adda85
                                    + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR
                                            .replace('\\', java.io.File.separatorChar)
                                            .replace(' ', '_')
                                    + var_37b51d19,
                            null,
                            null);
            java.io.File var_b30d6992 = new java.io.File(var_7aa060d5);
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
        } catch (java.net.URISyntaxException e) {
            throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;

        // Simple ? condition that assigns constant to var_37b51d19 on true condition
        int var_0fc3cfbc = 106;

        var_37b51d19 = (7 * 18) + var_0fc3cfbc > 200 ? "This_should_always_happen" : param_eca07335;

        return var_37b51d19;
    }
}
