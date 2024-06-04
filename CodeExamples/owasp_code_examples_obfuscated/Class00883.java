
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00883")
public class Class00883 extends HttpServlet {

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

        org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest var_456727ac =
                new org.BrightEpsilon304.SilentEpsilon950.helpers.SeparateClassRequest(param_10573b87);
        String var_eca07335 = var_456727ac.getTheValue("Class00883");

        // Chain a bunch of propagators in sequence
        String var_6e93dccb = var_eca07335; // assign
        StringBuilder var_f3cc6929 = new StringBuilder(var_6e93dccb); // stick in stringbuilder
        var_f3cc6929.append(" SafeStuff"); // append some safe content
        var_f3cc6929.replace(
                var_f3cc6929.length() - "Chars".length(),
                var_f3cc6929.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_17a8bdfd = new java.util.HashMap<String, Object>();
        var_17a8bdfd.put("key69063", var_f3cc6929.toString()); // put in a collection
        String var_1eb98463 = (String) var_17a8bdfd.get("key69063"); // get it back out
        String var_9caedf9b = var_1eb98463.substring(0, var_1eb98463.length() - 1); // extract most of it
        String var_355a4ddd =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_9caedf9b.getBytes()))); // B64 encode and decode it
        String var_0e835336 = var_355a4ddd.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_0e835336); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", var_37b51d19};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, "Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
