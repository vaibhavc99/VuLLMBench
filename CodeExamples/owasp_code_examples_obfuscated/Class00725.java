
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00725")
public class Class00725 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00725");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_3e77644f = var_eca07335; // assign
        StringBuilder var_5f07b284 = new StringBuilder(var_3e77644f); // stick in stringbuilder
        var_5f07b284.append(" SafeStuff"); // append some safe content
        var_5f07b284.replace(
                var_5f07b284.length() - "Chars".length(),
                var_5f07b284.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_bb43d6cd = new java.util.HashMap<String, Object>();
        var_bb43d6cd.put("key49441", var_5f07b284.toString()); // put in a collection
        String var_7629fa9b = (String) var_bb43d6cd.get("key49441"); // get it back out
        String var_f014dedd = var_7629fa9b.substring(0, var_7629fa9b.length() - 1); // extract most of it
        String var_42cb5256 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_f014dedd.getBytes()))); // B64 encode and decode it
        String var_e44e5576 = var_42cb5256.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_e44e5576); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19);
    }
}
