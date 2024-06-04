
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02396")
public class Class02396 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheParameter("Class02396");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", "b"};
        param_d1fc8eaf.getWriter().format(var_37b51d19, var_be8f8018);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_45616898 = param_eca07335; // assign
        StringBuilder var_776276e4 = new StringBuilder(var_45616898); // stick in stringbuilder
        var_776276e4.append(" SafeStuff"); // append some safe content
        var_776276e4.replace(
                var_776276e4.length() - "Chars".length(),
                var_776276e4.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_688a0716 = new java.util.HashMap<String, Object>();
        var_688a0716.put("key53479", var_776276e4.toString()); // put in a collection
        String var_20efcc6b = (String) var_688a0716.get("key53479"); // get it back out
        String var_3501b81a = var_20efcc6b.substring(0, var_20efcc6b.length() - 1); // extract most of it
        String var_c2b191d8 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_3501b81a.getBytes()))); // B64 encode and decode it
        String var_1d09112c = var_c2b191d8.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_1d09112c); // reflection

        return var_37b51d19;
    }
}
