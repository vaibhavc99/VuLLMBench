
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01034")
public class Class01034 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class01034") != null) {
            param_eca07335 = param_10573b87.getHeader("Class01034");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_5b063e27 = null;
        java.io.FileOutputStream var_e571b045 = null;

        try {
            var_5b063e27 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR + var_37b51d19;

            var_e571b045 = new java.io.FileOutputStream(var_5b063e27);
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

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = "";
            if (param_eca07335 != null) {
                var_37b51d19 =
                        new String(
                                org.apache.commons.codec.binary.Base64.decodeBase64(
                                        org.apache.commons.codec.binary.Base64.encodeBase64(
                                                param_eca07335.getBytes())));
            }

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
