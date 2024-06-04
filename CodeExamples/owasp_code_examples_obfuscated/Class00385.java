
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-00/Class00385")
public class Class00385 extends HttpServlet {

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

        String var_eca07335 = param_10573b87.getParameter("Class00385");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_ae43a446 = var_eca07335; // assign
        StringBuilder var_2c39b530 = new StringBuilder(var_ae43a446); // stick in stringbuilder
        var_2c39b530.append(" SafeStuff"); // append some safe content
        var_2c39b530.replace(
                var_2c39b530.length() - "Chars".length(),
                var_2c39b530.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_c4e07c66 = new java.util.HashMap<String, Object>();
        var_c4e07c66.put("key21475", var_2c39b530.toString()); // put in a collection
        String var_d11e3d36 = (String) var_c4e07c66.get("key21475"); // get it back out
        String var_0a573f83 = var_d11e3d36.substring(0, var_d11e3d36.length() - 1); // extract most of it
        String var_6269446c =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_0a573f83.getBytes()))); // B64 encode and decode it
        String var_5e6e31be = var_6269446c.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_5e6e31be); // reflection

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        Object[] var_be8f8018 = {var_37b51d19, "b"};
        param_d1fc8eaf.getWriter().printf("Formatted like: %1$s and %2$s.", var_be8f8018);
    }
}
