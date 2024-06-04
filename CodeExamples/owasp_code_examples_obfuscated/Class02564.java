
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-03/Class02564")
public class Class02564 extends HttpServlet {

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

        String var_be571b25 = param_10573b87.getQueryString();
        String var_fb72fe30 = "Class02564" + "=";
        int var_799f5a62 = -1;
        if (var_be571b25 != null) var_799f5a62 = var_be571b25.indexOf(var_fb72fe30);
        if (var_799f5a62 == -1) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "getQueryString() couldn't find expected parameter '"
                                    + "Class02564"
                                    + "' in query string.");
            return;
        }

        String param_eca07335 =
                var_be571b25.substring(
                        var_799f5a62
                                + var_fb72fe30
                                        .length()); // 1st assume "Class02564" param_eca07335 is last
        // parameter in query string.
        // And then check to see if its in the middle of the query string and if so, trim off what
        // comes after.
        int var_a89f0a70 = var_be571b25.indexOf("&", var_799f5a62);
        if (var_a89f0a70 != -1) {
            param_eca07335 = var_be571b25.substring(var_799f5a62 + var_fb72fe30.length(), var_a89f0a70);
        }
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = null;
        java.io.FileOutputStream var_e571b045 = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;

            var_e571b045 = new java.io.FileOutputStream(new java.io.File(var_5b063e27), false);
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
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19;
        String var_41420474 = "ABC";
        char var_654e45cf = var_41420474.charAt(2);

        // Simple case statement that assigns param_eca07335 to var_37b51d19 on conditions 'A', 'C', or 'D'
        switch (var_654e45cf) {
            case 'A':
                var_37b51d19 = param_eca07335;
                break;
            case 'B':
                var_37b51d19 = "bobs_your_uncle";
                break;
            case 'C':
            case 'D':
                var_37b51d19 = param_eca07335;
                break;
            default:
                var_37b51d19 = "bobs_your_uncle";
                break;
        }

        return var_37b51d19;
    }
}
