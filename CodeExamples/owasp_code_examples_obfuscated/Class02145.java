
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02145")
public class Class02145 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02145");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write("Parameter value: " + var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_44b2b60a = param_eca07335; // assign
        StringBuilder var_a4205b48 = new StringBuilder(var_44b2b60a); // stick in stringbuilder
        var_a4205b48.append(" SafeStuff"); // append some safe content
        var_a4205b48.replace(
                var_a4205b48.length() - "Chars".length(),
                var_a4205b48.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_5574b91c = new java.util.HashMap<String, Object>();
        var_5574b91c.put("key32743", var_a4205b48.toString()); // put in a collection
        String var_57ce9ff0 = (String) var_5574b91c.get("key32743"); // get it back out
        String var_a5b3d525 = var_57ce9ff0.substring(0, var_57ce9ff0.length() - 1); // extract most of it
        String var_21a5f7c0 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_a5b3d525.getBytes()))); // B64 encode and decode it
        String var_b54c4b29 = var_21a5f7c0.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_b54c4b29); // reflection

        return var_37b51d19;
    }
}
