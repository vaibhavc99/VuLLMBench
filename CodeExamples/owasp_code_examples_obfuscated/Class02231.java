
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02231")
public class Class02231 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class02231");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_0fa3c272 = param_eca07335; // assign
        StringBuilder var_1342c22c = new StringBuilder(var_0fa3c272); // stick in stringbuilder
        var_1342c22c.append(" SafeStuff"); // append some safe content
        var_1342c22c.replace(
                var_1342c22c.length() - "Chars".length(),
                var_1342c22c.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_d153e22b = new java.util.HashMap<String, Object>();
        var_d153e22b.put("key60610", var_1342c22c.toString()); // put in a collection
        String var_20c6a11f = (String) var_d153e22b.get("key60610"); // get it back out
        String var_79941521 = var_20c6a11f.substring(0, var_20c6a11f.length() - 1); // extract most of it
        String var_d0655294 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_79941521.getBytes()))); // B64 encode and decode it
        String var_f04c62e0 = var_d0655294.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_d54742b5 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_d54742b5); // reflection

        return var_37b51d19;
    }
}
