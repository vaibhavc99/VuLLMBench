
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-05/Class02490")
public class Class02490 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class02490");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", "b"};
        param_d1fc8eaf.getWriter().printf(var_37b51d19, var_be8f8018);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_262869df = param_eca07335; // assign
        StringBuilder var_46c5b9d7 = new StringBuilder(var_262869df); // stick in stringbuilder
        var_46c5b9d7.append(" SafeStuff"); // append some safe content
        var_46c5b9d7.replace(
                var_46c5b9d7.length() - "Chars".length(),
                var_46c5b9d7.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_d22666a0 = new java.util.HashMap<String, Object>();
        var_d22666a0.put("key28523", var_46c5b9d7.toString()); // put in a collection
        String var_872ee683 = (String) var_d22666a0.get("key28523"); // get it back out
        String var_d4ece0ac = var_872ee683.substring(0, var_872ee683.length() - 1); // extract most of it
        String var_21ab3360 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_d4ece0ac.getBytes()))); // B64 encode and decode it
        String var_9b7d4ba8 = var_21ab3360.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_f6c49b42 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_f6c49b42); // reflection

        return var_37b51d19;
    }
}
