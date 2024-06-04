
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00218")
public class Class00218 extends HttpServlet {

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
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getHeaderNames();
        while (var_a8998c31.hasMoreElements()) {
            String var_b068931c = (String) var_a8998c31.nextElement();

            if (org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.commonHeaders.contains(var_b068931c)) {
                continue; // If standard header, move on to next one
            }

            java.util.Enumeration<String> var_f09cc7ee = param_10573b87.getHeaders(var_b068931c);
            if (var_f09cc7ee != null && var_f09cc7ee.hasMoreElements()) {
                var_eca07335 = var_b068931c; // Grabs the var_b068931c of the first non-standard header as the parameter
                // value
                break;
            }
        }
        // Note: We don't URL decode header var_a8998c31 because people don't normally do that

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

        // FILE URIs are tricky because they are different between Mac and Windows because of lack
        // of standardization.
        // Mac requires an extra slash for some reason.
        String var_80adda85 = "";
        if (System.getProperty("os.var_b068931c").indexOf("Windows") != -1)
            if (System.getProperty("os.var_b068931c").indexOf("Windows") != -1) var_80adda85 = "/";
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
    }
}
