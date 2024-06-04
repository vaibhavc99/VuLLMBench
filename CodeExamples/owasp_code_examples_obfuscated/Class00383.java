
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00383")
public class Class00383 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00383");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_ac7bcfae = var_eca07335; // assign
        StringBuilder var_5908f4ac = new StringBuilder(var_ac7bcfae); // stick in stringbuilder
        var_5908f4ac.append(" SafeStuff"); // append some safe content
        var_5908f4ac.replace(
                var_5908f4ac.length() - "Chars".length(),
                var_5908f4ac.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_191f83ee = new java.util.HashMap<String, Object>();
        var_191f83ee.put("key2196", var_5908f4ac.toString()); // put in a collection
        String var_4e06bbee = (String) var_191f83ee.get("key2196"); // get it back out
        String var_e03bc57d = var_4e06bbee.substring(0, var_4e06bbee.length() - 1); // extract most of it
        String var_d224af53 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_e03bc57d.getBytes()))); // B64 encode and decode it
        String var_1edcd6eb = var_d224af53.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_1edcd6eb); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {"a", "b"};
        param_d1fc8eaf.getWriter().printf(java.util.Locale.US, var_37b51d19, var_be8f8018);
    }
}
