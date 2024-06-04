
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02408")
public class Class02408 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheParameter("Class02408");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray());
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_1624991a = param_eca07335; // assign
        StringBuilder var_9a40eab2 = new StringBuilder(var_1624991a); // stick in stringbuilder
        var_9a40eab2.append(" SafeStuff"); // append some safe content
        var_9a40eab2.replace(
                var_9a40eab2.length() - "Chars".length(),
                var_9a40eab2.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_5a2c1f17 = new java.util.HashMap<String, Object>();
        var_5a2c1f17.put("key67594", var_9a40eab2.toString()); // put in a collection
        String var_7be7352f = (String) var_5a2c1f17.get("key67594"); // get it back out
        String var_ce214616 = var_7be7352f.substring(0, var_7be7352f.length() - 1); // extract most of it
        String var_f636542a =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_ce214616.getBytes()))); // B64 encode and decode it
        String var_015fbd9c = var_f636542a.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_d4c8e139 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_d4c8e139); // reflection

        return var_37b51d19;
    }
}
