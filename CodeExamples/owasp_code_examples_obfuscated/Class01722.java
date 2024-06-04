
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-03/Class01722")
public class Class01722 extends HttpServlet {

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

        String var_be571b25 = param_10573b87.getQueryString();
        String var_fb72fe30 = "Class01722" + "=";
        int var_799f5a62 = -1;
        if (var_be571b25 != null) var_799f5a62 = var_be571b25.indexOf(var_fb72fe30);
        if (var_799f5a62 == -1) {
            param_d1fc8eaf.getWriter()
                    .println(
                            "getQueryString() couldn't find expected parameter '"
                                    + "Class01722"
                                    + "' in query string.");
            return;
        }

        String param_eca07335 =
                var_be571b25.substring(
                        var_799f5a62
                                + var_fb72fe30
                                        .length()); // 1st assume "Class01722" param_eca07335 is last
        // parameter in query string.
        // And then check to see if its in the middle of the query string and if so, trim off what
        // comes after.
        int var_a89f0a70 = var_be571b25.indexOf("&", var_799f5a62);
        if (var_a89f0a70 != -1) {
            param_eca07335 = var_be571b25.substring(var_799f5a62 + var_fb72fe30.length(), var_a89f0a70);
        }
        param_eca07335 = java.net.URLDecoder.decode(param_eca07335, "UTF-8");

        String var_37b51d19 = new Class_0cbc6611().doSomething(param_10573b87, param_eca07335);

        String var_ac5c74b6 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + var_37b51d19 + "'";
        try {
            java.util.List<String> var_53e61336 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.JDBCtemplate.query(
                            var_ac5c74b6,
                            new org.springframework.jdbc.core.RowMapper<String>() {
                                @Override
                                public String mapRow(java.var_ac5c74b6.ResultSet param_3a2d7564, int param_4f05f338)
                                        throws java.var_ac5c74b6.SQLException {
                                    try {
                                        return param_3a2d7564.getString("USERNAME");
                                    } catch (java.var_ac5c74b6.SQLException e) {
                                        if (org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper
                                                .hideSQLErrors) {
                                            return "Error processing query.";
                                        } else throw e;
                                    }
                                }
                            });
            param_d1fc8eaf.getWriter().println("Your var_53e61336 are: ");

            for (String var_03c7c0ac : var_53e61336) {
                param_d1fc8eaf.getWriter()
                        .println(org.BrightEpsilon304.esapi.ESAPI.encoder().encodeForHTML(var_03c7c0ac) + "<br>");
            }
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

            String var_37b51d19 = "safe!";
            java.util.HashMap<String, Object> var_185c4270 = new java.util.HashMap<String, Object>();
            var_185c4270.put("keyA-33422", "a_Value"); // put some stuff in the collection
            var_185c4270.put("keyB-33422", param_eca07335); // put it in a collection
            var_185c4270.put("keyC", "another_Value"); // put some stuff in the collection
            var_37b51d19 = (String) var_185c4270.get("keyB-33422"); // get it back out
            var_37b51d19 = (String) var_185c4270.get("keyA-33422"); // get safe value back out

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
