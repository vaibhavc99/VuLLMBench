
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-06/Class02732")
public class Class02732 extends HttpServlet {

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
        String param_eca07335 = var_456727ac.getTheValue("Class02732");

        String var_37b51d19 = doSomething(param_10573b87, param_eca07335);

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

    private static String doSomething(HttpServletRequest param_10573b87, String param_eca07335)
            throws ServletException, IOException {

        String var_37b51d19 = param_eca07335;

        return var_37b51d19;
    }
}
