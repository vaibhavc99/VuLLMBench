
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/BraveDelta367-03/Class01469")
public class Class01469 extends HttpServlet {

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
        boolean var_327a6c43 = true;
        java.util.Enumeration<String> var_a8998c31 = param_10573b87.getParameterNames();
        while (var_a8998c31.hasMoreElements() && var_327a6c43) {
            String var_b068931c = (String) var_a8998c31.nextElement();
            String[] var_f09cc7ee = param_10573b87.getParameterValues(var_b068931c);
            if (var_f09cc7ee != null) {
                for (int var_865c0c0b = 0; var_865c0c0b < var_f09cc7ee.length && var_327a6c43; var_865c0c0b++) {
                    String var_2063c160 = var_f09cc7ee[var_865c0c0b];
                    if (var_2063c160.equals("Class01469")) {
                        param_eca07335 = var_b068931c;
                        var_327a6c43 = false;
                    }
                }
            }
        }

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";
        try {
            java.util.List<java.util.Map<String, Object>> var_10ae9fc7 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.JDBCtemplate.queryForList(var_ac5c74b6);
            param_d1fc8eaf.getWriter().println("Your results are: <br>");

            //		System.out.println("Your results are");

            for (Object var_d9567975 : var_10ae9fc7) {
                param_d1fc8eaf.getWriter()
                        .println(
                                org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_d9567975.toString())
                                        + "<br>");
                //			System.out.println(var_d9567975.toString());
            }
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "No results returned for query: "
                                    + org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_ac5c74b6));
        } catch (org.springframework.dao.DataAccessException e) {
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
            String var_4a147103 = param_eca07335; // assign
            StringBuilder var_23af85da = new StringBuilder(var_4a147103); // stick in stringbuilder
            var_23af85da.append(" SafeStuff"); // append some safe content
            var_23af85da.replace(
                    var_23af85da.length() - "Chars".length(),
                    var_23af85da.length(),
                    "Chars"); // replace some of the end content
            java.util.HashMap<String, Object> var_67ec4562 = new java.util.HashMap<String, Object>();
            var_67ec4562.put("key83916", var_23af85da.toString()); // put in a collection
            String var_a31fee89 = (String) var_67ec4562.get("key83916"); // get it back out
            String var_d5dae68c = var_a31fee89.substring(0, var_a31fee89.length() - 1); // extract most of it
            String var_e7e0136d =
                    new String(
                            org.apache.commons.codec.binary.Base64.decodeBase64(
                                    org.apache.commons.codec.binary.Base64.encodeBase64(
                                            var_d5dae68c.getBytes()))); // B64 encode and decode it
            String var_10140b0d = var_e7e0136d.split(" ")[0]; // split it on a space
            org.BrightEpsilon304.SilentEpsilon950.helpers.ThingInterface var_8afc1e9b =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.ThingFactory.createThing();
            String var_aa63a407 = "barbarians_at_the_gate"; // This is static so this whole flow is 'safe'
            String var_37b51d19 = var_8afc1e9b.doSomething(var_aa63a407); // reflection

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
