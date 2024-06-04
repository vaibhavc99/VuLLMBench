
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-02/Class01217")
public class Class01217 extends HttpServlet {

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

        String param_eca07335 = "";
        java.util.Enumeration<String> var_4340fd73 = param_10573b87.getHeaders("Class01217");

        if (var_4340fd73 != null && var_4340fd73.hasMoreElements()) {
            param_eca07335 = var_4340fd73.nextElement(); // just grab first element
        }

        // URL Decode the header value since req.getHeaders() doesn't. Unlike req.getParameters().
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            var_4245bf56.addBatch(var_ac5c74b6);
            int[] var_0700cd3e = var_4245bf56.executeBatch();
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_ac5c74b6, var_0700cd3e, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_0a6d0af4 = param_eca07335; // assign
            StringBuilder var_3f58ec95 = new StringBuilder(var_0a6d0af4); // stick in stringbuilder
            var_3f58ec95.append(" SafeStuff"); // append some safe content
            var_3f58ec95.replace(
                    var_3f58ec95.length() - "Chars".length(),
                    var_3f58ec95.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_c23700c0 = new java.util.HashMap<String, Object>();
            var_c23700c0.put("key89502", var_3f58ec95.toString()); // put in a collection
            String var_671a7d2a = (String) var_c23700c0.get("key89502"); // get it back out
            String var_a0fc63db = var_671a7d2a.substring(0, var_671a7d2a.length() - 1); // extract most of it
            String var_207ba8cf =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_a0fc63db.getBytes()))); // B64 encode and decode it
            String var_be21c592 = var_207ba8cf.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_21a6cdcb = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_21a6cdcb); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
