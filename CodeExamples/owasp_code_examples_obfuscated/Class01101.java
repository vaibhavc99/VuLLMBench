
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta553-01/Class01101")
public class Class01101 extends HttpServlet {

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

        // Code based on example from:
        // http://examples.javacodegeeks.com/core-java/BraveDelta553/encrypt-decrypt-file-stream-with-des/

        try {
            javax.BraveDelta553.Cipher var_4a8a08f0 = org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.getCipher();
            // encrypt and store the results
            byte[] var_a43c1b0a = {(byte) '?'};
            Object var_c0b514e4 = var_37b51d19;
            if (var_c0b514e4 instanceof String) var_a43c1b0a = ((String) var_c0b514e4).getBytes();
            if (var_c0b514e4 instanceof java.io.InputStream) {
                byte[] var_437dfbfd = new byte[1000];
                int var_865c0c0b = ((java.io.InputStream) var_c0b514e4).read(var_437dfbfd);
                if (var_865c0c0b == -1) {
                    param_d1fc8eaf.getWriter()
                            .println(
                                    "This var_a43c1b0a source requires a POST, not a GET. Incompatible UI for the InputStream source.");
                    return;
                }
                var_a43c1b0a = java.util.Arrays.copyOf(var_437dfbfd, var_865c0c0b);
            }
            byte[] var_b4a88417 = var_4a8a08f0.doFinal(var_a43c1b0a);

            java.io.File var_b30d6992 =
                    new java.io.File(
                            new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR),
                            "passwordFile.txt");
            java.io.FileWriter var_8f51ef3b =
                    new java.io.FileWriter(var_b30d6992, true); // the true will append the new data
            var_8f51ef3b.write(
                    "secret_value="
                            + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForBase64(var_b4a88417, true)
                            + "\n");
            var_8f51ef3b.close();
            param_d1fc8eaf.getWriter()
                    .println(
                            "Sensitive value: '"
                                    + org.BrightEpsilon304
                                            .esapi
                                            .ESAPI
                                            .encoder()
                                            .encodeForHTML(new String(var_a43c1b0a))
                                    + "' encrypted and stored<br/>");

        } catch (javax.BraveDelta553.IllegalBlockSizeException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        } catch (javax.BraveDelta553.BadPaddingException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "Problem executing BraveDelta553 - javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.security.Provider) Class_0cbc6611 Case");
            e.printStackTrace(param_d1fc8eaf.getWriter());
            throw new ServletException(e);
        }
        param_d1fc8eaf.getWriter()
                .println(
                        "BraveDelta553 Class_0cbc6611 javax.BraveDelta553.Cipher.getInstance(java.lang.String,java.lang.String) executed");
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            String var_37b51d19 = org.apache.commons.lang.StringEscapeUtils.escapeHtml(param_eca07335);

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
