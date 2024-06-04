
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00206")
public class Class00206 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class00206") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00206");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_155ca9d1 = var_eca07335; // assign
        StringBuilder var_68b4b355 = new StringBuilder(var_155ca9d1); // stick in stringbuilder
        var_68b4b355.append(" SafeStuff"); // append some safe content
        var_68b4b355.replace(
                var_68b4b355.length() - "Chars".length(),
                var_68b4b355.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_0498f9af = new java.util.HashMap<String, Object>();
        var_0498f9af.put("key13396", var_68b4b355.toString()); // put in a collection
        String var_e4ccebd6 = (String) var_0498f9af.get("key13396"); // get it back out
        String var_7405f020 = var_e4ccebd6.substring(0, var_e4ccebd6.length() - 1); // extract most of it
        String var_f7a7e858 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_7405f020.getBytes()))); // B64 encode and decode it
        String var_e35fdf35 = var_f7a7e858.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_7e2769e3 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_7e2769e3); // reflection

        String var_ac5c74b6 = "INSERT INTO users (username, password) VALUES ('foo','" + var_37b51d19 + "')";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            int var_e2942a04 = var_4245bf56.executeUpdate(var_ac5c74b6, new String[] {"USERNAME", "PASSWORD"});
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.outputUpdateComplete(var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    }
}
