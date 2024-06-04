
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00650")
public class Class00650 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00650");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_fd46708a = var_eca07335; // assign
        StringBuilder var_819a8f68 = new StringBuilder(var_fd46708a); // stick in stringbuilder
        var_819a8f68.append(" SafeStuff"); // append some safe content
        var_819a8f68.replace(
                var_819a8f68.length() - "Chars".length(),
                var_819a8f68.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_7998b7e5 = new java.util.HashMap<String, Object>();
        var_7998b7e5.put("key17321", var_819a8f68.toString()); // put in a collection
        String var_1acf63e2 = (String) var_7998b7e5.get("key17321"); // get it back out
        String var_5066f8c7 = var_1acf63e2.substring(0, var_1acf63e2.length() - 1); // extract most of it
        String var_7af5f184 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_5066f8c7.getBytes()))); // B64 encode and decode it
        String var_d36513e7 = var_7af5f184.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_ef3f9b8f = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_ef3f9b8f); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write(var_37b51d19.toCharArray());
    }
}
