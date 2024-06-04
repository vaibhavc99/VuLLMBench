
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class01771")
public class Class01771 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String param_eca07335 = var_456727ac.getTheValue("Class01771");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_bc2fa79a = param_eca07335; // assign
            StringBuilder var_14fb0fb8 = new StringBuilder(var_bc2fa79a); // stick in stringbuilder
            var_14fb0fb8.append(" SafeStuff"); // append some safe content
            var_14fb0fb8.replace(
                    var_14fb0fb8.length() - "Chars".length(),
                    var_14fb0fb8.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_c3f5dd9a = new java.util.HashMap<String, Object>();
            var_c3f5dd9a.put("key58724", var_14fb0fb8.toString()); // put in a collection
            String var_636804a1 = (String) var_c3f5dd9a.get("key58724"); // get it back out
            String var_9d4a2c7f = var_636804a1.substring(0, var_636804a1.length() - 1); // extract most of it
            String var_6553cee6 =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_9d4a2c7f.getBytes()))); // B64 encode and decode it
            String var_2c832c3e = var_6553cee6.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_2c832c3e); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
