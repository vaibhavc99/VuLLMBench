
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-05/Class02491")
public class Class02491 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class02491");
        String param_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) param_eca07335 = var_f09cc7ee[0];
        else param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19.toCharArray());
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_33e3e1bc = param_eca07335; // assign
        StringBuilder var_dfc9c22c = new StringBuilder(var_33e3e1bc); // stick in stringbuilder
        var_dfc9c22c.append(" SafeStuff"); // append some safe content
        var_dfc9c22c.replace(
                var_dfc9c22c.length() - "Chars".length(),
                var_dfc9c22c.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_c5fcfa87 = new java.util.HashMap<String, Object>();
        var_c5fcfa87.put("key17852", var_dfc9c22c.toString()); // put in a collection
        String var_ba3d6f8c = (String) var_c5fcfa87.get("key17852"); // get it back out
        String var_851598b4 = var_ba3d6f8c.substring(0, var_ba3d6f8c.length() - 1); // extract most of it
        String var_f12c88e9 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_851598b4.getBytes()))); // B64 encode and decode it
        String var_49375aab = var_f12c88e9.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_648599b1 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_648599b1); // reflection

        return var_37b51d19;
    }
}
