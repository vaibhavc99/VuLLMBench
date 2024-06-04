
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00717")
public class Class00717 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00717");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_4e12f84a = var_eca07335; // assign
        StringBuilder var_9512cbb2 = new StringBuilder(var_4e12f84a); // stick in stringbuilder
        var_9512cbb2.append(" SafeStuff"); // append some safe content
        var_9512cbb2.replace(
                var_9512cbb2.length() - "Chars".length(),
                var_9512cbb2.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_63fce52a = new java.util.HashMap<String, Object>();
        var_63fce52a.put("key59129", var_9512cbb2.toString()); // put in a collection
        String var_fa54c945 = (String) var_63fce52a.get("key59129"); // get it back out
        String var_c0f80959 = var_fa54c945.substring(0, var_fa54c945.length() - 1); // extract most of it
        String var_da09b7d2 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_c0f80959.getBytes()))); // B64 encode and decode it
        String var_553e4032 = var_da09b7d2.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_57933da2 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_57933da2); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
