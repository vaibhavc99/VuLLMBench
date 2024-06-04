
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/CleverBeta661-00/Class00621")
public class Class00621 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00621");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_31d75efe = var_eca07335; // assign
        StringBuilder var_d36fc7e9 = new StringBuilder(var_31d75efe); // stick in stringbuilder
        var_d36fc7e9.append(" SafeStuff"); // append some safe content
        var_d36fc7e9.replace(
                var_d36fc7e9.length() - "Chars".length(),
                var_d36fc7e9.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_10ea7f07 = new java.util.HashMap<String, Object>();
        var_10ea7f07.put("key28566", var_d36fc7e9.toString()); // put in a collection
        String var_a13ee92c = (String) var_10ea7f07.get("key28566"); // get it back out
        String var_04ebea7a = var_a13ee92c.substring(0, var_a13ee92c.length() - 1); // extract most of it
        String var_72285b47 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_04ebea7a.getBytes()))); // B64 encode and decode it
        String var_45ca96fa = var_72285b47.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_190350c0 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_190350c0); // reflection

        java.io.File var_b30d6992 =
                new java.io.File(
                        new java.io.File(org.BrightEpsilon304.SilentEpsilon950.helpers.Utils.TESTFILES_DIR), var_37b51d19);
        param_d1fc8eaf.getWriter()
                .println(
                        "Access to file: '"
                                + org.BrightEpsilon304
                                        .esapi
                                        .ESAPI
                                        .encoder()
                                        .encodeForHTML(var_b30d6992.toString())
                                + "' created.");
        if (var_b30d6992.exists()) {
            param_d1fc8eaf.getWriter().println(" And file already exists.");
        } else {
            param_d1fc8eaf.getWriter().println(" But file doesn't exist yet.");
        }
    }
}
