
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-01/Class00674")
public class Class00674 extends HttpServlet {

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
        String var_eca07335 = var_456727ac.getTheParameter("Class00674");
        if (var_eca07335 == null) var_eca07335 = "";

        // Chain a bunch of propagators in sequence
        String var_5529b893 = var_eca07335; // assign
        StringBuilder var_731981ac = new StringBuilder(var_5529b893); // stick in stringbuilder
        var_731981ac.append(" SafeStuff"); // append some safe content
        var_731981ac.replace(
                var_731981ac.length() - "Chars".length(),
                var_731981ac.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_22ff070e = new java.util.HashMap<String, Object>();
        var_22ff070e.put("key55926", var_731981ac.toString()); // put in a collection
        String var_542a481e = (String) var_22ff070e.get("key55926"); // get it back out
        String var_5f19a993 = var_542a481e.substring(0, var_542a481e.length() - 1); // extract most of it
        String var_665fb06e =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_5f19a993.getBytes()))); // B64 encode and decode it
        String var_b0a7f207 = var_665fb06e.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_82006a50 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_82006a50); // reflection

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME=? and PASSWORD='" + var_37b51d19 + "'";

        try {
            java.var_ac5c74b6.Connection var_4717d53e =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.getSqlConnection();
            java.var_ac5c74b6.PreparedStatement var_4245bf56 =
                    var_4717d53e.prepareStatement(
                            var_ac5c74b6,
                            java.var_ac5c74b6.ResultSet.TYPE_FORWARD_ONLY,
                            java.var_ac5c74b6.ResultSet.CONCUR_READ_ONLY);
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
