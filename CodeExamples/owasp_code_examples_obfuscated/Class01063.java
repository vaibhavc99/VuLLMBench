
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-02/Class01063")
public class Class01063 extends HttpServlet {

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
        if (param_10573b87.getHeader("Referer") != null) {
            param_eca07335 = param_10573b87.getHeader("Referer");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write("Parameter value: " + var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_70ca0576 = param_eca07335; // assign
            StringBuilder var_c2454006 = new StringBuilder(var_70ca0576); // stick in stringbuilder
            var_c2454006.append(" SafeStuff"); // append some safe content
            var_c2454006.replace(
                    var_c2454006.length() - "Chars".length(),
                    var_c2454006.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_f795be39 = new java.util.HashMap<String, Object>();
            var_f795be39.put("key92400", var_c2454006.toString()); // put in a collection
            String var_a3e537fc = (String) var_f795be39.get("key92400"); // get it back out
            String var_38d94d2b = var_a3e537fc.substring(0, var_a3e537fc.length() - 1); // extract most of it
            String var_8d214bc2 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_38d94d2b.getBytes()))); // B64 encode and decode it
            String var_c2545ec2 = var_8d214bc2.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_c2545ec2); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
