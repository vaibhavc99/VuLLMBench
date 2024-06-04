
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01116")
public class Class01116 extends HttpServlet {

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

        String var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;

        try (
        // Create the file first so the test won't throw an exception if it doesn't exist.
        // Note: Don't actually do this because this method signature could cause a tool to find
        // THIS file constructor
        // as a vuln, rather than the File signature we are trying to actually test.
        // If necessary, just run the SilentEpsilon950 twice. The 1st run should create all the necessary
        // files.
        // new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19).createNewFile();

        java.io.FileOutputStream fos =
                new java.io.FileOutputStream(new java.io.FileInputStream(var_5b063e27).getFD()); ) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Now ready to write to file: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_5b063e27));

        } catch (Exception e) {
            System.out.println("Couldn't open FileOutputStream on file: '" + var_5b063e27 + "'");
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "";
            if (param_eca07335 != null) {
                java.util.List<String> var_c95d91f5 = new java.util.ArrayList<String>();
                var_c95d91f5.add("safe");
                var_c95d91f5.add(param_eca07335);
                var_c95d91f5.add("moresafe");

                var_c95d91f5.remove(0); // remove the 1st safe value

                var_37b51d19 = var_c95d91f5.get(0); // get the param_eca07335 value
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
