
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00379")
public class Class00379 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00379");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_e71e1413 = var_eca07335; // assign
        StringBuilder var_6fe3144d = new StringBuilder(var_e71e1413); // stick in stringbuilder
        var_6fe3144d.append(" SafeStuff"); // append some safe content
        var_6fe3144d.replace(
                var_6fe3144d.length() - "Chars".length(),
                var_6fe3144d.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_ce72b386 = new java.util.HashMap<String, Object>();
        var_ce72b386.put("key75704", var_6fe3144d.toString()); // put in a collection
        String var_199560d2 = (String) var_ce72b386.get("key75704"); // get it back out
        String var_ca9f17c7 = var_199560d2.substring(0, var_199560d2.length() - 1); // extract most of it
        String var_9a909c2c =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_ca9f17c7.getBytes()))); // B64 encode and decode it
        String var_1c1d6456 = var_9a909c2c.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_2ad2da7c = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_2ad2da7c); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19.toCharArray());
    }
}
