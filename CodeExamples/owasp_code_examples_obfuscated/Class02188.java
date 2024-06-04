
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-04/Class02188")
public class Class02188 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class02188");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Statement var_4245bf56 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlStatement();
            java.var_ac5c74b6.ResultSet var_3a2d7564 = var_4245bf56.executeQuery(var_ac5c74b6);
            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.printResults(var_3a2d7564, var_ac5c74b6, param_d1fc8eaf);
        } catch (java.var_ac5c74b6.SQLException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
                return;
            } else throw new ServletException(e);
        }
    } // end doPost

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        // Chain a bunch of propagators in sequence
        String var_6ee9ded0 = param_eca07335; // assign
        StringBuilder var_7b0e3408 = new StringBuilder(var_6ee9ded0); // stick in stringbuilder
        var_7b0e3408.append(" SafeStuff"); // append some safe content
        var_7b0e3408.replace(
                var_7b0e3408.length() - "Chars".length(),
                var_7b0e3408.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_3395106e = new java.util.HashMap<String, Object>();
        var_3395106e.put("key16667", var_7b0e3408.toString()); // put in a collection
        String var_dae804d7 = (String) var_3395106e.get("key16667"); // get it back out
        String var_8bb74397 = var_dae804d7.substring(0, var_dae804d7.length() - 1); // extract most of it
        String var_1380d0c7 =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_8bb74397.getBytes()))); // B64 encode and decode it
        String var_e441c948 = var_1380d0c7.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_a09daedd = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_a09daedd); // reflection

        return var_37b51d19;
    }
}
