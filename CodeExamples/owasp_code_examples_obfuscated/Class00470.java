
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00470")
public class Class00470 extends HttpServlet {

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
        String var_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class00470");
            if (var_f09cc7ee != null) var_eca07335 = var_f09cc7ee[0];
        }

        // Chain a bunch of propagators in sequence
        String var_e4e827f3 = var_eca07335; // assign
        StringBuilder var_8acf1599 = new StringBuilder(var_e4e827f3); // stick in stringbuilder
        var_8acf1599.append(" SafeStuff"); // append some safe content
        var_8acf1599.replace(
                var_8acf1599.length() - "Chars".length(),
                var_8acf1599.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_5782875a = new java.util.HashMap<String, Object>();
        var_5782875a.put("key1504", var_8acf1599.toString()); // put in a collection
        String var_fcfc3b3c = (String) var_5782875a.get("key1504"); // get it back out
        String var_2cafff88 = var_fcfc3b3c.substring(0, var_fcfc3b3c.length() - 1); // extract most of it
        String var_9d8b844d =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_2cafff88.getBytes()))); // B64 encode and decode it
        String var_4d38bacd = var_9d8b844d.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_b0803d2a = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_b0803d2a); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19);
    }
}
