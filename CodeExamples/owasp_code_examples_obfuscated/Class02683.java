
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-05/Class02683")
public class Class02683 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheValue("Class02683");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_0b441fee = param_eca07335; // assign
        StringBuilder var_c0f9e0e2 = new StringBuilder(var_0b441fee); // stick in stringbuilder
        var_c0f9e0e2.append(" SafeStuff"); // append some safe content
        var_c0f9e0e2.replace(
                var_c0f9e0e2.length() - "Chars".length(),
                var_c0f9e0e2.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_b869a79a = new java.util.HashMap<String, Object>();
        var_b869a79a.put("key47309", var_c0f9e0e2.toString()); // put in a collection
        String var_6e95a199 = (String) var_b869a79a.get("key47309"); // get it back out
        String var_68765bd2 = var_6e95a199.substring(0, var_6e95a199.length() - 1); // extract most of it
        String var_694611dd =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_68765bd2.getBytes()))); // B64 encode and decode it
        String var_4808bafc = var_694611dd.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_8a0cb296 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_8a0cb296); // reflection

        return var_37b51d19;
    }
}
