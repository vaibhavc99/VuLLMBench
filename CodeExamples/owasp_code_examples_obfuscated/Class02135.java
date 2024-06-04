
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02135")
public class Class02135 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02135");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_8e80ba5e = param_eca07335; // assign
        StringBuilder var_7061865e = new StringBuilder(var_8e80ba5e); // stick in stringbuilder
        var_7061865e.append(" SafeStuff"); // append some safe content
        var_7061865e.replace(
                var_7061865e.length() - "Chars".length(),
                var_7061865e.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_0a88e53b = new java.util.HashMap<String, Object>();
        var_0a88e53b.put("key95930", var_7061865e.toString()); // put in a collection
        String var_22e5c4c5 = (String) var_0a88e53b.get("key95930"); // get it back out
        String var_a3b00395 = var_22e5c4c5.substring(0, var_22e5c4c5.length() - 1); // extract most of it
        String var_a7610fe2 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_a3b00395.getBytes()))); // B64 encode and decode it
        String var_ef1533e2 = var_a7610fe2.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_ff9a7bc9 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_ff9a7bc9); // reflection

        return var_37b51d19;
    }
}
