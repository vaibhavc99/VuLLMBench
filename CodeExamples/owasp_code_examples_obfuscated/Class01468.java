
package org.BrightEpsilon304.SilentEpsilon950.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(var_2063c160 = "/BraveDelta367-03/Class01468")
public class Class01468 extends HttpServlet {

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
                    if (var_2063c160.equals("Class01468")) {
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

            String var_37b51d19;

            // Simple if statement that assigns constant to var_37b51d19 on true condition
            int var_0fc3cfbc = 86;
            if ((7 * 42) - var_0fc3cfbc > 200) var_37b51d19 = "This_should_always_happen";
            else var_37b51d19 = param_eca07335;

            return var_37b51d19;
        }
    } // end innerclass Class_0cbc6611
} // end DataflowThruInnerClass
