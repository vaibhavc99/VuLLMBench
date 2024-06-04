
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverAlpha224-04/Class02126")
public class Class02126 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02126");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        param_d1fc8eaf.setHeader("X-CleverAlpha224-Protection", "0");
        param_d1fc8eaf.getWriter().print(var_37b51d19);
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_fb152c66 = param_eca07335; // assign
        StringBuilder var_b8ec2194 = new StringBuilder(var_fb152c66); // stick in stringbuilder
        var_b8ec2194.append(" SafeStuff"); // append some safe content
        var_b8ec2194.replace(
                var_b8ec2194.length() - "Chars".length(),
                var_b8ec2194.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_73b07565 = new java.util.HashMap<String, Object>();
        var_73b07565.put("key64181", var_b8ec2194.toString()); // put in a collection
        String var_21171f2a = (String) var_73b07565.get("key64181"); // get it back out
        String var_730736ec = var_21171f2a.substring(0, var_21171f2a.length() - 1); // extract most of it
        String var_df1ba45a =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_730736ec.getBytes()))); // B64 encode and decode it
        String var_f73823ec = var_df1ba45a.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_37b51d19 = var_8afc1e9b.doSomething(var_f73823ec); // reflection

        return var_37b51d19;
    }
}
