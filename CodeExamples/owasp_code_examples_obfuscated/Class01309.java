
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-02/Class01309")
public class Class01309 extends HttpServlet {

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

        String param_eca07335 = param_10573b87.getParameter("Class01309");
        if (param_eca07335 == null) param_eca07335 = "";

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 =
                "SELECT TOP 1 USERNAME from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";
        try {
            Object var_53e61336 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.JDBCtemplate.queryForObject(
                            var_ac5c74b6, new Object[] {}, String.class);
            param_d1fc8eaf.getWriter().println("Your var_53e61336 are: ");

            //		System.out.println("Your var_53e61336 are");
            param_d1fc8eaf.getWriter()
                    .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_53e61336.toString()));
            //		System.out.println(var_53e61336.toString());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "No var_53e61336 returned for query: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_ac5c74b6));
        } catch (org.springframework.dao.DataAccessException e) {
            if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.hideSQLErrors) {
                param_d1fc8eaf.getWriter().println("Error processing param_10573b87.");
            } else throw new ServletException(e);
        }
    } // end doPost

    private class Class_0cbc6611 {

        public String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
                throws ServletException, IOException {

            // Chain a bunch of propagators in sequence
            String var_4b7c5f56 = param_eca07335; // assign
            StringBuilder var_3b085298 = new StringBuilder(var_4b7c5f56); // stick in stringbuilder
            var_3b085298.append(" SafeStuff"); // append some safe content
            var_3b085298.replace(
                    var_3b085298.length() - "Chars".length(),
                    var_3b085298.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_bfd74625 = new java.util.HashMap<String, Object>();
            var_bfd74625.put("key9334", var_3b085298.toString()); // put in a collection
            String var_ccf3a576 = (String) var_bfd74625.get("key9334"); // get it back out
            String var_2efb6b3f = var_ccf3a576.substring(0, var_ccf3a576.length() - 1); // extract most of it
            String var_494600cb =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_2efb6b3f.getBytes()))); // B64 encode and decode it
            String var_2b639b6f = var_494600cb.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_00862cd7 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_00862cd7); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
