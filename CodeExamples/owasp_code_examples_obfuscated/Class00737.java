
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-01/Class00737")
public class Class00737 extends HttpServlet {

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

        String[] var_f09cc7ee = param_10573b87.getParameterValues("Class00737");
        String var_eca07335;
        if (var_f09cc7ee != null && var_f09cc7ee.length > 0) var_eca07335 = var_f09cc7ee[0];
        else var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_ee294921 = var_eca07335; // assign
        StringBuilder var_b569d135 = new StringBuilder(var_ee294921); // stick in stringbuilder
        var_b569d135.append(" SafeStuff"); // append some safe content
        var_b569d135.replace(
                var_b569d135.length() - "Chars".length(),
                var_b569d135.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_b72010e1 = new java.util.HashMap<String, Object>();
        var_b72010e1.put("key57334", var_b569d135.toString()); // put in a collection
        String var_2eb54889 = (String) var_b72010e1.get("key57334"); // get it back out
        String var_3d411f7b = var_2eb54889.substring(0, var_2eb54889.length() - 1); // extract most of it
        String var_a9c43135 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_3d411f7b.getBytes()))); // B64 encode and decode it
        String var_1a9f09e7 = var_a9c43135.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_1a9f09e7); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().write("Parameter value: " + var_37b51d19);
    }
}
