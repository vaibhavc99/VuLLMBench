
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00277")
public class Class00277 extends HttpServlet {

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
        String var_5fe2b85d = var_eca07335; // assign
        StringBuilder var_8e33e4ab = new StringBuilder(var_5fe2b85d); // stick in stringbuilder
        var_8e33e4ab.append(" SafeStuff"); // append some safe content
        var_8e33e4ab.replace(
                var_8e33e4ab.length() - "Chars".length(),
                var_8e33e4ab.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_c2037b6c = new java.util.HashMap<String, Object>();
        var_c2037b6c.put("key54571", var_8e33e4ab.toString()); // put in a collection
        String var_2d79714a = (String) var_c2037b6c.get("key54571"); // get it back out
        String var_fa0ff143 = var_2d79714a.substring(0, var_2d79714a.length() - 1); // extract most of it
        String var_5b7c1480 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_fa0ff143.getBytes()))); // B64 encode and decode it
        String var_63105edf = var_5b7c1480.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_184ffc5e = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_184ffc5e); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19);
    }
}
