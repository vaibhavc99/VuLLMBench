
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02132")
public class Class02132 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02132");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().println(var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_859e1db5 = param_eca07335; // assign
        StringBuilder var_d9ca0ddc = new StringBuilder(var_859e1db5); // stick in stringbuilder
        var_d9ca0ddc.append(" SafeStuff"); // append some safe content
        var_d9ca0ddc.replace(
                var_d9ca0ddc.length() - "Chars".length(),
                var_d9ca0ddc.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_3ca14ee1 = new java.util.HashMap<String, Object>();
        var_3ca14ee1.put("key76744", var_d9ca0ddc.toString()); // put in a collection
        String var_41c74580 = (String) var_3ca14ee1.get("key76744"); // get it back out
        String var_78330644 = var_41c74580.substring(0, var_41c74580.length() - 1); // extract most of it
        String var_33c2d7fd =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_78330644.getBytes()))); // B64 encode and decode it
        String var_68086281 = var_33c2d7fd.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_68086281); // reflection

        return var_37b51d19;
    }
}
