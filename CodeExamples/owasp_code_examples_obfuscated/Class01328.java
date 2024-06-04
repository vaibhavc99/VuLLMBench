
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01328")
public class Class01328 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01328");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

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
                            "file:"
                                    + var_80adda85
                                    + org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR
                                            .replace('\\', '/')
                                            .replace(' ', '_')
                                    + var_37b51d19);
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

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_ecadf286 = param_eca07335; // assign
            StringBuilder var_493bb5dc = new StringBuilder(var_ecadf286); // stick in stringbuilder
            var_493bb5dc.append(" SafeStuff"); // append some safe content
            var_493bb5dc.replace(
                    var_493bb5dc.length() - "Chars".length(),
                    var_493bb5dc.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_9134786f = new java.util.HashMap<String, Object>();
            var_9134786f.put("key17973", var_493bb5dc.toString()); // put in a collection
            String var_eafc9e29 = (String) var_9134786f.get("key17973"); // get it back out
            String var_d4ec94e6 = var_eafc9e29.substring(0, var_eafc9e29.length() - 1); // extract most of it
            String var_59079133 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_d4ec94e6.getBytes()))); // B64 encode and decode it
            String var_0bdd755a = var_59079133.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_ec1af00f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_ec1af00f); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
