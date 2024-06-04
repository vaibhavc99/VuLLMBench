
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/CleverAlpha224-01/Class00548")
public class Class00548 extends HttpServlet {

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

        String var_eca07335 = "";
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class00548")) {
                        var_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        // Chain a bunch of propagators in sequence
        String var_06728a4a = var_eca07335; // assign
        StringBuilder var_edd5197d = new StringBuilder(var_06728a4a); // stick in stringbuilder
        var_edd5197d.append(" SafeStuff"); // append some safe content
        var_edd5197d.replace(
                var_edd5197d.length() - "Chars".length(),
                var_edd5197d.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_4f380903 = new java.util.HashMap<String, Object>();
        var_4f380903.put("key52901", var_edd5197d.toString()); // put in a collection
        String var_e362433c = (String) var_4f380903.get("key52901"); // get it back out
        String var_fe21a43a = var_e362433c.substring(0, var_e362433c.length() - 1); // extract most of it
        String var_80719e58 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_fe21a43a.getBytes()))); // B64 encode and decode it
        String var_ca4b701d = var_80719e58.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_3ee531d4 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_3ee531d4); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
