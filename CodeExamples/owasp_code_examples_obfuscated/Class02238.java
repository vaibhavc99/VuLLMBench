
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02238")
public class Class02238 extends HttpServlet {

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
            String[] var_f09cc7ee = var_1d78dc8e.get("Class02238");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_2693cc42 = param_eca07335; // assign
        StringBuilder var_d8020700 = new StringBuilder(var_2693cc42); // stick in stringbuilder
        var_d8020700.append(" SafeStuff"); // append some safe content
        var_d8020700.replace(
                var_d8020700.length() - "Chars".length(),
                var_d8020700.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_2be8d2f4 = new java.util.HashMap<String, Object>();
        var_2be8d2f4.put("key16227", var_d8020700.toString()); // put in a collection
        String var_a2adbcef = (String) var_2be8d2f4.get("key16227"); // get it back out
        String var_cd13eca0 = var_a2adbcef.substring(0, var_a2adbcef.length() - 1); // extract most of it
        String var_86bac47e =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_cd13eca0.getBytes()))); // B64 encode and decode it
        String var_503374ae = var_86bac47e.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_b2bb5990 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_b2bb5990); // reflection

        return var_37b51d19;
    }
}
