
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00201")
public class Class00201 extends HttpServlet {

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
        if (param_10573b87.getHeader("Class00201") != null) {
            var_eca07335 = param_10573b87.getHeader("Class00201");
        }

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        var_eca07335 = java.net.URLDecoder.decode(var_eca07335, "UTF-8");

        // Chain a bunch of propagators in sequence
        String var_4b57d203 = var_eca07335; // assign
        StringBuilder var_5b05c563 = new StringBuilder(var_4b57d203); // stick in stringbuilder
        var_5b05c563.append(" SafeStuff"); // append some safe content
        var_5b05c563.replace(
                var_5b05c563.length() - "Chars".length(),
                var_5b05c563.length(),
                "Chars"); // replace some of the end content
        java.util.HashMap<String, Object> var_3417c206 = new java.util.HashMap<String, Object>();
        var_3417c206.put("key60073", var_5b05c563.toString()); // put in a collection
        String var_5804ef42 = (String) var_3417c206.get("key60073"); // get it back out
        String var_ee3f98a4 = var_5804ef42.substring(0, var_5804ef42.length() - 1); // extract most of it
        String var_6938e68f =
                new String(
                        org.apache.commons.codec.binary.Base64.decodeBase64(
                                org.apache.commons.codec.binary.Base64.encodeBase64(
                                        var_ee3f98a4.getBytes()))); // B64 encode and decode it
        String var_a6aae776 = var_6938e68f.split(" ")[0]; // split it on a space
        org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
        String var_e64897ed = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
        String var_37b51d19 = var_8afc1e9b.doSomething(var_e64897ed); // reflection

        try {
            String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";

            org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(var_ac5c74b6);
            param_d1fc8eaf.getWriter()
                    .println(
                            "No results can be displayed for query: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_ac5c74b6)
                                    + "<br>"
                                    + " because the Spring batchUpdate method doesn't return results.");
        } catch (org.springframework.dao.DataAccessException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
            } else throw new ServletException(e);
        }
    }
}
