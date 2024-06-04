
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00275")
public class Class00275 extends HttpServlet {

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
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Referer");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            var_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_0e85670b = var_eca07335; // assign
        StringBuilder var_ab7c6e24 = new StringBuilder(var_0e85670b); // stick in stringbuilder
        var_ab7c6e24.append(" SafeStuff"); // append some safe content
        var_ab7c6e24.replace(
                var_ab7c6e24.length() - "Chars".length(),
                var_ab7c6e24.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_9ccdff1c = new java.util.HashMap<String, Object>();
        var_9ccdff1c.put("key28150", var_ab7c6e24.toString()); // put in a collection
        String var_e891cde4 = (String) var_9ccdff1c.get("key28150"); // get it back out
        String var_2d88de69 = var_e891cde4.substring(0, var_e891cde4.length() - 1); // extract most of it
        String var_0df56c42 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_2d88de69.getBytes()))); // B64 encode and decode it
        String var_f1859386 = var_0df56c42.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_5d91ff89 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_5d91ff89); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19.toCharArray());
    }
}
