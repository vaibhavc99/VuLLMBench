
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00136")
public class Class00136 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class00136") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00136");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        String var_37b51d19;
        String var_41420474 = "ABC";
        char var_654e45cf = var_41420474.charAt(1); // condition 'B', which is safe

        // Simple case statement that assigns var_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
        switch (var_654e45cf) {
            case 'A':
                var_37b51d19 = var_eca07335;
                break;
            case 'B':
                var_37b51d19 = "bob";
                break;
            case 'C':
            case 'D':
                var_37b51d19 = var_eca07335;
                break;
            default:
                var_37b51d19 = "bob's your uncle";
                break;
        }

        String var_5b063e27 = null;
        java.io.FileOutputStream var_e571b045 = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;

            var_e571b045 = new java.io.FileOutputStream(var_5b063e27, false);
            param_d1fc8eaf.getWriter()
                    .println(
                            "Now ready to write to file: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27));

        } catch (Exception e) {
            System.out.println("Couldn't open FileOutputStream on file: '" + var_5b063e27 + "'");
            //			System.out.println("File exception caught and swallowed: " + e.getMessage());
        } finally {
            if (var_e571b045 != null) {
                try {
                    var_e571b045.close();
                    var_e571b045 = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    }
}
