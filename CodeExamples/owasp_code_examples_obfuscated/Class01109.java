
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-01/Class01109")
public class Class01109 extends HttpServlet {

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

        java.io.File var_b30d6992 = new java.io.File(var_37b51d19, "/Class_0cbc6611.txt");
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
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_196a73b5 = param_eca07335; // assign
            StringBuilder var_3542eb67 = new StringBuilder(var_196a73b5); // stick in stringbuilder
            var_3542eb67.append(" SafeStuff"); // append some safe content
            var_3542eb67.replace(
                    var_3542eb67.length() - "Chars".length(),
                    var_3542eb67.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_39e67747 = new java.util.HashMap<String, Object>();
            var_39e67747.put("key87030", var_3542eb67.toString()); // put in a collection
            String var_c708260b = (String) var_39e67747.get("key87030"); // get it back out
            String var_fb8d72de = var_c708260b.substring(0, var_c708260b.length() - 1); // extract most of it
            String var_c7358f27 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_fb8d72de.getBytes()))); // B64 encode and decode it
            String var_c490d716 = var_c7358f27.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_ec46b10c = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_ec46b10c); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
