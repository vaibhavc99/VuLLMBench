
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-03/Class02048")
public class Class02048 extends HttpServlet {

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

        String param_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Referer");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            param_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_17942714 = param_eca07335; // assign
        StringBuilder var_f66e685b = new StringBuilder(var_17942714); // stick in stringbuilder
        var_f66e685b.append(" SafeStuff"); // append some safe content
        var_f66e685b.replace(
                var_f66e685b.length() - "Chars".length(),
                var_f66e685b.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_7cb5f7cb = new java.util.HashMap<String, Object>();
        var_7cb5f7cb.put("key96053", var_f66e685b.toString()); // put in a collection
        String var_aa4edaf1 = (String) var_7cb5f7cb.get("key96053"); // get it back out
        String var_af474c3d = var_aa4edaf1.substring(0, var_aa4edaf1.length() - 1); // extract most of it
        String var_b55a7e26 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_af474c3d.getBytes()))); // B64 encode and decode it
        String var_5e17c211 = var_b55a7e26.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_8baeb5e3 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_8baeb5e3); // reflection

        return var_37b51d19;
    }
}
