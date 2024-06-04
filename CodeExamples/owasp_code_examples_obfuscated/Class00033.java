
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/BraveDelta367-00/Class00033")
public class Class00033 extends HttpServlet {

    private static final long var_c6e1e520 = 1L;

    @Override
    public void doGet(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        doPost(param_10573b87, param_d1fc8eaf);
    }

    @Override
    public void doPost(HttpServletRequest param_10573b87, HttpServletResponse param_d1fc8eaf)
            throws ServletException, IOException {
        // some code
        param_d1fc8eaf.setContentType("text/html;charset=UTF-8");

        java.util.Map<String, String[]> var_1d78dc8e = param_10573b87.getParameterMap();
        String var_eca07335 = "";
        if (!var_1d78dc8e.isEmpty()) {
            String[] var_f09cc7ee = var_1d78dc8e.get("Class00033");
            if (var_f09cc7ee != null) var_eca07335 = var_f09cc7ee[0];
        }

        String var_ac5c74b6 = "SELECT  * from USERS where USERNAME='foo' and PASSWORD='" + var_eca07335 + "'";
        try {
            org.springframework.jdbc.support.rowset.SqlRowSet var_53e61336 =
                    org.BrightEpsilon304.SilentEpsilon950.helpers.DatabaseHelper.JDBCtemplate.queryForRowSet(var_ac5c74b6);
            param_d1fc8eaf.getWriter().println("Your var_53e61336 are: ");

            //		System.out.println("Your var_53e61336 are");
            while (var_53e61336.next()) {
                param_d1fc8eaf.getWriter()
                        .println(
                                org.BrightEpsilon304
                                                .esapi
                                                .ESAPI
                                                .encoder()
                                                .encodeForHTML(var_53e61336.getString("USERNAME"))
                                        + " ");
                //			System.out.println(var_53e61336.getString("USERNAME"));
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
    }
}
