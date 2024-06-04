
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class01598")
public class Class01598 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class01598");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19);
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_621e4a04 = param_eca07335; // assign
            StringBuilder var_a0de268b = new StringBuilder(var_621e4a04); // stick in stringbuilder
            var_a0de268b.append(" SafeStuff"); // append some safe content
            var_a0de268b.replace(
                    var_a0de268b.length() - "Chars".length(),
                    var_a0de268b.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_f2ebf718 = new java.util.HashMap<String, Object>();
            var_f2ebf718.put("key75770", var_a0de268b.toString()); // put in a collection
            String var_838ca4a6 = (String) var_f2ebf718.get("key75770"); // get it back out
            String var_5876d8b5 = var_838ca4a6.substring(0, var_838ca4a6.length() - 1); // extract most of it
            String var_c4946f1e =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_5876d8b5.getBytes()))); // B64 encode and decode it
            String var_b1db7453 = var_c4946f1e.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_37b51d19 = var_8afc1e9b.doSomething(var_b1db7453); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
