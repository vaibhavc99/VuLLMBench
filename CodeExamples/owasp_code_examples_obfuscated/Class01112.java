
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01112")
public class Class01112 extends HttpServlet {

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
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getHeaderNames();
        while (var_a8998c31.hasMoreElements()) {
            String var_b068931c = (String) var_a8998c31.nextElement();

            if (org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.commonHeaders.contains(var_b068931c)) {
                continue; // If standard header, move on to next one
            }

            java.util.Enumeration<String> var_f09cc7ee = param_10573b87.getHeaders(var_b068931c);
            if (var_f09cc7ee != null && var_f09cc7ee.hasMoreElements()) {
                param_eca07335 = var_b068931c; // Grabs the var_b068931c of the first non-standard header as the parameter
                // value
                break;
            }
        }
        // Note: We don't URL decode header var_a8998c31 because people don't normally do that

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = null;
        java.io.FileInputStream var_37ab815c = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;
            var_37ab815c = new java.io.FileInputStream(var_5b063e27);
            byte[] var_92eb5ffe = new byte[1000];
            int var_f7bd60b7 = var_37ab815c.read(var_92eb5ffe);
            param_d1fc8eaf.getWriter()
                    .println(
                            "The beginning of file: '"
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27)
                                    + "' is:\n\n");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(new String(var_92eb5ffe, 0, var_f7bd60b7)));
        } catch (Exception e) {
            System.out.println("Couldn't open FileInputStream on file: '" + var_5b063e27 + "'");
            //			System.out.println("File exception caught and swallowed: " + e.getMessage());
        } finally {
            if (var_37ab815c != null) {
                try {
                    var_37ab815c.close();
                    var_37ab815c = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
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
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
