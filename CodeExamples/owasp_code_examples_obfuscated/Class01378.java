
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-02/Class01378")
public class Class01378 extends HttpServlet {

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

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String param_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class01378");
            if (var_f09cc7ee != null) param_eca07335 = var_f09cc7ee[0];
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "{call " + var_37b51d19 + "}";

        try {
            java.var_ac5c74b6.Connection var_4717d53e =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlConnection();
            java.var_ac5c74b6.CallableStatement var_4245bf56 =
                    var_4717d53e.prepareCall(
                            var_ac5c74b6,
                            java.var_ac5c74b6.ResultSet.TYPE_FORWARD_ONLY,
                            java.var_ac5c74b6.ResultSet.CONCUR_READ_ONLY);
            java.var_ac5c74b6.ResultSet var_3a2d7564 = var_4245bf56.executeQuery();
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_3a2d7564, var_ac5c74b6, param_d1fc8eaf);
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
            String var_49dac4f4 = param_eca07335; // assign
            StringBuilder var_26e09c17 = new StringBuilder(var_49dac4f4); // stick in stringbuilder
            var_26e09c17.append(" SafeStuff"); // append some safe content
            var_26e09c17.replace(
                    var_26e09c17.length() - "Chars".length(),
                    var_26e09c17.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_607b1c6c = new java.util.HashMap<String, Object>();
            var_607b1c6c.put("key50098", var_26e09c17.toString()); // put in a collection
            String var_776c7d02 = (String) var_607b1c6c.get("key50098"); // get it back out
            String var_aec1c57d = var_776c7d02.substring(0, var_776c7d02.length() - 1); // extract most of it
            String var_d20af3fd =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_aec1c57d.getBytes()))); // B64 encode and decode it
            String var_7e60f258 = var_d20af3fd.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_7fa87e86 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_7fa87e86); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
