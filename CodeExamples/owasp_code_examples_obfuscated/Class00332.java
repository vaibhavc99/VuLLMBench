
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00332")
public class Class00332 extends HttpServlet {

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

        String var_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Class00332");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            var_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_60da6bb6 = var_eca07335; // assign
        StringBuilder var_9ba9150d = new StringBuilder(var_60da6bb6); // stick in stringbuilder
        var_9ba9150d.append(" SafeStuff"); // append some safe content
        var_9ba9150d.replace(
                var_9ba9150d.length() - "Chars".length(),
                var_9ba9150d.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_30637e04 = new java.util.HashMap<String, Object>();
        var_30637e04.put("key40477", var_9ba9150d.toString()); // put in a collection
        String var_07e6c0f9 = (String) var_30637e04.get("key40477"); // get it back out
        String var_d91153f2 = var_07e6c0f9.substring(0, var_07e6c0f9.length() - 1); // extract most of it
        String var_e163c9ef =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_d91153f2.getBytes()))); // B64 encode and decode it
        String var_f91e0875 = var_e163c9ef.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_d1f11ae2 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_d1f11ae2); // reflection

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME=? and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Connection var_4717d53e =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlConnection();
            java.var_ac5c74b6.PreparedStatement var_4245bf56 = var_4717d53e.prepareStatement(var_ac5c74b6);
            var_4245bf56.setString(1, "foo");
            var_4245bf56.execute();
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_4245bf56, var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    }
}
